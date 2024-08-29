import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Charlotte {

    private static final String FILE_PATH = "./data/charlotte.txt";
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    public static void main(String[] args) {
        loadTasks();
        String line = "__________________________________________________________________";
        System.out.println(line + "\n Hello! I'm Charlotte!\n What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in);

        String input;

        while (true) {
            input = scanner.nextLine();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (TASKS.isEmpty()) {
                        throw new CharlotteException("Your task list is currently empty. Start by adding some tasks!");
                    }

                    System.out.println(line + "\n Here are the tasks in your list:");
                    for (int i = 0; i < TASKS.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + TASKS.get(i).toString());
                    }
                    System.out.println(line);
                } else if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumber < 0 || taskNumber >= TASKS.size()) {
                        throw new CharlotteException("Task number is invalid. Please try again");
                    }

                    TASKS.get(taskNumber).markAsDone();
                    System.out.println(line + "\n Nice! I've marked this task as done:\n  "
                            + TASKS.get(taskNumber) + "\n" + line);
                    saveTasks();
                } else if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber < 0 || taskNumber >= TASKS.size()) {
                        throw new CharlotteException("Task number is invalid. Please try again");
                    }
                    TASKS.get(taskNumber).unmark();
                    System.out.println(line + "\n OK, I've marked this task as not done yet:\n  "
                            + TASKS.get(taskNumber) + "\n" + line);
                    saveTasks();
                } else if (input.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber < 0 || taskNumber >= TASKS.size()) {
                        throw new CharlotteException("Task number is invalid. Please try again");
                    }
                    Task deletedTask = TASKS.remove(taskNumber);
                    System.out.println(line + "\n Noted. I've removed this task:\n  " + deletedTask
                            + "\n Now you have " + TASKS.size() + " tasks in the list.\n" + line);
                    saveTasks();
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new CharlotteException("The description of a todo cannot be empty. Please try again.");
                    }
                    String description = input.substring(5);
                    Task newTask = new ToDo(description);
                    TASKS.add(newTask);
                    System.out.println(line + "\n Got it. I've added this task:\n  " + newTask
                            + "\n Now you have " + TASKS.size() + " tasks in the list.\n" + line);
                    saveTasks();
                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 9) {
                        throw new CharlotteException("The description of a deadline cannot be empty. Please try again.");
                    }
                    String[] deadline = input.substring(9).split(" /by ");
                    String description = deadline[0];
                    String by = deadline[1];
                    Task newTask = new Deadline(description, by);
                    TASKS.add(newTask);
                    System.out.println(line + "\n Got it. I've added this task:\n  " + newTask
                            + "\n Now you have " + TASKS.size() + " tasks in the list.\n" + line);
                    saveTasks();
                } else if (input.startsWith("event")) {
                    if (input.length() <= 6) {
                        throw new CharlotteException("The description of an event cannot be empty. Please try again.");
                    }
                    String[] event = input.substring(6).split(" /from | /to ");
                    String description = event[0];
                    String from = event[1];
                    String to = event[2];
                    Task newTask = new Event(description, from, to);
                    TASKS.add(newTask);
                    System.out.println(line + "\n Got it. I've added this task:\n  " + newTask
                            + "\n Now you have " + TASKS.size() + " tasks in the list.\n" + line);
                    saveTasks();
                } else {
                    throw new CharlotteException("I'm sorry, I don't know what that means :( Please try again!");
                }
            } catch (CharlotteException error) {
                System.out.println(line + "\n " + error.getMessage() + "\n" + line);
            }

        }

        System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);

        scanner.close();
    }

    private static void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();

            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : TASKS) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }

    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
                System.out.println("No existing data file found");
                return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String nextTask = scanner.nextLine();
                String[] taskData = nextTask.split(" \\| ");
                String taskType = taskData[0];
                boolean isDone = taskData[1].equals("1");
                String description = taskData[2];

                Task task;
                switch (taskType) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        String by = taskData[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String from = taskData[3];
                        String to = taskData[4];
                        task = new Event(description, from, to);
                        break;
                    default:
                        throw new CharlotteException("Unknown task type in file: " + taskType);
                }

                if (isDone) {
                    task.markAsDone();
                }

                TASKS.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
        } catch (CharlotteException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}



