package maxine;

import maxine.exception.MaxineException;
import maxine.task.Deadline;
import maxine.task.Event;
import maxine.task.Task;
import maxine.task.Todo;
import java.util.Iterator;
import java.util.ArrayList;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> list;
    
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTodo(String[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[1]);
        if (arr.length >= 3) {
            for (int i = 2; i < arr.length; i++) {
                String word = " " + arr[i];
                sb.append(word);
            }
        }
        Todo task = new Todo(sb.toString());
        list.add(task);
    }

    public void addDeadline(String[] arr) throws MaxineException {
        StringBuilder desc = new StringBuilder();
        StringBuilder ddl = new StringBuilder();
        desc.append(arr[1]);
        boolean isChecked = false;
        for (int i = 1; i < (arr.length - 1); i++) {
            if (arr[i].equals("/by")) {
                isChecked = true;
                break;
            }
        }
        if (!isChecked || arr[1].equals("/by")) {
            throw new MaxineException("Please follow this format: deadline [enter maxine.task] /by [enter deadline]");
        }
        boolean hasBy = false;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                hasBy = true;
            }
            else if (hasBy) {
                String word = " " + arr[i];
                ddl.append(word);
            } else {
                String word = " " + arr[i];
                desc.append(word);
            }
        }
        Deadline task = new Deadline(desc.toString(), ddl.toString());
        list.add(task);
    }

    public void addEvent(String[] arr) throws MaxineException {
        StringBuilder desc = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        desc.append(arr[1]);
        boolean hasFrom = false;
        boolean hasTo = false;
        for (int i = 2; i < (arr.length - 1); i++) {
            if (arr[i].equals("/from")) {
                hasFrom = true;
            }
            if (arr[i].equals("/to")) {
                hasTo = true;
            }
        }

        if (!hasFrom || !hasTo || arr[1].equals("/from")) {
            throw new MaxineException("Please follow this format: " +
                    "event [enter event] /from [start date] /to [end date]");
        }
        boolean isAfterFrom = false;
        boolean isAfterTo = false;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i].equals("/from")) {
                isAfterFrom = true;
            }
            else if (arr[i].equals("/to")) {
                isAfterFrom = false;
                isAfterTo = true;
            } else if (isAfterFrom) {
                String word = " " + arr[i];
                start.append(word);
            } else if (isAfterTo) {
                String word = " " + arr[i];
                end.append(word);
            } else {
                String word = " " + arr[i];
                desc.append(word);
            }
        }
        Event task = new Event(desc.toString(), start.toString(), end.toString());
        list.add(task);
    }
    
    public void delete(Task task) {
        task.delete();
    }
    
    public int size() {
        return list.size();
    }
    
    public Task get(int num) {
        return list.get(num);
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
