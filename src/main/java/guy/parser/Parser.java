package guy.parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import guy.exception.GuyException;
import guy.tasks.TaskManager;

/**
 * Parser class to handle user input in the ThatOneGuy application.
 * Processes commands and interacts with the provided TaskManager instance.
 */
public class Parser {
    private final Scanner sc;

    /**
     * Creates a Parser object with the given Scanner.
     *
     * @param sc Scanner to be used within the instance
     */
    public Parser(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Handles user input until the user requests to quit.
     *
     * @return false if the user requests to quit, true otherwise
     */
    public boolean cmd() {
        TaskManager tm = TaskManager.getInstance();
        while (sc.hasNext()) {
            String[] input = splitCmd(sc.nextLine());
            assert input.length > 1;
            String cmd = input[0];
            String args = input[1];

            try {
                switch (cmd) {
                case "bye":
                    sc.close();
                    return false;
                case "list":
                    tm.list();
                    break;
                case "mark":
                    tm.markTask(args);
                    break;
                case "unmark":
                    tm.unmarkTask(args);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    tm.addTask(cmd, args);
                    break;
                case "delete":
                    tm.deleteTask(args);
                    break;
                case "find":
                    tm.findTask(args);
                    break;
                default:
                    throw new GuyException("Maybe put in an actual command next time, shitass.");
                }
            } catch (GuyException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    /**
     * Handles user input until the user requests to quit, but when the GUI is active.
     * @param input string
     * @return string
     */
    public String guiInput(String input) throws GuyException {
        TaskManager tm = TaskManager.getInstance();
        ByteArrayOutputStream bstream = new ByteArrayOutputStream();
        PrintStream pstream = new PrintStream(bstream);
        PrintStream sysstream = System.out;
        System.setOut(pstream);

        String[] temp = splitCmd(input);
        assert temp.length > 1;
        String cmd = temp[0];
        String args = temp[1];

        try {
            switch (cmd) {
            case "bye":
                sc.close();
                return "Whatever. Hope you never come back.";
            case "list":
                tm.list();
                break;
            case "mark":
                tm.markTask(args);
                break;
            case "unmark":
                tm.unmarkTask(args);
                break;
            case "todo":
            case "deadline":
            case "event":
                tm.addTask(cmd, args);
                break;
            case "delete":
                tm.deleteTask(args);
                break;
            case "find":
                tm.findTask(args);
                break;
            default:
                throw new GuyException("Maybe put in an actual command next time, shitass.");
            }
            System.out.flush();
            System.setOut(sysstream);
            return bstream.toString();
        } catch (GuyException e) {
            return e.getMessage();
        }
    }

    /**
     * Preprocesses the input string by splitting it into command and arguments.
     * Also trims whitespace and converts the command to lowercase.
     *
     * @param input input string from the user
     * @return an array of two strings, where the first element is the command and the second is the arguments
     */
    private String[] splitCmd(String input) {
        String[] raw = input.split(" ", 2);
        assert raw.length == 2;
        String[] output = {"", ""};
        for (int i = 0; i < raw.length; ++i) {
            output[i] = raw[i].trim();
        }
        output[0] = output[0].toLowerCase();
        return output;
    }
}
