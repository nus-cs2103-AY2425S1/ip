package ipman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser class
 * @author miloaisdino
 */
public class Parser {
    private final ArrayList<Task> list;
    private final Storage db;
    private final Ui ui;

    /**
     * Constructor
     *
     * @param list Collection of tasks
     * @param db   Storage handler
     * @param ui   Display output engine
     */
    public Parser(ArrayList<Task> list, Storage db, Ui ui) {
        this.list = list;
        this.db = db;
        this.ui = ui;
    }

    /**
     * Sends text to parseLine method line by line
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
                ui.addToBuffer(ce.getMessage());
            } catch (Exception e) {
                ui.addToBuffer("Unknown error: " + e.getMessage());
            } finally {
                if (!isSaved) {
                    ui.outputBuffer();
                } else {
                    ui.flushBuffer();
                }
            }
        }
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
            ui.printSeparator();
            System.exit(0);
        }
        String command = line.split(" ")[0];
        switch (command) {
        case "list": {
            ui.addToBuffer("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                ui.addToBuffer((i + 1) + ". " + list.get(i));
            }
            break;
        }
        case "find": {
            if (!line.contains(" ")) {
                throw new CommandException("The search string must not be empty.");
            }
            String query = line.split(" ", 2)[1];
            ui.addToBuffer("Here are the matching tasks in your list:");
            int displayIndex = 1;
            for (Task task : list) {
                if (String.valueOf(task).contains(query)) {
                    ui.addToBuffer((displayIndex++) + ". " + task);
                }
            }
            break;
        }
        case "delete": {
            int idx = Integer.parseInt(line.split(" ")[1]) - 1;
            if (idx < 0 || idx >= list.size()) {
                throw new CommandException("The item to delete does not exist.");
            }
            ui.addToBuffer("Noted. I've removed this task:");
            ui.addToBuffer(String.valueOf(list.get(idx)));
            list.remove(idx);
            ui.addToBuffer("Now you have " + list.size() + " tasks in the list.");
            break;
        }
        case "unmark": {
            int idx = Integer.parseInt(line.split(" ")[1]) - 1;
            if (idx < 0 || idx >= list.size()) {
                return;
            }
            ui.addToBuffer("OK, I've marked this task as not done yet:");
            ui.addToBuffer(list.get(idx).unmarkStatus());
            break;
        }
        case "mark": {
            int idx = Integer.parseInt(line.split(" ")[1]) - 1;
            if (idx < 0 || idx >= list.size()) {
                return;
            }
            ui.addToBuffer("Nice! I've marked this task as done:");
            ui.addToBuffer(list.get(idx).markStatus());
            break;
        }
        case "todo": {
            if (!line.contains(" ")) {
                throw new CommandException("The description of the todo must not be empty.");
            }
            String desc = line.split(" ", 2)[1];
            list.add(new Todo(desc));
            break;
        }
        case "deadline": {
            if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                throw new CommandException("The description of the deadline must not be empty.");
            }
            String[] tail = line.split(" ", 2)[1].split(" /by ");
            list.add(new Deadline(tail[0], tail[1]));
            break;
        }
        case "event": {
            if (!line.contains(" ") || line.split(" ", 2)[1].isEmpty()) {
                throw new CommandException("The description of the event must not be empty.");
            }
            String desc = line.split(" ", 2)[1].split(" /from ")[0];
            String from = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[0];
            String to = line.split(" ", 2)[1].split(" /from ")[1].split(" /to ")[1];
            list.add(new Event(desc, from, to));
            break;
        }
        case "":
            break;
        default: {
            throw new CommandException("Invalid command");
        }
        }

        if (Arrays.asList("todo", "deadline", "event").contains(line.split(" ")[0])) {
            ui.addToBuffer("Got it. I've added this task:");
            ui.addToBuffer(String.valueOf(list.get(list.size() - 1)));
            ui.addToBuffer("Now you have " + list.size() + " tasks in the list.");
        }
    }
}
