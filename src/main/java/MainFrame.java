package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class MainFrame extends JFrame {
    private ImagePanel imagePanel;
    private JButton jNextImage;
    private JButton jLastImage;
    private ImageModel model;

    public MainFrame() throws HeadlessException {
        model = new ImageModel();

        imagePanel = new ImagePanel();
        jNextImage = new JButton("Next image");
        jLastImage = new JButton("Last image");

        jNextImage.addActionListener(this::nextImage);
        jLastImage.addActionListener(this::lastImage);

        this.add(jNextImage, BorderLayout.EAST);
        this.add(jLastImage, BorderLayout.WEST);
        this.add(imagePanel, BorderLayout.CENTER);

        this.setTitle("Change image");
        this.setSize(750, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        loadImage(model.getPath());
    }

    private void lastImage(ActionEvent e) {
        model.lastImage();
        loadImage(model.getPath());
    }

    private void nextImage(ActionEvent e) {
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
}

