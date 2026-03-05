package chatapplication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class ScreenShareClient implements Runnable {
    private String serverIP;
    private int port;
    private volatile boolean running = true;
    private Socket socket;
    private JFrame frame;

    public ScreenShareClient(String serverIP, int port) {
        this.serverIP = serverIP;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverIP, port);
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            frame = new JFrame("Screen Viewer - " + serverIP + ":" + port);
            JLabel label = new JLabel();
            frame.add(label);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);

            while (running && !Thread.currentThread().isInterrupted()) {
                int size;
                try {
                    size = dis.readInt();
                } catch (EOFException eof) {
                    break;
                }
                if (size <= 0) continue;
                byte[] imageBytes = new byte[size];
                dis.readFully(imageBytes);

                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                BufferedImage img = ImageIO.read(bais);

                if (img != null && frame.isVisible()) {
                    label.setIcon(new ImageIcon(img.getScaledInstance(
                            frame.getWidth(), frame.getHeight(), java.awt.Image.SCALE_SMOOTH)));
                }
            }
        } catch (Exception e) {
            if (running) e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    public void stopClient() {
        running = false;
        try {
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (Exception e) {}
        if (frame != null) {
            try { javax.swing.SwingUtilities.invokeLater(() -> frame.dispose()); } catch (Exception e) {}
        }
    }

    private void cleanup() {
        try { if (socket != null && !socket.isClosed()) socket.close(); } catch (Exception e) {}
        if (frame != null) {
            try { javax.swing.SwingUtilities.invokeLater(() -> frame.dispose()); } catch (Exception e) {}
        }
    }
}
