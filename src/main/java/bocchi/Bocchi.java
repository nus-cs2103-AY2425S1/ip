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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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

    private static final String COMMAND_SUMMARY = """
            - bye
                ends the conversation;
            - list [/type <type>+] [/tag <tag>+]
                lists out all tasks, optionally filtered by task type and/or tag;
            - mark <index>
                mark the task in the specified index as done;
            - unmark <index>
                mark the task in the specified index as not done;
            - todo <description> [/tag <tag>+]
                adds a new todo with the specified description;
            - ddl/deadline <description> /by <dueDateTime> [/tag <tag>+]
                adds a new deadline with the given description and due date/time;
            - event <description> /from <fromDateTime> /to <toDateTime> [/tag <tag>+]
                adds a new event with the specified description, start date/time and end date/time;
            - del/delete <index>
                delete the task in the specified index.
            - tag <index> /tag <tag>+
                tags the task in the specified index with the specified tags.
            - untag <index> /tag <tag>+
                removes the specified tags from the task in the specified index.
            """;
    private static final String GREET_MESSAGE = """
            Hi! I'm Bocchi! Nice to see you!");
            Here are the things I can do for you! o(*//▽//*)q""";
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
    private static final String TAG_MESSAGE =
            "I have added the tag to the task! (*/ω＼*)";
    private static final String UNTAG_MESSAGE =
            "I have removed the tag from the task! (*/ω＼*)";

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
    public String exit() {
        taskList.saveTasks();
        return EXIT_MESSAGE;
    }

    /**
     * Prints all items in the item list.
     *
     * @return The response to the command.
     */
    private String list(List<String> taskTypes, List<String> tags) {

        class TaskStringBuilder {
            private int index = 1;
            private final StringBuilder stringBuilder = new StringBuilder(LIST_MESSAGE);

            public void addTask(Task task) {
                stringBuilder.append(index)
                        .append(". ")
                        .append(task)
                        .append("\n");
                index++;
            }

            public String build() {
                return stringBuilder.toString();
            }
        }

        TaskStringBuilder response = new TaskStringBuilder();

        Stream<Task> taskStream = taskList.stream().sequential();

        // filter by task types if specified
        if (taskTypes != null) {
            List<String> lowercaseTaskTypes = taskTypes.stream()
                    .map(String::toLowerCase)
                    .toList();
            taskStream = taskStream.filter(task -> lowercaseTaskTypes.contains(task.getClass().getSimpleName().toLowerCase()));
        }

        // filter by tags if specified
        if (tags != null) {
            taskStream = taskStream.filter(task -> task.hasAnyTag(tags));
        }

        taskStream.forEach(response::addTask);

        return response.build();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @param tags The tags to be added to the task.
     * @return The response to the command.
     */
    private String task(Task task, List<String> tags) {
        if (tags != null) {
            tags.forEach(task::addTag);
        }
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

        assert index >= 0 && index < taskList.size() : "Index out of bounds";
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

        assert index >= 0 && index < taskList.size() : "Index out of bounds";
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

        assert index >= 0 && index < taskList.size() : "Index out of bounds";
        Task task = taskList.removeTask(index);

        return DELETE_MESSAGE + "\n" + task + "\n";
    }


    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return GREET_MESSAGE +
                "\n-------------------------------------------------------\n" +
                COMMAND_SUMMARY;
    }


    /**
     * Tags the i-th task (1-indexed) with the specified tags.
     *
     * @param index The index of the task to be tagged.
     * @param tags  The tags to be added to the task.
     * @return The response to the command.
     */
    private String tag(int index, List<String> tags) throws BocchiException {
        index--;

        checkTaskIndex(index);

        assert index >= 0 && index < taskList.size() : "Index out of bounds";

        Task task = taskList.getTask(index);

        tags.forEach(task::addTag);

        return TAG_MESSAGE;
    }

    /**
     * Untags the i-th task (1-indexed) with the specified tags.
     *
     * @param index The index of the task to be untagged.
     * @param tags  The tags to be removed from the task.
     * @return The response to the command.
     */
    private String untag(int index, List<String> tags) throws BocchiException {
        index--;

        checkTaskIndex(index);

        assert index >= 0 && index < taskList.size() : "Index out of bounds";

        Task task = taskList.getTask(index);

        tags.forEach(task::removeTag);

        return UNTAG_MESSAGE;
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
                case "list" -> list(command.getListKeywordParam("type"), command.getListKeywordParam("tag"));
                case "mark" -> mark(Integer.parseInt(command.getParam()));
                case "unmark" -> unmark(Integer.parseInt(command.getParam()));
                case "todo" -> task(new Todo(command.getParam()), command.getListKeywordParam("tag"));
                case "ddl", "deadline" -> task(
                                new Deadline(command.getParam(), command.getKeywordParam("by")),
                                command.getListKeywordParam("tag")
                        );
                case "event" -> task(new Event(
                        command.getParam(),
                        command.getKeywordParam("from"),
                        command.getKeywordParam("to")
                ), command.getListKeywordParam("tag"));
                case "del", "delete" -> delete(Integer.parseInt(command.getParam()));
                case "tag" -> tag(Integer.parseInt(command.getParam()), command.getListKeywordParam("tag"));
                case "untag" -> untag(Integer.parseInt(command.getParam()), command.getListKeywordParam("tag"));
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
