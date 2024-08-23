import java.util.ArrayList;
import java.util.Scanner;

// ToDos: tasks without any date/time attached to it e.g., visit new theme park
// Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
// Events: tasks that start at a specific date/time and ends at a specific date/time
// e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019
public class Pebble {
    public static void main(String[] args) {
        // Array to store Task objects
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        // Introduction text
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Pebble");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            try {
                // Exit if "bye" is typed
                if (input.equals("bye")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println("    ____________________________________________________________");
                    break;
                }

                // List all tasks
                if (input.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("    " + (i + 1) + "." + taskList.get(i).toString());
                    }
                    System.out.println("    ____________________________________________________________");
                    continue;
                }

                // Mark task as done
                if (input.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.size()) {
                        taskList.get(taskNumber).markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + taskList.get(taskNumber).toString());
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Invalid task number.");
                        System.out.println("    ____________________________________________________________");
                    }
                    continue;
                }

                // Unmark task as not done
                if (input.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.size()) {
                        taskList.get(taskNumber).unmarkAsNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("      " + taskList.get(taskNumber).toString());
                        System.out.println("    ____________________________________________________________");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Invalid task number.");
                        System.out.println("    ____________________________________________________________");
                    }
                    continue;
                }

                // Add a new todo
                if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                    }

                    String description = input.substring(5);
                    ToDo newTodo = new ToDo(description);

                    // Add to list
                    taskList.add(newTodo);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + newTodo.toString());
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("    ____________________________________________________________");
                    continue;
                }

                // Add a new Deadline
                if (input.startsWith("deadline")) {
                    String[] parts = input.substring(9).split(" /by ");
                    // if no split means no /by
                    if (parts.length < 2) {
                        throw new InvalidInputFormatException("OOPS!!! The deadline command requires a description and a date/time after '/by'.");
                    }
                    String description = parts[0];
                    String by = parts[1];
                    Deadline newDeadline = new Deadline(description, by);

                    // Add to list
                    taskList.add(newDeadline);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + newDeadline.toString());
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("    ____________________________________________________________");
                    continue;
                }

                // Add a new Event
                if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    // if improper split
                    if (parts.length < 3) {
                        throw new InvalidInputFormatException("OOPS!!! The event command requires a description, start time after '/from', and end time after '/to'.");
                    }

                    // could be encapsulated under event class
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    Event newEvent = new Event(description, from, to);

                    // Add to list
                    taskList.add(newEvent);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + newEvent.toString());
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("    ____________________________________________________________");
                    continue;
                }

                // delete
                if (input.startsWith("delete")) {
                    // skip the first 7 letters and get number, minus one to be zero indexed
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber >= 0 && taskNumber < taskList.size()) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("      " + taskList.get(taskNumber).toString());
                        System.out.println("    Now you have " + (taskList.size() - 1)  + " tasks in this list.");
                        System.out.println("    ____________________________________________________________");
                        taskList.remove(taskNumber);
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Invalid task number.");
                        System.out.println("    ____________________________________________________________");
                    }
                    continue;
                }

                // Input command is unrecognised,throw exception
                throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            } catch (UnknownCommandException | EmptyDescriptionException | InvalidInputFormatException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            }
        }
        scanner.close();
    }
}
