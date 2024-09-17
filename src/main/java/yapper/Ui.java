package yapper;

import java.util.Scanner;

/**
 * Represents a Ui, which takes in the user input and sends it to the parser to parse.
 */
public class Ui {
    private final Scanner sc;
    private final Parser parser;

    /**
     * Creates an instance of UI
     *
     * @param parser Parser object for UI to send user inputs to be parsed.
     */
    public Ui(Parser parser) {
        this.sc = new Scanner(System.in);
        this.parser = parser;
    }

    /**
     * Reads user inputs and sends it to the parser to parse.
     */
    public void readInput() {
        while (true) {
            if (sc.hasNextLine()) {
                String text = sc.nextLine();
                this.parser.readCommand(text);
            }
        }
    }

    public String readGuiInput(String string) {
        return this.parser.readCommand(string);
    }
}
