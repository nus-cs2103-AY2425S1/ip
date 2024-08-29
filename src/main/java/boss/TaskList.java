package boss;

import java.util.ArrayList;

/**
 * Represents the class that contains the task list and
 * has operations to add/delete tasks from the list
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Unmarks a task from the list.
     * @param task the task that the user wants to unmark
     * @return the text to replace text in file
     */
    public String unmark(String task) {
        // replace all characters with nothing, in order to extract number!
        String taskNum = task.replaceAll("[^0-9]", "");
        try {
            if (taskNum.equals("")) {
                throw new BossException("Please specify a task number to unmark");
            }
            int num = Integer.parseInt(taskNum);
            if (tasks.size() < num) {
                throw new BossException("boss.Task " + num + " does not exist yet");
            }
            Task item = tasks.get(num - 1);
            item.markAsUnDone();
            String toReplace = item.getDescription();
            String newFileData = reWrite(tasks, num, toReplace);

            System.out.println("Ok! I have marked this task as not done yet!");
            System.out.println(tasks.get(num - 1));
            return newFileData;
        } catch (BossException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Unmarks a task from the list.
     * @param task the task that the user wants to mark
     * @return the text to replace text in file
     */
    public String mark(String task) {
        String taskNum = task.replaceAll("[^0-9]", "");
        try {
            if (taskNum.equals("")) {
                throw new BossException("Please specify a task number to mark");
            }
            int num = Integer.parseInt(taskNum);
            if (tasks.size() < num) {
                throw new BossException("boss.Task " + num + " does not exist yet");
            }
            Task item = tasks.get(num - 1);
            item.markAsDone();
            String toReplace = item.getDescription();

            String newFileData = reWrite(tasks, num, toReplace);

            System.out.println("Nice! I have marked this task as done!");
            System.out.println(tasks.get(num - 1));
            return newFileData;
        } catch (BossException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Deletes a task from the list.
     * @param task the task that the user wants to delete
     * @return the text to replace text in file
     */
    public String delete(String task) {
        try {
            String taskNum = task.replaceAll("[^0-9]", "");
            if (taskNum.equals("")) {
                throw new BossException("Please specify a task number to delete");
            }
            int num = Integer.parseInt(taskNum);
            if (tasks.size() < num) {
                throw new BossException("boss.Task " + num + " does not exist");
            }
            Task item = tasks.remove(num - 1);
            String newFileData = reWrite(tasks, num, "");

            System.out.println("Ok. This task has been removed!");
            System.out.println(item);
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
            return newFileData;

        } catch (BossException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * rewrites text file
     *
     * @param tasks
     * @param index
     * @param toReplace
     * @return
     */
    private static String reWrite(ArrayList<Task> tasks, int index, String toReplace) {
        String s = "";
        int i = 1;
        for (Task str : tasks) {
            s += "" + str + '\n';
        }
        return s;
    }


    /**
     * Prints user messages to the screen.
     * @param len number of tasks in list
     */
    public void printabstraction(int len) {
        System.out.println("Got it! I've added this task now");
        int size = tasks.size();
        System.out.println(tasks.get(size-1));
        System.out.println("Now you have " + len + " tasks in the list.");
    }

}

