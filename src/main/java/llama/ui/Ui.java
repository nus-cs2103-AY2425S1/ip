package llama.ui;

import java.time.format.DateTimeFormatter;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String logo = "";
    private static final String hr = "____________________________________________________________";
    /**
     * Displays welcome message to user
     *
     * @return welcome message with proper formatting
     */
    public String displayWelcome() {
        String response = "";
        this.displayLine();
        response += this.displayString("Hello! I'm Llama!");
        this.displayString(logo);
        response += this.displayString("What can I do for you?");
        return response;
    }

    /**
     * Displays end program message to user
     *
     * @return end program message with proper formatting
     */
    public String displayBye() {
        return displayString("Baaaaaa byeeee. Come baaaaack soon!");
    }

    /**
     * Displays in CLI program message to user and returns message
     *
     * @param str string to be displayed
     * @return message with proper formatting
     */
    public String displayString(String str) {
        System.out.println("\t" + str);
        return "\n" + str;
    }

    /**
     * Displays a horizontal line
     */
    public void displayLine() {
        this.displayString(hr);
    }
}
