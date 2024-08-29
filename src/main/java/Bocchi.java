import command.Command;
import exception.BocchiException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Represents a chatbot that can manage tasks.
 */
public class Bocchi {
    /**
     * The list of tasks.
     */
    private TaskList taskList;

    /**
     * The loader and saver for the task list.
     */
    private Storage storage = new Storage();

    /**
     * The user interface.
     */
    private Ui ui = new Ui();

    /**
     * The constructor.
     */
    public Bocchi() {
        taskList = new TaskList(storage);
    }


    /**
     * Ends the conversation.
     */
    private void exit() {
        ui.printSeparator();
        ui.printMessage("Oh no you are leaving.. It was a great time talking to you ::>_<::");
        ui.printSeparator();
        taskList.saveTasks();
    }

    /**
     * Prints all items in the item list.
     */
    private void list() {
        ui.printSeparator();
        ui.printMessage("No...no problem! This is your task list! (/▽＼)");
        for (int i = 0; i < taskList.size(); i++) {
            ui.printMessage((i + 1) + ". " + taskList.getTask(i));
        }
        ui.printSeparator();
    }

    /**
     * Adds a task to the list.
     */
    private void task(Task task) {
        ui.printSeparator();
        taskList.addTask(task);
        ui.printMessage("Only if I can be as diligent as you... (っ °Д °;)っ An..anyway, task added!");
        ui.printMessage(task);
        ui.printSeparator();
    }

    /**
     * Mark the i-th task (1-indexed) as done.
     *
     * @param index The index of the task to be marked as done.
     */
    private void mark(int index) throws BocchiException {
        index--;
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException("Sorry but ... erm maybe it is better to double check the index you entered? " +
                    "Cause it seems to be out of bounds. ＞﹏＜");
        }

        Task task = taskList.getTask(index);
        task.markAsDone();

        ui.printSeparator();
        ui.printMessage("I have marked the task as done! You are doing such a good job! (*/ω＼*)");
        ui.printMessage(task);
        ui.printSeparator();
    }

    /**
     * Mark the i-th task (1-indexed) as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    private void unmark(int index) throws BocchiException {
        index--;
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException("Sorry but ... maybe it is better to double check the index you entered? " +
                    "Cause it seems to be out of bounds. ＞﹏＜");
        }

        Task task = taskList.getTask(index);
        task.markAsUnDone();

        ui.printSeparator();
        ui.printMessage("I have marked the task as not done. You will do it better next time! (*/ω＼*)");
        ui.printMessage(task);
        ui.printSeparator();
    }

    /**
     * Delete the i-th (1-indexed) task.
     *
     * @param index The index of the task to be deleted.
     */
    private void delete(int index) throws BocchiException {
        index--;
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException("Sorry but ... maybe it is better to double check the index you entered? " +
                    "Cause it seems to be out of bounds. ＞﹏＜");
        }

        Task task = taskList.removeTask(index);

        ui.printSeparator();
        ui.printMessage("I have removed the task!");
        ui.printMessage(task);
        ui.printSeparator();
    }

    private void printError(Exception e) {
        ui.printSeparator();
        ui.printMessage(e.getMessage());
        ui.printSeparator();
    }

    /**
     * Starts a conversation.
     * Commands available:
     * - bye: ends the conversation;
     * - list: lists out all tasks;
     * - mark [index]: mark the task in the specified index as done;
     * - unmark [index]: mark the task in the specified index as not done;
     * - todo [description]: adds a new todo with the specified description;
     * - ddl/deadline [description] /by [dueDateTime]: adds a new deadline with the specified description and due date/time;
     * - event [description] /from [fromDateTime] /to [toDateTime]: adds a new event with the specified description,
     * start date/time and end date/time;
     * - del/delete [index]: delete the task in the specified index.
     */
    public void start() {
        ui.greet();
        // try-with-resources code optimisation done by IntelliJ
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Command command = ui.readCommand(scanner);
                // optimized from if statements to switch by IntelliJ
                try {
                    switch (command.getName()) {
                        case "bye" -> {
                            exit();
                            return;
                        }
                        case "list" -> list();
                        case "mark" -> mark(Integer.parseInt(command.getParam()));
                        case "unmark" -> unmark(Integer.parseInt(command.getParam()));
                        case "todo" -> task(new Todo(command.getParam()));
                        case "ddl", "deadline" -> task(new Deadline(command.getParam(), command.getKeywordParams("by")));
                        case "event" -> task(new Event(
                                command.getParam(),
                                command.getKeywordParams("from"),
                                command.getKeywordParams("to")
                        ));
                        case "del", "delete" -> delete(Integer.parseInt(command.getParam()));
                        case "" -> throw new BocchiException(
                                "I'm soooo sorry I did't hear you, could you please repeat that? ( T﹏T )"
                        );
                        default -> throw new BocchiException(
                                "Wh..what did you say? I'm soooo sorry I did't understand that ( T﹏T )"
                        );
                    }
                } catch (NumberFormatException e) {
                    printError(new BocchiException("So sorry... I can't understand the number you entered. ( T_T )"));
                } catch (DateTimeParseException e) {
                    printError(new BocchiException("So sorry... I can't understand the date/time format you entered. ( T_T )"));
                } catch (BocchiException e) {
                    printError(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Bocchi().start();
    }

}
