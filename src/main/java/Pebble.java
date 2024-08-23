import java.util.ArrayList;
import java.util.Scanner;

// ToDos: tasks without any date/time attached to it e.g., visit new theme park
// Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
// Events: tasks that start at a specific date/time and ends at a specific date/time
// e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019
public class Pebble {
    enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }
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
            CommandType commandType = getCommandType(input);

            try {
                switch (commandType) {
                    case BYE:
                    // Exit if "bye" is typed
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Bye. Hope to see you again soon!");
                        System.out.println("    ____________________________________________________________");
                        return;

                    // List all tasks
                    case LIST:
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println("    " + (i + 1) + "." + taskList.get(i).toString());
                        }
                        System.out.println("    ____________________________________________________________");
                        break;

                    // Mark task as done
                    case MARK:
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
                        break;

                    // Unmark task as not done
                    case UNMARK:
                        int unmarkTaskNumber = Integer.parseInt(input.substring(7)) - 1;
                        if (unmarkTaskNumber >= 0 && unmarkTaskNumber < taskList.size()) {
                            taskList.get(unmarkTaskNumber).unmarkAsNotDone();
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    OK, I've marked this task as not done yet:");
                            System.out.println("      " + taskList.get(unmarkTaskNumber).toString());
                            System.out.println("    ____________________________________________________________");
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Invalid task number.");
                            System.out.println("    ____________________________________________________________");
                        }
                        break;

                    // Add a new todo
                    case TODO:
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
                        break;


                    // Add a new Deadline
                    case DEADLINE:
                        String[] deadlineParts = input.substring(9).split(" /by ");
                        // if no split means no /by
                        if (deadlineParts.length < 2) {
                            throw new InvalidInputFormatException("OOPS!!! The deadline command requires a description and a date/time after '/by'.");
                        }
                        String deadlineDescription = deadlineParts[0];
                        String by = deadlineParts[1];
                        Deadline newDeadline = new Deadline(deadlineDescription, by);

                        // Add to list
                        taskList.add(newDeadline);

                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + newDeadline.toString());
                        System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                        System.out.println("    ____________________________________________________________");
                        break;

                    // Add a new Event
                    case EVENT:
                        String[] eventParts = input.substring(6).split(" /from | /to ");
                        // if improper split
                        if (eventParts.length < 3) {
                            throw new InvalidInputFormatException("OOPS!!! The event command requires a description, start time after '/from', and end time after '/to'.");
                        }

                        // could be encapsulated under event class
                        String eventDescription = eventParts[0];
                        String from = eventParts[1];
                        String to = eventParts[2];
                        Event newEvent = new Event(eventDescription, from, to);

                        // Add to list
                        taskList.add(newEvent);

                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + newEvent.toString());
                        System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                        System.out.println("    ____________________________________________________________");
                        break;

                    // delete
                    case DELETE:
                        // skip the first 7 letters and get number, minus one to be zero indexed
                        int deleteTaskNumber = Integer.parseInt(input.substring(7)) - 1;
                        if (deleteTaskNumber >= 0 && deleteTaskNumber < taskList.size()) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Noted. I've removed this task:");
                            System.out.println("      " + taskList.get(deleteTaskNumber).toString());
                            System.out.println("    Now you have " + (taskList.size() - 1)  + " tasks in this list.");
                            System.out.println("    ____________________________________________________________");
                            taskList.remove(deleteTaskNumber);
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("    Invalid task number.");
                            System.out.println("    ____________________________________________________________");
                        }
                        break;

                    // Input command is unrecognised,throw exception
                    default:
                    throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (UnknownCommandException | EmptyDescriptionException | InvalidInputFormatException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}
