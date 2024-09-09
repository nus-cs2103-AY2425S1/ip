package Task;

import java.util.ArrayList;
import java.util.List;
import Exception.*;
import Ui.Ui;
import Parse.Parse;
public class TaskList {
    private List<Task> array;
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
     * @throws MissingArg if there are wrong number of arguments in the input string
     */
    public String handleTask(String input) throws WrongKeyword, MissingArg {
        assert array != null : "empty list";
        if (input.startsWith("todo")) {
            try {
                Todo x = new Todo(Parse.parseTodo(input));
                array.add(x);
                return Ui.uiTodo(array.size(), x);
            } catch(Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] split = Parse.parseDeadline(input);
                Deadline x = new Deadline(split[0], split[1]);
                array.add(x);
                return Ui.uiDeadline(array.size(), x);
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("event")) {
            try {
                String[] split = Parse.parseEvent(input);
                Event x = new Event(split[0], split[1], split[2]);
                array.add(x);
                return Ui.uiEvent(array.size(), x);
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else {
            throw new WrongKeyword("Wrong keyword");
        }
    }
    /**
     * Marks a task as done.
     *
     * @param input the input string containing the index of the task to be marked as done
     */
    public String markDone(String input) {
        int index = input.charAt(input.length() - 1) - '0';
        assert index >= 0: "wrong indexing";
        array.get(index - 1).markAsDone();
        return Ui.uiMark(array.get(index - 1));
    }
    /**
     * Marks a task as not done.
     *
     * @param input the input string containing the index of the task to be marked as not done
     */
    public String markUnDone(String input) {
        int index = input.charAt(input.length() - 1) - '0';
        assert index >= 0: "wrong indexing";
        array.get(index - 1).markAsNotDone();
        return Ui.uiUnMark(array.get(index - 1));
    }
    /**
     * Deletes a task from the list.
     *
     * @param input the input string containing the index of the task to be deleted
     */
    public String delete(String input) {
        int index = input.charAt(input.length() - 1) - '0';
        assert index >= 0: "wrong indexing";
        Task t = array.get(index - 1);
        array.remove(index - 1);
        return Ui.uiDelete(t, array.size());
    }
    public List<Task> getArray() {
        return this.array;
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
}
