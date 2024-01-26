from xml.dom import minidom

size=8
board=[]
brd=[['_' for x in range(9)]for x in range(9)]


def printboard(board):
    for i in range(1,9):
        for j in range(1,9):
            print str(brd[i][j])+" ",
        print "\n"



def danger(row,col):
    for (i,j) in board:
        if(row==i):
            return True
        if(col==j):
            return True
        if(abs(row-i)==abs(col-j)):
	    return True
    return False


def placequeen(row):
    if(row>size):
        print board
        printboard(board)


    else:
        for col in range(1,size+1):
            if not danger(row,col):
                board.append((row,col))
                brd[row][col]='Q'
                placequeen(row+1)
                board.remove((row,col))
                brd[row][col]='_'



if __name__=="__main__":
    data=[]
    doc=minidom.parse("input_queens.xml")
    item=doc.getElementsByTagName("element")
    for i in item:
       data.append(int(i.attributes['no'].value.encode("utf-8")))
    c=data[0]
    board.append((1,c))
    brd[1][c]='Q'
    placequeen(2)
	
		
			
    
	
		
