package task;
import storage.Storage;
import java.util.ArrayList;
import java.util.List;
import exception.MissingArg;
import exception.WrongKeyword;
import exception.WrongIndex;
import parse.Parse;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import ui.Ui;
public class TaskList {
    private List<Task> array;
    private static final String keywordError = "Wrong keyword";
    private static final String wrongIndexError = "Please enter a valid index. Index cannot be longer than size of list or negative";
    private static final String dateTimeError = "Please enter in format DD/MM/YYYY HHMM";
    private static final String noTaskMatched = "No matching tasks found";
    public TaskList(List<Task> array) {
        this.array = array;
    }
    /**
     * Displays the list of tasks after user types lists
     */
    public String handleList() {
        assert array != null : "empty list";
        return Ui.uiList(array);
    }
    /**
     * Handles task creation based on the input string.
     * <p>
     * This method checks the input string to determine the type of task (Todo, Deadline, or Event),
     * parses the input accordingly, creates the task, adds it to the task list, and writes the updated
     * list to storage. If the input does not match any known task type, an exception is thrown.
     * The actual handling of each task type is delegated to helper methods.
     * </p>
     *
     * @param input The input string.
     * @param storage The storage instance that will store updated task list.
     * @return A string which will be the message displayed to the user.
     * @throws WrongKeyword If the input string does not start with a valid task keyword (e.g., "todo", "deadline", or "event").
     * @throws AssertionError If the task list (array) is null.
     */
    public String handleTask(String input, Storage storage) throws WrongKeyword {
        assert array != null : "empty list";
        if (input.startsWith("todo ")) {
            try {
                return handleTodoTask(input, storage,this.array);
            } catch(MissingArg e) {
                return e.getMessage();
            }
        } else if (input.startsWith("deadline ")) {
            try {
                return handleDeadlineTask(input, storage, this.array);
            } catch (MissingArg e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return dateTimeError;
            }
        } else if (input.startsWith("event ")) {
            try {
                return handleEventTask(input, storage, this.array);
            } catch (MissingArg e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return dateTimeError;
            }
        } else {
            throw new WrongKeyword(keywordError);
        }
    }
    /**
     * Handles the creation and storage of a Todo task.
     * <p>
     * This method parses the input for a Todo task, adds the created task to the task list,
     * and updates the storage.
     * </p>
     *
     * @param input The input string representing the Todo task details.
     * @param storage The storage instance that will store the updated task list.
     * @param array The task list where the new task will be added.
     * @return A string representing the message to be displayed to the user.
     * @throws MissingArg If the input is missing necessary arguments for creating a Todo task.
     */
    public static String handleTodoTask(String input, Storage storage, List<Task> array) throws MissingArg {
        Todo x = new Todo(Parse.parseTodo(input));
        array.add(x);
        storage.writeFile(array);
        return Ui.uiTodo(array.size(), x);
    }
    /**
     * Handles the creation and storage of a Deadline task.
     * <p>
     * This method parses the input for a Deadline task, adds the created task to the task list,
     * and updates the storage.
     * </p>
     *
     * @param input The input string representing the Deadline task details.
     * @param storage The storage instance that will store the updated task list.
     * @param array The task list where the new task will be added.
     * @return A string representing the message to be displayed to the user.
     * @throws MissingArg If the input is missing necessary arguments for creating a Deadline task.
     */
    public static String handleDeadlineTask(String input, Storage storage, List<Task> array) throws MissingArg {
        String[] split = Parse.parseDeadline(input);
        Deadline x = new Deadline(split[0], split[1]);
        array.add(x);
        storage.writeFile(array);
        return Ui.uiDeadline(array.size(), x);
    }
    /**
     * Handles the creation and storage of an Event task.
     * <p>
     * This method parses the input for an Event task, adds the created task to the task list,
     * and updates the storage.
     * </p>
     *
     * @param input The input string representing the Event task details.
     * @param storage The storage instance that will store the updated task list.
     * @param array The task list where the new task will be added.
     * @return A string representing the message to be displayed to the user.
     * @throws MissingArg If the input is missing necessary arguments for creating an Event task.
     */
    public static String handleEventTask(String input, Storage storage, List<Task> array) throws MissingArg {
        String[] split = Parse.parseEvent(input);
        Event x = new Event(split[0], split[1], split[2]);
        array.add(x);
        storage.writeFile(array);
        return Ui.uiEvent(array.size(), x);
    }
    /**
     * Marks a task as done.
     *
     * @param input the input string containing the index of the task to be marked as done
     */
    public String markDone(String input, Storage storage) {
        try {
            int index = getIndex(input, array.size());
            assert index >= 0: "wrong indexing";
            array.get(index - 1).markAsDone();
            storage.writeFile(this.array);
            return Ui.uiMark(array.get(index - 1));
        } catch (WrongIndex e) {
            return e.getMessage();
        }
    }
    /**
     * Marks a task as not done.
     *
     * @param input the input string containing the index of the task to be marked as not done
     */
    public String markUnDone(String input, Storage storage) {
        try {
            int index = getIndex(input, array.size());
            assert index >= 0: "wrong indexing";
            array.get(index - 1).markAsNotDone();
            storage.writeFile(this.array);
            return Ui.uiUnMark(array.get(index - 1));
        } catch (WrongIndex e) {
            return e.getMessage();
        }

    }
    /**
     * Deletes a task from the list.
     *
     * @param input the input string containing the index of the task to be deleted
     */
    public String delete(String input, Storage storage) {
        try {
            int index = getIndex(input, array.size());
            assert index >= 0: "wrong indexing";
            Task t = array.get(index - 1);
            array.remove(index - 1);
            storage.writeFile(this.array);
            return Ui.uiDelete(t, array.size());
        } catch (WrongIndex e) {
            return e.getMessage();
        }
    }
    /**
     * Extracts the index from the input string and validates it.
     *
     * @param input The input string containing the index.
     * @param arrayLength The length of the task array to validate the index.
     * @return The valid index as an integer.
     * @throws WrongIndex If the index is out of the valid range (less than 0 or greater than the array length).
     */
    public static int getIndex(String input, int arrayLength) throws WrongIndex {
        int index = input.charAt(input.length() - 1) - '0';
        if (index <= 0 || index > arrayLength || input.charAt(input.length() - 2) == '-') {
            throw new WrongIndex(wrongIndexError);
        }
        return index;
    }
    /**
     * Searches for tasks in the list that contain a matching word from the input string.
     *
     * @param input The input string containing the search keyword.
     * @return A formatted string listing all tasks that contain the matching word in their description.
     */
    public String search(String input) {
        try {
            String matchingWord = Parse.parseFind(input);
            List<Task> match = new ArrayList<>();
            for (Task t : this.array) {
                if (t.description.contains(matchingWord)) {
                    match.add(t);
                }
            }
            if (match.isEmpty()) {
                return noTaskMatched;
            }
            return Ui.uiList(match);
        } catch (MissingArg e) {
            return e.getMessage();
        }
    }
    /**
     * Sorts the tasks in the list based on their associated date and time.
     *
     * @param storage The storage instance used to save the updated task list after sorting.
     * @return A string that is a formatted list of the sorted tasks.
     */
    public String sort(Storage storage) {
        array.sort(Comparator.comparing(task -> task.getDateTime(), Comparator.nullsLast(Comparator.naturalOrder())));
        storage.writeFile(this.array);
        return "After sorting " + this.handleList();
    }
}
