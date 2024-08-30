import java.util.Scanner;

import parser.BobException;
import parser.Parser;

import storage.Storage;

import task.TaskList;

import ui.Ui;


/**
 * Bob program keeps track of the users ToDos, Events, Deadlines.
 * Remembers past tasks.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Bob() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.parser = new Parser();
        ui.introBobUi();
    }

    /**
     * Exits the program. Saves the task into a text file.
     */
    public void exit() {
        ui.exitBobUi();
        this.tasks.saveTask(this.storage);
    }

    /**
     * Starts the program by loading current tasks and then receives input from uses.
     */
    public void start() {
        // Load task
        this.storage.loadTasks(this.tasks);

        // For others
        Scanner s2 = new Scanner(System.in);
        String message = "";
        do {
            message = s2.nextLine().trim();
        } while (receiveMessage(message));
    }

    /**
     * Receives input and sends to the parser for further processing.
     * @param message Message of user input
     * @return Whether Bob should still be active
     */
    public boolean receiveMessage(String message) {
        String command = message.split(" ")[0];
        try {
            switch (command) {
            case "bye":
                exit();
                return false;
            case "list":
                this.tasks.listTasks();
                break;
            case "mark":
                this.parser.markTaskParser(message, this.tasks);
                break;
            case "unmark":
                this.parser.unmarkTaskParser(message, this.tasks);
                break;
            case "delete":
                this.parser.deleteTaskParser(message, this.tasks);
                break;
            case "todo":
                this.parser.addToDoParser(message, this.tasks);
                break;
            case "deadline":
                this.parser.addDeadlineParser(message, this.tasks);
                break;
            case "event":
                this.parser.addEventParser(message, this.tasks);
                break;
            case "find":
                this.parser.findParser(message, this.tasks);
                break;
            default:
                throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (BobException e) {
            this.ui.errorMessageUi(e);
        }
        return true;
    }

    public static void main(String[] args) {
        Bob b = new Bob();
        b.start();
    }
}
