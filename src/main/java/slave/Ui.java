package slave;

import java.util.List;

public class Ui {
    private String user = "slave driver";
    private Parser parser;

    public Ui(List<Task> tasklist) {
        parser = new Parser(tasklist);
    }

    public Pair<Boolean, Boolean> getUserInputs() {
        return parser.getUserInput();

    }

    /**
     * Prints the greeting message
     */
    public void welcome() {
        Parser.pageBreakLine();
        // @@author ASCII Figlet Generator -reused
        // logo sourced from: https://www.askapache.com/online-tools/figlet-ascii/
        String logo
                = "_______        _______ _    _ _______\n"
                + "|______ |      |_____|  \\  /  |______\n"
                + "______| |_____ |     |   \\/   |______\n";
        //@@author
        System.out.println("Ugh... why did you wake me up...\nGuess I am now your personal\n" + logo);
        System.out.println("What do you want from me? Say it now, I don't have all day...");
        Parser.pageBreakLine();
    }

    /**
     * Prints the goodbye message in response to the user inputting "bye"
     */
    public void goodbye() {
        System.out.println("Good riddance " + user + ", try not to bother me in the future...");
        Parser.pageBreakLine();
    }
}
