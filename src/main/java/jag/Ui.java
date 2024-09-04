package jag;

import java.util.Scanner;

/**
 * Represents an instance of User Interface interactions
 * Purpose is to read messages / commands, respond appropriately,
 * and return values to be used for other Command instances e.g. "Event"
 */
public class Ui {
    private String loadingError = "File not found :(";
    private static String dashed = "----------";
    private Scanner scanner = new Scanner(System.in);
    private static String greetings = dashed + "\nHello! I'm Jag What can I do for you?\n" + dashed;
    private String bye = dashed + "\nBye. Hope to see you again soon!\n" + dashed;
    private String response = "";

    private String command;
    public void showLoadingError() {
        System.out.println(this.loadingError);;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public static String getGreetings() {
        return Ui.greetings;
    }

    public void setResponse(String response) {
        this.response = this.dashed + "\n" + response + "\n" + this.dashed;
    }

    public String getResponse() {
        return this.response;
    }

    /**
     * Prints out the error message in the case of an exception caught
     *
     * @param e String representation of the error message
     */
    public void showError(String e) {
        System.out.println(this.dashed);
        System.out.println(e);
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

    public void showLine() {
        System.out.println(dashed);
    }

    /**
     * Prints out a greeting message for the user
     */
    public void showWelcome() {
        System.out.println(greetings);
        this.command = scanner.nextLine();
    }

    public String readCommand() {
        return this.command;
    }


    /**
     * Returns the description of the task from user input
     *
     * @param type Type represents T, D, E, F, which helps to indicate
     *             the type of Task to be processed.
     * @return description of the task from the user input on a case
     *          by case basis, of T, D, E, F
     */
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
        } else {
            String[] split = command.split("find");
            description = split[1].trim();
        }
        return description;
    }

    /**
     * Returns a String representation of the deadline from user input
     * for a deadline task
     *
     * @return a String of the deadline set by the user for a deadline task
     */
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
    public void list(String response) {
        System.out.println(this.dashed);
        System.out.println(response);
        System.out.println(this.dashed);
        setResponse(response);
        command = scanner.nextLine();
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
    public void addedResponse(char type, Task task, TaskList tasks) {
        String response = "Got it. I've added this task: "
                + "\n" + task.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list";
        setResponse(response);
        if (type == 'T') {
            // Response for ToDos
            System.out.println(this.dashed);
            System.out.println(response);
            System.out.println(this.dashed);
            command = scanner.nextLine();
        } else if (type == 'D') {
            // Response for jag.Deadline
            System.out.println(this.dashed);
            System.out.println(response);
            System.out.println(this.dashed);
            command = scanner.nextLine();
        } else {
            // Response for event
            System.out.println(this.dashed);
            System.out.println(response);
            System.out.println(this.dashed);
            command = scanner.nextLine();
        }

    }

    /**
     * Displays the right response to the user that a task has been deleted
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     * @param size Represents the updated size of the task list after the deletion
     */
    public void deleteResponse(Task task, int size) {
        String response = "Noted. I've removed this task:"
                + "\n" + task.toString()
                + "\n" + "Now you have " + size + " tasks in the list.";
        setResponse(response);
        System.out.println(this.dashed);
        System.out.println(response);
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

    /**
     * Displays the right response to the user that a task has been unmarked
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     */
    public void unmarkResponse(Task task) {
        String response = "OK, I've marked this task as not done yet:"
                + "\n" + task.toString();
        setResponse(response);
        System.out.println(this.dashed);
        System.out.println(response);
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

    /**
     * Displays the right response to the user that a task has been marked
     *
     * @param task Task instance that has been created, so that it's toString() method
     *             can be called upon
     */
    public void markResponse(Task task) {
        String response = "Nice! I've marked this task as done:"
                + "\n" + task.toString();
        setResponse(response);
        System.out.println(this.dashed);
        System.out.println(response);
        System.out.println(this.dashed);
        command = scanner.nextLine();
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
    public void findResponse(TaskList foundTasks) {
        String response;
        if (foundTasks.size() != 0) {
            System.out.println(this.dashed);
            System.out.println("Here are the matching tasks in your list: ");
            response = "Here are the matching tasks in your list: ";
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.getTask(i).toString());
                response += (i + 1) + ". " + foundTasks.getTask(i).toString();
            }
            System.out.println(this.dashed);
        } else {
            System.out.println(this.dashed);
            System.out.println("Sorry we could not find any matching tasks in your list");
            System.out.println(this.dashed);
            response = "Sorry we could not find any matching tasks in your list";
        }
        setResponse(response);
        command = scanner.nextLine();
    }


    /**
     * Displays the response for exiting the chatbot
     * and to close the instance of the scanner to end the application
     */
    public void exitResponse() {
        setResponse(bye);
        System.out.println(bye);
        scanner.close();
    }









}
