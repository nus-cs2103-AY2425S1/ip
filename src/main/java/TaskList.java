import java.io.IOException;
import java.util.List;

/**
 * This class represents a list containing tasks.
 * It provides methods to add, mark, unmark and print tasks in the list.
 */
public class TaskList {
    private static TaskList tasks;
    private List<Task> listOfTasks;

    private TaskList() {
        try {
            listOfTasks = TaskDataBase.load();
        } catch (IOException e) {
            Reply.printMessage("Oops! There is a issue with file database.");
        }
    }

    public static TaskList init() {
        if (tasks == null) {
            tasks = new TaskList();
        }
        return tasks;
    }

    /**
     * Adds new task to todolist
     *
     * @param task the name of task to be added
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
        TaskDataBase.save(listOfTasks);
        Reply.printMessage("Got it. I've added this task:" + "\n" +
                "  " + task.toString() + "\n" +
                "Now you have "  + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Mark specific task as done
     *
     * @param index the task number to be marked
     */
    public void markTask(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            TaskDataBase.save(listOfTasks);
            Reply.printMessage("Nice! I've marked this task as done:\n" + "  " + task.toString());
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Mark specific task as not done
     *
     * @param index the task number to be marked
     */
    public void unmarkTask(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            TaskDataBase.save(listOfTasks);
            task.markAsNotDone();
            Reply.printMessage("OK, I've marked this task as not done yet:\n" + "  " + task.toString());
        } else {
            throw new TaskNotFoundException();
        }
    }

    public void deleteTask(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            Task removedTask = listOfTasks.remove(index - 1);
            TaskDataBase.save(listOfTasks);
            Reply.printMessage("Noted. I've removed this task:" + "\n" + "  "
                    + removedTask + "\n" + "Now you have "
                    + listOfTasks.size() + " tasks in the list." );
        } else {
            throw new TaskNotFoundException();
        }
    }
    /**
     * print all tasks added to the list
     *
     * @return description of all tasks added
     */
    public String printList(){
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list:" + "\n");

        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(i + 1).append(".").append(listOfTasks.get(i).toString());
            if (i < listOfTasks.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }
}
