package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MainFrame extends JFrame {
    private ImagePanel imagePanel;
    private ImageModel model;
    private Point initialClick;

    public MainFrame() throws HeadlessException {
        model = new ImageModel();

        imagePanel = new ImagePanel();
        this.add(imagePanel, BorderLayout.CENTER);

        this.setTitle("Change image");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        loadImage(model.getPath());

        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int deltaX = e.getX() - initialClick.x;
                if (deltaX > 50) {
                    nextImage();
                } else if (deltaX < -50) {
                    lastImage();
                }
            }
        });
    }

    private void lastImage() {
        model.lastImage();
        loadImage(model.getPath());
    }

    private void nextImage() {
        model.nextImage();
        loadImage(model.getPath());
    }

    private void loadImage(String filename) {
        URL resource = getClass().getResource("/" + filename);
        if (resource != null) {
            ImageIcon icon = new ImageIcon(resource);
            Image image = icon.getImage();
            imagePanel.setImage(image);

            int imgWidth = image.getWidth(null);
            int imgHeight = image.getHeight(null);
            float aspectRatio = (float) imgWidth / imgHeight;

            int frameWidth = 800;
            int frameHeight = 600;
            if (aspectRatio > 1) {
                frameHeight = (int) (frameWidth / aspectRatio);
            } else {
                frameWidth = (int) (frameHeight * aspectRatio);
            }

            this.setSize(frameWidth, frameHeight);


            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - frameWidth) / 2;
            int y = (screenSize.height - frameHeight) / 2;
            this.setLocation(x, y);
        } else {
            System.err.println("Hubo un error al localizar la imagen: " + filename);
        }
    }
}
