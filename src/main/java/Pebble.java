import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


// ToDos: tasks without any date/time attached to it e.g., visit new theme park
// Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
// Events: tasks that start at a specific date/time and ends at a specific date/time
// e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019

/**
 * Pebble Class is a chat bot that manages tasks: ToDos, Deadlines and Events.
 */
public class Pebble {

    /**
     * Enum shows all the different types of commands that is possible for this chat bot.
     */
    enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    /**
     * Main method that runs the chatbot,
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Array to store Task objects
        ArrayList<Task> tasksList = new ArrayList<>();
        loadTasksFromFile(tasksList);

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
                    for (int i = 0; i < tasksList.size(); i++) {
                        System.out.println("    " + (i + 1) + "." + tasksList.get(i).toString());
                    }
                    System.out.println("    ____________________________________________________________");
                    break;

                // Mark task as done
                case MARK:
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumber >= 0 && taskNumber < tasksList.size()) {
                        tasksList.get(taskNumber).markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + tasksList.get(taskNumber).toString());
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
                    if (unmarkTaskNumber >= 0 && unmarkTaskNumber < tasksList.size()) {
                        tasksList.get(unmarkTaskNumber).unmarkAsNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("      " + tasksList.get(unmarkTaskNumber).toString());
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
                    tasksList.add(newTodo);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + newTodo.toString());
                    System.out.println("    Now you have " + tasksList.size() + " tasks in this list.");
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
                    tasksList.add(newDeadline);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + newDeadline.toString());
                    System.out.println("    Now you have " + tasksList.size() + " tasks in this list.");
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
                    tasksList.add(newEvent);

                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + newEvent.toString());
                    System.out.println("    Now you have " + tasksList.size() + " tasks in this list.");
                    System.out.println("    ____________________________________________________________");
                    break;

                // delete
                case DELETE:
                    // skip the first 7 letters and get number, minus one to be zero indexed
                    int deleteTaskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (deleteTaskNumber >= 0 && deleteTaskNumber < tasksList.size()) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("      " + tasksList.get(deleteTaskNumber).toString());
                        System.out.println("    Now you have " + (tasksList.size() - 1) + " tasks in this list.");
                        System.out.println("    ____________________________________________________________");
                        tasksList.remove(deleteTaskNumber);
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

            saveTasksToFile(tasksList);
        }
    }

    /**
     * Loads tasks from a local file and populates the tasksList.
     * If the file does not exist, a new file is created.
     *
     * @param tasksList The taskslist that current program handles.
     */
    public static void loadTasksFromFile(ArrayList<Task> tasksList) {
        File file = new File("./data/tasksList.txt");
        try {
            if (file.exists() && !file.isDirectory()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    Task task = parseTaskFromString(line);
                    tasksList.add(task);
                }
                scanner.close();
            } else {
                createNewTasksFile(tasksList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found.");
        } catch (Exception e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Parses a string from the file into a Task object.
     * @param line Line from file corresponding to each task.
     * @return Converted Task object from the string.
     */
    private static Task parseTaskFromString(String line) {
        if (line.startsWith("[T]")) {
            // format: [T][X] task description
            String description = line.substring(line.indexOf("] ") + 2);
            ToDo todo = new ToDo(description);
            if (line.contains("[X]")) {
                todo.markAsDone();
            }
            return todo;
        } else if (line.startsWith("[D]")) {
            // format: [D][X] task description (by: date)
            String description = line.substring(line.indexOf("] ") + 2, line.lastIndexOf(" (by: "));
            String by = line.substring(line.lastIndexOf(" (by: ") + 6, line.length() - 1);
            Deadline deadline = new Deadline(description, by);
            if (line.contains("[X]")) {
                deadline.markAsDone();
            }
            return deadline;
        } else if (line.startsWith("[E]")) {
            // format: [E][X] task description (from: start to: end)
            String description = line.substring(line.indexOf("] ") + 2, line.lastIndexOf(" (from: "));
            String from = line.substring(line.lastIndexOf(" (from: ") + 8, line.lastIndexOf(" to: "));
            String to = line.substring(line.lastIndexOf(" to: ") + 5, line.length() - 1);
            Event event = new Event(description, from, to);
            if (line.contains("[X]")) {
                event.markAsDone();
            }
            return event;
        }
        // should never be reached unless file is corrupted
        return new invalidTask();
    }

    /**
     * Creates a new tasksList file if one does not already exist.
     * @param tasksList List of task that is handled within the program.
     */
    public static void createNewTasksFile(ArrayList<Task> tasksList) {
        try {
            // Create new file and write a test string to it
            File file = new File("./data/tasksList.txt");
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();
            System.out.println("New file created.");
        } catch (IOException e) {
            System.out.println("Unexpected error occurred when creating a new file.");
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasksList) {
        try {
            FileWriter fw = new FileWriter("./data/tasksList.txt");
            for (Task task : tasksList) {
                fw.write(task.toString() + "\n"); // Assuming each Task subclass implements toFileString()
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unexpected error occurred when saving tasks to file: " + e.getMessage());
        }
    }
    
    /**
     * Converts string input to command type.
     * @param input Input string by the user.
     * @return Corresponding command type.
     */
    private static CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
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
