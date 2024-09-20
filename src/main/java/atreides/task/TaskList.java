package atreides.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import atreides.ui.AtreidesException;

/**
 * Represents the list of tasks that can be added
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from a list of tasks represented as arrays of strings.
     *
     * @param tasks An ArrayList of String arrays where each array contains task details.
     *              The format is expected to be {taskType, isDone, description, additionalInfo}.
     *              taskType: "T" for ToDo, "D" for Deadline, "E" for Event.
     *              isDone: "1" if the task is done, "0" otherwise.
     *              description: The description of the task.
     *              additionalInfo: For "D", this is the deadline; for "E", this is the start and end time.
     * @throws AtreidesException If there is an error in processing a deadline task.
     */
    public TaskList(ArrayList<String[]> tasks) throws AtreidesException {
        this.list = new ArrayList<>();
        for (String[] words : tasks) {
            assert words.length > 2;

            Task newTask = new Task();
            Boolean isDone = words[1].equals("1");
            if (words[0].equals("T")) {
                newTask = new ToDo(words[2]);
            } else if (words[0].equals("D")) {
                String description = words[2];
                String by = words[3];
                try {
                    newTask = new Deadline(description, by);
                } catch (AtreidesException e) {
                    continue;
                }
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
     * Creates a new Task object based on the input message and command type.
     *
     * @param msg The complete input message containing the task description and any additional information.
     * @param words An array of words  to identify the task type (e.g., "todo", "deadline", "event").
     * @return The created Task object, which can be a ToDo, Deadline, or Event, based on the input message.
     * @throws AtreidesException If the task type is unrecognized or if the message format is invalid.
     */
    public static Task getTask(String msg, String[] words) throws AtreidesException {
        Task newTask;
        if (words[0].equals("todo")) {
            newTask = new ToDo(msg.substring(5));
        } else if (words[0].equals("deadline")) {
            if (!msg.contains(" /by ")) {
                throw new AtreidesException("deadline must have /by <datetime> specified");
            }
            String[] parts = msg.split(" /by ");
            String by = parts[parts.length - 1];
            String description = parts[0].split("deadline ")[1];
            newTask = new Deadline(description, by);
        } else if (words[0].equals("event")) {
            if (!msg.contains(" /from ") || !msg.contains(" /to ")) {
                throw new AtreidesException("event must be /from <datetime> /to <datetime> specified.");
            }
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
     * @return String representation of the list of tasks.
     */
    public String showList() {
        String tasks = IntStream.range(0, list.size())
                .mapToObj(i -> (i + 1) + "." + list.get(i))
                .collect(Collectors.joining("\n"));
        return tasks;
    }

    /**
     * creates a string of list of tasks that will be written into the file object
     * @return String representation of the list of tasks that will be saved in the Storage text file.
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

    /**
     * Checks if the provided index is present in the list.
     *
     * @param index The index to be checked.
     * @throws AtreidesException If the index is out of bounds.
     */
    public void checkIndexPresent(int index) throws AtreidesException {
        if (index >= list.size() || index < 0) {
            throw new AtreidesException("list does not have the index present");
        }

        assert index >= 0;
    }

    public Task getTaskAtIndex(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed from the list.
     * @throws AtreidesException If the index is out of bounds.
     */
    public Task delete(int index) throws AtreidesException {
        checkIndexPresent(index);
        return list.remove(index);
    }

    /**
     * Adds a new task to the task list. Throws an exception if an identical task already exists.
     *
     * @param task The task to be added to the list.
     * @throws AtreidesException If the task already exists in the list.
     */
    public void addTask(Task task) throws AtreidesException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(task)) {
                throw new AtreidesException("Same Task identified \n"
                        + (i + 1) + "." + list.get(i));
            }
        }
        list.add(task);
    }

    /**
     * Finds and returns a list of tasks whose descriptions contain the given keyword.
     *
     * @param description The keyword to search for in the task descriptions.
     * @return A formatted string listing all tasks that match the given keyword.
     */
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
