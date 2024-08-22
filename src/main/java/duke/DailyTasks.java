package duke;

import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.tasks.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class DailyTasks {
    public static final String BOT_NAME = "DailyTasks";
    public static final String GREETING = "Hello! I'm " + BOT_NAME + ", your awesome task planner!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static final String STATE_FILE_DIRECTORY = "./data";
    private static final String STATE_FILE = "save.txt";

    private final TaskManager taskManager;

    public DailyTasks() {
        this.taskManager = new TaskManager();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DailyTasks dailyTasks = new DailyTasks();

        try {
            List<Task> tasks = DailyTasks.loadStateFileToTasksList();
            dailyTasks.taskManager.setTasks(tasks);

            dailyTasks.start(scanner);

            DailyTasks.saveTasksListToStateFile(dailyTasks.taskManager.getTasks());
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
    }

    private static List<Task> loadStateFileToTasksList() throws IOException {
        Path dirPath = Paths.get(DailyTasks.STATE_FILE_DIRECTORY);
        Path filePath = dirPath.resolve(DailyTasks.STATE_FILE);

        if (Files.notExists(dirPath)) { // create the SAVE_DIRECTORY folder if it doesn't exist
            Files.createDirectories(dirPath);
        }

        if (Files.notExists(filePath)) { // create an empty file initially
            Files.createFile(filePath);
        }

        List<String> lines = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath);
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        // convert the state file `lines` to an arraylist of tasks
        List<Task> tasks = new ArrayList<>();
        for (String state : lines) {
            tasks.add(DailyTasks.convertStateToTask(state));
        }

        return tasks;
    }

    private static Task convertStateToTask(String state) {
        // format of state file below
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | project meeting | Aug 6th 2-4pm
        String[] taskInformation = state.split(" \\| ");
        String description = taskInformation[2];
        Task task;

        if (taskInformation[0].equals("T")) {
            task = new ToDos(description);
        } else if (taskInformation[0].equals("D")) {
            String by = taskInformation[3];
            task = new Deadline(description, by);
        } else {
            String[] times = taskInformation[3].split("-");
            String from = times[0];
            String to = times[1];

            task = new Event(description, from, to);
        }

        if (taskInformation[1].equals("1")) {
            task.setDone();
        }

        return task;
    }

    private static void saveTasksListToStateFile (List<Task> tasks) throws IOException {
        Path dirPath = Paths.get(DailyTasks.STATE_FILE_DIRECTORY);
        Path filePath = dirPath.resolve(DailyTasks.STATE_FILE);

        BufferedWriter writer = Files.newBufferedWriter(filePath);
        List<String> stateFile = new ArrayList<>();

        for (Task task : tasks) {
            stateFile.add(DailyTasks.convertTaskToState(task));
        }

        for (String line : stateFile) {
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    private static String convertTaskToState(Task task) {
        StringBuilder str = new StringBuilder();
        str.append(task.getStatusIcon().equals("X") ? "| 1 " : "| 0 ");
        str.append("| ");
        str.append(task.getDescription());
        str.append(" ");

        if (task instanceof ToDos) {
            str.insert(0, "T ");
        } else if (task instanceof Deadline deadline) {
            str.insert(0, "D ");
            str.append("| ");
            str.append(deadline.getBy());
        } else if (task instanceof Event event) {
            str.insert(0, "E ");
            str.append("| ");
            str.append(event.getFrom());
            str.append("-");
            str.append(event.getTo());
        }

        return str.toString();
    }

    private void start(Scanner scanner) {
        System.out.println(Formatter.formatOutputMessage(GREETING));

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                System.out.println(Formatter.formatOutputMessage(GOODBYE));
                break;
            }

            handleCommand(userInput);
        }
    }

    private void handleCommand(String userInput) {
        try {
            if (userInput.equals("list")) {
                System.out.println(Formatter.formatTaskListings(taskManager.getTasks()));
            } else if (userInput.startsWith("unmark")) {
                handleMarkingTask(userInput, false);
            } else if (userInput.startsWith("mark")) {
                handleMarkingTask(userInput, true);
            } else if (userInput.startsWith("delete")) {
                handleDeleteTask(userInput);
            } else { // we try to add a task (todos/ deadline/ event) else throw an exception
                taskManager.addTask(userInput);
                System.out.println(Formatter.formatAddTask(taskManager.getTasks().size(),
                        taskManager.getLastTask()));
            }
        } catch (UnknownMessageException | EmptyTodoDescriptionException e) {
            System.out.println(Formatter.formatOutputMessage("Please enter a valid task!"));
        }
    }

    private void handleMarkingTask(String userInput, boolean markAsDone) {
        int index = parseTaskIndex(userInput);

        if (index != -1) {
            Task task = taskManager.getTask(index);
            if (markAsDone) {
                task.setDone();
                System.out.println(Formatter.formatMarkTask(task));
            } else {
                task.setNotDone();
                System.out.println(Formatter.formatUnmarkTask(task));
            }
        }
    }

    private void handleDeleteTask(String userInput) {
        int index = parseTaskIndex(userInput);

        if (index != -1) {
            Task task = taskManager.deleteTask(index);
            System.out.println(Formatter.formatDeleteTask(task, taskManager.getTasks().size()));
        }
    }

    private int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task number provided.");
            return -1;
        }
    }
}
