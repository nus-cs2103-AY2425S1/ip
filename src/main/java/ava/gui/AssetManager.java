package ava.gui;

import javafx.application.Application;
import javafx.scene.image.Image;

/**
 * Manages the interaction with the
 * assets.
 */
public class AssetManager {
    private static final String ASSET_PATH = "/ava/assets/";
    private static final String IMAGE_PATH = ASSET_PATH + "images/";

    private static final Image userImage = new Image(Application.class.getResourceAsStream(IMAGE_PATH + "user.jpg"));
    private static final Image AVAImage = new Image(Application.class.getResourceAsStream(IMAGE_PATH + "AVA.jpg"));

    /**
     * Gets the image of the user.
     *
     * @return Image of the user.
     */
    public static Image getUserImage() {
        return userImage;
    }

    //CHECKSTYLE.OFF: AbbreviationAsWordInName
    /**
     * Gets the image of AVA.
     *
     * @return Image of AVA.
     */
    public static Image getAVAImage() {
        return AVAImage;
    }
    //CHECKSTYLE.ON: AbbreviationAsWordInName
}

