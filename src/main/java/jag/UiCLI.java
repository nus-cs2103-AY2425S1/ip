package jag;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents an instance of User Interface interactions
 * Purpose is to read messages / commands, respond appropriately,
 * and return values to be used for other Command instances e.g. "Event"
 */
public class UiCLI extends Ui {
    private String loadingError = "File not found :(";
    private static String dashed = "----------";
    private Scanner scanner = new Scanner(System.in);
    private static String greetings = dashed + "\nHello! I'm Jag What can I do for you?\n" + dashed;
    private String bye = dashed + "\nBye. Hope to see you again soon!\n" + dashed;
    private String response = "";

    private String command;

    private void readNextCommand() {
        command = scanner.nextLine();
        setCommand(command);
    }

    @Override
    public void showLoadingError() {
        System.out.println(this.loadingError);;
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }

    public static String getGreetings() {
        return UiCLI.greetings;
    }

    @Override
    public void setResponse(String response) {
        this.response = this.dashed + "\n" + response + "\n" + this.dashed;
    }

    @Override
    public String getResponse() {
        return this.response;
    }

    /**
     * Prints out the error message in the case of an exception caught
     *
     * @param e String representation of the error message
     */
    @Override
    public void showError(String e) {
        showLine();
        System.out.println(e);
        showLine();
        readNextCommand();
    }

    @Override
    public void showLine() {
        System.out.println(dashed);
    }

    /**
     * Prints out a greeting message for the user
     */
    @Override
    public void showWelcome() {
        System.out.println(greetings);
        readNextCommand();
    }

    @Override
    public String readCommand() {
        return this.command;
    }

    @Override
    public int getUpdateIndex() {
        String[] parts = this.command.split(" ");
        return Integer.parseInt(parts[1]);
    }


    /**
     * Returns the description of the task from user input
     *
     * @param type Type represents T, D, E, F, U, which helps to indicate
     *             the type of Task to be processed.
     * @return description of the task from the user input on a case
     *          by case basis, of T, D, E, F, U
     */
    @Override
    public String getDescription(char type) {
        String description = "";

        if (type == 'T') {
            String[] split = command.split("todo");
            description = split[1].trim();
        } else if (type == 'D') {
            String[] split = command.split("/by");
            description = split[0].replaceFirst("deadline", "").trim();
        } else if (type == 'E') {
            String[] split = command.split("/from | /to");
            description = split[0].replaceFirst("event", "").trim();
        } else if (type == 'F') {
            String[] split = command.split("find");
            description = split[1].trim();
        } else if (type == 'U') {
            description = Arrays.stream(command.split(" "))
                    .skip(2)
                    .takeWhile(part -> !part.startsWith("/"))
                    .collect(Collectors.joining(" "));
        } else {
            return "description not found";
        }
        return description;
    }

    /**
     * Returns a String representation of the deadline from user input
     * for a deadline task
     *
     * @return a String of the deadline set by the user for a deadline task
     */
    @Override
    public String getBy() {
        String[] split = command.split("/by");
        return split[1].trim();
    }

    /**
     * Returns a String representation of the starting date from user input
     * for an event
     *
     * @return a String of the starting date set by the user for an event
     */
    @Override
    public String getFrom() {
        String[] split = command.split("/from | /to");
        return split[1].trim();
    }

    /**
     * Returns a String representation of the end date from user input
     * for an event
     *
     * @return a String of the end date set by the user for an event
     */
    @Override
    public String getTo() {
        String[] split = command.split("/from | /to");
        return split[2].trim();
    }

