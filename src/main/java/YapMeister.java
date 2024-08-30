import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class YapMeister {
    final static int MAX_TASKS = 100;
    private UI ui;
    
    enum TaskType {
        ToDo,
        Deadline,
        Event
    }

    public YapMeister(String filepath) {

    }

    public static void main(String[] args) {
        boolean isRunning = true;
        File tasksFile = null;
        try {
            tasksFile = loadTaskFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            isRunning = false;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Hello I'm YapMeister
                YAPYAPYAPYAP
                """);
        ArrayList<Task> tasks = null;
        try {
            tasks = loadSavedTasks(tasksFile);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            isRunning = false;
        }

        String input = "";
        while (isRunning) {
            System.out.print("\n");
            input = scanner.nextLine();
            String[] command = input.split(" ");
            try {
                switch (command[0]) {
                case "bye":
                    saveLoadedTasks(tasksFile, tasks);
                    isRunning = false;
                    break;
                case "list":
                    int i = 0;
                    for (Task task : tasks) {
                        System.out.println(String.format("%d. %s", i + 1, task.toString()));
                        i++;
                    }
                    break;
                case "mark":
                    int index = parseInt(command[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        throw new InvalidMarkException("No task at that index");
                    }
                    tasks.get(index).setCompleted(true);
                    System.out.println("You did this:");
                    System.out.println(tasks.get(index).toString());
                    break;
                case "unmark":
                    int umindex = parseInt(command[1]) - 1;
                    if (umindex >= tasks.size() || umindex < 0) {
                        throw new InvalidMarkException("No task at that index");
                    }
                    tasks.get(umindex).setCompleted(false);
                    System.out.println("You undid this:");
                    System.out.println(tasks.get(umindex).toString());
                    break;
                case "todo":
                    //Fallthrough
                case "deadline":
                    //Fallthrough
                case "event":
                    Task task = null;
                    task = createTaskInput(command[0], input);
                    System.out.println("Added:");
                    tasks.add(task);
                    System.out.println(String.format("You have %d tasks", tasks.size()));
                    break;
                case "delete":
                    int dindex = parseInt(command[1]) - 1;
                    if (dindex >= tasks.size() || dindex < 0) {
                        throw new InvalidMarkException("No task at that index");
                    }
                    Task removedTask = tasks.remove(dindex);
                    System.out.println("Removed this task");
                    System.out.println(removedTask.toString());
                    System.out.println(String.format("You have %d tasks", tasks.size()));
                    break;
                default:
                    System.out.println("Invalid input please yap yapology");
                }
            } catch (InvalidDescriptionException | InvalidMarkException | IOException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        }
        System.out.println("Fine. Bye. Leave and never return");
    }

    private static File loadTaskFile() throws IOException {
        //create filepath if it does not exist
        if (!Files.exists(Paths.get("./data/"))) {
            new File("./data/").mkdirs();
        }

        File file = new File("./data/tasks.txt");

        //create file if it does not exist
        file.createNewFile();
        return file;
    }

    private static ArrayList<Task> loadSavedTasks(File taskFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(taskFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] taskDetails = line.split("\\|");

            boolean isCompleted = taskDetails[1].equals("1");

            switch (taskDetails[0]) {
            case "T":
                tasks.add(createTask(TaskType.ToDo, isCompleted, taskDetails[2]));
                break;
            case "D":
                tasks.add(createTask(TaskType.Deadline, isCompleted, taskDetails[2], taskDetails[3]));
                break;
            case "E":
                tasks.add(createTask(TaskType.Event, isCompleted,
                                taskDetails[2], taskDetails[3], taskDetails[4]));
                break;
            }
        }
        return tasks;
    }

    private static void saveLoadedTasks(File taskFile, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        for (Task task : tasks) {
            fw.write(String.format("%s\n", task.exportString()));
        }
        fw.close();
    }

    private static Task createTask(TaskType type, boolean isCompleted, String ... args) {
        Task task = null;
        switch (type) {
        case ToDo:
            task = new ToDo(args[0]);
            task.setCompleted(isCompleted);
            break;
        case Deadline:
            task = new Deadline(args[0], args[1]);
            task.setCompleted(isCompleted);
            break;
        case Event:
            task = new Event(args[0], args[1], args[2]);
            task.setCompleted(isCompleted);
            break;
        }
        return task;
    }

    //To utilise createTask method later
    private static Task createTaskInput(String type, String input) throws InvalidDescriptionException {
        Task task = null;
        String[] format;
        switch (type) {
        case "todo":
            format = input.split("todo ");
            if (format.length <= 1) {
                throw new InvalidDescriptionException("Invalid Task description");
            }
            task = new ToDo(format[1]);
            break;
        case "deadline":
            format = input.split("deadline | /by ");
            if (format.length <= 2) {
                throw new InvalidDescriptionException("Invalid Task description");
            }
            task = new Deadline(format[1], format[2]);
            break;
        case "event":
            format = input.split("event | /from | /to ");
            if (format.length <= 3) {
                throw new InvalidDescriptionException("Invalid Task description");
            }
            task = new Event(format[1], format[2], format[3]);
            break;
        }
        return task;
    }
}
