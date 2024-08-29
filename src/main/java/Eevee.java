import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Eevee {
    private static final String FILE_PATH = "data/tasks.txt";
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

        public static Command enumerate(String command) {
            return Command.valueOf(command.toUpperCase());
        }
    }

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

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String divider = "____________________________________________________________\n";
        String greeting = "Hello! I'm Eevee\nWhat can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.print(divider + greeting + divider);
//        try {
//            printFileContents(FILE_PATH);
//            System.out.print(divider);
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found!");
//        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next();
            System.out.print(divider);

            try {
                Command command = Command.enumerate(input);
                switch (command) {
                case BYE:
                    // Save tasks to hard disk before exiting
                    try {
                        for (Task task : tasks) {
                            String type;
                            // Check the type of task
                            if (task instanceof Todo) {
                                type = "T";
                                appendToFile(FILE_PATH, type + "|" + task.getStatus() + "|"
                                        + task.getDescription() + "\n");
                            } else if (task instanceof Deadline) {
                                type = "D";
                                appendToFile(FILE_PATH, type + "|" + task.getStatus() + "|"
                                        + task.getDescription() + "|" + ((Deadline) task).getDeadline() + "\n");
                            } else if (task instanceof Event) {
                                type = "E";
                                appendToFile(FILE_PATH, type + "|" + task.getStatus() + "|"
                                        + task.getDescription() + "|" + ((Event) task).getTimeline() + "\n");
                            } else {
                                throw new EeveeException("There is a task of unknown type!");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Unable to store tasks!");
                    }
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
                    System.out.println("Added the following task to your list:\n" + t);
                    break;
                }
                case DEADLINE: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( "
                                + "Please input the task details and description correctly");
                    }
                    String[] info = s.split("/", 2);
                    if (info.length < 2) {
                        throw new EeveeException("Deadline not given for task type 'deadline'. "
                                + "Please input a deadline denoted by '/by' or use task type 'todo' instead.");
                    }
                    Deadline d = new Deadline(info[0], info[1]);
                    tasks.add(d);
                    System.out.println("Added the following task to your list:\n" + d);
                    break;
                }
                case EVENT: {
                    String s = scanner.nextLine().trim();
                    if (s.isEmpty()) {
                        throw new EeveeException("No task found :( "
                                + "Please input the task details and description correctly");
                    }
                    String[] info = s.split("/", 3);
                    if (info.length < 3) {
                        throw new EeveeException("Event start and/or end timings not provided."
                                + "Please input a start time denoted by '/from' "
                                + "and an end time denoted by '/to' when using task type Event");
                    }
                    Event e = new Event(info[0], info[1], info[2]);
                    tasks.add(e);
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
