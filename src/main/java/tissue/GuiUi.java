package tissue;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import tissue.parse.Parser;
import tissue.task.Deadline;
import tissue.task.Event;
import tissue.task.Task;
import tissue.task.ToDo;

/**
 * The main class to display all messages on the GUI.
 */
public class GuiUi {
    private static final String LINE =
            "--------------------------------------------------------------";
    private static final String INDENT = "       ";
    private final Parser parser;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor to initialize the required members.
     */
    public GuiUi(Parser parser, TaskList taskList, Storage storage) {
        assert parser != null : "Parser cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        this.parser = parser;
        this.taskList = taskList;
        this.storage = storage;
    }

    public String getGreetingMessage() {
        return "Hello! I'm Tissue\n" + "What can I do for you?";
    }

    private String listResponse() {
        return INDENT + "Here are the tasks in your list:\n" + taskList.toString();
    }

    private String markResponse() {
        Task task = taskList.retrieveTask(parser.retrieveNextInt() - 1).markTask();
        return INDENT + "Nice! I've marked this task as done:\n" + INDENT + " " + task;
    }

    private String unmarkResponse() {
        Task task = taskList.retrieveTask(parser.retrieveNextInt() - 1).unmarkTask();
        return INDENT
                + "OK, I've marked this task as not done yet:\n"
                + INDENT
                + "  "
                + task;
    }

    private String deleteResponse() {
        int line = parser.retrieveNextInt();
        Task task = taskList.deleteTask(line);
        storage.delete(line);
        return INDENT
                + "Noted. I've removed this task:\n"
                + INDENT
                + "  "
                + task
                + "\n"
                + INDENT
                + "Now you have "
                + taskList.size()
                + " tasks in the list.";
    }

    private String findResponse() {
        ArrayList<Task> matches = taskList.searchKeyword(parser.retrieveNextString());
        String s = INDENT + "Here are the matching tasks!\n";
        for (Task task : matches) {
            s = s + INDENT + task + "\n";
        }
        return s;
    }

    public String getResponse(String input) {
        parser.setScanner(new Scanner(input));
        input = parser.retrieveNextString();
        switch (input) {
        case "list" -> {
            return listResponse();
        }
        case "mark" -> {
            return markResponse();
        }
        case "unmark" -> {
            return unmarkResponse();
        }
        case "delete" -> {
            return deleteResponse();
        }
        case "find" -> {
            return findResponse();
        }
        default -> {
            return storeTask(input);
        }
        }
    }


    private String storeTask(String input) {
        String messageTemplate =
                INDENT
                        + "Got it. I've added this task:\n"
                        + INDENT
                        + "  "
                        + "%s"
                        + "\n"
                        + INDENT
                        + "Now you have "
                        + taskList.size()
                        + " tasks in the list.";
        switch (input) {
        case "todo" -> {
            return storeToDo(messageTemplate);
        }
        case "deadline" -> {
            return storeDeadline(messageTemplate);
        }
        case "event" -> {
            return storeEvent(messageTemplate);
        }
        case "help" -> {
            return help();
        }
        default -> {
            return "Invalid input. Type 'help' to show the list of commands.";
        }
        }
    }

    private String storeToDo(String messageTemplate) {
        String item = parser.retrieveNextString();
        if (item.isEmpty()) {
            return "Description of TODO cannot be empty.";
        } else {
            Task task = new ToDo(false, item);
            storage.save(task);
            taskList.add(task);
            return String.format(messageTemplate, task);
        }
    }

    private String storeDeadline(String messageTemplate) {
        String item = parser.scanUntil("/by");
        String by = parser.retrieveNextString().strip();
        try {
            Task task = new Deadline(false, item, by);
            storage.save(task);
            taskList.add(task);
            return String.format(messageTemplate, task);

        } catch (DateTimeParseException d) {
            return "OOPS! Please enter a date in the format YYYY-MM-DD";
        }
    }

    private String storeEvent(String messageTemplate) {
        String item = parser.scanUntil("/from");
        String from = parser.scanUntil("/to");
        String to = parser.retrieveNextString().strip();
        Task task = new Event(false, item, from, to);
        storage.save(task);
        taskList.add(task);
        return String.format(messageTemplate, task);
    }

    private String help() {
        return """
                The available commands are
                todo [item]
                deadline [item] /by [date]
                event [item] /from [date] /to [date]
                """;
    }
}
