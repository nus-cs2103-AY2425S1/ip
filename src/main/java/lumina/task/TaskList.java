package lumina.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lumina.exception.LuminaException;
import lumina.parser.Parser;
import lumina.ui.Ui;

/**
 * Manages a list of tasks and handles various operations such as adding,
 * listing, and marking tasks as done or not done.
 */
public class TaskList {

    // tasks
    private ArrayList<Task> tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a TaskList with the specified Ui and Parser.
     *
     * @param ui the Ui instance for displaying messages to the user
     * @param parser the Parser instance for parsing inputs
     */
    public TaskList(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task the Task to be added to the list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        StringBuilder addTaskMessage = new StringBuilder();
        addTaskMessage.append("Got it. I've added this task:\n");
        addTaskMessage.append(ui.indentMessage(task.toString())).append("\n");
        addTaskMessage.append(String.format("Now you have %d tasks in the list.", tasks.size()));
        ui.printMessage(addTaskMessage.toString());
    }

    /**
     * Lists all tasks in the list and prints them.
     */
    public void listTasks() {
        StringBuilder listedTaskMessage = new StringBuilder();
        listedTaskMessage.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            listedTaskMessage.append(Integer.toString(i + 1)).append(".");
            listedTaskMessage.append(this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                listedTaskMessage.append("\n");
            }
        }
        ui.printMessage(listedTaskMessage.toString());
    }

    /**
     * Converts all tasks in the list to a list of strings for saving purposes.
     *
     * @return an ArrayList of strings representing the save strings
     */
    public ArrayList<String> toLines() {
        return tasks.stream()
                .map(Task::saveString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Marks the specified task as done and prints a confirmation message.
     *
     * @param index the index of the task to be marked as done
     * @throws LuminaException if the index is out of bounds
     */
    public void markTaskDone(int index) throws LuminaException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new LuminaException("Oh no! Lumina detected index out of bounds! Please try again");
        }
        StringBuilder taskDoneMessage = new StringBuilder();
        taskDoneMessage.append("Nice! I've marked this task as done:\n");
        this.tasks.get(index).markAsDone();
        taskDoneMessage.append(ui.indentMessage(this.tasks.get(index).toString()));
        ui.printMessage(taskDoneMessage.toString());
    }

    /**
     * Marks the specified task as not done and prints a confirmation message.
     *
     * @param index the index of the task to be marked as not done
     * @throws LuminaException if the index is out of bounds
     */
    public void markTaskNotDone(int index) throws LuminaException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new LuminaException("Oh no! Lumina detected index out of bounds! "
                    + "Please try again");
        }
        StringBuilder taskNotDoneMessage = new StringBuilder();
        taskNotDoneMessage.append("OK, I've marked this task as not done yet:\n");
        this.tasks.get(index).markAsNotDone();
        taskNotDoneMessage.append(ui.indentMessage(this.tasks.get(index).toString()));
        ui.printMessage(taskNotDoneMessage.toString());
    }

    /**
     * Handles the creation of a Todo task based on the provided message.
     * The message should be in the format: todo `description`
     *
     * @param msg the message containing the task description
     * @throws LuminaException if the message format is invalid
     */
    public void handleTodoTask(String msg) throws LuminaException {
        String[] msgSplit = msg.split(" ");
        if (msgSplit.length < 2) {
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "ToDo Task! Please try again");
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < msgSplit.length; i++) {
            builder.append(msgSplit[i]);
            if (i < msgSplit.length - 1) {
                builder.append(" ");
            }
        }
        Task task = new TodoTask(builder.toString());
        this.addTask(task);

    }

    /**
     * Handles the creation of a Deadline task based on the provided message.
     * The message should be in the format: "deadline `description` /by `dateTime`"
     *
     * @param msg the message containing the task description and deadline
     * @throws LuminaException if the message format is invalid or missing required parts
     */

    public void handleDeadlineTask(String msg) throws LuminaException {
        String[] msgSplit = msg.split(" ");
        if (msgSplit.length < 4) { // need desc and bydatetime
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "Deadline Task! Please try again");
        }

        StringBuilder builder = new StringBuilder();
        String desc = "";
        String byDateTime = "";
        boolean by = false;

        for (int i = 1; i < msgSplit.length; i++) {
            if (msgSplit[i].equals("/by")) {
                if (by) {
                    throw new LuminaException("Oh no! Lumina detected invalid format for "
                            + "your Deadline Task.Task! Please try again");
                }
                by = true;
                desc = builder.toString().trim();
                builder = new StringBuilder();
                continue;
            }
            builder.append(msgSplit[i]);
            if (i < msgSplit.length - 1) {
                builder.append(" ");
            }
        }

        if (!by) {
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "Deadline Task! Please try again");
        }
        byDateTime = builder.toString().trim();
        if (desc.isEmpty() || byDateTime.isEmpty()) {
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "Deadline Task! Please try again");
        }
        LocalDate byDateObject = parser.parseDateString(byDateTime);
        Task task = new DeadlineTask(desc, byDateObject);
        this.addTask(task);
    }

    /**
     * Deletes a task from the list based on the specified index and prints a confirmation message.
     *
     * @param index the index of the task to be deleted
     * @throws LuminaException if the index is out of bounds
     */
    public void deleteTask(int index) throws LuminaException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new LuminaException("Oh no! Lumina detected index out of bounds! "
                    + "Please try again");
        }
        StringBuilder deleteTaskMessage = new StringBuilder();
        deleteTaskMessage.append("Noted. I've removed this task:\n");
        deleteTaskMessage.append(ui.indentMessage(this.tasks.get(index).toString()));
        deleteTaskMessage.append("\n");
        this.tasks.remove(index);
        deleteTaskMessage.append(String.format("Now you have %d tasks in the list.",
                tasks.size()));
        ui.printMessage(deleteTaskMessage.toString());
    }
    /**
     * Searches for tasks in the list that contain the specified search string in their description.
     * @param searchString the string to search for within task descriptions
     */
    public void findTasks(String searchString) {
        StringBuilder findTasksMessage = new StringBuilder();
        findTasksMessage.append("Here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).findInDescription(searchString)) {
                if (count > 1) {
                    findTasksMessage.append("\n");
                }
                findTasksMessage.append(Integer.toString(count)).append(".");
                findTasksMessage.append(this.tasks.get(i));
                count++;
            }
        }
        ui.printMessage(findTasksMessage.toString());
    }

    /**
     * Handles the creation of an Event task based on the provided message.
     * The message should be in the format: "event `description` /from `startDateTime` /to `endDateTime`"
     *
     * @param msg the message containing the task description, start date/time, and end date/time
     * @throws LuminaException if the message format is invalid or missing required parts
     */
    public void handleEventTask(String msg) throws LuminaException {
        String[] msgSplit = msg.split(" ");
        if (msgSplit.length < 6) { // need desc, startDateTime, endDateTime
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "Event Task! Please try again");
        }

        enum Type {
            DESC, FROM, TO
        }

        Type currentType = Type.DESC;

        StringBuilder builder = new StringBuilder();
        String desc = "";
        String startDateTime = "";
        String endDateTime = "";
        boolean from = false;
        boolean to = false;
        for (int i = 1; i < msgSplit.length; i++) {
            if (msgSplit[i].equals("/from")) {
                if (from) {
                    throw new LuminaException("Oh no! Lumina detected invalid format for "
                            + "your Event Task! Please try again");
                }
                switch (currentType) {
                case DESC:
                    desc = builder.toString().trim();
                    break;
                case FROM:
                    startDateTime = builder.toString().trim();
                    break;
                case TO:
                    endDateTime = builder.toString().trim();
                    break;
                default:
                    break;
                }
                builder = new StringBuilder();
                from = true;
                currentType = Type.FROM;
                continue;
            }
            if (msgSplit[i].equals("/to")) {
                if (to) {
                    throw new LuminaException("Oh no! Lumina detected invalid format for "
                            + "your Event Task! Please try again");
                }
                switch (currentType) {
                case DESC:
                    desc = builder.toString().trim();
                    break;
                case FROM:
                    startDateTime = builder.toString().trim();
                    break;
                case TO:
                    endDateTime = builder.toString().trim();
                    break;
                default:
                    break;
                }

                builder = new StringBuilder();
                to = true;
                currentType = Type.TO;
                continue;
            }
            builder.append(msgSplit[i]);
            if (i < msgSplit.length - 1) {
                builder.append(" ");
            }
        }

        if (!from || !to) {
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "Event Task! Please try again");
        }
        switch (currentType) {
        case DESC:
            desc = builder.toString().trim();
            break;
        case FROM:
            startDateTime = builder.toString().trim();
            break;
        case TO:
            endDateTime = builder.toString().trim();
            break;
        default:
            break;
        }
        if (desc.trim().isEmpty() || startDateTime.trim().isEmpty() || endDateTime.trim().isEmpty()) {
            throw new LuminaException("Oh no! Lumina detected invalid format for your "
                    + "Event Task! Please try again");
        }
        LocalDate startDateObject = parser.parseDateString(startDateTime);
        LocalDate endDateObject = parser.parseDateString(endDateTime);
        Task task = new EventTask(desc, startDateObject, endDateObject);
        this.addTask(task);
    }

    /**
     * Sets the data for the task list.
     *
     * @param data an ArrayList of tasks to set
     */
    public void setData(ArrayList<Task> data) {
        tasks = data;
    }
}
