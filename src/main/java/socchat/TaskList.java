package socchat;

import java.time.LocalDateTime;
import java.util.ArrayList;

import socchat.task.Task;
import socchat.task.deadline.Deadline;
import socchat.task.event.Event;
import socchat.task.todo.Todo;

/**
 * The TaskList class manages a list of tasks, allowing users to add, delete,
 * mark, unmark, list, and find tasks.
 */
public class TaskList {

    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Default constructor that initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that initializes the task list with existing tasks.
     *
     * @param tasks the list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes a task from the task list based on the index provided as a string.
     *
     * @param indexString the index of the task to delete (1-based index)
     * @throws SocchatException if the index is invalid or not a valid number
     */
    public String delete(String indexString) throws SocchatException {
        try {
            String respond = "";
            int taskIndex = Integer.parseInt(indexString);
            Task task = tasks.get(taskIndex - 1);
            tasks.remove(taskIndex - 1);
            respond += ("Deleted " + "\"" + task.toString() + "\"" + "\n");
            respond += ("Now you have " + tasks.size() + " task(s).\n");
            Storage.update(tasks, false);
            return respond;
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number for deletion.");
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    public String list() {
        String respond = "Your task list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            respond += (i + 1) + ": ";
            respond += (curr.toString() + "\n");
        }
        return respond;
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param strToken an array where the first element contains the ToDo description
     * @throws SocchatException if the ToDo format is invalid
     */
    public String addTodo(String[] strToken) throws SocchatException {
        try {
            String respond = "";
            String des = strToken[0].substring("todo ".length()).trim();
            Task t = new Todo(des);
            tasks.add(t);
            respond += "added: ";
            respond += (t.toString() + "\n");
            respond += ("Now you have " + tasks.size() + " task(s).\n");

            Storage.update(tasks, true);
            return respond;
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Todo format: Description is empty");

        }
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param strToken an array containing the event description, start time, and end time
     * @throws SocchatException if the event format is invalid
     */
    public String addEvent(String[] strToken) throws SocchatException {
        try {
            String respond = "";
            String des = strToken[0].substring("event ".length());
            String from = strToken[1].substring("from ".length());
            String to = strToken[2].substring("to ".length());
            LocalDateTime formattedFrom = Parser.parseDate(from);
            LocalDateTime formattedTo = Parser.parseDate(to);

            Task t = new Event(des, formattedFrom, formattedTo);
            tasks.add(t);
            respond += "added: ";
            respond += (t.toString() + "\n");
            respond += ("Now you have " + tasks.size() + " task(s).\n");

            storage.update(tasks, true);
            return respond;
        } catch (IndexOutOfBoundsException e) {

            throw new SocchatException("Invalid Event format: event <description> /from <startTime> /to <endTime>");
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param strToken an array containing the deadline description and due date
     * @throws SocchatException if the deadline format is invalid
     */
    public String addDeadline(String[] strToken) throws SocchatException {
        try {
            String respond = "";
            String des = strToken[0].substring("deadline ".length());
            String by = strToken[1].substring("by ".length());
            LocalDateTime formattedBy = Parser.parseDate(by);

            Task t = new Deadline(des, formattedBy);
            tasks.add(t);
            respond += "added: ";
            respond += (t.toString() + "\n");
            respond += ("Now you have " + tasks.size() + " task(s).\n");

            storage.update(tasks, true);
            return respond;
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid Deadline format: deadline <description> /by <deadline>");
        }

    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * @param indexString the index of the task to mark or unmark (1-based index)
     * @param mark whether to mark or unmark the task
     * @throws SocchatException if the index is invalid or not a valid number
     */
    public String setMark(String indexString, Boolean mark) throws SocchatException {
        try {
            String respond = "";
            int taskIndex = Integer.parseInt(indexString);
            if (mark) {
                respond =  tasks.get(taskIndex - 1).mark();
            } else {
                respond =  tasks.get(taskIndex - 1).unmark();
            }
            Storage.update(tasks, false);
            return respond;
        } catch (IndexOutOfBoundsException e) {
            throw new SocchatException("Invalid task number.");
        } catch (NumberFormatException e) {
            throw new SocchatException("Please enter a valid task number.");
        }
    }

    /**
     * Finds tasks that contain a keyword in their description.
     *
     * @param keyword the keyword to search for in the task descriptions
     * @throws SocchatException if no tasks contain the keyword
     */
    public String find(String keyword) throws SocchatException {
        String respond = "";
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        respond += ("Found " + foundTasks.size() + " task(s). \n");
        for (int i = 0; i < foundTasks.size(); i++) {
            Task curr = foundTasks.get(i);
            respond += ((i + 1) + ": ");
            respond += (curr.toString() + "\n");
        }
        return respond;
    }



}
