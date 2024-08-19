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

    public static void markTask(String taskNumber) throws MeejuException{
        Task taskToMark;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to mark!");
        }
        try {
            taskToMark = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to mark!");
        }
        if (taskToMark.getIsDone()) {
            throw new MeejuException("The task is already marked!");
        }
        taskToMark.setIsDone(true);
        System.out.println("Meow! I've marked this task as done:\n" +
                "\t" + taskToMark);
    }

    public static void unmarkTask(String taskNumber) throws MeejuException{
        Task taskToUnmark;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to unmark!");
        }
        try {
            taskToUnmark = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to unmark!");
        }
        if (!taskToUnmark.getIsDone()) {
            throw new MeejuException("The task is not marked yet!");
        }
        taskToUnmark.setIsDone(false);

        System.out.println("Meow! I've marked this task as not done yet:\n" +
                "\t" + taskToUnmark);
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
        System.out.println("Meow! I've added this task:\n" +
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
            throw new MeejuException("I'm having a bit of trouble understanding the task.\n" +
                    "Could you please explain it using the correct format?\n" +
                    "The Correct format is -> deadline <desc> /by <by>");
        }
        if (taskDescription.isEmpty() || taskDeadline.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }
        Deadline task = new Deadline(taskDescription, taskDeadline);
        taskList.add(task);

        System.out.println("Meow! I've added this task:\n" +
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
            throw new MeejuException("I'm having a bit of trouble understanding the task. \n" +
                    "Could you please explain it using the correct format?\n" +
                    "The Correct format is -> event <desc> /from <from> /to <to>");
        }
        if (taskDescription.isEmpty() || taskStart.isEmpty() || taskEnd.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }

        Event task = new Event(taskDescription, taskStart, taskEnd);
        taskList.add(task);

        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
    }

    public static void deleteTask(String taskNumber) throws MeejuException {
        Task taskToDelete;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to delete!");
        }
        try {
            taskToDelete = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to delete!");
        }

        taskList.remove(Integer.parseInt(taskNumber) - 1);

        System.out.println("Meow! I've removed this task:\n" +
                "\t" + taskToDelete +
                "\nNow you have " + getNumberOfTask() + " tasks in the list.");

    }

}
