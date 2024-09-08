package slothingwaffler;

import java.util.ArrayList;

/**
 * Manages a list of tasks.
 * <p>
 * This class allows adding, removing, and accessing tasks in a list.
 * </p>
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList instance with a specified Storage object.
     *
     * @param storage the Storage object used for saving and loading tasks
     */
    public TaskList(Storage storage) {
        this.tasks = storage.load();
    }

    public TaskList(StorageStub storage) {
        this.tasks = storage.load();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param split an array containing the task description split by space
     * @return the added Todo task
     * @throws SlothingWafflerException if the task description is empty
     */
    public String addTodoTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Todo Task cannot be empty!");
        }
        Task task = new Todo(split[1]);
        assert task != null : "task should not be null";
        tasks.add(task);
        String count = "\nYou now have " + tasks.size() + " task(s) to do.";
        return "Keep it up! I've added this new task:\n" + task + count;
    }

    /**
     * Adds an Event task to the list.
     *
     * @param split an array containing the task description and times
     * @return the added Event task
     * @throws SlothingWafflerException if the task description, start time, or end time is missing
     */
    public String addEventTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of an Event Task cannot be empty.");
        }
        String[] desc = split[1].split(" /from | /to ");
        if (desc.length < 3) {
            throw new SlothingWafflerException("HEY!! An event must have a description, start time, and end time.");
        }
        Task task = new Event(desc[0], desc[1], desc[2]);
        assert task != null : "task should not be null";
        tasks.add(task);
        String count = "\nYou now have " + tasks.size() + " task(s) to do.";
        return "Keep it up! I've added this new task:\n" + task + count;
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param split an array containing the task description and due date
     * @return the added Deadline task
     * @throws SlothingWafflerException if the task description or due date is missing
     */
    public String addDeadlineTask(String[] split) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Deadline Task cannot be empty.");
        }
        String[] desc = split[1].split(" /by ", 2);
        if (desc.length < 2) {
            throw new SlothingWafflerException("HEY!! The Deadline Task must have a description AND a due date.");
        }
        Task task = new Deadline(desc[0], desc[1]);
        assert task != null : "task should not be null";
        tasks.add(task);
        String count = "\nYou now have " + tasks.size() + " task(s) to do.";
        return "Keep it up! I've added this new task:\n" + task + count;
    }

    /**
     * Displays the list of tasks to the console.
     */
    public String displayTaskList() {
        if (tasks.isEmpty()) {
            return "Great job!! You are on track with everything! You have no tasks on your list currently.";
        }
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            msg.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return msg.toString();
        }



    public String markTask(int taskNum) throws SlothingWafflerException {
        if (taskNum > tasks.size() || taskNum <= 0) {
            throw new SlothingWafflerException("You either have no tasks or don't have that many tasks!");
        }
        Task task = tasks.get(taskNum - 1);
        task.markAsDone();
        return "Great job!. I've marked this task as done:\n" + task;
    }

    public String deleteTask(int taskNum) throws SlothingWafflerException {
        if (taskNum > tasks.size() || taskNum <= 0) {
            throw new SlothingWafflerException("You either have no tasks or don't have that many tasks!");
        }
        Task task = tasks.get(taskNum - 1);
        assert task != null : "task should not be null";
        tasks.remove(taskNum - 1);
        String count = "\nYou now have " + tasks.size() + " task(s) to do.";
        return "Just clearing up I see. I've removed this task:\n" + task.toString() + count;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Finds and displays tasks that match a search term.
     *
     * @param split an array containing the search term
     */
    public String findMatchingTasks(String[] split) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(split[1])) {
                matchingTasks.add(task);
            }
        }
        StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            msg.append(i + 1).append(". ").append(matchingTasks.get(i).toString()).append("\n");
        }
        return msg.toString();
    }

    /**
     * Moves a specified task to the top of the task list, marking it as a higher priority.
     *
     * @param taskNum the index of the task to be prioritised (1-based index)
     * @return a message confirming the task has been prioritised
     * @throws SlothingWafflerException if the specified task number is invalid (e.g., out of range or zero)
     */
    public String prioritiseTask(int taskNum) throws SlothingWafflerException {
        if (taskNum > tasks.size() || taskNum <= 0) {
            throw new SlothingWafflerException("You either have no tasks or don't have that many tasks!");
        }
        Task task = tasks.get(taskNum - 1);
        tasks.remove(task);
        tasks.add(0, task);
        return "Good to see you getting your priorities straight! " +
                "I have put this task at the top of your task list:\n" + task;
    }

}
