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
        } else {
            String[] split = command.split("/from | /to");
            description = split[0].replaceFirst("event", "").trim();
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
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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

    public void exitResponse() {
        System.out.println(bye);
        scanner.close();
    }






}
