LAN Chat (Java Multicast)
A minimalist, no‑frills LAN chat client written in plain old Java SE. It spams a multicast group (230.0.0.0:4446) with your messages so everyone on the same network can see your profound thoughts—assuming you manage to run the program without breaking something.

✨ Features
Zero server setup. Spin up one instance per machine and you’re done.

Simple Swing UI. One window, a text area, an input field, and a “Send” button—because that’s all you can handle.

Multicast broadcast. Uses UDP multicast so messages go to everyone listening on the group.

🛠 Prerequisites
What	Minimum Version	Why you need it
JDK 8 (or newer)	1.8+	To compile/run the code—duh
A LAN	Any	The whole point is “Local Area Network”
Firewalls opened	UDP 4446	Stop blaming the code if you forgot this

🚀 Build & Run
bash
Copy
Edit
# 1. Compile
javac ChatApp.java

# 2. Run (each client runs the same command)
java ChatApp
That’s it. Seriously. If you can’t manage two commands, maybe programming isn’t for you.

🗣 Usage
Launch the jar/java ChatApp on each machine in the same subnet.

Start typing in the text field.

Hit Enter or click Send.

Watch the chat flood with whatever nonsense you broadcast.

Anything typed is shoved into a UDP packet and blasted to 230.0.0.0:4446. Every listening instance displays it instantly—there’s no delivery guarantee, so quit whining if you drop a packet or two.

⚙️ Customization
What to tweak	Where	Why
Multicast IP	private static final String MULTICAST_IP	Pick an address in 224.0.0.0 – 239.255.255.255 that isn’t already in use.
Port	private static final int PORT	Use any free UDP port; match it on every client.
Buffer size	byte[] buffer = new byte[1024];	Increase if you need longer messages (and enjoy fragmentation hell).
Window size	frame.setSize(400, 300);	Resize if your monitor isn’t from the Stone Age.

🧐 Gotchas & Limitations
No message ordering/guarantees. UDP doesn’t care—neither should you.

LAN‑only. Routers rarely forward multicast; stay on the same network.

No encryption. Anyone on the LAN can eavesdrop; don’t share your deepest secrets here.

Single chat room. Change IP/port if you crave “channels.”

🏗 Roadmap (if you’re bored)
Nicknames & timestamps

Simple AES encryption

File transfer (try not to nuke your own network)

Emoji support (because apparently people can’t type words anymore)

🤝 Contributing
Fork it.

Make your changes in a branch.

Open a pull request with clear commit messages (no “fix stuff lol”).

Try not to introduce more bugs than features.

📝 License
MIT—do whatever you want, just don’t blame me when it breaks.
