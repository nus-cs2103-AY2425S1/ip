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
        String command = line.split(" ")[0];
        switch (command) {
        case "list": {
            textUi.addToBuffer("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                textUi.addToBuffer((i + 1) + ". " + taskList.get(i));
            }
            break;
        }
        case "find": {
            if (!line.contains(" ")) {
                throw new CommandException("The search string must not be empty.");
            }
            String query = line.split(" ", 2)[1];
            textUi.addToBuffer("Here are the matching tasks in your list:");
            int displayIndex = 1;
            for (Task task : taskList) {
                if (String.valueOf(task).contains(query)) {
                    textUi.addToBuffer((displayIndex++) + ". " + task);
                }
            }
            break;
        }
        case "delete": {
            int idx = Integer.parseInt(line.split(" ")[1]) - 1;
            if (idx < 0 || idx >= taskList.size()) {
                throw new CommandException("The item to delete does not exist.");
            }
            textUi.addToBuffer("Noted. I've removed this task:");
            textUi.addToBuffer(String.valueOf(taskList.get(idx)));
            taskList.remove(idx);
            textUi.addToBuffer("Now you have " + taskList.size() + " tasks in the list.");
            break;
        }
        case "unmark": {
            int idx = Integer.parseInt(line.split(" ")[1]) - 1;
            if (idx < 0 || idx >= taskList.size()) {
                return;
            }
            textUi.addToBuffer("OK, I've marked this task as not done yet:");
            textUi.addToBuffer(taskList.get(idx).unmarkStatus());
            break;
        }
        case "mark": {
            int idx = Integer.parseInt(line.split(" ")[1]) - 1;
            if (idx < 0 || idx >= taskList.size()) {
                return;
            }
            textUi.addToBuffer("Nice! I've marked this task as done:");
            textUi.addToBuffer(taskList.get(idx).markStatus());
            break;
        }
        case "todo": {
            if (!line.contains(" ")) {
                throw new CommandException("The description of the todo must not be empty.");
            }
            String desc = line.split(" ", 2)[1];
            taskList.add(new Todo(desc));
            break;
        }
        case "deadline": {
            if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                throw new CommandException("The description of the deadline must not be empty.");
            }
            String[] tail = line.split(" ", 2)[1].split(" /by ");
            taskList.add(new Deadline(tail[0], tail[1]));
            break;
        }
        case "event": {
            if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                throw new CommandException("The description of the event must not be empty.");
            }
            String desc = line.split(" ", 2)[1].split(" /from ")[0];
            String from = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[0];
            String to = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[1];
            taskList.add(new Event(desc, from, to));
            break;
        }
        case "":
            break;
        default: {
            throw new CommandException("Invalid command");
        }
        }

        if (Arrays.asList("todo", "deadline", "event").contains(line.split(" ")[0])) {
            textUi.addToBuffer("Got it. I've added this task:");
            textUi.addToBuffer(String.valueOf(taskList.get(taskList.size() - 1)));
            textUi.addToBuffer("Now you have " + taskList.size() + " tasks in the list.");
        }
    }
}
