package Meeju;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    private Storage storage;

    public TaskList() {
        this.storage = new Storage();
        this.taskList = this.storage.initialiseList();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }


    public int getNumberOfTask() {
        return this.taskList.size();
    }
    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + this.taskList.get(i));
        }
    }
    public void markTask(String taskNumber) throws MeejuException{
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
        this.storage.updateFile(taskList);
    }

    public void unmarkTask(String taskNumber) throws MeejuException{
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
        this.storage.updateFile(taskList);
    }

    public void addTodoTask(String taskInstruction) throws MeejuException {
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        Todo task = new Todo(taskInstruction);
        this.taskList.add(task);
        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.storage.updateFile(taskList);
    }

    public void addDeadlineTask(String taskInstruction) throws MeejuException{
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
                    "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM");
        }
        if (taskDescription.isEmpty() || taskDeadline.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }
        Deadline task = new Deadline(taskDescription, taskDeadline);
        this.taskList.add(task);

        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.storage.updateFile(taskList);
    }

    public void addEventTask(String taskInstruction) throws MeejuException{
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
                    "The Correct format is -> event <desc> /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM");
        }
        if (taskDescription.isEmpty() || taskStart.isEmpty() || taskEnd.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }

        Event task = new Event(taskDescription, taskStart, taskEnd);
        this.taskList.add(task);

        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.storage.updateFile(taskList);
    }

    public void deleteTask(String taskNumber) throws MeejuException {
        Task taskToDelete;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to delete!");
        }
        try {
            taskToDelete = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to delete!");
        }

        this.taskList.remove(Integer.parseInt(taskNumber) - 1);

        System.out.println("Meow! I've removed this task:\n" +
                "\t" + taskToDelete +
                "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.storage.updateFile(taskList);

    }

}
