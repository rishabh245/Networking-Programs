import socket             
import sys
import sqlite3

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)         
host = socket.gethostname() 
port = 4444
s.bind((host, port))        
s.listen(5)                 
c, addr = s.accept()    
conn = sqlite3.connect('test.db')
# conn.execute('''CREATE TABLE COMPANY
#          (ID INT PRIMARY KEY     NOT NULL,
#          NAME           TEXT    NOT NULL,
#          AGE            INT     NOT NULL,
#          SALARY         INT);''')
# conn.execute("INSERT INTO COMPANY (ID,NAME,AGE,SALARY) \
#       VALUES (1, 'Paul', 32, 20000 )");

# conn.execute("INSERT INTO COMPANY (ID,NAME,AGE,SALARY) \
#       VALUES (2, 'Allen', 25, 15000 )");

# conn.execute("INSERT INTO COMPANY (ID,NAME,AGE,SALARY) \
#       VALUES (3, 'Teddy', 23, 20000 )");

# conn.execute("INSERT INTO COMPANY (ID,NAME,AGE,SALARY) \
#       VALUES (4, 'Mark', 25, 65000)");

#conn.commit()
print ('Got connection from', addr)
while True:   
	rcv=c.recv(1024)
	query=rcv.decode('utf-8')
	data = query.split()
	if(data[0].lower()=='insert'):
		try:
			conn.execute(query)
			conn.commit()	   	
			var1 = 'Insert successful'
			c.send(var1.encode('utf-8'))
		except sqlite3.Error as e:	
			var1 = 'Insert not successful'
			c.send(var1.encode('utf-8'))
	elif(data[0].lower()=='select'):
		try:
		    cursor = conn.execute(query)
		    var1=''
		    for row in cursor:
		    	var1 = var1 + 'ID = '+ str(row[0]) + '\n' + 'NAME = ' + str(row[1]) + '\n' + 'AGE = '  + str(row[2])+ '\n' + 'SALARY = ' + str(row[3]) + '\n\n'
		    c.send(var1.encode('utf-8'))
		except sqlite3.Error as e :
			var1 = 'Selection not Possible'
			c.send(var1.encode('utf-8'))    	    	
	elif(data[0].lower()=='update'):
		try:
			conn.execute(query)
			conn.commit()
			var1 = 'Total number of rows updated :' + str(conn.total_changes)
			c.send(var1.encode('utf-8'))
		except sqlite3.Error as e:
			var1 = 'Update not possible'
			c.send(var1.encode('utf-8'))	
	elif(data[0].lower()=='delete'):
		try:
			conn.execute(query)
			conn.commit()	
			var1 = 'Deletion Successfull'
			c.send(var1.encode('utf-8'))
		except sqlite3.Error as e:
			var1 = 'Deletion not possible'
			c.send(var1.encode('utf-8'))	
	else:
		var1 = 'Wrong Query!!'
		c.send(var1.encode('utf-8'))		

c.close()	         

