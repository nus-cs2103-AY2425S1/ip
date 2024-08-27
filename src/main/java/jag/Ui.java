package jag;

import java.util.Scanner;

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




    public void list(String response) {
        System.out.println(this.dashed);
        System.out.println(response);
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

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

    public String getBy() {
        String[] split = command.split("/by");
        return split[1].trim();
    }

    public String getFrom() {
        String[] split = command.split("/from | /to");
        return split[1].trim();
    }

    public String getTo() {
        String[] split = command.split("/from | /to");
        return split[2].trim();
    }

    public int getDeleteIndex() {
        char marker = command.charAt(command.length() - 1);
        int index = 0;

        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }
        return index;
    }

    public int getMark() {
        char marker = command.charAt(command.length() - 1);
        int index = 0;

        // Convert index character to a string
        if (Character.isDigit(marker)) {
            index = Integer.parseInt(Character.toString(marker));
        }
        return index;
    }

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

    public void deleteResponse(Task task, TaskList tasks, int size) {
        System.out.println(this.dashed);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

    public void unmarkResponse(Task task) {
        System.out.println(this.dashed);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(this.dashed);
        command = scanner.nextLine();
    }

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

    public void exitResponse() {
        System.out.println(bye);
        scanner.close();
    }








}
