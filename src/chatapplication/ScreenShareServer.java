package chatapplication;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class ScreenShareServer implements Runnable {
    private int port;

    public ScreenShareServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Screen sharing started on port " + port);
            Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            while (true) {
                BufferedImage screenShot = robot.createScreenCapture(screenRect);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(screenShot, "jpg", baos);
                byte[] imageBytes = baos.toByteArray();

                dos.writeInt(imageBytes.length);
                dos.write(imageBytes);
                dos.flush();

                Thread.sleep(200); // ~5 fps
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
