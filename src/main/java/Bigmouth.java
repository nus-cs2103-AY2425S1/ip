import java.util.ArrayList;
import java.util.Scanner;
public class Bigmouth {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Introduction
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Bigmouth\nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");

        // Main loop to handle user input
        while (true) {
            String userInput = scanner.nextLine();

            try {
                // Exit the program if the user types "bye"
                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                // List all stored tasks if the user types "list"
                if (userInput.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new BigmouthException("Your task list is empty!");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                }
                // Mark a task as done
                else if (userInput.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNumber));
                    System.out.println("____________________________________________________________");
                }
                // Unmark a task as not done
                else if (userInput.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskNumber));
                    System.out.println("____________________________________________________________");
                }
                // Add a Todo task
                else if (userInput.startsWith("todo ")) {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                // Add a Deadline task
                else if (userInput.startsWith("deadline ")) {
                    String[] parts = userInput.split(" /by ");
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The deadline command is missing a date/time.");
                    }
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of a deadline cannot be empty.");
                    }
                    tasks.add(new Deadline(description, by));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                // Add an Event task
                else if (userInput.startsWith("event ")) {
                    String[] parts = userInput.split(" /from | /to ");
                    if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The event command is missing a start or end time.");
                    }
                    String description = parts[0].substring(6).trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of an event cannot be empty.");
                    }
                    tasks.add(new Event(description, from, to));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                // Delete a task
                else if (userInput.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. Please enter a valid task number.");
                    }
                    Task removedTask = tasks.remove(taskNumber);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
                // Handle unknown commands
                else {
                    throw new BigmouthException("OOPS! I don't know what that means. Please try again.");
                }
            } catch (BigmouthException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS! Please enter a valid number.");
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
