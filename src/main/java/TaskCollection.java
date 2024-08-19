import java.util.ArrayList;
public class TaskCollection {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + taskList.get(i));
        }
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
                "\t" + currentTask);
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
                "\t" + currentTask);
    }

    public static int getNumberOfTask() {
        return taskList.size();
    }

    public static void addTodoTask(String taskInstruction) {
        //Raise Exception if task instruction is empty
        Todo task = new Todo(taskInstruction);
        taskList.add(task);
        System.out.println("\tMeow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

    public static void addDeadlineTask(String taskInstruction) {
        //add below in try catch exception
        String taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /by"));
        String taskDeadline = taskInstruction.substring(taskInstruction.indexOf(" /by")+ 5);

        Deadline task = new Deadline(taskDescription, taskDeadline);
        taskList.add(task);

        System.out.println("\tMeow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

    public  static void addEventTask(String taskInstruction) {
        //add below in try catch exception
        String taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /from"));
        String taskStart = taskInstruction.substring(taskInstruction.indexOf(" /from")+ 7,
                taskInstruction.indexOf(" /to"));
        String taskEnd = taskInstruction.substring(taskInstruction.indexOf(" /to")+ 5);

        Event task = new Event(taskDescription, taskStart, taskEnd);
        taskList.add(task);

        System.out.println("\tMeow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

}
