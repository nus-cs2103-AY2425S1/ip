package max.main;

import max.exception.MaxException;
import max.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It processes the input from the user, interacts with the TaskList, and handles
 * task-related operations like adding, deleting, and marking tasks.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Parser object with the given TaskList, Ui, and Storage components.
     *
     * @param taskList The list of tasks to be managed.
     * @param ui The UI component used for interacting with the user.
     * @param storage The storage component for saving and loading tasks.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Default constructor for the Parser class.
     */
    public Parser() {

    }

    /**
     * Reads user input and processes commands in a loop until the "bye" command is issued.
     *
     * @param scanner The Scanner object used to read user input.
     * @throws MaxException If an invalid command is given or an error occurs during execution.
     */
    public void parseText(Scanner scanner) throws MaxException {
        boolean running = true;

        while (running) {
            String text = scanner.nextLine().trim();
            try {
                if (text.equals("bye")) {
                    running = false;
                } else if (text.equals("list")) {
                    ui.list(taskList.getTasks());
                } else if (text.startsWith("mark")) {
                    int index = Integer.parseInt(text.replace("mark ", "")) - 1;
                    Task task = taskList.getTask(index);
                    task.markDone();
                    ui.printMarkDone(task);
                } else if (text.startsWith("unmark")) {
                    int index = Integer.parseInt(text.replace("unmark ", "")) - 1;
                    Task task = taskList.getTask(index);
                    task.markNotDone();
                    ui.printMarkNotDone(task);
                } else if (text.startsWith("deadline")) {
                    handleDeadline(text);
                } else if (text.startsWith("todo")) {
                    handleTodo(text);
                } else if (text.startsWith("event")) {
                    handleEvent(text);
                } else if (text.startsWith("delete")){
                    int index = Integer.parseInt(text.replace("delete ", "")) - 1;
                    Task task = taskList.getTask(index);
                    taskList.deleteTask(index);
                    ui.printDeleteTask(task, taskList.getSize());
                } else {
                    throw new MaxException("What does that mean?:( Begin with todo, event, or deadline.");
                }
                this.storage.saveTasks(taskList.getTasks());
            } catch (MaxException e) {
                ui.printLine();
                ui.printMessage(e.getMessage());
            }
        }


    }

    /**
     * Handles the creation of a new Deadline task.
     *
     * @param text The user input text containing the task description and due date.
     * @throws MaxException If the task description or date format is invalid.
     */
    private void handleDeadline(String text) throws MaxException {
        String[] temp = text.replace("deadline ", "").split(" /by ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        LocalDateTime LDT = parseDate(temp[1]);
        Deadline deadline = (LDT != null) ? new Deadline(temp[0], LDT) : new Deadline(temp[0], temp[1]);
        taskList.addTask(deadline);
        ui.printTaskTypeAdded(deadline, taskList.getSize());
    }

    /**
     * Handles the creation of a new Event task.
     *
     * @param text The user input text containing the task description and event period.
     * @throws MaxException If the task description or event period is invalid.
     */
    private void handleEvent(String text) throws MaxException {
        String[] temp = text.replace("event ", "").split(" /from ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        Event event = new Event(temp[0], temp[1]);
        taskList.addTask(event);
        ui.printTaskTypeAdded(event, taskList.getSize());
    }

    /**
     * Handles the creation of a new Todo task.
     *
     * @param text The user input text containing the task description.
     * @throws MaxException If the task description is empty.
     */
    private void handleTodo(String text) throws MaxException {
        String temp = text.replace("todo", "").trim();
        checkTask(temp);

        Todo todo = new Todo(temp);
        taskList.addTask(todo);
        ui.printTaskTypeAdded(todo, taskList.getSize());
    }

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date.
     * @throws MaxException If the date format is invalid.
     */
    public LocalDateTime parseDate(String date) throws MaxException {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime LDT = LocalDateTime.parse(date, converter);
            return LDT;
        } catch (DateTimeParseException e){
            throw new MaxException("Invalid date format! Please use d/M/yyyy HHmm. "
                    + "For example, '2/12/2024 1800'");
        }
    }

    /**
     * Checks if the given task description is empty.
     *
     * @param todo The task description to be checked.
     * @throws MaxException If the task description is empty.
     */
    public void checkTask(String todo) throws MaxException {
        if (todo.isEmpty()) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
    }
}
