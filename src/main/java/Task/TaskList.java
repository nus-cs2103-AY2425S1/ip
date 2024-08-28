package Task;

import Task.Task;

import java.util.List;
import Exception.*;
import Ui.Ui;
import Parse.Parse;
public class TaskList {
    private List<Task> array;
    public TaskList(List<Task> array) {
        this.array = array;
    }
    public void handleList(){
        Ui.uiList(array);
    }
    public void handleTask(String input) throws WrongKeyword, MissingArg {
        if (input.startsWith("todo")) {
            try {
                Todo x = new Todo(Parse.parseTodo(input));
                array.add(x);
                Ui.uiTodo(array.size(), x);
            } catch(Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] split = Parse.parseDeadline(input);
                Deadline x = new Deadline(split[0], split[1]);
                array.add(x);
                Ui.uiDeadline(array.size(), x);
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("event")) {
            try {
                String[] split = Parse.parseEvent(input);
                Event x = new Event(split[0], split[1], split[2]);
                array.add(x);
                Ui.uiEvent(array.size(), x);
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else {
            throw new WrongKeyword("Wrong keyword");
        }
    }

    public void markDone(String input) {
        int index = input.charAt(input.length() - 1) - '0';
        array.get(index - 1).markAsDone();
        Ui.uiMark(array.get(index - 1));
    }

    public void markUnDone(String input) {
        int index = input.charAt(input.length() - 1) - '0';
        array.get(index - 1).markAsNotDone();
        Ui.uiUnMark(array.get(index - 1));
    }

    public void delete(String input) {
        int index = input.charAt(input.length() - 1) - '0';
        Task t = array.get(index - 1);
        array.remove(index - 1);
        Ui.uiDelete(t, array.size());
    }
    public List<Task> getArray() {
        return this.array;
    }
}