    /**
     * Returns an Integer representing the index of the task to be deleted
     * in the stored TaskList
     *
     * @return an Integer representing the index of the task to be deleted
     *          in the stored TaskList
     */
    @Override
    public int getDeleteIndex() {
        char marker = command.charAt(command.length() - 1);
        int index = 0;

        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }
        return index;
    }

    /**
     * Returns an Integer representing the index of the task to be marked
     *  or unmarked in the stored TaskList
     *
     * @return an Integer representing the index of the task to be marked
     *          or unmarked in the stored TaskList
     */
    @Override
    public int getMark() {
        char marker = command.charAt(command.length() - 1);
        int index = 0;

        // Convert index character to a string
        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }
        return index;
    }

    /**
     * Responds by printing out the String response
     *
     * @param response Response that consist of all Tasks
     *                 that have been turned into Strings
     *                 from the List class
     */
    @Override
    public void list(String response) {
        showLine();
        System.out.println(response);
        showLine();
        setResponse(response);
        readNextCommand();
    }


    /**
     * Displays the right response to the user that a task has been added
     * based on the type of task
     *
     * @param type Type of task so that the right type of response can be displayed
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     * @param tasks The Instance of the TaskList so that the size of the list can be
     *              accessed and displayed to the user upon the addition
     */
    @Override
    public void addedResponse(char type, Task task, TaskList tasks) {
        String response = "Got it. I've added this task: "
                + "\n" + task.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list";
        setResponse(response);
        if (type == 'T') {
            // Response for ToDos
            showLine();
            System.out.println(response);
            showLine();
            readNextCommand();
        } else if (type == 'D') {
            // Response for jag.Deadline
            showLine();
            System.out.println(response);
            showLine();
            readNextCommand();
        } else {
            // Response for event
            showLine();
            System.out.println(response);
            showLine();
            readNextCommand();
        }

    }

    /**
     * Displays the right response to the user that a task has been deleted
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     * @param size Represents the updated size of the task list after the deletion
     */
    @Override
    public void deleteResponse(Task task, int size) {
        String response = "Noted. I've removed this task:"
                + "\n" + task.toString()
                + "\n" + "Now you have " + size + " tasks in the list.";
        setResponse(response);
        showLine();
        System.out.println(response);
        showLine();
        readNextCommand();
    }

    /**
     * Displays the right response to the user that a task has been unmarked
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     */
    @Override
    public void unmarkResponse(Task task) {
        String response = "OK, I've marked this task as not done yet:"
                + "\n" + task.toString();
        setResponse(response);
        showLine();
        System.out.println(response);
        showLine();
        readNextCommand();
    }
    /**
     * Displays the right response to the user that a task has been marked
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     */
    @Override
    public void markResponse(Task task) {
        String response = "Nice! I've marked this task as done:"
                + "\n" + task.toString();
        setResponse(response);
        showLine();
        System.out.println(response);
        showLine();
        readNextCommand();
    }


    /**
     * Display list of tasks that matches the description of the find
     * command in the saved list, if size of the list is 0, that means
     * there are no found tasks and a "sorry" message will be displayed
     *
     * @param foundTasks Represents an instance of a TaskList that
     *                   consists of all the found tasks that was
     *                   found by the FindCommand instance
     */
    @Override
    public void findResponse(TaskList foundTasks) {
        String response;
        if (foundTasks.size() != 0) {
            showLine();
            System.out.println("Here are the matching tasks in your list: ");
            response = "Here are the matching tasks in your list: ";
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.getTask(i).toString());
                response += (i + 1) + ". " + foundTasks.getTask(i).toString();
            }
            readNextCommand();
        } else {
            readNextCommand();
            System.out.println("Sorry we could not find any matching tasks in your list");
            readNextCommand();
            response = "Sorry we could not find any matching tasks in your list";
        }
        setResponse(response);
        readNextCommand();
    }

    /**
     * Displays the updated task
     * @param task Task instance that has been updated
     */
    @Override
    public void updateResponse(Task task) {
        String response = "The following task has been updated: \n" + task.toString();
        showLine();
        System.out.println(response);
        showLine();
        setResponse(response);
        readNextCommand();
    }


    /**
     * Displays the response for exiting the chatbot
     * and to close the instance of the scanner to end the application
     */
    @Override
    public void exitResponse() {
        setResponse(bye);
        System.out.println(bye);
        scanner.close();
    }









}
