import socket     
import sys          
s = socket.socket()         
host = socket.gethostname() 
port = 4444

s.connect((host, port))
while True:
	data=input("Enter the query\n")
	s.send(data.encode('utf-8'))
	rcv=s.recv(1024)
	print(rcv.decode('utf-8'))
s.close                     
