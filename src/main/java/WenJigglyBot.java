import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class WenJigglyBot {
    private TaskList tasks;
    private Ui ui;

    public WenJigglyBot() {
        tasks = new TaskList();
        ui = new Ui();
        try {
            loadTasksFromStorage();
        } catch (WenJigglyBotException e) {

        }
    }

    public static void main(String[] args) {
        new WenJigglyBot().run();
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        ui.intro();
        String task;
        boolean flag = true;
        while (flag) {
            task = scanner.nextLine();
            task = task.trim();
            Command command = null;
            try {
                command = parseCommand(task);
            } catch (InvalidCommandException e) {
                System.out.println(e);
                continue;
            }

            String[] strings;
            String action;
            int idx;
            String taskName;

            switch (Objects.requireNonNull(command)) {
            case LIST:
                ui.displayTasks(tasks);
                break;
            case MARK:
                strings = task.split(" ");
                action = "mark";
                idx = Integer.parseInt(strings[1].trim()) - 1;
                toggleTask(action, idx);
                break;
            case UNMARK:
                action = "unmark";
                strings = task.split(" ");
                idx = Integer.parseInt(strings[1].trim()) - 1;
                toggleTask(action, idx);
                break;
            case TODO:
                taskName = task.replaceFirst("todo", "").trim();
                addTask(new ToDoTask(taskName));
                break;
            case DEADLINE:
                try {
                    String[] parts = processDeadlineTask(task);
                    taskName = parts[0].trim();
                    String deadline = parts[1].trim();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(deadline);
                        addTask(new DeadlineTask(taskName, date));
                    } catch (DateTimeParseException e) {
                        System.out.println("Incorrect date format, please input in yyyy-mm-dd format!");
                    }
                } catch (DeadlineException deadlineException) {
                    System.out.println(deadlineException);
                }
                break;
            case EVENT:
                // Split the string by "/from" and "/to"
                try {
                    String[] processedEvent = processEventTask(task);
                    addTask(new EventTask(processedEvent[0], processedEvent[1], processedEvent[2]));
                } catch (EventException eventException) {
                    System.out.println(eventException);
                }
                break;
            case DELETE:
                strings = task.split(" ");
                idx = Integer.parseInt(strings[1].trim()) - 1;
                deleteTask(idx);
                break;
            case BYE:
                flag = false;
                break;

            }
        }
        System.out.println("Goodbye!");
    }

    private void loadTasksFromStorage() throws WenJigglyBotException {
        File file = new File("./data/data.txt");

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            System.out.println("Tasks loaded from " + file.getPath());
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }

    private static Task parseTask(String line) {
        // Example format: 1. [T][X] task description
        String[] parts = line.split(" ", 3);
        if (parts.length < 3) {
            return null;
        }

        String taskType = parts[1].substring(1, 2);
        boolean isDone = parts[1].charAt(3) == 'X';
        String description = parts[2];

        switch (taskType) {
        case "T": // ToDo task
            ToDoTask todo = new ToDoTask(description.split(" ")[1]);
            if (isDone) {
                todo.markTask();
            }
            return todo;
        case "D": // Deadline task
            // Format: [D][ ] description (by: date/time)
            String[] deadlineParts = description.split("\\(by: ");
            if (deadlineParts.length == 2) {
                String unprocessedTaskDescription = deadlineParts[0].trim();
                String[] splitted = unprocessedTaskDescription.split(" ");
                String taskDescription;
                if (splitted.length != 1) {
                    taskDescription = splitted[1];
                } else {
                    taskDescription = splitted[0];
                }
                String deadline = deadlineParts[1].replace(")", "").trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                DeadlineTask deadlineTask = new DeadlineTask(taskDescription, LocalDate.parse(deadline, formatter));
                if (isDone) {
                    deadlineTask.markTask();
                }
                return deadlineTask;
            }
            break;
        case "E": // Event task
            // Format: [E][ ] description (from: start time to: end time)
            String[] eventParts = description.split("\\(from: ");
            if (eventParts.length == 2) {
                String taskDescription = eventParts[0].trim();
                String[] timeParts = eventParts[1].split(" to: ");
                if (timeParts.length == 2) {
                    String startTime = timeParts[0].trim();
                    String endTime = timeParts[1].replace(")", "").trim();
                    EventTask eventTask = new EventTask(taskDescription, startTime, endTime);
                    if (isDone) {
                        eventTask.markTask();
                    }
                    return eventTask;
                }
            }
            break;
        }

        return null; // return null if the task could not be parsed
    }

    private static Command parseCommand(String command) throws InvalidCommandException {
        for (Command cmd : Command.values()) {
            if (command.startsWith(cmd.name().toLowerCase())) {
                return cmd;
            }
        }
        throw new InvalidCommandException();
    }

    private static String[] processEventTask(String task) throws EventException {
        String[] fromParts = task.split("/from");
        if (fromParts.length != 2) {
            throw new EventException();
        }
        String[] toParts = fromParts[1].split("/to");
        if (toParts.length != 2) {
            throw new EventException();
        }
        // Extract the event description, start time, and end time
        String event = fromParts[0].replaceFirst("event", "").trim();
        String startTime = toParts[0].trim();
        String endTime = toParts[1].trim();
        return new String[]{event, startTime, endTime};
    }

    private static String[] processDeadlineTask(String task) throws DeadlineException {
        // remove deadline tag
        String taskNameAndDeadline = task.replaceFirst("deadline", "").trim();

        // split the title and deadline
        String[] parts = taskNameAndDeadline.split("/by");
        if (parts.length != 2) {
            throw new DeadlineException();
        }
        return parts;
    }

    private void deleteTask(int idx) {
        if (idx < 0 || idx > tasks.size() - 1) {
            System.out.println("You entered an invalid index you fool!");
            return;
        }
        ui.showLine();
        ui.showDeleteTask(tasks, idx);
        tasks.remove(idx);
        ui.showTaskCount(tasks);
        ui.showLine();
    }

    private void toggleTask(String action, int idx) {
        // handle invalid index
        if (idx < 0 || idx > tasks.size() - 1) {
            System.out.println("You entered an invalid index you fool!");
            return;
        }
        Task task = tasks.get(idx);
        if (action.equals("mark")) {
            ui.showLine();
            task.markTask();
            ui.showCompletedTask(task);
            ui.showLine();
        } else {
            ui.showLine();
            tasks.get(idx).unmarkTask();
            ui.showUncompletedTask(task);
            ui.showLine();
        }
    }

    private void addTask(Task task) {
        tasks.addTask(task);
        saveTasksToFile();
        ui.showLine();
        ui.showAddedTask(task);
        ui.showTaskCount(tasks);
        ui.showLine();
    }


    private String tasksToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    private void saveTasksToFile() {
        String tasksString = tasksToString();
        // Create a File object for the directory
        File directory = new File("./data");

        // If the directory doesn't exist, create it
        if (!directory.exists()) {
            directory.mkdirs(); // This will create the directory and any necessary but nonexistent parent directories
        }
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(tasksString);
            System.out.println("Tasks saved to " + "data.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }
}