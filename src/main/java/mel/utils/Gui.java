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
     * @return boolean indicator for ending chatbot session,
     *       False - persist session,
     *       True - end session.
     */
    public boolean read(String input) {
        boolean isBye;
        if (input.length() > 100) {
            mel.println("Mel's eyes explode "
                    + "reading your many words XD");
            isBye = false;
        } else if (Objects.equals(input, "bye")) {
            mel.println("Buh-bye :)");
            isBye = true;
        } else {
            mel.executeTask(input);
            isBye = false;
        }
        return isBye;
    }
}
