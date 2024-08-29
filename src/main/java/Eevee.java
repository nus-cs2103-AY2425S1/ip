import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a task management program.
 */
public class Eevee {
    /**
     * Enumerates the possible commands that can be executed by the Eevee program.
     */
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

        /**
         * Converts a string command to its corresponding Command enumeration.
         *
         * @param command The string representation of the command.
         * @return The corresponding Command enumeration.
         */
        public static Command enumerate(String command) {
            return Command.valueOf(command.toUpperCase());
        }
    }

    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Appends desired String to the file.
     *
     * @param filePath The file path of target file.
     * @param textToAppend The String to be appended into the file.
     * @throws IOException Input / Output exceptions.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Prints contents of the file.
     *
     * @param filePath The file that is to be printed.
     * @throws FileNotFoundException Exception if the file is not found.
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void loadTasks(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner scanner = new Scanner(f);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split("\\|");

            String type = taskData[0];
            boolean isDone = taskData[1].equals("1");
            String description = taskData[2];

            switch (type) {
            case "T":
                Todo t = new Todo(description);
                tasks.add(t);
                break;

            case "D":
                String deadline = taskData[3];
                Deadline d = new Deadline(description, deadline);
                tasks.add(d);
                break;

            case "E":
                String from = taskData[3];
                String to = taskData[4];
                Event e = new Event(description, from, to);
                tasks.add(e);
                break;

            default:
                System.out.println("Invalid task found!");
                break;
            }
        }
    }

    /** 
     * Serves as the entry point for the Eevee program.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        try {
            loadTasks(FILE_PATH, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        System.out.print(divider + greeting + divider);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            System.out.print(divider);

            try {
                Command command = Command.enumerate(input);
                switch (command) {
                case BYE:
                    System.out.println(exit);
                    return;
                case LIST:
                    if (tasks.isEmpty()) {
                        throw new EeveeException("No tasks yet! Start adding tasks :)");
                    }
                    System.out.println("Here are your tasks:");
                    tasks.forEach((task) ->
                            System.out.println((tasks.indexOf(task) + 1) + ". " + task)
                    );
                    break;
                case MARK: {
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > tasks.size()) {
                        throw new EeveeException("No task under the given task number. "
                                + "Did you type the wrong number?");
                    }
                    Task t = tasks.get(taskNumber - 1);
                    if (t.isDone) {
                        throw new EeveeException("Task has already been marked as done.");
                    }
                    t.markAsDone();
                    System.out.println("Congratulations! I've marked the following task as done:\n  " + t);
                    break;
                }
                case UNMARK: {
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > tasks.size()) {
                        throw new EeveeException("No task under the given task number. "
                                + "Did you type the wrong number?");
                    }
                    Task t = tasks.get(taskNumber - 1);
                    if (!t.isDone) {
                        throw new EeveeException("Task is not marked as done. "
                                + "Needs to be marked done in order to unmark it.");
                    }
                    t.unmarkAsDone();
                    System.out.println("Ok! Task no longer marked as done:\n  " + t);
                    break;
                }
                case DELETE: {
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > tasks.size()) {
                        throw new EeveeException("No task under the given task number. "
                                + "Did you type the wrong number?");
                    }
                    Task t = tasks.get(taskNumber - 1);
                    tasks.remove(taskNumber - 1);
                    System.out.println("As you wish, this task has been removed:\n " + t);
                    break;
                }
                case TODO: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( "
                                + "Please input the task details and description correctly");
                    }
                    Todo t = new Todo(s);
                    tasks.add(t);
                    try {
                        appendToFile(FILE_PATH, "T" + "|" + t.getStatus() + "|"
                                + s + "\n");
                    } catch (IOException e) {
                        throw new EeveeException("Unable to store task!");
                    }
                    System.out.println("Added the following task to your list:\n" + t);
                    break;
                }
                case DEADLINE: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( "
                                + "Please input the task details and description correctly");
                    }
                    String[] info = s.split("/by", 2);
                    if (info.length < 2) {
                        throw new EeveeException("Deadline not given for task type 'deadline'. "
                                + "Please input a deadline denoted by '/by' or use task type 'todo' instead.");
                    }

                    // Create and store task
                    Deadline d = new Deadline(info[0], info[1]);
                    tasks.add(d);
                    try {
                        appendToFile(FILE_PATH, "D" + "|" + d.getStatus() + "|"
                                + info[0] + "|" + d.getDeadline() + "\n");
                    } catch (IOException e) {
                        throw new EeveeException("Unable to store task!");
                    }
                    System.out.println("Added the following task to your list:\n" + d);
                    break;
                }
                case EVENT: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( "
                                + "Please input the task details and description correctly");
                    }
                    String[] info = s.split("/from|/to", 3);
                    if (info.length < 3) {
                        throw new EeveeException("Event start and/or end timings not provided."
                                + "Please input a start time denoted by '/from' "
                                + "and an end time denoted by '/to' when using task type Event");
                    }

                    // Create and store task
                    Event e = new Event(info[0], info[1], info[2]);
                    tasks.add(e);
                    try {
                        appendToFile(FILE_PATH, "E" + "|" + e.getStatus() + "|"
                                + info[0] + "|" + e.getFrom() + "|" + e.getTo() + "\n");
                    } catch (IOException exception) {
                        throw new EeveeException("Unable to store task!");
                    }
                    System.out.println("Added the following task to your list:\n" + e);
                    break;
                }
                default:
                    throw new EeveeException("You seemed to have typed wrong. This is not a valid command.");
                }
                System.out.println("You now have " + tasks.size() + " task(s).");
            } catch (EeveeException e) {
                System.out.println(e.getMessage());
            }
            System.out.print(divider);
        }
    }
}
