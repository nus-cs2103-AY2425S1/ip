import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;

public class Rob {
    private static final String FILE_PATH = "./data/robsaved.txt";

    private static final String GREET = "Hello! I'm Rob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!";
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        loadTasks(tasks);
        System.out.println(GREET);

        while (true) {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Invalid input! Please enter a task.");
                continue;
            }

            // exit
            if (Objects.equals(input, "bye")) {
                System.out.println(EXIT);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else if (input.startsWith("mark")) {
                try {
                    int taskNum = findTaskNum(input);
                    tasks.get(taskNum - 1).markAsDone();
                    saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("unmark")) {
                try {
                    int taskNum = findTaskNum(input);
                    tasks.get(taskNum - 1).unmark();
                    saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("delete")) {
                try {
                    int taskNum = findTaskNum(input);
                    // echo
                    String echo = "____________________________________________________________\n" +
                            "deleted: " + tasks.get(taskCount - 1) + "\n" +
                            "____________________________________________________________\n";
                    String numTaskInList = "Now you have " + (taskCount - 1) + " task(s) in the list.\n";
                    System.out.println(echo + numTaskInList);

                    tasks.remove(taskNum - 1);
                    saveTasks(tasks);
                    taskCount--;

                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                try {
                    if (input.startsWith("deadline")) {
                        if (input.split(" ", 2).length < 2) {
                            throw new DukeException("Invalid format... What deadline would you like to add?");
                        }

                        if (!input.contains(" /by")) {
                            throw new DukeException("Missing '/by' in deadline command.");
                        }
                        String rem = input.split(" ", 2)[1].trim(); // ignore first keyword of input
                        String desc = rem.split(" /by")[0].trim();
                        String day = rem.split(" /by")[1].trim();
                        tasks.add(new Deadline(desc, day));
                        saveTasks(tasks);
                        taskCount++;

                    } else if (input.startsWith("event")) {
                        if (input.split(" ", 2).length < 2) {
                            throw new DukeException("Invalid format... What event would you like to add?");
                        }

                        if (!input.contains(" /from") || !input.contains(" /to")) {
                            throw new DukeException("Missing '/from' or '/to' in event command.");
                        }

                        String rem = input.split(" ", 2)[1].trim(); // ignore first keyword of input
                        String desc = rem.split(" /from")[0].trim();
                        String from = rem.split(" /from")[1].split(" /to")[0].trim();
                        String to = rem.split(" /from")[1].split(" /to")[1].trim();
                        tasks.add(new Event(desc, from, to));
                        saveTasks(tasks);
                        taskCount++;

                    } else if (input.startsWith("todo")) {
                        if (input.split(" ", 2).length < 2) {
                            throw new DukeException("Invalid format... What todo would you like to add?");
                        }

                        String rem = input.split(" ", 2)[1].trim(); // ignore first keyword of input
                        tasks.add(new Todo(rem));
                        saveTasks(tasks);
                        taskCount++;
                    } else {
                        throw new DukeException("I'm sorry... I don't seem to understand.");
                    }
                    // echo
                    String echo = "____________________________________________________________\n" +
                            "added: " + tasks.get(taskCount - 1) + "\n" +
                            "____________________________________________________________\n";
                    String numTaskInList = "Now you have " + taskCount + " task(s) in the list.\n";
                    System.out.println(echo + numTaskInList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }

    /**
     * Finds and returns the last integer found in the input string,
     * which is assumed to be a task number.
     *
     * @param input The input string that contains the task number.
     * @return The task number extracted from the input string.
     * @throws DukeException If no number is found in the input string or if the task number is out of range.
     */
    private static int findTaskNum(String input) throws DukeException {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String lastNum = null;

        while (matcher.find()) {
            lastNum = matcher.group();
        }

        if (lastNum == null) {
            throw new DukeException("Invalid task number...");
        } else {
            int taskNum = Integer.parseInt(lastNum);
            if (taskNum < 1 || taskNum > taskCount) {
                throw new DukeException("Invalid task number");
            } else {
                return taskNum;
            }
        }
    }

    /**
     * Saves the list of tasks to a file on the hard disk.
     *
     * <p>This method ensures that the directory structure for the file exists
     * by creating the necessary directories if they do not already exist.
     * It then writes each task to the file, with each task on a new line.</p>
     *
     * <p>If an error occurs during the file writing process, an error message
     * is printed to the console.</p>
     *
     * @param tasks the list of tasks to be saved
     */
    private static void saveTasks(List<Task> tasks) {
        try {
            Files.createDirectories(Paths.get("./data"));
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toSaveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and adds them to the provided list.
     * <p>
     * The method reads tasks from {@code FILE_PATH}, parses each task's type, status, description, and
     * additional details, and adds them to the {@code tasks} list. The {@code taskCount} is incremented
     * for each task added.
     * </p>
     *
     * @param tasks The list to add the loaded tasks to.
     * @throws IOException If an error occurs while reading the file.
     */
    private static void loadTasks(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ", 5);


                    String type = parts[0].trim();
                    String status = parts[1].trim();
                    String desc = parts[2].trim();
                    String firstLimiter  = (parts.length > 3) ? parts[3].trim() : "";
                    String secondLimiter = (parts.length > 4) ? parts[4].trim() : "";

                    if (type.equals("[T]")) {
                        Todo todoTask = new Todo(desc);
                        if (status.equals("[X]")) {
                            todoTask.markAsDone();
                        }
                        tasks.add(todoTask);
                        taskCount++;
                    } else if (type.equals("[D]")) {
                        Deadline deadlingTask = new Deadline(desc, firstLimiter);
                        if (status.equals("[X]")) {
                            deadlingTask.markAsDone();
                        }
                        tasks.add(deadlingTask);
                        taskCount++;
                    } else if (type.equals("[E]")) {
                        Event eventTask = new Event(desc, firstLimiter, secondLimiter);
                        if (status.equals("[X]")) {
                            eventTask.markAsDone();
                        }
                        tasks.add(eventTask);
                        taskCount++;
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

}
