import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import Exceptions.*;

public class Revir {
    static TaskList taskList;

    public static void main(String[] args) {
        taskList = new TaskList(Path.of("data", "tasks.txt"));
        String name = "Revir";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                handleCommand(input);
            } catch (IllegalCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid task index. Expected a number.");
            }
        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void handleCommand(String command) throws IllegalCommandException, InvalidFormatException {
        if (command.equals("bye")) {
            return;
        } else if (command.equals("list")) {
            // Print the user input list
            System.out.println("List:");
            String list = taskList.list();
            if (!list.isEmpty()) {
                System.out.println(list);
            }
        } else if (command.startsWith("mark ")) {
            // Mark a task as completed
            String taskIndexStr = command.substring(5);
            int taskIndex = Integer.parseInt(taskIndexStr);
            System.out.println(taskList.setCompleted(taskIndex, true));
        } else if (command.startsWith("unmark ")) {
            // Unmark a completed task
            String taskIndexStr = command.substring(7);
            int taskIndex = Integer.parseInt(taskIndexStr);
            System.out.println(taskList.setCompleted(taskIndex, false));
        } else if (command.startsWith("todo ")) {
            // Add a todo task
            String taskDescription = command.substring(5);
            if (taskDescription.isEmpty()) {
                throw new InvalidFormatException(Todo.format);
            }
            taskList.add(new Todo(taskDescription));
            System.out.println("Todo task added: " + taskDescription);
        } else if (command.startsWith("deadline ")) {
            // Add a deadline task
            String taskDetails = command.substring(9);
            String[] taskInfo = taskDetails.split(" /by ");
            if (taskInfo.length == 2) {
                String taskDescription = taskInfo[0];
                String deadline = taskInfo[1];
                taskList.add(new Deadline(taskDescription, deadline));
                System.out.println("Deadline task added: " + taskDescription + " (by: " + deadline + ")");
            } else {
                throw new InvalidFormatException(Deadline.format);
            }
        } else if (command.startsWith("event ")) {
            // Add an event task
            String taskDetails = command.substring(6);
            String[] taskInfo = taskDetails.split(" /from ");
            if (taskInfo.length == 2) {
                String taskDescription = taskInfo[0];
                String startDate = taskInfo[1].split(" /to ")[0];
                String endDate = taskInfo[1].split(" /to ")[1];
                taskList.add(new Event(taskDescription, startDate, endDate));
                System.out.println(
                        "Event task added: " + taskDescription + " (from: " + startDate + " to: " + endDate + ")");
            } else {
                throw new InvalidFormatException(Event.format);
            }
        } else if (command.startsWith("delete ")) {
            // Delete a task
            String taskIndexStr = command.substring(7);

            int taskIndex = Integer.parseInt(taskIndexStr);
            System.out.println(taskList.remove(taskIndex));
        } else {
            throw new IllegalCommandException(command);
        }
    }
}
