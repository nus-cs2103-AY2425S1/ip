package Task;
import Storage.Storage;
import java.util.ArrayList;
import java.util.List;
import Exception.*;
import Ui.Ui;
import Parse.Parse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class TaskList {
    private List<Task> array;
    private static final String keywordError = "Wrong keyword";
    public TaskList(List<Task> array) {
        this.array = array;
    }
    /**
     * Displays the list of tasks after user types lists
     */
    public String handleList(){
        assert array != null : "empty list";
        return Ui.uiList(array);
    }
    /**
     * Checks for the type of task and handle them accordingly
     * This method checks for todo, deadline and event classes and adds them to a task list
     * An exception is thrown if an unknown class or wrong number of arguments is received
     *
     *
     * @param input the input string
     * @throws WrongKeyword if the input string does not start with a valid task keyword
     */
    public String handleTask(String input, Storage storage) throws WrongKeyword{
        assert array != null : "empty list";
        if (input.startsWith("todo")) {
            try {
                Todo x = new Todo(Parse.parseTodo(input));
                array.add(x);
                storage.writeFile(array);
                return Ui.uiTodo(array.size(), x);
            } catch(MissingArg e) {
                return e.getMessage();
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] split = Parse.parseDeadline(input);
                Deadline x = new Deadline(split[0], split[1]);
                array.add(x);
                storage.writeFile(array);
                return Ui.uiDeadline(array.size(), x);
            } catch (MissingArg e) {
                return e.getMessage();
            }
        } else if (input.startsWith("event")) {
            try {
                String[] split = Parse.parseEvent(input);
                Event x = new Event(split[0], split[1], split[2]);
                array.add(x);
                storage.writeFile(array);
                return Ui.uiEvent(array.size(), x);
            } catch (MissingArg e) {
                return e.getMessage();
            }
        } else {
            throw new WrongKeyword(keywordError);
        }
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

    public static int getIndex(String input, int arrayLength) throws WrongIndex {
        int index = input.charAt(input.length() - 1) - '0';
        if (index <= 0 || index > arrayLength) {
            throw new WrongIndex("Please enter a valid index. Index cannot be longer than size of list or negative");
        }
        return index;
    }
    public String search(String input) {
        String matchingWord = Parse.parseFind(input);
        List<Task> match = new ArrayList<>();
        for (Task t : this.array) {
            if (t.description.contains(matchingWord)) {
                match.add(t);
            }
        }
        return Ui.uiList(match);
    }
    public String sort(Storage storage) {
        array.sort(Comparator.comparing(task -> task.getDateTime(), Comparator.nullsLast(Comparator.naturalOrder())));
        storage.writeFile(this.array);
        return "After sorting " + this.handleList();
    }
}
