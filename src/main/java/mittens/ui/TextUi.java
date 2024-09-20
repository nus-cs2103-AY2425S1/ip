package mittens.ui;

import mittens.MittensException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the CLI version of the user interface.
 */
public class TextUi extends Ui {
    
    private final static String GREETING_MESSAGE = """
             /\\_/\\     ____________________
             >^,^<    / Hi, I'm Mittens!   \\
              / \\     \\ I'm a cat! Meow :3 /
             (___)_/   --------------------""";

    private final static String GOODBYE_MESSAGE = """
             /\\_/\\     _____________
             >^,^<    ( Bye-bye! :3 )
              / \\      -------------
             (___)_/""";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a new TextUi object with the default input and output streams.
     */
    public TextUi() {
        this(System.in, System.out);
    }
    
    /**
     * Creates a new TextUi object with the given input and output streams.
     * 
     * @param in The input stream
     * @param out The output stream
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    
    /**
     * Prints the given message to the output stream.
     * 
     * @param message The message to print
     */
    public void printMessage(String message) {
        this.out.println(message);
    }
    
    /**
     * Prints a blank line to the output stream.
     */
    public void printBlankLine() {
        this.out.println();
    }

    /**
     * Prints the given message accompanied by the cute cat Mittens.
     * 
     * @param message The message to print
     */
    public void printMittens(String message) {
        int len = message.length();
        String uiMessage = """
                 /\\_/\\     %s
                 >^,^<    ( %s )
                  / \\      %s
                 (___)_/""".formatted("_".repeat(len + 2),
                message, "-".repeat(len + 2));

        this.out.println(uiMessage);
    }
    
    /**
     * Prints the given error message accompanied by the cute cat Mittens.
     * 
     * @param e The exception to print
     */
    public void printErrorMessage(MittensException e) {
        String mittensMessage = e.getMittensMessage();
        int len = mittensMessage.length();

        String helpMessage = e.getHelpMessage();
        String errorMessage = e.getMessage();

        String uiMessage = """
                 /\\_/\\     %s
                 >x.x<    ( %s )
                  / \\      %s
                 (___)_/
                
                Error: %s
                %s""".formatted("_".repeat(len + 2), mittensMessage,
                "-".repeat(len + 2), errorMessage, helpMessage);

        this.out.println(uiMessage);
    }
    
    @Override
    public void showGreetingMessage() {
        this.printBlankLine();
        this.out.println(GREETING_MESSAGE);
        this.printBlankLine();
    }

    @Override
    public void showGoodbyeMessage() {
        this.printBlankLine();
        this.out.println(GOODBYE_MESSAGE);
        this.printBlankLine();
    }

    @Override
    public void showRegularMessage(List<String> messages) {
        this.printBlankLine();
        for (String message : messages) {
            this.printMessage(message);
        }
        this.printBlankLine();
    }
    
    @Override
    public void showRegularMessage(String... messages) {
        showRegularMessage(List.of(messages));
    }
    
    @Override
    public void showMittensMessage(List<String> messages) {
        this.printBlankLine();
        for (String message : messages) {
            this.printMittens(message);
            this.printBlankLine();
        }
    }

    @Override
    public void showErrorMessage(MittensException e) {
        this.printBlankLine();
        this.printErrorMessage(e);
        this.printBlankLine();
    }

    @Override
    public String getUserInput() {
        this.out.print("> ");
        return this.in.nextLine();
    }
}
