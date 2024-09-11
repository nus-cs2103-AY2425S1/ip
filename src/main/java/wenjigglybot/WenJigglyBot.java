package wenjigglybot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class WenJigglyBot {
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new WenJigglyBot instance.
     * Initializes the TaskList and Ui, and attempts to load tasks from storage.
     */
    public WenJigglyBot() {
        tasks = new TaskList();

        ui = new Ui();
        try {
            Storage storage = new Storage("./data/data.txt");
            storage.load(tasks);
        } catch (WenJigglyBotException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    /**
     * The main method to start the WenJigglyBot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new WenJigglyBot().run();
    }

    /**
     * Runs the main loop of the WenJigglyBot application.
     * Continuously takes user input, parses commands, and executes the appropriate actions.
     */
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
                command = Parser.parseCommand(task);
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
                    String[] parts = Parser.processDeadlineTask(task);
                    assert parts.length == 2 : "Deadline task should have a task name and deadline";
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
                    String[] processedEvent = Parser.processEventTask(task);
                    addTask(new EventTask(processedEvent[0], processedEvent[1], processedEvent[2]));
                } catch (EventException eventException) {
                    System.out.println(eventException);
                }
                break;
            case FIND:
                strings = task.split(" ");
                String title = strings[1];
                List<Task> matchingTasks = tasks.searchAndListTasks(title);
                ui.displayTasks(new TaskList(matchingTasks));
                break;
            case DELETE:
                strings = task.split(" ");
                idx = Integer.parseInt(strings[1].trim()) - 1;
                deleteTask(idx);
                break;
            case BYE:
                flag = false;
                break;
            default:
                System.out.println("Invalid command!");
            }

        }
        System.out.println("Goodbye!");
    }

    /**
     * Parses and processes a task
     */
    public String parseAndProcess(String task) {
        task = task.trim();
        Command command = null;
        try {
            command = Parser.parseCommand(task);
        } catch (InvalidCommandException e) {
            System.out.println(e);
            return "Invalid command!";
        }

        String[] strings;
        String action;
        int idx;
        String taskName;

        switch (Objects.requireNonNull(command)) {
        case LIST:
            return ui.displayTasks(tasks);
        case MARK:
            strings = task.split(" ");
            action = "mark";
            idx = Integer.parseInt(strings[1].trim()) - 1;
            return toggleTask(action, idx);
        case UNMARK:
            action = "unmark";
            strings = task.split(" ");
            idx = Integer.parseInt(strings[1].trim()) - 1;
            return toggleTask(action, idx);
        case TODO:
            taskName = task.replaceFirst("todo", "").trim();
            return addTask(new ToDoTask(taskName));
        case DEADLINE:
            try {
                String[] parts = Parser.processDeadlineTask(task);
                taskName = parts[0].trim();
                String deadline = parts[1].trim();
                LocalDate date;
                try {
                    date = LocalDate.parse(deadline);
                    return addTask(new DeadlineTask(taskName, date));
                } catch (DateTimeParseException e) {
                    System.out.println("Incorrect date format, please input in yyyy-mm-dd format!");
                    return "Incorrect date format, please input in yyyy-mm-dd format!";
                }
            } catch (DeadlineException deadlineException) {
                System.out.println(deadlineException);
                return deadlineException.toString();
            }
        case EVENT:
            // Split the string by "/from" and "/to"
            try {
                String[] processedEvent = Parser.processEventTask(task);
                return addTask(new EventTask(processedEvent[0], processedEvent[1], processedEvent[2]));
            } catch (EventException eventException) {
                System.out.println(eventException);
                return eventException.toString();
            }
        case FIND:
            strings = task.split(" ");
            String title = strings[1];
            List<Task> matchingTasks = tasks.searchAndListTasks(title);
            if (matchingTasks.isEmpty()) {
                return "No tasks found :(";
            }
            return ui.displayTasks(new TaskList(matchingTasks));
        case DELETE:
            strings = task.split(" ");
            idx = Integer.parseInt(strings[1].trim()) - 1;
            return deleteTask(idx);
        case BYE:
            return "Bye bye!";
        default:
            System.out.println("Invalid command!");
        }
        System.out.println("Goodbye!");
        return "";
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param idx The index of the task to delete.
     */
    private String deleteTask(int idx) {
        if (idx < 0 || idx > tasks.size() - 1) {
            System.out.println("You entered an invalid index you fool!");
            return "You entered an invalid index you fool!";
        }
        StringBuilder output = new StringBuilder();
        ui.showLine();
        output.append(ui.showDeleteTask(tasks, idx) + "\n");
        tasks.remove(idx);
        Storage.saveTasksToFile(tasks);
        output.append(ui.showTaskCount(tasks));
        ui.showLine();
        return output.toString();
    }

    /**
     * Toggles the completion status of the task at the specified index.
     *
     * @param action The action to perform ("mark" or "unmark").
     * @param idx    The index of the task to toggle.
     */
    private String toggleTask(String action, int idx) {
        // Handle invalid index
        if (idx < 0 || idx > tasks.size() - 1) {
            System.out.println("You entered an invalid index you fool!");
            return "You entered an invalid index you fool!";
        }
        Task task = tasks.get(idx);
        StringBuilder output = new StringBuilder();
        if (action.equals("mark")) {
            ui.showLine();
            task.markTask();
            Storage.saveTasksToFile(tasks);
            output.append(ui.showCompletedTask(task));
            ui.showLine();
        } else {
            ui.showLine();
            task.unmarkTask();
            output.append(ui.showUncompletedTask(task));
            Storage.saveTasksToFile(tasks);
            ui.showLine();
        }
        return output.toString();
    }

    /**
     * Adds a new task to the task list and saves it to storage.
     *
     * @param task The task to add.
     */
    private String addTask(Task task) {
        assert task != null : "Task to add cannot be null";
        StringBuilder response = new StringBuilder("");
        tasks.addTask(task);
        Storage.saveTasksToFile(tasks);
        ui.showLine();
        response.append(ui.showAddedTask(task));
        response.append("\n");
        response.append(ui.showTaskCount(tasks));
        ui.showLine();
        return response.toString();
    }

    public String getResponse(String meow) {
        return "Meow";
    }
}