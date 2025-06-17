import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class ChatApp {
    private static final String MULTICAST_IP = "230.0.0.0";
    private static final int PORT = 4446;

    private DatagramSocket socket;
    private InetAddress group;

    public ChatApp() throws Exception {
        group = InetAddress.getByName(MULTICAST_IP);
        socket = new DatagramSocket();
        createGUI();
        listenForMessages();
    }

    private void createGUI() {
        JFrame frame = new JFrame("LAN Chat");
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JTextField input = new JTextField();
        JButton sendBtn = new JButton("Send");

        JScrollPane scroll = new JScrollPane(chatArea);
        frame.add(scroll, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(sendBtn, BorderLayout.EAST);
        frame.add(bottom, BorderLayout.SOUTH);

        ActionListener sendAction = e -> {
            try {
                String message = input.getText().trim();
                if (!message.isEmpty()) {
                    byte[] buffer = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
                    socket.send(packet);
                    input.setText("");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };

        sendBtn.addActionListener(sendAction);
        input.addActionListener(sendAction);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start listener thread
        new Thread(() -> {
            try (MulticastSocket receiver = new MulticastSocket(PORT)) {
                receiver.joinGroup(group);
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    receiver.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    SwingUtilities.invokeLater(() -> chatArea.append(msg + "\n"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void listenForMessages() {
        // Already handled by thread in createGUI()
    }

    public static void main(String[] args) {
        try {
            new ChatApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}