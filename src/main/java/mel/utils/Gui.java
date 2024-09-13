package mel.utils;

import java.util.Objects;

import mel.main.Mel;

/**
 * Gui class handles the user interface of Mel
 * chatbot, to receive and respond to user input.
 */
public class Gui {
    private final Mel mel;
    /**
     * Constructor for Gui.
     * @param mel Mel chatbot instance.
     */
    public Gui(Mel mel) {
        this.mel = mel;
    }

    /**
     * Outputs introduction for Mel on startup.
     * @return String introductory message.
     */
    public String hello() {
        return "Hihi! Mel here (:\n"
                + "What you need?";
    }

    /**
     * Reads user input to Mel chatbot.
     * @param input user input string.
     */
    public void read(String input) {
        if (input.length() > 100) {
            mel.println("Mel's eyes explode "
                    + "reading your many words XD");
        } else if (Objects.equals(input, "bye")) {
            mel.println("Buh-bye :)");
        } else {
            mel.executeTask(input);
        }
    }
}
