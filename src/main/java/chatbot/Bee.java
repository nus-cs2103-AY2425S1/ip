package chatbot;

import todo.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main entrypoint to the chatbot "Bee"
 *
 * @author celeschai
 * @version 1.0 20 Aug 2023
 */
public class Bee {
    /**
     * Print out "Bee" logo in ASCII art
     */
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
        ChatbotOutput.printBtnLines("Hello, I'm Bee! What can I do for you?");

        // Scan for user input
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            // Case-insensitive input
            String next = sc.nextLine();

            // Exit program if user inputs bye
            if (next.matches("bye.*")) {
                ChatbotOutput.printBtnLines("Bye~ Hope to see you again soon!");
                break;

            // List tasks in todoList
            } else if (next.equalsIgnoreCase("list")) {
                ChatbotOutput.printBtnLines(
                        "Here are the tasks in your list:\n".concat(todoList.toString()));

            // Parse input from user to get task number
            } else if (next.matches("mark .*")) {
                String[] taskIndex = next.split(" ");
                int index = Integer.parseInt(taskIndex[1]);

                // Mark task as done
                if (todoList.isTaskExist(index)) {
                    todoList.markTaskAsDone(index);

                    ChatbotOutput.printBtnLines(
                            String.format(
                                    "Nice! I've marked this task as done:\n    %s",
                                    todoList.getTask(index)));

                // Warn user of invalid task specified
                } else {
                    ChatbotOutput.printBtnLines(
                            "Hey! This task does not exist.\n" +
                                    "Type 'list' to see what tasks you have");
                }

            // Parse input from user to get task number
            } else if (next.matches("unmark .*")) {
                String[] taskIndex = next.split(" ");
                int index = Integer.parseInt(taskIndex[1]);

                // Mark task as incomplete
                if (todoList.isTaskExist(index)) {
                    todoList.markTaskAsIncomplete(index);

                    ChatbotOutput.printBtnLines(
                            String.format(
                                    "OK, I've marked this task as not done yet:\n    %s",
                                    todoList.getTask(index)));

                // Warn user of invalid task specified
                } else {
                    ChatbotOutput.printBtnLines(
                            "Hey! This task does not exist.\n" +
                                    "Type 'list' to see what tasks you have");
                }

            // Add todo
            } else if (next.matches("todo .*")) {
                Todo todo = new Todo(next);
                todoList.addTask(todo);
                ChatbotOutput.addTaskResponse(todo.toString(), todoList.getTotalNumOfTasks());

            // Add deadline
            } else if (next.matches("deadline (.+) /by (.+)")) {
                // Regular expression to capture event name /from and /to parameters
                Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
                Matcher matcher = pattern.matcher(next);

                // If match succeeds, obtain parameters
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    String byParam = matcher.group(2);

                    // Instantiate a Task object and add it to todolist
                    Deadline deadline = new Deadline(name, byParam);
                    todoList.addTask(deadline);
                    ChatbotOutput.addTaskResponse(deadline.toString(), todoList.getTotalNumOfTasks());
                }

            // Add event
            } else if (next.matches("event (.+) /from (.+) /to (.+)")) {
                // Regular expression to capture event name /from and /to parameters
                Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
                Matcher matcher = pattern.matcher(next);

                // If match succeeds, obtain parameters
                if (matcher.matches()) {
                    String name = matcher.group(1);
                    String fromParam = matcher.group(2);
                    String toParam = matcher.group(3);

                    // Instantiate a Task object and add it to todolist
                    Event event = new Event(name, fromParam, toParam);
                    todoList.addTask(event);
                    ChatbotOutput.addTaskResponse(event.toString(), todoList.getTotalNumOfTasks());
                }

            // Prompt user for valid input
            // when nothing, whitespaces, or no name is provided
            } else {
                ChatbotOutput.printBtnLines("Hey! Say something helpful.");
            }
        }
    }
}
