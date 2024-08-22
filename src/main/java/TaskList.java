import java.util.ArrayList;
/**
 * This class represents a list containing tasks.
 * It provides methods to add, mark, unmark and print tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Adds new task to todolist
     *
     * @param task the name of task to be added
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
        Reply.printMessage(" Got it. I've added this task:" + "\n" +
                "   " + task.toString() + "\n" +
                " Now you have "  + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Mark specific task as done
     *
     * @param index the task number to be marked
     */
    public void markTask(int index) {
        if (index > 0 && index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            Reply.printMessage("Nice! I've marked this task as done:\n" + "   " + task.toString());
        } else {
            Reply.printMessage("Task number " + index + " does not exist.");
        }
    }

    /**
     * Mark specific task as not done
     *
     * @param index the task number to be marked
     */
    public void unmarkTask(int index) {
        if (index > 0 && index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            task.markAsNotDone();
            Reply.printMessage("OK, I've marked this task as not done yet:\n" + "   " + task.toString());
        } else {
            Reply.printMessage("Task number " + index + " does not exist.");
        }
    }

    /**
     * print all tasks added to the list
     *
     * @return description of all tasks added
     */
    public String printTodo(){
        StringBuilder tasks = new StringBuilder(" Here are the tasks in your list:" + "\n");

        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(listOfTasks.get(i).toString());
            if (i < listOfTasks.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }
}
