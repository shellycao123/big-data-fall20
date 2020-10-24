import sys

def main():
    file_name = sys.argv[1]
    isTraining = sys.argv[2]
    f = open(file_name, 'r')
    prev_process = {}
    two_before = {}
    if isTraining == 'training':
        w = open('training.feature', 'w',newline='\n')
        for line in f:
            if len(line.strip()) == 0:
                w.write("\n")
                two_before = prev_process
                prev_process = {}
            else:
                tem = prev_process
                prev_process = process(line, prev_process, two_before, True)
                two_before = tem
                w.write(format(prev_process))
        w.close()
    else:
        w = open('test.feature', 'w', newline='\n')
        for line in f:
            if len(line.strip()) == 0:
                w.write("\n")
                two_before = prev_process
                prev_process = {}
            else:
                tem = prev_process
                prev_process = process(line, prev_process, two_before, False)
                two_before = tem
                w.write(format(prev_process))
        w.close()
    f.close()


def process(line, prev_line, two_before, isTraining):
    words = line.split("\t")
    words[0] = words[0].lower()
    result = {}
    result['word'] = words[0]
    result['POS'] = words[1].strip()
    if isTraining:
        result['BIO'] = words[2].strip()

    if words[0][len(words[0]) - 2:] == 'ed':
        result['stem'] = words[0][:len(words[0]) - 2]
    elif words[0][len(words[0]) - 3:] == 'ing':
        result['stem'] = words[0][:len(words[0]) - 3]
    elif words[0][len(words[0]) - 2:] == 'es':
        result['stem'] = words[0][:len(words[0]) - 2]
    elif words[0][len(words[0]) - 1] == 's':
        result['stem'] = words[0][:len(words[0]) - 1]
    else:
        result['stem'] = words[0]

    if len(prev_line) != 0:#not first word in sentence
        result['prev_POS'] = prev_line['POS']
        result['prev_word'] = prev_line['word']
        result['prev_stem'] = prev_line['stem']
        if isTraining: 
            result['previous_BIO'] = prev_line['BIO']

    if len(two_before) != 0:#not first word in sentence
        result['two_before_POS'] = two_before['POS']
        result['two_before_word'] = two_before['word']
        result['two_before_stem'] = two_before['stem']
        if isTraining: 
            result['two_before_BIO'] = two_before['BIO']

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