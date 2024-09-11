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
     * Messages.
     */
    private static final String EMPTY_COMMAND_ERROR_MESSAGE =
            "I'm soooo sorry I did't hear you, could you please repeat that? ( T﹏T )";
    private static final String INVALID_COMMAND_ERROR_MESSAGE =
            "Wh..what did you say? I'm soooo sorry I did't understand that ( T﹏T )";
    private static final String INVALID_NUMBER_ERROR_MESSAGE =
            "So sorry... I can't understand the number you entered. ( T_T )";
    private static final String INVALID_DATE_TIME_ERROR_MESSAGE =
            "I'm so sorry but I can't understand the date/time you entered. ( T_T )";
    private static final String INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE =
            "Sorry but ... erm maybe it is better to double check the index you entered? " +
                    "Because it seems to be out of bounds. ＞﹏＜";

    private static final String EXIT_MESSAGE =
            "Oh no you are leaving.. It was a great time talking to you ::>_<::";
    private static final String LIST_MESSAGE =
            "No problem! This is your task list! (/▽＼)\n";
    private static final String TASK_MESSAGE =
            "Only if I can be as diligent as you... (っ °Д °;)っ An..anyway, task added!";
    private static final String MARK_MESSAGE =
            "I have marked the task as done! You are doing such a good job! (*/ω＼*)";
    private static final String UNMARK_MESSAGE =
            "I have marked the task as not done. You will do it better next time! (*/ω＼*)";
    private static final String DELETE_MESSAGE =
            "I have removed the task!";
    private static final String COMMAND_SUMMARY = """
            - bye: ends the conversation;
            - list: lists out all tasks;
            - mark [index]: mark the task in the specified index as done;
            - unmark [index]: mark the task in the specified index as not done;
            - todo [description]: adds a new todo with the specified description;
            - ddl/deadline [description] /by [dueDateTime]: adds a new deadline with the given description and due date/time;
            - event [description] /from [fromDateTime] /to [toDateTime]: adds a new event with the specified description,
                 start date/time and end date/time;
            - del/delete [index]: delete the task in the specified index.""";
    private static final String GREET_MESSAGE = """
            Hi! I'm Bocchi! Nice to see you!");
            Here are the things I can do for you! o(*//▽//*)q""";

    /**
     * The constructor.
     */
    public Bocchi() {
        taskList = new TaskList(storage);
    }

    /**
     * Ends the conversation.
     *
     * @return The response to the command.
     */
    private String exit() {
        taskList.saveTasks();
        return EXIT_MESSAGE;
    }

    /**
     * Prints all items in the item list.
     *
     * @return The response to the command.
     */
    private String list() {
        StringBuilder response = new StringBuilder(LIST_MESSAGE);
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
     *
     * @param task The task to be added.
     * @return The response to the command.
     */
    private String task(Task task) {
        taskList.addTask(task);
        return TASK_MESSAGE;
    }

    private void checkTaskIndex(int index) throws BocchiException {
        if (index >= taskList.size() || index < 0) {
            throw new BocchiException(INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE);
        }
    }

    /**
     * Mark the i-th task (1-indexed) as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The response to the command.
     */
    private String mark(int index) throws BocchiException {
        index--;

        checkTaskIndex(index);

        Task task = taskList.getTask(index);
        task.markAsDone();

        return MARK_MESSAGE;
    }

    /**
     * Mark the i-th task (1-indexed) as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return The response to the command.
     */
    private String unmark(int index) throws BocchiException {
        index--;

        checkTaskIndex(index);

        Task task = taskList.getTask(index);
        task.markAsUnDone();

        return UNMARK_MESSAGE;
    }

    /**
     * Delete the i-th (1-indexed) task.
     *
     * @param index The index of the task to be deleted.
     * @return The response to the command.
     */
    private String delete(int index) throws BocchiException {
        index--;

        checkTaskIndex(index);

        Task task = taskList.removeTask(index);

        return DELETE_MESSAGE + "\n" + task + "\n";
    }


    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return GREET_MESSAGE + "\n" + COMMAND_SUMMARY;
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
                case "" -> throw new BocchiException(EMPTY_COMMAND_ERROR_MESSAGE);
                default -> throw new BocchiException(INVALID_COMMAND_ERROR_MESSAGE);
            };
        } catch (NumberFormatException e) {
            throw new BocchiException(INVALID_NUMBER_ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            throw new BocchiException(INVALID_DATE_TIME_ERROR_MESSAGE);
        }
    }

}
