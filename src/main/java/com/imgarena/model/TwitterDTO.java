package com.imgarena.model;

public class TwitterDTO {
    private String author;
    private String text;
    private String imageURL;

    public TwitterDTO(String author, String text, String imageURL) {
        this.author = author;
        this.text = text;
        this.imageURL = imageURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "TwitterDTO{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
