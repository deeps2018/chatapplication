Desktop Multi-user Chat Application
This application allows users to connect to a server to send messages to all users connected to the server
And also logs all the messages sent so that new users would receive them when they connect

How to run:
1. Keep Server.java, Conversation.java, MessageQueue.java and MessageDispatcher.java in the same folder
2. Edit "filepath" variable in line 17, Server.java to a file for logging
3. Run the server.java file
4. Note the IP address of the server machine
5. Edit IP address in line 17, SocketP.java
6. Run SocketP.java and enter username in the UI, it is the client application
7. Run multiple clients and start messaging
8. send "end" to disconnect the client