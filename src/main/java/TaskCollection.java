import java.util.ArrayList;
public class TaskCollection {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + taskList.get(i).getStatusIcon()
                    + " " + taskList.get(i).getTaskDescription());
        }
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("\t added: " + task.getTaskDescription());
    }

    public static Task getTask(int index) {
        return taskList.get(index);
    }

    public static void markItem(String itemNumber) {
        if (itemNumber.isEmpty()) {
            //Raise Exception
            return;
        }
        //include the below in try catch as well and raise a invalid task exception
        Task currentTask = getTask(Integer.parseInt(itemNumber) - 1);
        currentTask.setIsDone(true);
        System.out.println("\tMeow! I've marked this task as done:\n" +
                "\t" + currentTask.getStatusIcon() + " " + currentTask.getTaskDescription());
    }

    public static void unmarkItem(String itemNumber) {
        if (itemNumber.isEmpty()) {
            //Raise Exception
            return;
        }
        //include the below in try catch as well and raise a invalid task exception
        Task currentTask = getTask(Integer.parseInt(itemNumber) - 1);
        currentTask.setIsDone(false);

        System.out.println("\tMeow! I've marked this task as done:\n" +
                "\t" + currentTask.getStatusIcon() + " " + currentTask.getTaskDescription());
    }
}
