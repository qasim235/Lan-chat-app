# LAN Chat (Java Multicast)

A minimalist, no-frills LAN chat client written in plain Java. It uses multicast (`230.0.0.0:4446`) to broadcast messages to all clients on the same network.

## ✨ Features
- **No Server Needed** – Peer-to-peer communication using multicast.
- **Simple GUI** – Built using Swing with a chat display, input field, and send button.
- **Real-time Messaging** – Messages are sent and received instantly over UDP.

## 🛠 Prerequisites
| Requirement       | Details                           |
|-------------------|-----------------------------------|
| Java Development Kit | JDK 8 or newer                 |
| Local Network        | All clients must be on same LAN |
| Open UDP Port        | Port 4446 must be accessible    |

## 🚀 How to Run

```bash
# 1. Compile the source
javac ChatApp.java

# 2. Run the application
java ChatApp
