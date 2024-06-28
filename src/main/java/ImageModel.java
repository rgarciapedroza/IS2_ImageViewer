package main.java;

public class ImageModel {
    private String[] imagePaths;
    private int imageIndex;

    public ImageModel() {
        imagePaths = new String[]{
                "main/resources/imagen1.png",
                "main/resources/imagen2.jpg",
                "main/resources/imagen3.jpeg",
                "main/resources/imagen4.jpg",
                "main/resources/imagen5.jpeg"
        };
        imageIndex = 0;
    }

    public String getPath() {
        return imagePaths[imageIndex];
    }

    public void nextImage() {
        imageIndex = (imageIndex + 1) % imagePaths.length;
    }

    public void lastImage() {
        imageIndex = (imageIndex - 1 + imagePaths.length) % imagePaths.length;
    }
}

