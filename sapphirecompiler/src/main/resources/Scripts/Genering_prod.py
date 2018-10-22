enter = input()
en = [enter]
while True:
    if 'K' in enter:
        break
    enter = input()
    en += [enter.replace('->','=')]


for x in (en):
    if x != "":
        print("productions.add(new Production(\""+x+"\"));")