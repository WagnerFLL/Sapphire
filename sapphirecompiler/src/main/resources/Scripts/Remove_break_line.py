while True:
    enter = input()
    enter = enter.split()
    i = 0
    for x in enter:
        print("map.put(\""+x+"\","+str(i)+");")
        i += 1