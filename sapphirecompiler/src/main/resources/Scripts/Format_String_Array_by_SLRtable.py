print("------- ACTION TABLE ----------")
while True:
  enter = input()
  # enter = enter.replace('-','-')
  print("{",end="")
  enter = enter.split('	')
  for x in enter[1:42]:
    print("\""+x+"\"", end=",")
  print("},")
  # print(enter.count(','))