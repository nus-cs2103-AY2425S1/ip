package mel.utils;

import java.util.Objects;

import mel.main.Mel;

/**
 * Ui class handles the user interface of Mel
 * chatbot, to receive and respond to user input.
 */
public class Ui {
    private static final String LOGO = " __    __        __  \n"
            + "|   \\/   | ____ |  | \n"
            + "| |\\  /| |/ __ \\|  | \n"
            + "| | \\/ | |  ___/|  | \n"
            + "|_|    |_|\\____||__| ";
    private static final String LINE = "____________________________________";
    private final Mel mel;

    /**
     * Constructor for Ui.
     * @param mel Mel chatbot instance.
     */
    public Ui(Mel mel) {
        this.mel = mel;
    }

    /**
     * Sends introductory text to user on Mel startup.
     */
    public void hello() {
        System.out.println(LOGO + "\n" + LINE + "\n"
                + "Hihi! Mel here (:\n"
                + "What you need?\n" + LINE);
    }

    /**
     * Reads user input to Mel chatbot.
     * @param input user input string.
     * @return boolean indicator for ending chatbot session,
     *      False - persist session,
     *      True - end session.
     */
    public boolean read(String input) {
        System.out.println(LINE);
        boolean isBye;
        if (input.length() > 100) {
            System.out.println("Mel's eyes explode "
                    + "reading your many words XD");
            isBye = false;
        } else if (Objects.equals(input, "bye")) {
            System.out.println("Buh-bye :)");
            isBye = true;
        } else {
            mel.executeTask(input);
            isBye = false;
        }
        System.out.println(LINE);
        return isBye;
    }

    /**
     * Sends string response to user using system output,
     * terminates line after output.
     * @param str user input string.
     */
    public void println(String str) {
        System.out.println(str);
    }
}
