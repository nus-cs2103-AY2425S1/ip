import java.util.ArrayList;
import java.util.Scanner;

class ChatBuddyException extends Exception {
    public ChatBuddyException(String message) {
        super(message);
    }
}

public class ChatBuddy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(); // Using ArrayList instead of array

        try {
            System.out.println("_____________________________________________");
            System.out.println("Hello! I'm ChatBuddy.\nLet me know how can I assist you today?");
            System.out.println("_____________________________________________");

            String userInput = scanner.nextLine();
            while (!userInput.equals("bye")) {
                try {
                    if (userInput.equals("list")) { // Display all stored tasks
                        System.out.println("_____________________________________________");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println("_____________________________________________");

                    } else if (userInput.startsWith("mark ")) {
                        // Mark a task as done
                        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        if (taskNumber < tasks.size() && taskNumber >= 0) {
                            tasks.get(taskNumber).markAsDone();
                            System.out.println("_____________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(" " + tasks.get(taskNumber));
                            System.out.println("_____________________________________________");
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
                        } else {
                            throw new ChatBuddyException("Task number out of range!");
                        }

                    } else if (userInput.startsWith("todo ")) {
                        // Add a ToDo task
                        String description = userInput.substring(5).trim();
                        if (description.isEmpty()) {
                            throw new ChatBuddyException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new ToDo(description));
                        System.out.println("_____________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________________");

                    } else if (userInput.startsWith("deadline ")) {
                        // Add a Deadline task
                        String[] parts = userInput.split(" /by ");
                        if (parts.length < 2) {
                            throw new ChatBuddyException("The deadline must have a description and a due date.");
                        }
                        String description = parts[0].substring(9).trim();
                        String by = parts[1].trim();
                        if (description.isEmpty() || by.isEmpty()) {
                            throw new ChatBuddyException("The description and due date of a deadline cannot be empty.");
                        }
                        tasks.add(new Deadline(description, by));
                        System.out.println("_____________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________________");

                    } else if (userInput.startsWith("event ")) {
                        // Add an Event task
                        String[] parts = userInput.split(" /from | /to ");
                        if (parts.length < 3) {
                            throw new ChatBuddyException("The event must have a description, start time, and end time.");
                        }
                        String description = parts[0].substring(6).trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();
                        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new ChatBuddyException("The description, start time, and end time of an event cannot be empty.");
                        }
                        tasks.add(new Event(description, from, to));
                        System.out.println("_____________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________________");

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
