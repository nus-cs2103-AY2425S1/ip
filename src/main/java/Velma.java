import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Velma {
    private static final String FILE_PATH = "./data/velma.txt";
    public static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public enum Command {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        UNKNOWN
    }
    public static Command getCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }

    public static void saveTasks(ArrayList<Task> list) {

        File file = new File("/Users/zeonchew04/ip/data/velma.txt");
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter("/Users/zeonchew04/ip/data/velma.txt")) {
            for (Task task : list) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File("/Users/zeonchew04/ip/data/velma.txt");
        if (!file.exists()) {
            System.out.println("No previous tasks found.");
            return list;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ", 3);
                Task task;
                switch (parts[0].charAt(1)) { // Extract task type from the string
                case 'T':
                    task = new Todo(parts[2]);
                    break;
                case 'D':
                    String[] deadlineParts = parts[2].split(" \\(by: ", 2);
                    String deadlineDescription = deadlineParts[0];
                    String by = deadlineParts[1].substring(0, deadlineParts[1].length() - 1); // Remove closing parenthesis
                    task = new Deadline(deadlineDescription, by);
                    break;
                case 'E':
                    String[] eventParts = parts[2].split(" \\(from: | to: ", 3);
                    String eventDescription = eventParts[0];
                    String startTime = eventParts[1];
                    String endTime = eventParts[2].substring(0, eventParts[2].length() - 1); // Remove closing parenthesis
                    task = new Event(eventDescription, startTime, endTime);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type: " + parts[0]);
                }
                if (parts[1].equals("[X]")) {
                    task.changeIsDone();
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading tasks.");
        }
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        int count = 1;
        ArrayList<Task> list = loadTasks();
        boolean end = false;
        printLine();
        System.out.println("Hello! I am Velma!");
        System.out.println("What can I do for you?");
        Scanner req = new Scanner(System.in);
        printLine();

        while (!end) {
            String request = req.nextLine();
            Command command = getCommand(request);

            try {
                switch (command) {
                case TODO:
                    String todoDescription = request.replaceFirst("todo\\s*", "").trim();
                    if (todoDescription.isEmpty()) {
                        throw new VelmaException("Sorry boss! Where is your todo description?");
                    }
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    Task newTodo = new Todo(todoDescription);
                    list.add(newTodo);
                    System.out.println("  " + newTodo.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    printLine();
                    saveTasks(list);
                    break;

                case DEADLINE:
                    String[] parts = request.replaceFirst("deadline\\s*", "").split(" /by ");
                    if (parts.length < 2) {
                        throw new VelmaException("Sorry boss! Your deadline task needs a deadline!");
                    }
                    String description = parts[0];
                    String deadline = parts[1];
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    Task newDeadline = new Deadline(description, deadline);
                    list.add(newDeadline);
                    System.out.println("  " + newDeadline.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                    printLine();
                    saveTasks(list);
                    break;

                case EVENT:
                    parts = request.replaceFirst("event\\s+", "").split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new VelmaException("Sorry boss! An event needs a valid start time and end time!");
                    }
                    description = parts[0];
                    String startTime = parts[1];
                    String endTime = parts[2];
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    Task newEvent = new Event(description, startTime, endTime);
                    list.add(newEvent);
                    System.out.println("  " + newEvent.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printLine();
                    saveTasks(list);
                    break;

                case LIST:
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (Task task : list) {
                        System.out.println(count + "." + task.toString());
                        count++;
                    }
                    printLine();
                    count = 1;
                    break;

                case MARK:
                case UNMARK:
                    parts = request.split(" ");
                    if (parts.length < 2) {
                        throw new VelmaException("Sorry boss! Please specify which task.");
                    }
                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    list.get(taskNumber).changeIsDone();
                    printLine();
                    if (command == Command.MARK) {
                        System.out.println("Nice! I have marked this task as done:");
                    } else {
                        System.out.println("OK! I have marked this task as not done yet:");
                    }
                    System.out.println("  " + "[" + list.get(taskNumber).getStatusIcon() + "] " + list.get(taskNumber).description);
                    printLine();
                    saveTasks(list);
                    break;

                case DELETE:
                    parts = request.split(" ");
                    if (parts.length < 2) {
                        throw new VelmaException("Sorry boss! Please specify which task to delete.");
                    }
                    taskNumber = Integer.parseInt(parts[1]) - 1;
                    printLine();
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("  " + list.get(taskNumber).toString());
                    list.remove(taskNumber);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    printLine();
                    saveTasks(list);
                    break;

                case BYE:
                    printLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    end = true;
                    break;

                case UNKNOWN:
                default:
                    throw new VelmaException("Sorry boss! What are you talking about?");
                }
            } catch (VelmaException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }
}
