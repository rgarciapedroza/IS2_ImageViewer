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
        this.setSize(750, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        loadImage(model.getPath());

        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        imagePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - initialClick.x;
                if (deltaX > 50) {
                    nextImage();
                    initialClick = e.getPoint();
                } else if (deltaX < -50) {
                    lastImage();
                    initialClick = e.getPoint();
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
        } else {
            System.err.println("Resource not found: " + filename);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
