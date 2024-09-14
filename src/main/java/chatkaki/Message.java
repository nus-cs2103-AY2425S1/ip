package chatkaki;

import javafx.scene.image.Image;

/**
 * Represents a message in the chat.
 */
public class Message {
    private String text;
    private boolean isUser;
    private Image profileImage;

    /**
     * Constructs a Message object with the specified inputs.
     *
     * @param text The text of the message.
     * @param isUser Whether the message is sent by the user.
     * @param profileImage The profile image of the sender.
     */
    public Message(String text, boolean isUser, Image profileImage) {
        this.text = text;
        this.isUser = isUser;
        this.profileImage = profileImage;
    }

    public String getText() { return text; }
    public boolean isUser() { return isUser; }
    public Image getProfileImage() { return profileImage; }
}
