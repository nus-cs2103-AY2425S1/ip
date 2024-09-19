package ipman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parses user input and executes commands.
 * This class interacts with the task list, storage, and user interface.
 * It processes commands to manage tasks and handles input from various sources.
 *
 * @author miloaisdino
 */
public class Parser {
    private final ArrayList<Task> taskList;
    private final Storage db;
    private final TextUi textUi;

    /**
     * Constructs a Parser with the specified task list, storage, and user interface.
     *
     * @param taskList The list of tasks.
     * @param db The storage for saving and loading tasks.
     * @param textUi The user interface for interacting with the user.
     */
    public Parser(ArrayList<Task> taskList, Storage db, TextUi textUi) {
        this.taskList = taskList;
        this.db = db;
        this.textUi = textUi;
    }

    /**
     * Reads input from the specified scanner and processes each line.
     * If reading from saved state, it skips adding entries to storage.
     *
     * @param scanner Specified scanner
     * @param isSaved True when reading from state history
     */
    public void parseFromScanner(Scanner scanner, boolean isSaved) {
        while (scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine();
                parseLine(line);
                if (!isSaved && !line.split(" ")[0].equals("list")) {
                    db.addEntry(line);
                }
            } catch (CommandException ce) {
                textUi.addToBuffer(ce.getMessage());
            } catch (Exception e) {
                textUi.addToBuffer("Unknown error: " + e.getMessage());
            } finally {
                if (!isSaved) {
                    textUi.printBuffer();
                } else {
                    textUi.flushBuffer();
                }
            }
        }
    }

    /**
     * Parses input from the user interface and processes each line.
     * It adds entries to storage when necessary.
     *
     * @param line The text command from the user interface
     * @return The output buffer content
     */
    public String parseFromUi(String line) {
        try {
            parseLine(line);
            if (!line.split(" ")[0].equals("list")) {
                db.addEntry(line);
            }
        } catch (CommandException ce) {
            textUi.addToBuffer(ce.getMessage());
        } catch (Exception e) {
            textUi.addToBuffer("Unknown error: " + e.getMessage());
        }
        return textUi.outputBuffer();
    }

    /**
     * Parses a line and runs actions
     *
     * @param line The text command in full
     * @throws CommandException Error when syntax error
     */
    public void parseLine(String line) throws CommandException {
        if (line.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            textUi.printSeparator();
            System.exit(0);
        }
        String commandName = line.split(" ")[0];
        Commands command = new Commands(textUi, taskList, line);

        switch (commandName) {
        case "list": {
            command.list();
            break;
        }
        case "find": {
            command.find();
            break;
        }
        case "delete": {
            command.delete();
            break;
        }
        case "unmark": {
            command.unmark();
            break;
        }
        case "mark": {
            command.mark();
            break;
        }
        case "todo": {
            command.todo();
            break;
        }
        case "deadline": {
            command.deadline();
            break;
        }
        case "event": {
            command.event();
            break;
        }
        case "tag": {
            command.tag();
            break;
        }
        case "untag": {
            command.untag();
            break;
        }
        case "":
            break;
        default: {
            throw new CommandException("Invalid command");
        }
        }
    }
}
