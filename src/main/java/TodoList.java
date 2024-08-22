import java.util.ArrayList;
/**
 * This class represents a todolist.
 * It provides methods to add and print tasks in the todolist.
 */
public class TodoList {
    private ArrayList<Task> listOfTasks;

    public TodoList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Adds new task to todolist
     *
     * @param name the name of task to be added
     */
    public void addTask(String name) {
        Task task = new Task(name);
        listOfTasks.add(task);
        Reply.printMessage(" added: " + task.printTask());
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
            Reply.printMessage("Nice! I've marked this task as done:\n" + "   " + task.printTask());
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
            Reply.printMessage("OK, I've marked this task as not done yet:\n" + "   " + task.printTask());
        } else {
            Reply.printMessage("Task number " + index + " does not exist.");
        }
    }

    /**
     * print all tasks added to todolist
     *
     * @return String of all tasks added
     */
    public String printTodo(){
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(listOfTasks.get(i).printTask());
            if (i < listOfTasks.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }
}
