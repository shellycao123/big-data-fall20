import sys

def main():
    file_name = sys.argv[1]
    isTraining = sys.argv[2]
    f = open(file_name, 'r')
    prev_process = {}
    if isTraining == 'training':
        w = open('training.feature', 'w',newline='\n')
        for line in f:
            if len(line.strip()) == 0:
                w.write("\n")
                prev_process = {}
            else:
                prev_process = process(line, prev_process, True)
                w.write(format(prev_process))
        w.close()
    else:
        w = open('test.feature', 'w', newline='\n')
        for line in f:
            if len(line.strip()) == 0:
                w.write("\n")
                prev_process = {}
            else:
                prev_process = process(line, prev_process, False)
                w.write(format(prev_process))
        w.close()
    f.close()


def process(line, prev_line, isTraining):
    words = line.split("\t")
    result = {}
    result['word'] = words[0]
    result['POS'] = words[1].strip()
    if isTraining:
        result['BIO'] = words[2].strip()
    elif words[0][len(words[0]) - 2:] == 'ed':
        words['stem'] = words[0][:len(words[0]) - 2]
    elif words[0][len(words[0]) - 3:] == 'ing':
        words['stem'] = words[0][:len(words[0]) - 3]
    elif words[0][len(words[0]) - 2:] == 'es':
        words['stem'] = words[0][:len(words[0]) - 2]
    elif words[0][len(words[0]) - 1] == 's':
        words['stem'] = words[0][:len(words[0]) - 1]
    else:
        words['stem'] = words[0]

    if len(prev_line) != 0:#not first word in sentence
        result['prev_POS'] = prev_line['POS']
        result['prev_word'] = prev_line['word']
        result['prev_stem'] = prev_line['stem']
        result['previous_BIO'] = prev_line['BIO']

    return result


def format(dic):
    result = ''
    result += dic['word']
    word = dic.pop('word')
    for key, value in dic.items():
        if key != 'BIO':
            result += f'\t{key}={value}'
    if 'BIO' in dic:
        result += f'\tBIO={dic["BIO"]}'
    result += '\n'
    dic['word'] = word
    return result


if __name__ == '__main__':
    main()