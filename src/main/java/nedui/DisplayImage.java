package nedui;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class DisplayImage {
    public static void applyCircularFilter(ImageView existingImageView, double size) {
        existingImageView.setFitWidth(size);
        existingImageView.setFitHeight(size);
        existingImageView.setPreserveRatio(true);
        existingImageView.setSmooth(true);
        Circle clip = new Circle(size / 2, size / 2, size / 2);
        existingImageView.setClip(clip);
    }
}
