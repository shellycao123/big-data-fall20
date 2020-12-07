# preprocess the file
import sys
from fastNLP.io.loader import CSVLoader
bundle = CSVLoader(headers=['raw_words1', 'raw_words2','target'],sep='\t').load(sys.argv[1])

#####test
import jieba
from fastNLP.core import Vocabulary
bundle.apply(lambda line: jieba.lcut(line['raw_words1']) + ['[SEP]'] + jieba.lcut(line['raw_words2']), new_field_name='words')
bundle.apply(lambda line: len(line['words']), new_field_name='seq_len')
bundle.apply(lambda line: 1, new_field_name='target')
vocab = Vocabulary()
vocab.from_dataset(bundle.get_dataset("train"), field_name='words',no_create_entry_dataset=[bundle.get_dataset("test"),bundle.get_dataset("dev")])
vocab.index_dataset(bundle.get_dataset("train"), field_name = 'words')
vocab.index_dataset(bundle.get_dataset("test"), field_name = 'words')
vocab.index_dataset(bundle.get_dataset("dev"), field_name = 'words')


# establish the model
from fastNLP import Const
import torch
from fastNLP.models import BertForSentenceMatching
from fastNLP.embeddings.bert_embedding import BertEmbedding
embed = BertEmbedding(vocab,model_dir_or_name='cn-base', requires_grad=False)

#pad the input array
bundle.set_pad_val("words",0)
bundle.set_input("words")
bundle.set_target("target")

model = BertForSentenceMatching(embed)
from fastNLP import AccuracyMetric
metrics=AccuracyMetric(pred=Const.OUTPUT, target=Const.TARGET)


# train the model
from fastNLP import Trainer
from fastNLP import CrossEntropyLoss
N_EPOCHS = 1
BATCH_SIZE = 16

trainer = Trainer(loss=CrossEntropyLoss(),model=model, train_data=bundle.get_dataset("train"), dev_data=bundle.get_dataset("dev"), metrics=metrics,
n_epochs=N_EPOCHS, batch_size=BATCH_SIZE)
trainer.train()

from fastNLP import Tester

tester = Tester(bundle.get_dataset("test"), model, metrics)
tester.test()

# print result