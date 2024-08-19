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

    public static void markItem(String itemNumber) throws MeejuException{
        Task currentTask;
        if (itemNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to mark!");
        }
        try {
            currentTask = getTask(Integer.parseInt(itemNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to mark!");
        }
        if (currentTask.getIsDone()) {
            throw new MeejuException("The task is already marked!");
        }
        currentTask.setIsDone(true);
        System.out.println("\tMeow! I've marked this task as done:\n" +
                "\t" + currentTask);
    }

    public static void unmarkItem(String itemNumber) throws MeejuException{
        Task currentTask;
        if (itemNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to unmark!");
        }
        try {
            currentTask = getTask(Integer.parseInt(itemNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to unmark!");
        }
        if (!currentTask.getIsDone()) {
            throw new MeejuException("The task is not marked yet!");
        }
        currentTask.setIsDone(false);

        System.out.println("\tMeow! I've marked this task as done:\n" +
                "\t" + currentTask);
    }

    public static int getNumberOfTask() {
        return taskList.size();
    }

    public static void addTodoTask(String taskInstruction) throws MeejuException {
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        Todo task = new Todo(taskInstruction);
        taskList.add(task);
        System.out.println("\tMeow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

    public static void addDeadlineTask(String taskInstruction) throws MeejuException{
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        String taskDescription;
        String taskDeadline;
        try {
            taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /by"));
            taskDeadline = taskInstruction.substring(taskInstruction.indexOf(" /by") + 5);
        } catch (StringIndexOutOfBoundsException e){
            throw new MeejuException("I'm having a bit of trouble understanding the task. " +
                    "Could you please explain it using the correct format?");
        }
        if (taskDescription.isEmpty() || taskDeadline.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }
        Deadline task = new Deadline(taskDescription, taskDeadline);
        taskList.add(task);

        System.out.println("\tMeow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

    public  static void addEventTask(String taskInstruction) throws MeejuException{
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        String taskDescription;
        String taskStart;
        String taskEnd;
        try {
            taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /from"));
            taskStart = taskInstruction.substring(taskInstruction.indexOf(" /from") + 7,
                    taskInstruction.indexOf(" /to"));
            taskEnd = taskInstruction.substring(taskInstruction.indexOf(" /to") + 5);
        } catch (StringIndexOutOfBoundsException e){
            throw new MeejuException("I'm having a bit of trouble understanding the task. " +
                    "Could you please explain it using the correct format?");
        }
        if (taskDescription.isEmpty() || taskStart.isEmpty() || taskEnd.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }

        Event task = new Event(taskDescription, taskStart, taskEnd);
        taskList.add(task);

        System.out.println("\tMeow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

}
