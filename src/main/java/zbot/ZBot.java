package zbot;

import java.time.format.DateTimeParseException;

import zbot.task.Deadline;
import zbot.task.Event;
import zbot.task.Task;
import zbot.task.ToDo;

/**
 * Represents the main class of the ZBot application.
 */
public class ZBot {
    private static final String SAVE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for ZBot.
     *
     * @param filepath Path of the file to store tasks.
     */
    public ZBot(String filepath) {
        ui = new Ui();
        storage = new Storage(SAVE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (ZBotException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new ZBot(SAVE_PATH).run();
    }

    /**
     * Runs the ZBot application.
     *
     * Reads user input and processes it until the user types "bye".
     */
    public void run() {
        ui.intro();
        storage.createFileIfNotExists();
        String input = ui.readCommand();
        while (!input.equals("bye")) {
            executeInput(input, ui, storage);
            input = ui.readCommand();
        }

        ui.outro();
    }

    /**
     * Executes the user input.
     *
     * The input is processed and the corresponding action is taken.
     *
     * @param input   User input.
     * @param ui      User interface of the chatbot.
     * @param storage Storage of the chatbot.
     */
    public void executeInput(String input, Ui ui, Storage storage) {
        if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("mark")) {
            markTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("unmark")) {
            unmarkTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            addTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("delete")) {
            deleteTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("find")) {
            findTask(input, ui);
        } else {
            System.out.println("Invalid command.\n");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public void addTask(String input, Ui ui) {
        Task task;
        String[] inputParts = input.split(" ", 2);

        try {
            if (inputParts[0].equals("deadline")) {
                String[] deadlineParts = inputParts[1].split(" /by ", 2);
                task = new Deadline(deadlineParts[0], Parser.parseDateTime(deadlineParts[1]));
            } else if (inputParts[0].equals("event")) {
                String[] eventParts = inputParts[1].split(" /from ", 2);
                String[] period = eventParts[1].split(" /to ", 2);
                task = new Event(eventParts[0],
                        Parser.parseDateTime(period[0]),
                        Parser.parseDateTime(period[1]));
            } else {
                task = new ToDo(inputParts[1]);
            }

            tasks.add(task);
            ui.printAddTaskMsg(task, tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task format!\n");
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Please enter a valid date and time format (dd/MM/yyyy HHmm, dd/MM/yyyy)!\n");
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        System.out.println();
    }

    /**
     * Marks a task as done.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public void markTask(String input, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markAsDone();
            ui.printMarkTaskMsg(tasks.get(taskNumber - 1));
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number!\n");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public void unmarkTask(String input, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markAsUndone();
            ui.printUnmarkTaskMsg(tasks.get(taskNumber - 1));
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number!\n");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public void deleteTask(String input, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            Task task = tasks.remove(taskNumber - 1);
            ui.printDeleteTaskMsg(task, tasks.size());
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a valid task number!\n");
        }
    }

    /**
     * Finds tasks with the given keyword.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public void findTask(String input, Ui ui) {
        String keyword = input.split(" ", 2)[1];
        int tasksFound = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                tasksFound++;
                System.out.println(String.format("%d. %s", i, tasks.get(i)));
            }
        }

        if (tasksFound == 0) {
            System.out.println("No tasks found.");
        }
        System.out.println();
    }
}
