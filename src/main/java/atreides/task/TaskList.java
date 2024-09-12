package atreides.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import atreides.ui.AtreidesException;

/**
 * Represents the list of tasks that can be added
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<String[]> tasks) {
        this.list = new ArrayList<>();
        for (String[] words : tasks) {
            Task newTask = new Task();
            Boolean isDone = words[1].equals("1");
            if (words[0].equals("T")) {
                newTask = new ToDo(words[2]);
            } else if (words[0].equals("D")) {
                String description = words[2];
                String by = words[3];
                newTask = new Deadline(description, by);
            } else if (words[0].equals("E")) {
                String description = words[2];
                String[] startEnd = words[3].split("-");
                newTask = new Events(description, startEnd);
            }
            newTask.markDone(isDone);
            this.list.add(newTask);
        }
    }

    /**
     * parses the string msg and words and creates the relevant Task object
     * @param msg
     * @param words
     * @return
     * @throws AtreidesException if msg and words do not follow the standard commands
     */
    public static Task getTask(String msg, String[] words) throws AtreidesException {
        Task newTask;
        if (words[0].equals("todo")) {
            newTask = new ToDo(msg.substring(5));
        } else if (words[0].equals("deadline")) {
            String[] parts = msg.split(" /by ");
            String by = parts[parts.length - 1];
            String description = parts[0].split("deadline ")[1];
            newTask = new Deadline(description, by);
        } else if (words[0].equals("event")) {
            String[] parts = msg.split(" /from ");
            String[] startEnd = parts[parts.length - 1].split(" /to ");
            String description = parts[0].split("event ")[1];
            newTask = new Events(description, startEnd);
        } else {
            throw new AtreidesException("I dont know what u mean, please give me a todo, event or deadline");
        }
        return newTask;
    }

    /**
     * Creates string of list of tasks that the Ui will print out
     * @return
     */
    public String showList() {
        String tasks = IntStream.range(0, list.size())
                .mapToObj(i -> (i + 1) + "." + list.get(i))
                .collect(Collectors.joining("\n"));
        return tasks;
    }

    /**
     * creates a string of list of tasks that will be written into the file object
     * @return
     */
    public String writeList() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            tasks.append(list.get(i).write());
            if (i != list.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }

    public void mark(int index) throws AtreidesException {
        markBool(index, true);
    }

    public void unmark(int index) throws AtreidesException {
        markBool(index, false);
    }

    /**
     * marks whether task at the specific index based on the bool
     * @param index
     * @param bool
     * @throws AtreidesException If index out of bounds
     */
    public void markBool(int index, boolean bool) throws AtreidesException {
        checkIndexPresent(index);
        list.get(index).markDone(bool);
    }

    public void checkIndexPresent(int index) throws AtreidesException {
        if (index >= list.size()) {
            throw new AtreidesException("list does not have the index present");
        }
    }

    public Task getTaskAtIndex(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public Task delete(int index) throws AtreidesException {
        checkIndexPresent(index);
        return list.remove(index);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public String find(String description) {
        StringBuilder tasks = new StringBuilder();
        tasks.append("Here are the matching tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).description.contains(description)) {
                tasks.append((i + 1)).append(".").append(list.get(i) + "\n");
            }
        }
        return tasks.substring(0, tasks.toString().length() - 1);
    }

}
