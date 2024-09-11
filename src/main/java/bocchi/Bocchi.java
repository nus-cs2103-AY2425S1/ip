package bocchi;

import bocchi.command.Command;
import bocchi.exception.BocchiException;
import bocchi.storage.Storage;
import bocchi.task.Deadline;
import bocchi.task.Event;
import bocchi.task.Task;
import bocchi.task.TaskList;
import bocchi.task.Todo;

import java.time.format.DateTimeParseException;

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
     * The constructor.
     */
    public Bocchi() {
        taskList = new TaskList(storage);
    }

    /**
     * Ends the conversation.
     * @return The response to the command.
     */
    private String exit() {
        taskList.saveTasks();
        return "Oh no you are leaving.. It was a great time talking to you ::>_<::";
    }

    /**
     * Prints all items in the item list.
     * @return The response to the command.
     */
    private String list() {
        StringBuilder response = new StringBuilder("No problem! This is your task list! (/▽＼)\n");
        for (int i = 0; i < taskList.size(); i++) {
            response.append((i + 1))
                    .append(". ")
                    .append(taskList.getTask(i))
                    .append("\n");
        }
        return response.toString();
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     * @return The response to the command.
     */
    private String task(Task task) {
        taskList.addTask(task);
        return "Only if I can be as diligent as you... (っ °Д °;)っ An..anyway, task added!";
    }

    /**
     * Mark the i-th task (1-indexed) as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The response to the command.
     */
    private String mark(int index) throws BocchiException {
        index--;
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException("Sorry but ... erm maybe it is better to double check the index you entered? " +
                    "Cause it seems to be out of bounds. ＞﹏＜");
        }

        assert index >= 0 && index < taskList.size() : "Index out of bounds";
        Task task = taskList.getTask(index);
        task.markAsDone();

        return "I have marked the task as done! You are doing such a good job! (*/ω＼*)";
    }

    /**
     * Mark the i-th task (1-indexed) as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The response to the command.
     */
    private String unmark(int index) throws BocchiException {
        index--;
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException("Sorry but ... maybe it is better to double check the index you entered? " +
                    "Cause it seems to be out of bounds. ＞﹏＜");
        }

        assert index >= 0 && index < taskList.size() : "Index out of bounds";
        Task task = taskList.getTask(index);
        task.markAsUnDone();

        return "I have marked the task as not done. You will do it better next time! (*/ω＼*)";
    }

    /**
     * Delete the i-th (1-indexed) task.
     *
     * @param index The index of the task to be deleted.
     * @return The response to the command.
     */
    private String delete(int index) throws BocchiException {
        index--;
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException("Sorry but ... maybe it is better to double check the index you entered? " +
                    "Cause it seems to be out of bounds. ＞﹏＜");
        }

        assert index >= 0 && index < taskList.size() : "Index out of bounds";
        Task task = taskList.removeTask(index);

        return "I have removed the task!";
    }


    /**
     * Greets the user.
     * @return The greeting message.
     */
    public String greet() {
        String commandSummary = """
                - bye: ends the conversation;
                - list: lists out all tasks;
                - mark [index]: mark the task in the specified index as done;
                - unmark [index]: mark the task in the specified index as not done;
                - todo [description]: adds a new todo with the specified description;
                - ddl/deadline [description] /by [dueDateTime]: adds a new deadline with the given description and due date/time;
                - event [description] /from [fromDateTime] /to [toDateTime]: adds a new event with the specified description,
                     start date/time and end date/time;
                - del/delete [index]: delete the task in the specified index.""";

        return """
                Hi! I'm Bocchi! Nice to see you!");
                Here are the things I can do for you! o(*//▽//*)q
                """ + commandSummary;
    }

    /**
     * Processes the command and returns the response.
     *
     * @param command The command to be processed.
     * @return The response to the command.
     * @throws BocchiException If an error occurs during processing.
     */
    public String processCommand(Command command) throws BocchiException {
        // optimized from if statements to switch by IntelliJ
        try {
            return switch (command.getName()) {
                case "bye" -> exit();
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
            };
        } catch (NumberFormatException e) {
            throw new BocchiException("So sorry... I can't understand the number you entered. ( T_T )");
        } catch (DateTimeParseException e) {
            throw new BocchiException(
                    "So sorry... I can't understand the date/time format you entered. ( T_T )"
            );
        }
    }

}
