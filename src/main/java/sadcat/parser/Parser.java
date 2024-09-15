package duke.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.storage.Storage;
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
        Storage storage = Storage.getInstance();
        while (scan.hasNext()) {
            String[] input = preprocess(scan.nextLine());
            assert input.length == 2;
            String cmd = input[0];
            String args = input[1];
            try {
                switch (cmd) {
                case "bye":
                    scan.close();
                    return false;
                case "help":
                    printHelpMessage();
                    break;
                case "list":
                    taskList.printTaskList();
                    break;
                case "mark":
                    taskList.mark(args);
                    break;
                case "unmark":
                    taskList.unmark(args);
                    break;
                case "update":
                    taskList.updateTask(args);
                    break;
                case "savefile":
                    taskList.saveFile(args);
                    break;
                case "archive":
                    String archiveFile = storage.archiveTasks();
                    System.out.println("Tasks archived to: " + archiveFile);
                    System.out.println("Current task list has been cleared.");
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
            } catch (IOException e) {
                System.out.println("Error archiving tasks: " + e.getMessage());
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
        TaskList taskList = TaskList.getInstance();
        Storage storage = Storage.getInstance();

        ByteArrayOutputStream bstream = new ByteArrayOutputStream();
        PrintStream pstream = new PrintStream(bstream);
        PrintStream sysstream = System.out;
        System.setOut(pstream);

        String[] temp = preprocess(input);
        assert temp.length == 2;
        String cmd = temp[0];
        String args = temp[1];
        try {
            switch (cmd) {
            case "bye":
                System.setOut(sysstream);
                return "Bye. Hope to see you again soon!";
            case "help":
                printHelpMessage();
                break;
            case "list":
                taskList.printTaskList();
                break;
            case "update":
                taskList.updateTask(args);
                break;
            case "savefile":
                taskList.saveFile(args);
                break;
            case "archive":
                String archiveFile = storage.archiveTasks();
                System.out.println("Tasks archived to: " + archiveFile);
                System.out.println("Current task list has been cleared.");
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
            System.out.flush();
            System.setOut(sysstream);
            return bstream.toString();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Error archiving tasks: " + e.getMessage();
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



    /**
     * Prints a help message with available commands and their usage.
     */
    private void printHelpMessage() {
        System.out.println("Available commands:");
        System.out.println("1. todo <task description> - Create a todo task");
        System.out.println("2. deadline <task description> /by <date time> - Create a deadline task");
        System.out.println(
                "3. event <task description> /from <start date time> /to <end date time> - Create an event task");
        System.out.println("4. list - Display all tasks");
        System.out.println("5. mark <task number> - Mark a task as done");
        System.out.println("6. unmark <task number> - Mark a task as not done");
        System.out.println("7. delete <task number> - Delete a task");
        System.out.println("8. find <keyword> - Find tasks containing the keyword");
        System.out.println("9. update <task number> <new task type> <new task description> - Update an existing task");
        System.out.println("10. savefile <filename> - Switch to a different save file");
        System.out.println("11. help - Display this help message");
        System.out.println("12. bye - Exit the application");
    }
}
