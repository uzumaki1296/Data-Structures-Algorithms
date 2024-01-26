import xml
from xml.dom import minidom
from threading import Thread
import threading
import time
import thread

data=[]
doc=minidom.parse("iput.xml")
item=doc.getElementsByTagName("element")
for i in item:
	data.append(int(i.attributes['no'].value.encode("utf-8")))
print (data)

def qsort(sets,left,right):

    print("thead {0} is sorting {1}".format(threading.current_thread(), sets[left:right]))

    i = left
    j = right
    pivot = sets[(left + right)/2]
    temp = 0
    while(i <= j):
         while(pivot > sets[i]):
             i = i+1
         while(pivot < sets[j]):
             j = j-1
         if(i <= j):
             temp = sets[i]     
             sets[i] = sets[j]
             sets[j] = temp
             i = i + 1
             j = j - 1

    lthread = None
    rthread = None

    if (left < j):
        lthread = Thread(target = lambda: qsort(sets,left,j))
        lthread.start()

    if (i < right):
        rthread = Thread(target=lambda: qsort(sets,i,right))
        rthread.start()

    if lthread is not None: lthread.join()
    if rthread is not None: rthread.join()
    return sets


res = qsort(data, 0, len(data) - 1)
print(res)



OUTPUT:

@ubuntu:~/CL3/Quick$ python cquick.py
[1, 3, 2, 5, 9]
thead <_MainThread(MainThread, started 140044366247744)> is sorting [1, 3, 2, 5]
thead <Thread(Thread-1, started 140044333815552)> is sorting [1]
thead <Thread(Thread-2, started 140044253787904)> is sorting [3, 5]
[1, 2, 3, 5, 9]