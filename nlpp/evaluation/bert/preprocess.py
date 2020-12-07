import sys

f = open(sys.argv[1], 'r', encoding='utf-8')
updated = open(sys.argv[1][:len(sys.argv[1]) - 4] + "_bert.csv", 'a', encoding='utf-8')
answers = []
for line in f:
	sent = line.split("\t")
	updated.write(sent[0] + "\t" + sent[1].rstrip() +"\t1\n")
	answers.append(sent[1].rstrip())
f.seek(0)

for i, line in enumerate(f):
	sent = line.split("\t")
	for j in range(len((answers))):
		if i != j:
			
			updated.write(sent[0] + "\t" + answers[j] + "\t0\n")

updated.close()
f.close()