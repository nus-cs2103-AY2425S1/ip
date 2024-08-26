package duke.parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Parser class for handling user input in the Duke application.
 * This class is responsible for processing user commands and interacting with the TaskList.
 */
public class Parser {

    private final Scanner scan;

    /**
     * Constructs a new Parser with the specified Scanner.
     *
     * @param scan the Scanner object to be used for reading user input
     */
    public Parser(Scanner scan) {
        this.scan = scan;
    }

    /**
     * Handles user input in a loop until the user decides to exit.
     * This method processes various commands such as list, mark, unmark, delete, and task creation.
     *
     * @return false if the user wants to exit, true otherwise
     */
    public boolean handleUserInput() {
        TaskList taskList = TaskList.getInstance();
        while (scan.hasNext()) {
            String[] input = preprocess(scan.nextLine());
            String cmd = input[0];
            String args = input[1];
            try {
                switch (cmd) {
                case "bye":
                    scan.close();
                    return false;
                case "list":
                    taskList.printTaskList();
                    break;
                case "mark":
                    taskList.mark(args);
                    break;
                case "unmark":
                    taskList.unmark(args);
                    break;
                case "find":
                    taskList.filter(args);
                    break;
                case "delete":
                    taskList.deleteTask(args);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    taskList.createTask(cmd, args);
                    break;
                default:
                    throw new DukeException("Invalid command provided.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    /**
     * Handles user input through the GUI, passed as a string.
     * This method processes various commands such as list, mark, unmark, delete, and task creation.
     *
     * @param input user input through the GUI
     *
     * @return string
     */
    public String handleGuiInput(String input) throws DukeException {
        TaskList tasklist = TaskList.getInstance();

        ByteArrayOutputStream bstream = new ByteArrayOutputStream();
        PrintStream pstream = new PrintStream(bstream);
        PrintStream sysstream = System.out;
        System.setOut(pstream);

        String[] temp = preprocess(input);
        String cmd = temp[0];
        String args = temp[1];
        try {
            switch (cmd) {
            case "bye":
                System.setOut(sysstream);
                return "Bye. Hope to see you again soon!";
            case "list":
                tasklist.printTaskList();
                break;
            case "unmark":
                tasklist.unmark(args);
                break;
            case "mark":
                tasklist.mark(args);
                break;
            case "todo":
            case "deadline":
            case "event":
                tasklist.createTask(cmd, args);
                break;
            case "delete":
                tasklist.deleteTask(args);
                break;
            case "find":
                tasklist.filter(args);
                break;
            default:
                throw new DukeException("Invalid command");
            }
            System.out.flush();
            System.setOut(sysstream);
            return bstream.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Preprocesses the input string by splitting it into command and arguments.
     * This method also trims whitespace and converts the command to lowercase.
     *
     * @param input the raw input string from the user
     * @return an array of two strings, where the first is the command (lowercase) and the second is the arguments
     */
    private String[] preprocess(String input) {
        String[] raw = input.split(" ", 2);
        String[] split = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            split[i] = raw[i].trim();
        }
        split[0] = split[0].toLowerCase();
        return split;
    }
}
