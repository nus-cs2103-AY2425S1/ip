import tasks.Task;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the chatbot, responsible for parsing input and generating output.
 */
public class ChatBot {
    /**
     * The tasks handled by the application.
     */
    private Tasks tasks;

    /**
     * Constructor for a chatbot.
     *
     * @param path The path of the file to load saved tasks from.
     */
    public ChatBot(String path) throws IOException {
        this.tasks = new Tasks(path);
    }

    /**
     * Formats a message according to a standardized format.
     *
     * @param msg The message to format.
     * @return The formatted message.
     */
    private String formatMessage(String msg) {
        return "___________________________________________________________\n"
                + msg
                + "\n___________________________________________________________\n";
    }

    /**
     * Parses an input message, performs the command, and returns the response message.
     *
     * @param input The input message.
     * @return The response message.
     */
    public String input(String input) {
        if (Objects.equals(input, "")) {
            return "";
        }

        String response;
        String command = input.split(" ")[0];
        try {
            switch (command) {
            case "bye":
                response = this.exit();
                break;
            case "list":
                response = this.list();
                break;
            case "mark":
                response = this.mark(input);
                break;
            case "unmark":
                response = this.unmark(input);
                break;
            case "todo":
                response = this.todo(input);
                break;
            case "deadline": {
                response = this.deadline(input);
                break;
            }
            case "event": {
                response = this.event(input);
                break;
            }
            case "delete": {
                response = this.delete(input);
                break;
            }
            default:
                throw new InvalidInputException("I'm sorry, I did not understand your message.");
            }
        } catch (InvalidInputException e) {
            response = e.toString();
        } catch (IOException e) {
            response = "Oh no! Something wrong occurred while reading or saving your tasks.";
        }

        return this.formatMessage(response);
    }

    /**
     * Returns the greeting message.
     * @return The greeting message.
     */
    public String greet() {
        return this.formatMessage(" Hello! I'm Bob\n"
                + " What can I do for you?");
    }

    /**
     * Returns the exit message.
     * @return The exit message.
     */
    public String exit() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Returns the response message for a new task command.
     *
     * @param task The added task.
     * @return The response message.
     */
    private String add(Task task) {
        return String.format("""
                 Got it. I've added this task:
                    %s
                 Now you have %d tasks in the list.""",
                task.toString(), this.tasks.size());
    }

    /**
     * Parses a to-do command, adds a new to-do task to the list and returns the response message.
     * @param input the to-do command.
     * @return The response message.
     */
    public String todo(String input) throws IOException {
        String name = input.substring(5);

        Task task = this.tasks.todo(name);

        return add(task);
    }

    /**
     * Parses a deadline command, adds a new deadline task to the list and returns the response message.
     * @param input The deadline command.
     * @return The response message.
     * @throws InvalidInputException If the deadline was not specified.
     */
    public String deadline(String input) throws InvalidInputException, IOException {
        Matcher matcher = Pattern.compile("^deadline (.*) /by (.*)$").matcher(input);
        if (!matcher.find()) {
            throw new InvalidInputException("Please specify the deadline using \"/by\".");
        }

        String name = matcher.group(1);
        String deadline = matcher.group(2);

        Task task = this.tasks.deadline(name, deadline);

        return add(task);
    }

    /**
     * Parses an event command, adds a new event task to the list and returns the response message.
     * @param input The event command.
     * @return The response message.
     * @throws InvalidInputException If the start and/or end of the event was not specified.
     */
    public String event(String input) throws InvalidInputException, IOException {
        Matcher matcher = Pattern.compile("^event (.*) /from (.*) /to (.*)$").matcher(input);
        if (!matcher.find()) {
            throw new InvalidInputException("Please specify the timing using \"/from\" and \"/to\".");
        }

        String name = matcher.group(1);
        String start = matcher.group(2);
        String end = matcher.group(3);

        Task task = this.tasks.event(name, start, end);

        return add(task);
    }

    /**
     * Parses a delete command, removes a task from the list and returns the response message.
     * @param input The delete command.
     * @return The response message.
     * @throws InvalidInputException If no item number was specified or the task with the specified item number does not exist.
     */
    public String delete(String input) throws InvalidInputException, IOException {
        Matcher matcher = Pattern.compile("^delete (\\d*)$").matcher(input);
        if (matcher.find()) {
            throw new InvalidInputException("Please specify which task to delete.");
        }

        int itemNum = Integer.parseInt(matcher.group(1));

        Task task = this.tasks.delete(itemNum);

        return String.format("""
                Noted. I've removed this task:
                    %s
                Now you have %d tasks in the list.
               """, task.toString(), this.tasks.size());
    }

    /**
     * Parses a mark command, marks the task, and returns the response message.
     * @param input The mark command.
     * @return The response message.
     * @throws InvalidInputException If no item number was specified or the task with the specified item number does not exist.
     */
    public String mark(String input) throws InvalidInputException, IOException {
        Matcher matcher = Pattern.compile("^mark (\\d*)$").matcher(input);

        if (!matcher.find()) {
            throw new InvalidInputException("Please specify which task to mark.");
        }

        int itemNum = Integer.parseInt(matcher.group(1));

        Task task = this.tasks.mark(itemNum, true);

        return " Nice! I've marked this task as done:\n   " + task;
    }

    /**
     * Parses an unmark command, unmarks the task, and returns the response message.
     * @param input The unmark command.
     * @return The response message.
     * @throws InvalidInputException If no item number was specified or the task with the specified item number does not exist.
     */
    public String unmark(String input) throws InvalidInputException, IOException {
        Matcher matcher = Pattern.compile("^unmark (\\d*)$").matcher(input);

        if (!matcher.find()) {
            throw new InvalidInputException("Please specify which task to unmark.");
        }

        int itemNum = Integer.parseInt(matcher.group(1));

        Task task = this.tasks.mark(itemNum, false);

        return " OK, I've marked this task as not done yet:\n   " + task;
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public String list() {
        StringBuilder listMsg = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            try {
                listMsg
                        .append("\n ")
                        .append(i)
                        .append(". ")
                        .append(tasks.get(i));
            } catch (Exception e) {
                return e.toString();
            }
        }
        return listMsg.toString();
    }
}