import java.util.ArrayList;
import java.util.Scanner;
public class Downy {

    public static void main(String[] args) {
        // Divider for separating ChatBot from input messages
        String divider = "________________________________________\n";
        // Initialise scanner and taskList
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        // Track task count
        int taskCount = 0;
        System.out.println(divider + "Hello! I'm Downy.\nHow can I help?\n" + divider);

        // Loop for user inputs until program exits
        while (true) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];
            try {
                // For commands with no arguments
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    if (taskCount == 0) {
                        System.out.println(divider + "There are no tasks!\n" + divider);
                        continue;
                    }
                    System.out.printf(divider);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(divider);
                    continue;
                }

                // For commands with arguments (either number or string)
                try {
                    switch (command) {
                        // Mark a task to be complete
                        case "mark" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Mark command requires a task number.\n" +
                                                                   "   Usage: mark <taskNumber> ");
                            }
                            String remainder = parts[1];
                            try {
                                int taskNumber = Integer.parseInt(remainder);
                                Task t = tasks.get(taskNumber - 1);
                                t.completeTask();
                                System.out.println(divider);
                                System.out.println("Nice! You've completed this task:\n  " + t);
                                System.out.println(divider);
                            } catch (NumberFormatException e) {
                                throw new InvalidFormatException("Task number has to be a positive integer.");
                            } catch (IndexOutOfBoundsException e) {
                                throw new IllegalArgumentException("Task number does not exist.");
                            }
                            continue;
                        }
                        // Un-mark a task such that it is not complete
                        case "unmark" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Unmark command requires a task number.\n" +
                                                                   "   Usage: unmark <taskNumber>");
                            }
                            String remainder = parts[1];
                            try {
                                int taskNumber = Integer.parseInt(remainder);
                                Task t = tasks.get(taskNumber - 1);
                                t.uncompleteTask();
                                System.out.println(divider);
                                System.out.println("Ok! This task is not complete:\n  " + t);
                                System.out.println(divider);
                            } catch (NumberFormatException e) {
                                throw new InvalidFormatException("Task number has to be a positive integer.");
                            } catch (IndexOutOfBoundsException e) {
                                throw new IllegalArgumentException("Task number does not exist.");
                            }
                            continue;
                        }
                        // Remove a task from the taskList
                        case "delete" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Delete command requires a task number.\n" +
                                        "   Usage: delete <taskNumber>");
                            }
                            String remainder = parts[1];
                            try {
                                int taskNumber = Integer.parseInt(remainder);
                                Task t = tasks.get(taskNumber - 1);
                                tasks.remove(taskNumber - 1);
                                taskCount--;
                                System.out.println(divider);
                                System.out.println("Ok! This task has been removed:\n  " + t);
                                System.out.println(divider);
                            } catch (NumberFormatException e) {
                                throw new InvalidFormatException("Task number has to be a positive integer.");
                            } catch (IndexOutOfBoundsException e) {
                                throw new IllegalArgumentException("Task number does not exist.");
                            }
                            continue;
                        }
                        // Add a todo task
                        case "todo" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Todo command requires a task description.\n" +
                                                                   "   todo <taskDescription>");
                            }
                            String remainder = parts[1];
                            tasks.add(taskCount, new Todo(remainder));
                            taskCount++;
                            System.out.println(divider + "Okay! Added this task:\n  " + tasks.get(taskCount - 1)
                                    + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                            continue;
                        }
                        // Add a deadline task
                        case "deadline" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Deadline command requires a task description " +
                                                                   "and a due date.\n" +
                                                                   "   deadline <taskDescription> /by <dueDate>");
                            }
                            String remainder = parts[1];
                            String[] splitParts = remainder.split("/by", 2);
                            if (splitParts.length < 2) {
                                throw new InvalidFormatException("Deadline command must follow the format: " +
                                                                 "<task> /by <dueDate>.");
                            }
                            String name = splitParts[0].trim();
                            String time = splitParts[1].trim();
                            tasks.add(taskCount, new Deadline(name, time));
                            taskCount++;
                            System.out.println(divider + "Okay! Added this task:\n  " + tasks.get(taskCount - 1)
                                    + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                            continue;
                        }
                        // Add a event task
                        case "event" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Event command requires a task description, " +
                                                                   "start time, and end time.\n   " +
                                                                   "event <taskDescription> " +
                                                                   "/from <startTime> /to <endTime>");
                            }
                            String remainder = parts[1];
                            String[] splitParts = remainder.split("/from", 2);
                            if (splitParts.length < 2 || !splitParts[1].contains("/to")) {
                                throw new InvalidFormatException("Event command must follow the format: " +
                                                                 "<task> /from <startTime> /to <endTime>.");
                            }
                            String name = splitParts[0].trim();
                            String[] time = splitParts[1].split("/to", 2);
                            String startTime = time[0].trim();
                            String endTime = time[1].trim();
                            tasks.add(taskCount, new Event(name, startTime, endTime));
                            taskCount++;
                            System.out.println(divider + "Okay! Added this task:\n  " + tasks.get(taskCount - 1)
                                    + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                            continue;
                        }
                    }
                    // Catch exceptions and print out error messages
                } catch (MissingArgumentException | InvalidFormatException | IllegalArgumentException e) {
                    System.out.println(divider);
                    System.out.println(e.getMessage());
                    System.out.println("Please try again.");
                    System.out.println(divider);
                    continue;
                }
                throw new InvalidCommandException();
                // Catch invalid command inputs here
            } catch (InvalidCommandException e) {
                System.out.print(divider);
                System.out.println(e);
                System.out.println("Here are a list of valid commands:");
                System.out.println(" - list");
                System.out.println(" - mark <taskNumber>");
                System.out.println(" - unmark <taskNumber>");
                System.out.println(" - delete <taskNumber>");
                System.out.println(" - todo <taskDescription>");
                System.out.println(" - deadline <taskDescription> /by <dueDate>");
                System.out.println(" - event <taskDescription> /from <startTime> /to <endTime>");
                System.out.println(" - bye");
                System.out.println(divider);
            }
        }
        scanner.close();
        System.out.println(divider + "Bye! Yippee!");
    }
}
