package chatbot;

import todo.*;

import java.util.Scanner;

/**
 * Main entrypoint to the chatbot "Bee"
 *
 * @author celeschai
 * @version 1.0 20 Aug 2023
 */
public class Bee {
    /** Print out "Bee" logo in ASCII art */
    private static final String logo = " ____\n"
            + "|  _ \\  ___   ___ \n"
            + "| |_/  / _ \\ / _ \\\n"
            + "| |_\\ |  __/|  __/ \n"
            + "|____/ \\___| \\___|\n";

    public static void main(String[] args) {
        // Instantiates a todolist
        TodoList todoList = new TodoList();

        // Welcome user
        System.out.println(logo);
        System.out.println(FormatString.printBtnLines("Hello, I'm Bee! What can I do for you?"));

        // Scan for user input
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            // Case-insensitive input
            String next = sc.nextLine().toLowerCase();

            // Exit program if user inputs bye
            if (next.matches("bye.*")) {
                System.out.println(FormatString.printBtnLines("Bye~ Hope to see you again soon!"));
                break;

                // Prompt user for input if no input or input only has whitespaces
            } else if (next.isEmpty() || next.matches("\\s+")) {
                System.out.println(FormatString.printBtnLines("Hey! Say something."));

                // List tasks in todoList
            } else if (next.equalsIgnoreCase("list")) {
                System.out.println(FormatString.printBtnLines(
                        "Here are the tasks in your list:\n".concat(todoList.toString())));

                // Parse input from user to get task number
            } else if (next.matches("mark .*")) {
                String[] taskIndex = next.split(" ");
                int index = Integer.parseInt(taskIndex[1]);

                // Mark task as done
                if (todoList.isTaskExist(index)) {
                    todoList.markTaskAsDone(index);

                    System.out.println(
                            FormatString.printBtnLines(
                                    String.format(
                                            "Nice! I've marked this task as done:\n    %s",
                                            todoList.getTask(index))));

                    // Warn user of invalid task specified
                } else {
                    System.out.println(
                            FormatString.printBtnLines(
                                    "Hey! This task does not exist.\n" +
                                            "Type 'list' to see what tasks you have"));
                }

                // Parse input from user to get task number
            } else if (next.matches("unmark .*")) {
                String[] taskIndex = next.split(" ");
                int index = Integer.parseInt(taskIndex[1]);

                // Mark task as incomplete
                if (todoList.isTaskExist(index)) {
                    todoList.markTaskAsIncomplete(index);

                    System.out.println(
                            FormatString.printBtnLines(
                                    String.format(
                                            "OK, I've marked this task as not done yet:\n    %s",
                                            todoList.getTask(index))));

                    // Warn user of invalid task specified
                } else {
                    System.out.println(
                            FormatString.printBtnLines(
                                    "Hey! This task does not exist.\n" +
                                            "Type 'list' to see what tasks you have"));
                }

                // Echo user input
            } else {
                todoList.addTask(new Task(next));
                System.out.println(FormatString.printBtnLines(String.format("added: \"%s\"", next)));
            }
        }
    }
}
