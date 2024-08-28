package chatbot;

import task.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Main entrypoint to the chatbot "Bee"
 *
 * @author celeschai
 */
public class Bee {
    public static void main(String[] args) {
        // Instantiates a todolist
        TaskList taskList = new TaskList();

        // Welcome user
        System.out.println(Ui.LOGO);
        Ui.printBtnLines("Hello, I'm Bee! What can I do for you?");

        // Scan for user input
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String next = sc.nextLine();

                // Exit program if user inputs bye
                if (next.matches("bye.*")) {
                    Ui.printBtnLines("Bye~ Hope to see you again soon!");
                    break;

                // List tasks in taskList
                } else if (next.equalsIgnoreCase("list")) {
                    Ui.printBtnLines(
                            "Here are the tasks in your list:\n".concat(taskList.toString()));

                // Parse input from user to get task index
                } else if (next.matches("mark (.+)")) {
                    // Regular expression to capture task index
                    Pattern pattern = Pattern.compile("mark (\\d+)");
                    Matcher matcher = pattern.matcher(next);

                    // If match succeeds, obtain task index
                    if (matcher.matches()) {
                        String num = matcher.group(1);
                        int index = Integer.parseInt(num);

                        // Mark task as done
                        if (taskList.isTaskExist(index)) {
                            taskList.markTaskAsDone(index);

                            Ui.printBtnLines(
                                    String.format(
                                            "Nice! I've marked this task as done:\n    %s",
                                            taskList.getTask(index)));
                        }

                    // Warn user of invalid task specified
                    } else {
                        throw new TaskIndexException();
                    }

                // Parse input from user to get task index
                } else if (next.matches("unmark (.+)")) {
                    // Regular expression to capture task index
                    Pattern pattern = Pattern.compile("unmark (\\d+)");
                    Matcher matcher = pattern.matcher(next);

                    // If match succeeds, obtain task index
                    if (matcher.matches()) {
                        String num = matcher.group(1);
                        int index = Integer.parseInt(num);

                        // Mark task as incomplete
                        if (taskList.isTaskExist(index)) {
                            taskList.markTaskAsIncomplete(index);

                            Ui.printBtnLines(
                                    String.format(
                                            "OK, I've marked this task as not done yet:\n    %s",
                                            taskList.getTask(index)));
                        }

                    // Warn user of invalid task specified
                    } else {
                        throw new TaskIndexException();
                    }

                    // Parse input from user to get task index
                } else if (next.matches("delete (.+)")) {
                    // Regular expression to capture task index
                    Pattern pattern = Pattern.compile("delete (\\d+)");
                    Matcher matcher = pattern.matcher(next);

                    // If match succeeds, obtain task index
                    if (matcher.matches()) {
                        String num = matcher.group(1);
                        int index = Integer.parseInt(num);

                        // Delete task
                        if (taskList.isTaskExist(index)) {
                            Task deletedTask = taskList.removeTask(index);

                            Ui.deleteTaskResponse(
                                    deletedTask.toString(), taskList.getTotalNumOfTasks());
                    }
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
                        taskList.addTask(todo);
                        Ui.addTaskResponse(
                                todo.toString(), taskList.getTotalNumOfTasks());

                    //Throw exception for invalid input
                    } else {
                        throw new TaskInputException("name", "task");
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
                        taskList.addTask(deadline);
                        Ui.addTaskResponse(deadline.toString(), taskList.getTotalNumOfTasks());

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
                        taskList.addTask(event);
                        Ui.addTaskResponse(event.toString(), taskList.getTotalNumOfTasks());

                        //Throw exception for invalid input
                    } else {
                        throw new TaskInputException("name, start, and end time", "event");
                    }

                // TODO: display list of commands
                } else if (next.matches("help")) {
                    Ui.printBtnLines(Ui.help);

                } else {
                    // Throw exception when nothing, whitespaces, or no name is provided
                    throw new BeeException("Say something helpful.");
            }

            // Exception message to prompt user for valid input
            } catch (BeeException e){
                Ui.printBtnLines(e.getMessage());
            }
        }
    }
}
