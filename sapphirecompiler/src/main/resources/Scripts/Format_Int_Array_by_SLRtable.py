print("------- ACTION TABLE ----------")
while True:
  enter = input()
  # enter = enter.replace('-','-')
  print("{",end="")
  enter = enter.split('	')
  for x in enter[42:]:
    if x not in "-":
        print(x, end=",")
    else:
        print("0",end=",")
  print("},")
  # print(enter.count(','))