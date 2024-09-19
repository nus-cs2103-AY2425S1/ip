package tissue;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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
        return INDENT + "Here are the tasks in your list:\n" + taskList;
    }

    private String markResponse() {
        try {
            Task task = taskList.retrieveTask(parser.retrieveNextInt() - 1).markTask();
            return INDENT + "Nice! I've marked this task as done:\n" + INDENT + " " + task;
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            return "Please enter a valid number.";
        }

    }

    private String unmarkResponse() {
        try {
            Task task = taskList.retrieveTask(parser.retrieveNextInt() - 1).unmarkTask();
            return INDENT
                    + "OK, I've marked this task as not done yet:\n"
                    + INDENT
                    + "  "
                    + task;
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            return "Please enter a valid number.";
        }
    }

    private String deleteResponse() {
        try {
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
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            return "Please enter a valid number.";
        } catch (IOException e) {
            return "My apologies there was an error deleting your task.";
        }

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
        case "help" -> {
            return help();
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
                        + (taskList.size() + 1)
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
        default -> {
            return "Invalid input. Type 'help' to show the list of commands.";
        }
        }
    }

    private String storeToDo(String messageTemplate) {
        try {
            String item = parser.retrieveUntilNextLine();
            Task task = new ToDo(false, item);
            storage.save(task);
            taskList.add(task);
            return String.format(messageTemplate, task);
        } catch (NoSuchElementException e) {
            return "Description of TODO cannot be empty.";
        } catch (IOException e) {
            return "Sorry there was an error saving your task";
        }
    }

    private String storeDeadline(String messageTemplate) {
        try {
            String item = parser.scanUntil("/by");
            String by = parser.retrieveNextString();
            Task task = new Deadline(false, item, by);
            storage.save(task);
            taskList.add(task);
            return String.format(messageTemplate, task);

        } catch (NoSuchElementException e) {
            return "Please check your input format. Type 'help' for more info.";
        } catch (DateTimeParseException d) {
            return "OOPS! Please enter a date in the format YYYY-MM-DD";
        } catch (IOException e) {
            return "Sorry there was an error saving your task";
        }
    }

    private String storeEvent(String messageTemplate) {
        try {
            String item = parser.scanUntil("/from");
            String from = parser.scanUntil("/to");
            String to = parser.retrieveNextString();
            Task task = new Event(false, item, from, to);
            storage.save(task);
            taskList.add(task);
            return String.format(messageTemplate, task);
        } catch (NoSuchElementException e) {
            return "Please check your input format. Type 'help' for more info.";
        } catch (IOException e) {
            return "Sorry there was an error saving your task";
        }
    }

    private String help() {
        return """
                The available commands are
                todo [item]
                deadline [item] /by [date]
                event [item] /from [date] /to [date]
                mark [number]
                unmark [number]
                list
                delete [number]
                """;
    }
}
