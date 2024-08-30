package xbot;

import xbot.task.Task;
import xbot.task.ToDo;
import xbot.task.Event;
import xbot.task.Deadline;
import xbot.parser.Parser;
import java.util.List;
import java.util.ArrayList;

/**
 * The TaskList class manages the list of tasks in the XBot application.
 * It provides methods to add, delete, and retrieve tasks, as well as
 * to mark tasks as done or undone.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the list of all tasks.
     *
     * @return A List of Task objects.
     */
    public List<Task> getAllTask() {
        return list;
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return list.size();
    }


    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to return.
     * @return The Task object at the specified index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds the task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes the task at the specified position in the list.
     * The task number should be a 1-based index as given by the user.
     *
     * @param rest The 1-based index of the task to be deleted.
     * @throws XBotException If the task number is invalid.
     */
    public void deleteTask(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                System.out.println("Noted. I've removed this task:");
                System.out.print(list.get(taskNumber - 1).toString() + "\n");
                list.remove(taskNumber - 1);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param rest The description of the ToDo task.
     */
    public void addTodo(String rest) {
        System.out.println("Got it. I've added this task:");
        Task newTask = new ToDo(rest);
        list.add(newTask);
        System.out.println(newTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Adds a new Deadline task to the task list.
     * The task description should be followed by a /by keyword and the deadline.
     *
     * @param rest The description and deadline of the task.
     * @throws XBotException If the input format is invalid or the date is in an incorrect format.
     */
    public void addDeadline(String rest) throws XBotException {
        String[] parts = rest.split("/by", 2);
        if (parts.length == 2) {
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();
            if (!deadline.isEmpty() && Parser.isValidDateFormat(deadline)) {
                Task newTask = new Deadline(taskDescription, deadline);
                list.add(newTask);

                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                throw new XBotException("Invalid date input format. Please use the format: D/M/YYYY");
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'deadline <task> /by <date>'");
        }
    }

    /**
     * Adds a new Event task to the task list.
     * The task description should be followed by /to and /from keyword specifying the time period.
     *
     * @param rest The description and time period of the task.
     * @throws XBotException If the input format is invalid or the dates are in an incorrect format.
     */
    public void addEvent(String rest) throws XBotException{
        String[] parts = rest.split("/from", 2);
        if (parts.length == 2) {
            String taskDescription = parts[0].trim();
            String time = parts[1].trim();
            String[] timeParts = time.split("/to", 2);
            if (timeParts.length == 2) {
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();

                if (Parser.isValidDateFormat(from) && Parser.isValidDateFormat(to)) {

                    Task newTask = new Event(taskDescription, from, to);
                    list.add(newTask);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new XBotException("Invalid date input format. Please use the format: D/M/YYYY");
                }
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }
    }

    /**
     * Marks the task at the specified position in the list as complete / done.
     * The task number should be a 1-based index as given by the user.
     *
     * @param rest The 1-based index of the task to be mark as done.
     * @throws XBotException If the task number is invalid.
     */
    public void markDone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                list.get(taskNumber - 1).markAsDone();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    /**
     * Marks the task at the specified position in the list as incomplete / undone.
     * The task number should be a 1-based index as given by the user.
     *
     * @param rest The 1-based index of the task to be mark as undone.
     * @throws XBotException If the task number is invalid.
     */
    public void markUndone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                list.get(taskNumber - 1).markAsUndone();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }
}
