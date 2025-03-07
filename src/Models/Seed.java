package Models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Seed {
    public void changeImage(ImageView imageView, String newImagePath) {
        try {
            Image newImage = new Image(getClass().getResource(newImagePath).toExternalForm());
            imageView.setImage(newImage);
        } catch (Exception e) {
            System.err.println("Erreur lors du changement de l'image : " + e.getMessage());
        }
    }

    public void startCooldown(ImageView imageView, int seconds) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), event -> changeImage(imageView, "/img/end.png")));
        timeline.setCycleCount(1);
        timeline.play();
    }
}