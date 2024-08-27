package jag;

import java.util.Scanner;

/**
 * Represents an instance of User Interface interactions
 * Purpose is to read messages / commands, respond appropriately,
 * and return values to be used for other Command instances e.g. "Event"
 */
public class Ui {
    String loadingError = "File not found :(";
    String dashed = "----------";
    Scanner scanner = new Scanner(System.in);
    String greetings = this.dashed + "\nHello! I'm jag.Jag What can I do for you?\n" + this.dashed;
    String bye = this.dashed + "\nBye. Hope to see you again soon!\n" + this.dashed;

    String command;
    public void showLoadingError() {
        System.out.println(this.loadingError);;
    }
    public void showError(String e) {
        System.out.println(this.dashed);
        System.out.println(e);
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

    public void showLine() {
        System.out.println(this.dashed);
    }

    public void showWelcome() {
        System.out.println(greetings);
        this.command = scanner.nextLine();
    }

    public String readCommand() {
        return this.command;
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
        command = scanner.nextLine();
    }

    /**
     * Returns the description of the task from user input
     *
     * @param type Type represents T, D, E, which helps to indicate
     *             the type of Task to be processed.
     * @return description of the task from the user input on a case
     *          by case basis, of T, D, E
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
        if (type == 'T') {
            // Response for ToDos
            System.out.println(this.dashed);
            System.out.println("Got it. I've added this task: ");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
            System.out.println(this.dashed);
            command = scanner.nextLine();
        } else if (type == 'D') {
            // Response for jag.Deadline
            System.out.println(this.dashed);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
            System.out.println(this.dashed);
            command = scanner.nextLine();
        } else {
            // Response for event
            System.out.println(this.dashed);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
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
        System.out.println(this.dashed);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
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
        System.out.println(this.dashed);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
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
        System.out.println(this.dashed);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
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
        if (foundTasks.size() != 0) {
            System.out.println(this.dashed);
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.getTask(i).toString());
            }
            System.out.println(this.dashed);
            command = scanner.nextLine();
        } else {
            System.out.println(this.dashed);
            System.out.println("Sorry we could not find any matching tasks in your list");
            System.out.println(this.dashed);
        }
    }


    /**
     * Displays the response for exiting the chatbot
     * and to close the instance of the scanner to end the application
     */

    public void exitResponse() {
        System.out.println(bye);
        scanner.close();
    }









}
