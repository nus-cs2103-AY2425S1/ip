package ava.gui;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * Manages the interaction with the
 * assets.
 */
public class AssetManager {

    //CHECKSTYLE.OFF: AbbreviationAsWordInName
    private final String ASSET_PATH;
    private final String IMAGE_PATH;

    private final InputStream userImageStream;
    private final InputStream AVAImageStream;

    private final Image userImage;
    private final Image AVAImage;
    private final Image icon;
    //CHECKSTYLE.ON: AbbreviationAsWordInName

    /**
     * Creates an AssetManager instance with default path.
     */
    public AssetManager() {
        this("/assets/", "images/");
    }

    /**
     * Creates an AssetManager instance with the specified path.
     * This uses default paths for user and AVA.
     *
     * @param assetPath Path to the assets.
     * @param imagePath relative path to the images from the assets.
     */
    public AssetManager(String assetPath, String imagePath) {
        this(assetPath, imagePath, "user_close_up.jpg", "AVA.jpg");
    }


    /**
     * Creates an AssetManager instance with the specified paths.
     *
     * @param assetPath Path to the assets.
     * @param imagePath relative path to the images from the assets.
     * @param userImagePath relative path to the user image from the assets.
     * @param avaImagePath relative path to the AVA image from the assets.
     */
    public AssetManager(String assetPath, String imagePath, String userImagePath, String avaImagePath) {
        this.ASSET_PATH = assetPath;
        this.IMAGE_PATH = ASSET_PATH + imagePath;
        this.userImageStream = Gui.class.getResourceAsStream(IMAGE_PATH + userImagePath);
        this.AVAImageStream = Gui.class.getResourceAsStream(IMAGE_PATH + avaImagePath);
        InputStream iconImageStream = Gui.class.getResourceAsStream(IMAGE_PATH + "icon.jpg");

        // the image streams should not be null
        assert userImageStream != null;
        assert AVAImageStream != null;
        assert iconImageStream != null;

        userImage = new Image(userImageStream);
        AVAImage = new Image(AVAImageStream);
        icon = new Image(iconImageStream);
    }

    /**
     * Gets the image of the user.
     *
     * @return Image of the user.
     */
    public Image getUserImage() {
        return userImage;
    }

    //CHECKSTYLE.OFF: AbbreviationAsWordInName
    /**
     * Gets the image of AVA.
     *
     * @return Image of AVA.
     */
    public Image getAVAImage() {
        return AVAImage;
    }
    //CHECKSTYLE.ON: AbbreviationAsWordInName

    public Image getIcon() {
        return icon;
    }
}

