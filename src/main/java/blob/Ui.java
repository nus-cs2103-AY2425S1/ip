package blob;

import java.util.Scanner;

/**
 * Responsible for initalising all other related objects.
 * Responsible for hosting conversation.
 * Responsible for certain repeated chat aesthetics used throughout the chat.
 * Constructor requires a TaskList object as an argument.
 */
public class Ui {

    public final String unknownCommandMsg = "ERROR! Unknown command!";
    public final String breakLine = "__________________________________________________";
    private Scanner human;
    private Parser parser;
    private TaskList tasklist;
    private boolean active = false;

    public Ui(TaskList tasklist) {
        this.tasklist = tasklist;
        this.human = new Scanner(System.in);
        this.parser = new Parser();
    }

    /**
     * Prints out a long line
     */
    public void breakLine() {
        System.out.println(this.breakLine);
    }

    /**
     * Begins conversation with user
     */
    public void initialise() {
        this.active = true;
        System.out.println();
        this.breakLine();
        System.out.println("Hello! I'm Blob");
        System.out.println("What can I do for you?");
        this.breakLine();
    }

    /**
     * Exits the conversation.
     */
    public void exit() {
        this.active = false;
    }

    /**
     * Maintains constant conversation with user.
     */
    public void converse() {
        while (this.active) {
            String action = human.nextLine();
            this.breakLine();
            parser.evaluateAction(this, tasklist, action);
        }
    }
}
