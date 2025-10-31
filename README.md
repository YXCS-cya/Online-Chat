# Java Instant Messaging System (Online-Chat)

> A multi-threaded instant messaging system built with Java and TCP/IP Socket communication

---

## 🧩 Project Overview
This project implements a desktop instant messaging system using **Java TCP/IP network programming**.  
It features real-time communication between multiple clients and a central server through **Socket** connections, **multi-threading**, and **object serialization**.  
The system provides a complete client–server messaging architecture with GUI interfaces and persistent chat history storage.

- **Language:** Java  
- **Communication Protocol:** TCP/IP  
- **Core Technologies:** Socket, ServerSocket, Multi-threading, Object Streams, File I/O  
- **Key Features:** Login & Registration, Friend List, Real-Time Chat, Persistent Message Storage  

---

## 🧰 Technical Highlights
| Module | Description |
|--------|--------------|
| **Network Communication** | Implements reliable connections via `Socket` and `ServerSocket` based on the TCP protocol |
| **Message Serialization** | Custom `Message` class and `MessageType` interface used for encoding and decoding data packets |
| **Concurrent Server** | Each client connection is handled by an independent thread to support multiple simultaneous users |
| **I/O and Object Streams** | Uses `ObjectInputStream` and `ObjectOutputStream` for object-level message transmission |
| **GUI Interface** | Built with `Swing`, including login, friend list, and chat windows |
| **Data Persistence** | Chat history is automatically stored locally in the `Chatdata` folder, named by user pairs |



## 💻 System Architecture
The system consists of three core components:

1. **Client Side**
   - Provides user login, friend list, and chat interfaces.  
   - Establishes a socket connection to the central server.  
   - Sends and receives serialized `Message` objects.  
   - Saves chat logs locally for each conversation.

2. **Server Side**
   - Uses `ServerSocket` to continuously listen for client requests.  
   - Creates a dedicated thread for each connected client.  
   - Manages online user sessions and forwards messages.  

3. **Message Definition Layer**
   - Defines communication rules through `Message` and `MessageType`.  
   - Supports login verification, text messages, and extensible message types.  



## 📁 Project Structure

### 🧭 Summary
- **Chat_system/** – Save src, all codes and datas are stored in this fileds.
- **Client/** – Handles GUI, user interactions, and Socket communication.  
- **Server/** – Manages concurrent client threads and message routing.  
- **User_data/** – Defines data models and communication protocols.  
- **Data/** – Stores persistent chat logs and user information.  



## 🧠 Feature Demonstration

### Login and Registration
![Login Interface](./docs/login.png)

- Users can log in with credentials or register a new account.  
- Upon successful authentication, the system connects to the server and displays the friend list.



### Friend List
![Friend List](./docs/friends.png)

- Displays all available contacts dynamically.  
- Double-click on a friend to start a conversation.  
- Supports multiple chat windows concurrently.



### Chat Window
![Chat Interface](./docs/chat.png)

- Real-time text communication with timestamped messages.  
- Message history is automatically written to local files.  
- Supports bi-directional messaging between any two online users.



### Chat Data Structure
![Chat Data Folder](./docs/chatdata.png)

Chat logs are automatically saved in the `Data/Chatdata` directory,  
where each file corresponds to a specific conversation:
```
YXCS_with_Miku
Miku_with_YXCS
YXCS_with_testUser
```


Each file contains complete time-stamped dialogues between two users.

---

## ⚙️ How to Run
1. Launch the server program `Server.java` to start listening on a defined port.  
2. Run the client-side application `ClientLogin.java`.  
3. Enter username and password to log in (or register a new user).  
4. After login, open the friend list and start chatting with other online users.  
5. Chat logs will be stored automatically under `Data/Chatdata`.



## 🔐 System Highlights
- **Complete TCP Communication Model** — implements real-time Socket-based transmission and response handling.  
- **Extensible Protocol Design** — `MessageType` allows easy addition of file transfer and extended message types.  
- **Thread-Safe Architecture** — each client runs in an independent thread, ensuring stable concurrency.  
- **Modular & Maintainable Design** — clear separation between UI, logic, and networking layers.  
- **Engineering-Oriented Implementation** — includes data persistence, error handling, and multi-user synchronization.





## 🧾 Summary
This project demonstrates **practical network programming and multi-threaded system design** using Java.  
It combines **Socket-based communication**, **object serialization**, **thread management**, and **persistent data storage**  
to create a complete instant messaging application with a functional user interface.

The project highlights proficiency in:
- TCP/IP communication principles  
- Concurrency control and thread management  
- Object-oriented system design  
- Networked GUI application development  

> Designed and implemented independently to explore real-time communication systems using Java.

---

## 🛠️ Build & Run Note
This project can be compiled and executed directly from source using IntelliJ IDEA or any Java IDE.  
No external libraries are required. The server should be launched before running the client.  
Detailed steps are showed above.(in "⚙️ How to Run")

