package boss;

import boss.exceptions.BossException;
import boss.exceptions.EmptyTaskInputException;
import boss.exceptions.NonExistentTaskException;
import boss.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the class that contains the task list and
 * has operations to add/delete tasks from the list
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Unmarks a task from the list.
     * @param task the task that the user wants to unmark
     * @return the text to replace text in file
     */
    public String unmark(String task) throws BossException {
        // replace all characters with nothing, in order to extract number!
        String taskNum = task.replaceAll("[^0-9]", "");
        if (taskNum.isEmpty()) {
            throw new EmptyTaskInputException("unmark");
        }
        int num = Integer.parseInt(taskNum);
        if (tasks.size() < num) {
            throw new NonExistentTaskException(num);
        }
        Task item = tasks.get(num - 1);
        item.markAsUnDone();

        String newFileData = getAll(tasks);

        System.out.println("Ok! I have marked this task as not done yet!");
        System.out.println(tasks.get(num - 1));
        return newFileData;
    }

    /**
     * Unmarks a task from the list.
     * @param task the task that the user wants to mark
     * @return the text to replace text in file
     */
    public String mark(String task) throws BossException {
        String taskNum = task.replaceAll("[^0-9]", "");

        if (taskNum.isEmpty()) {
            throw new EmptyTaskInputException("mark");
        }
        int num = Integer.parseInt(taskNum);
        if (tasks.size() < num) {
            throw new NonExistentTaskException(num);
        }
        Task item = tasks.get(num - 1);
        item.markAsDone();

        String newFileData = getAll(tasks);
        System.out.println("Nice! I have marked this task as done!");
        System.out.println(tasks.get(num - 1));
        return newFileData;
    }

    /**
     * Deletes a task from the list.
     * @param task the task that the user wants to delete
     * @return the text to replace text in file
     */
    public String delete(String task) throws BossException {
        String taskNum = task.replaceAll("[^0-9]", "");
        if (taskNum.isEmpty()) {
            throw new EmptyTaskInputException("delete");
        }
        int num = Integer.parseInt(taskNum);
        if (tasks.size() < num) {
            throw new NonExistentTaskException(num);
        }
        Task item = tasks.remove(num - 1);
        String newFileData = getAll(tasks);

        System.out.println("Ok. This task has been removed!");
        System.out.println(item);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return newFileData;
    }

    /**
     * used to rewrite text file
     *
     * @param tasks list of tasks
     * @return a String containing all the tasks
     */
    private static String getAll(ArrayList<Task> tasks) {
        String s = "";
        for (Task str : tasks) {
            s += "" + str + '\n';
        }
        return s;
    }


    /**
     * Prints user messages to the screen.
     */
    public void printTextAbstraction() {
        System.out.println("Got it! I've added this task now");
        int size = tasks.size();
        System.out.println(tasks.get(size-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public String printAbstraction() {
        String toPrint = "";
        toPrint = toPrint + "Got it! I've added this task now!" + "\n";
        toPrint = toPrint + "Now you have " + tasks.size() + " tasks in the list." + "\n";
        return toPrint;
    }


    /**
     * Finds a word in the list of tasks
     * @param word word to find
     */
    public void find(String word) {
        int i = 0;
        for (Task str : tasks) {
            if (str.getDescription().contains(word)) {
                if (i == 0) {
                    System.out.println("Here are the matching tasks in your list: ");
                    i++;
                }
                System.out.println(str);
            }
        }
        if (i == 0) {
            System.out.println("There are no matching tasks :(");
        }
    }

    public String findTask(String word) {
        int i = 0;
        String result = "";
        for (Task str : tasks) {
            if (str.getDescription().contains(word)) {
                if (i == 0) {
                    result = result + "Here are the matching tasks in your list: " + "\n";
                    i++;
                }
                result = result + str + "\n";
            }
        }
        if (i == 0) {
            return "There are no matching tasks :(";
        }
        return result;
    }

}

