import java.util.ArrayList;
import java.util.Scanner;

class ChatBuddyException extends Exception {
    public ChatBuddyException(String message) {
        super(message);
    }
}

public class ChatBuddy {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler(FILE_PATH);
        ArrayList<Task> tasks = fileHandler.loadTasks(); //Load tasks from file

        try {
            System.out.println("_____________________________________________");
            System.out.println("Hello! I'm ChatBuddy.\nLet me know how can I assist you today?");
            System.out.println("_____________________________________________");

            String userInput = scanner.nextLine();
            while (!userInput.equals("bye")) {
                try {
                    if (userInput.equals("list")) { // Display all stored tasks
                        if (tasks.isEmpty()) {
                            System.out.println("_____________________________________________");
                            System.out.println("You don't have any tasks in your list yet.");
                            System.out.println("_____________________________________________");
                        } else {
                            System.out.println("_____________________________________________");
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println((i + 1) + "." + tasks.get(i));
                            }
                            System.out.println("_____________________________________________");
                        }

                    } else if (userInput.startsWith("mark ")) {
                        // Mark a task as done
                        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        if (taskNumber < tasks.size() && taskNumber >= 0) {
                            tasks.get(taskNumber).markAsDone();
                            System.out.println("_____________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(" " + tasks.get(taskNumber));
                            System.out.println("_____________________________________________");
                            fileHandler.saveTasks(tasks); // Save changes
                        } else {
                            throw new ChatBuddyException("Task number out of range!");
                        }

                    } else if (userInput.startsWith("unmark ")) {
                        // Unmark a task as not done
                        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        if (taskNumber < tasks.size() && taskNumber >= 0) {
                            tasks.get(taskNumber).unmarkAsDone();
                            System.out.println("_____________________________________________");
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(" " + tasks.get(taskNumber));
                            System.out.println("_____________________________________________");
                            fileHandler.saveTasks(tasks);
                        } else {
                            throw new ChatBuddyException("Task number out of range!");
                        }

                    } else if (userInput.startsWith("todo")) {
                        // Add a ToDo task
                        String description = userInput.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new ChatBuddyException("The description of a todo cannot be empty.");
                        }

                        tasks.add(new ToDo(description));
                        System.out.println("_____________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________________");
                        fileHandler.saveTasks(tasks);

                    } else if (userInput.startsWith("deadline")) {
                        String[] parts = userInput.substring(8).split(" /by ");
                        String description = parts[0].trim();
                        String by = parts.length > 1 ? parts[1].trim() : "";

                        if (description.isEmpty() && by.isEmpty()) {
                            throw new ChatBuddyException("The description and due date of a deadline cannot be empty. Please provide both.");
                        } else if (by.isEmpty()) {
                            throw new ChatBuddyException("The due date of a deadline cannot be empty. Please provide a due date.");
                        }

                        tasks.add(new Deadline(description, by));
                        System.out.println("_____________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________________");
                        fileHandler.saveTasks(tasks);

                    } else if (userInput.startsWith("event")) {
                        String[] parts = userInput.substring(5).split(" /from | /to ");
                        String description = parts[0].trim();
                        String from = parts.length > 1 ? parts[1].trim() : "";
                        String to = parts.length > 2 ? parts[2].trim() : "";

                        if (description.isEmpty() && from.isEmpty() && to.isEmpty()) {
                            throw new ChatBuddyException("The description, start time, and end time of an event cannot be empty. Please provide all details.");
                        } else if (from.isEmpty() || to.isEmpty()) {
                            throw new ChatBuddyException("The start time or end time of an event cannot be empty. Please provide both.");
                        }

                        tasks.add(new Event(description, from, to));
                        System.out.println("_____________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________________");
                        fileHandler.saveTasks(tasks);

                    } else if  (userInput.startsWith("delete ")) {
                        // Delete a task
                        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        if (taskNumber < tasks.size() && taskNumber >= 0) {
                            Task removedTask = tasks.remove(taskNumber);
                            System.out.println("_____________________________________________");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(" " + removedTask);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println("_____________________________________________");
                            fileHandler.saveTasks(tasks);
                        } else {
                            throw new ChatBuddyException("Task number out of range!");
                        }

                    } else {
                        throw new ChatBuddyException("Sorry, I don't understand that command.");
                    }
                } catch (ChatBuddyException e) {
                    System.out.println("_____________________________________________");
                    System.out.println("OOPS!!! " + e.getMessage());
                    System.out.println("_____________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("_____________________________________________");
                    System.out.println("OOPS!!! Please enter a valid number.");
                    System.out.println("_____________________________________________");
                }
                userInput = scanner.nextLine();
            }
        } finally {
            System.out.println("_____________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("_____________________________________________");
        }
    }
}
