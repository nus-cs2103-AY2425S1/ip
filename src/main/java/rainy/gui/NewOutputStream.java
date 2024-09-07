package rainy.gui;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Reroutes the system generated statements fromt console to the Rainy GUI.
 */
public class NewOutputStream extends OutputStream {
    private VBox dialogContainer;
    private Image rainyImage;
    private StringBuilder stringBuilder = new StringBuilder();

    /**
     * Constructs a <code>NewOutputStream</code> object.
     * @param dialogContainer  Container housing the dialog boxes.
     * @param rainyImage       Image of the chatbot.
     */
    public NewOutputStream(VBox dialogContainer, Image rainyImage) {
        this.dialogContainer = dialogContainer;
        this.rainyImage = rainyImage;
    }

    /**
     * Captures the output string and builds it character by character.
     * @param b   the {@code byte}.
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        if (b == '^') {
            String text = stringBuilder.toString().trim();
            stringBuilder.setLength(0);
            Platform.runLater(() -> {
                dialogContainer.getChildren().add(DialogBox.getRainyDialog(text, rainyImage));
            });
        } else {
            stringBuilder.append((char) b);
        }
    }
}
