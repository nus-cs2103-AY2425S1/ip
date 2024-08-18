import java.util.Scanner;
public class Downy {

    public static void main(String[] args) {
        String divider = "________________________________________\n";
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        int taskCount = 0;
        System.out.println(divider + "Hello! I'm Downy.\nHow can I help?\n" + divider);

        while (true) {
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];
            try {
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
                        System.out.println((i + 1) + ". " + tasks.getTask(i));
                    }
                    System.out.println(divider);
                    continue;
                }

                try {
                    switch (command) {
                        case "mark" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Mark command requires a task number.\n" +
                                                                   "   Usage: mark <taskNumber> ");
                            }
                            String remainder = parts[1];
                            try {
                                int taskNumber = Integer.parseInt(remainder);
                                Task t = tasks.getTask(taskNumber - 1);
                                t.completeTask();
                                System.out.println(divider);
                                System.out.println("Nice! You've completed this task:\n  " + t);
                                System.out.println(divider);
                            } catch (NumberFormatException e) {
                                throw new InvalidFormatException("Task number has to be a positive integer.");
                            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                                throw new IllegalArgumentException("Task number does not exist.");
                            }
                            continue;
                        }
                        case "unmark" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Unmark command requires a task number.\n" +
                                                                   "   Usage: unmark <taskNumber>");
                            }
                            String remainder = parts[1];
                            try {
                                int taskNumber = Integer.parseInt(remainder);
                                Task t = tasks.getTask(taskNumber - 1);
                                t.uncompleteTask();
                                System.out.println(divider);
                                System.out.println("Ok! This task is not complete:\n  " + t);
                                System.out.println(divider);
                            } catch (NumberFormatException e) {
                                throw new InvalidFormatException("Task number has to be a positive integer.");
                            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                                throw new IllegalArgumentException("Task number does not exist.");
                            }
                            continue;
                        }
                        case "todo" -> {
                            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                                throw new MissingArgumentException("Todo command requires a task description.\n" +
                                                                   "   todo <taskDescription>");
                            }
                            String remainder = parts[1];
                            tasks.setTask(new Todo(remainder), taskCount);
                            taskCount++;
                            System.out.println(divider + "Okay! Added this task:\n  " + tasks.getTask(taskCount - 1)
                                    + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                            continue;
                        }
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
                            tasks.setTask(new Deadline(name, time), taskCount);
                            taskCount++;
                            System.out.println(divider + "Okay! Added this task:\n  " + tasks.getTask(taskCount - 1)
                                    + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                            continue;
                        }
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
                            tasks.setTask(new Event(name, startTime, endTime), taskCount);
                            taskCount++;
                            System.out.println(divider + "Okay! Added this task:\n  " + tasks.getTask(taskCount - 1)
                                    + "\nNow you have " + taskCount + " tasks in this list\n" + divider);
                            continue;
                        }
                    }
                } catch (MissingArgumentException | InvalidFormatException | IllegalArgumentException e) {
                    System.out.println(divider);
                    System.out.println(e.getMessage());
                    System.out.println("Please try again.");
                    System.out.println(divider);
                    continue;
                }
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                System.out.print(divider);
                System.out.println(e);
                System.out.println("Here are a list of valid commands:");
                System.out.println(" - list");
                System.out.println(" - mark <taskNumber>");
                System.out.println(" - unmark <taskNumber>");
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
