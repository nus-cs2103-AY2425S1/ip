package chatbot;

import todo.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main entrypoint to the chatbot "Bee"
 *
 * @author celeschai
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
            try {
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
                        throw new TaskIndexException();
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
                        throw new TaskIndexException();
                    }

                // Parse input from user to get task number
                } else if (next.matches("delete .*")) {
                    String[] taskIndex = next.split(" ");
                    int index = Integer.parseInt(taskIndex[1]);

                    // Mark task as incomplete
                    if (todoList.isTaskExist(index)) {
                        Task deletedTask = todoList.removeTask(index);

                        ChatbotOutput.deleteTaskResponse(
                                deletedTask.toString(), todoList.getTotalNumOfTasks());

                    // Warn user of invalid task specified
                    } else {
                        throw new TaskIndexException();
                    }

                // Add todo
                } else if (next.matches("todo (.*)")) {
                    // Regular expression to capture todo name
                    Pattern pattern = Pattern.compile("todo (\\S.*)");
                    Matcher matcher = pattern.matcher(next);

                    // If match succeeds, obtain parameters
                    if (matcher.matches()) {
                        String name = matcher.group(1);
                        Todo todo = new Todo(name);
                        todoList.addTask(todo);
                        ChatbotOutput.addTaskResponse(
                                todo.toString(), todoList.getTotalNumOfTasks());

                    //Throw exception for invalid input
                    } else {
                        throw new TaskInputException("name", "todo");
                    }

                    // Add deadline
                } else if (next.matches("deadline (.*)")) {
                    // Regular expression to capture deadline name and /by parameter
                    Pattern pattern = Pattern.compile("deadline (\\S.*) /by (\\S.*)");
                    Matcher matcher = pattern.matcher(next);

                    // If match succeeds, obtain parameters
                    if (matcher.matches()) {
                        String name = matcher.group(1);
                        String byParam = matcher.group(2);

                        // Instantiate a Task object and add it to todolist
                        Deadline deadline = new Deadline(name, byParam);
                        todoList.addTask(deadline);
                        ChatbotOutput.addTaskResponse(deadline.toString(), todoList.getTotalNumOfTasks());

                    //Throw exception for invalid input
                    } else {
                        throw new TaskInputException("name, and due date", "deadline");
                    }

                    // Add event
                } else if (next.matches("event (.*)")) {
                    // Regular expression to capture event name, /from and /to parameters
                    Pattern pattern = Pattern.compile("event (\\S.*) /from (\\S.*) /to (\\S.*)");
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

                        //Throw exception for invalid input
                    } else {
                        throw new TaskInputException("name, start, and end time", "event");
                    }

                // TODO: display list of commands
                } else if (next.matches("help")) {
                    ChatbotOutput.printBtnLines("This function is still under development.");
                } else {
                    // Throw exception when nothing, whitespaces, or no name is provided
                    throw new BeeException("Say something helpful.");
            }

            // Exception message to prompt user for valid input
            } catch (BeeException e){
                ChatbotOutput.printBtnLines(e.getMessage());
            }
        }
    }
}
