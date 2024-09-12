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
    private static final String DEFAULT_FILE_PATH = "./data/tasks.txt";
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
        storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.load());
        } catch (ZBotException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for ZBot with default file path.
     */
    public ZBot() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Runs the ZBot application.
     *
     * Reads user input and processes it until the user types "bye".
     */
    public void run() {
        ui.intro();
        storage.createFileIfNotExists();
        String input = ui.readUserInput();

        while (!input.equals("bye")) {
            executeInput(input);
            input = ui.readUserInput();
        }

        ui.outro();
    }

    /**
     * Executes the user input.
     *
     * The input is processed and the corresponding action is taken.
     *
     * @param input User input.
     * @return Response to the user input.
     */
    public String executeInput(String input) {
        String response;

        if (input.equals("list")) {
            response = listTasks();
        } else if (input.startsWith("mark")) {
            response = markTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("unmark")) {
            response = unmarkTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            response = addTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("delete")) {
            response = deleteTask(input, ui);
            storage.save(tasks);
        } else if (input.startsWith("find")) {
            response = findTask(input, ui);
        } else {
            response = "Invalid command!";
        }

        return response;
    }

    /**
     * Adds a task to the task list.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public String addTask(String input, Ui ui) {
        Task task = null;
        String response = "";
        String[] taskComponents = input.split(" ", 2);

        try {
            if (taskComponents[0].equals("deadline")) {
                String[] descriptionDateSplit = taskComponents[1].split(" /by ", 2);
                task = new Deadline(descriptionDateSplit[0], Parser.parseDateTime(descriptionDateSplit[1]));
            } else if (taskComponents[0].equals("event")) {
                String[] descriptionDateSplit = taskComponents[1].split(" /from ", 2);
                String[] fromToSplit = descriptionDateSplit[1].split(" /to ", 2);
                task = new Event(descriptionDateSplit[0],
                        Parser.parseDateTime(fromToSplit[0]),
                        Parser.parseDateTime(fromToSplit[1]));
            } else if (taskComponents[0].equals("todo")) {
                task = new ToDo(taskComponents[1]);
            }

            assert task != null;
            tasks.add(task);
            response = ui.generateAddTaskMsg(task, tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Please enter a valid task format!\n";
        } catch (DateTimeParseException e) {
            response = "Please enter a valid date and time format (dd/MM/yyyy HHmm, dd/MM/yyyy)!\n";
        }

        return response;
    }

    /**
     * Lists all tasks in the task list.
     */
    public String listTasks() {
        StringBuilder response = new StringBuilder();

        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return response.toString();
    }

    /**
     * Modifies a task in the task list.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     * @return Response to the user input.
     */
    private String modifyTask(String input, Ui ui) {
        String response = "";
        Task task = null;

        String[] taskActionIndexSplit = input.split(" ");
        int taskIndex = Integer.parseInt(taskActionIndexSplit[1]);

        try {
            if (taskActionIndexSplit[0].equals("mark")) {
                task = tasks.get(taskIndex - 1);
                task.markAsDone();
                response = ui.generateMarkTaskMsg(task);
            } else if (taskActionIndexSplit[0].equals("unmark")) {
                task = tasks.get(taskIndex - 1);
                task.markAsUndone();
                response = ui.generateUnmarkTaskMsg(task);
            } else if (taskActionIndexSplit[0].equals("delete")) {
                task = tasks.remove(taskIndex - 1);
                response = ui.generateDeleteTaskMsg(task, tasks.size());
            } else {
                assert false;
            }

        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            response = "Please enter a valid task number!\n";
        }

        return response;
    }

    /**
     * Marks a task as done.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public String markTask(String input, Ui ui) {
        return modifyTask(input, ui);
    }

    /**
     * Marks a task as not done.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public String unmarkTask(String input, Ui ui) {
        return modifyTask(input, ui);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public String deleteTask(String input, Ui ui) {
        return modifyTask(input, ui);
    }

    /**
     * Finds tasks with the given keyword.
     *
     * @param input User input.
     * @param ui    User interface of the chatbot.
     */
    public String findTask(String input, Ui ui) {
        StringBuilder response = new StringBuilder();

        String keyword = input.split(" ", 2)[1];
        int tasksFound = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).getDescription().contains(keyword)) {
                continue;
            }

            response.append(String.format("%d. %s\n", i, tasks.get(i)));
            tasksFound++;
        }

        if (tasksFound == 0) {
            response.append("No tasks found.");
        }

        return response.toString();
    }

}
