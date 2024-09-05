package cleo;

import java.io.IOException;
import java.util.Scanner;

import cleo.task.Deadline;
import cleo.task.Events;
import cleo.task.Task;
import cleo.task.TaskList;
import cleo.task.ToDos;

/**
 * Represents the Cleo chatbot and its tasks.
 */
public class Cleo {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Loads the available tasks file if there is in the folder.
     *
     * @param filePath is a string that contains path of file that store taskslist.
     */
    public Cleo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the Cleo application, taking user input and executing corresponding commands.
     *
     */
    public void run() {
        ui.displayWelcomeMessage();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("You: ");
                String userInput = scanner.nextLine();
                Parser.CommandType command = Parser.parseCommand(userInput);
                ui.showLineSeparator();
                try {
                    switch (command) {
                    case BYE -> {
                        System.out.println("Cleo: Goodbye, hope to see you again soon! :)");
                        return;
                    }
                    case HI -> System.out.println("Cleo: Hi there! How can I help you today?:)");
                    case LIST -> tasks.listTasks();
                    case FIND -> findTask(userInput.substring(4).trim());
                    case MARK -> {
                        int taskNumber = Parser.parseTaskNumber(userInput.substring(5), tasks.size());
                        tasks.getTask(taskNumber).setDone();
                        storage.saveTasks(tasks); // Save tasks to file
                    }
                    case UNMARK -> {
                        int taskNumber = Parser.parseTaskNumber(userInput.substring(7), tasks.size());
                        tasks.getTask(taskNumber).setUndone();
                        storage.saveTasks(tasks); // Save tasks to file
                    }
                    case DELETE -> {
                        int taskNumber = Parser.parseTaskNumber(userInput.substring(7), tasks.size());
                        tasks.removeTask(taskNumber);
                        storage.saveTasks(tasks); // Save tasks to file
                    }
                    case TODO -> {
                        addTodoTask(userInput.substring(4).trim());
                        storage.saveTasks(tasks); // Save tasks to file
                    }
                    case DEADLINE -> {
                        addDeadlineTask(userInput.substring(8).trim());
                        storage.saveTasks(tasks); // Save tasks to file
                    }
                    case EVENT -> {
                        addEventTask(userInput.substring(5).trim());
                        storage.saveTasks(tasks); // Save tasks to file
                    }
                    case INVALID -> System.out.println("Cleo: Invalid command!");
                    default -> System.out.println("Cleo: Unrecognised command!");
                    }
                } catch (CleoException e) {
                    System.out.println("Cleo: " + e.getMessage());
                }
            }
        }
    }
    /**
     * Searches for tasks in the task list based on the provided keyword.
     *
     * @param input the keyword to search for in the task descriptions.
     * @throws CleoException if the input is empty or no matching tasks are found.
     */
    private void findTask(String input) throws CleoException {
        try {
            int count = 0;
            if (input.isEmpty()) {
                throw new CleoException("Enter the keyword you want to search for.");
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                if (task.getDescription().contains(input)) {
                    result.append((count + 1) + "." + task + "\n");
                    count++;
                }
            }
            if (count == 0) {
                throw new CleoException("No matching tasks found in list!");
            } else {
                System.out.println("Cleo: Here are the matching task(s) in your list:");
                System.out.println(result.toString());
            }
        } catch (CleoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param input A string containing the description of the todo task to be added.
     * @throws CleoException if the input description is empty.
     */
    private void addTodoTask(String input) throws CleoException {
        try {
            if (input.isEmpty()) {
                throw new CleoException("Oops! The description of a todo cannot be empty.");
            }
            tasks.addTask(new ToDos(input));
        } catch (CleoException e) {
            throw new CleoException("Please enter a todo description!");
        }
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param input A string containing the description and deadline of the task, separated by '/by'.
     * @throws CleoException If the deadline description or date is empty, or if the deadline is in the past.
     */
    private void addDeadlineTask(String input) throws CleoException {
        try {
            String[] parts = input.split("/by", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CleoException("Oops! The deadline description or date cannot be empty!");
            }
            tasks.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
        } catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
    }
    /**
     * Adds an event task to the task list.
     *
     * @param input A string containing the description of the event, and start and end times,
     *          separated by '/from' and '/to'.
     * @throws CleoException If the event description, start time, or end time is empty,
     *          or if the start time is after the end time.
     */
    private void addEventTask(String input) throws CleoException {
        try {
            String[] parts = input.split("/from", 2);

            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            String[] time = parts[1].split("/to", 2);
            if (time.length < 2 || time[0].trim().isEmpty() || time[1].trim().isEmpty()) {
                throw new CleoException("Oops! Please specify both start and end times for the event!");
            }
            tasks.addTask(new Events(parts[0].trim(), time[0].trim(), time[1].trim()));
        } catch (IllegalArgumentException e) {
            throw new CleoException(e.getMessage());
        }
    }

    /**
     * Runs the main entry point of the Cleo application.
     *
     * @param  args  Command line arguments (not used in this implementation).
     * @throws CleoException if an error occurs during the execution of the Cleo application.
     */
    public static void main(String[] args) throws CleoException {
        new Cleo("./data/cleo.txt").run();
    }

}
