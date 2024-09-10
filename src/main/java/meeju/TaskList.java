package meeju;

import java.util.ArrayList;

/**
 * TaskList class manages a list of tasks using an ArrayList and provides methods to manipulate this list.
 * It supports adding, marking, unmarking, finding and deleting tasks.
 * It also interacts with the Storage class to permenantly store the tasks in disk upon every update to the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Construct for TaskList object.
     * Creates a new Storage object and initialises the ArrayList with the tasks saved in the disk.
     *
     * @param storage The Storage object responsible for loading and saving tasks.
     */
    public TaskList(Storage storage) {
        assert storage != null : "Storage object is null";

        this.storage = storage;
        this.taskList = this.storage.initialiseList();
    }

    private Task getTask(int index) {
        return this.taskList.get(index);
    }

    private int getNumberOfTasks() {
        return this.taskList.size();
    }

    /**
     * Prints out all the contents of taskList
     *
     * @return list The content of taskList in string form.
     */
    public String printList() {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            taskString.append((i + 1)).append(". ").append(this.taskList.get(i)).append("\n");
        }
        return taskString.toString();
    }

    /**
     * Marks a task as done based on its task number as per the list.
     *
     * @param taskNumber The number of the task to mark as done.
     * @return Response acknowledging completion of task
     * @throws MeejuException If the task number is invalid, or the task is already marked.
     */
    public String markTask(String taskNumber) throws MeejuException {
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
        this.storage.updateFile(taskList);
        return "Meow! I've marked this task as done:\n"
                + "\t" + taskToMark;
    }

    /**
     * Unmarks a task as not done based on its task number as per the list.
     * The undo operation of mark
     *
     * @param taskNumber The number of the task to unmark.
     * @return Response acknowledging completion of task
     * @throws MeejuException If the task number is invalid, or the task is not yet marked.
     */
    public String unmarkTask(String taskNumber) throws MeejuException {
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
        this.storage.updateFile(taskList);
        return "Meow! I've marked this task as not done yet:\n"
                + "\t" + taskToUnmark;
    }

    /**
     * Adds a new todo task to the list.
     *
     * @param taskInstruction The description of the todo task.
     * @return Response acknowledging completion of task
     * @throws MeejuException If the task description is empty.
     */
    public String addTodoTask(String taskInstruction) throws MeejuException {
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }

        Todo task = new Todo(taskInstruction);
        this.taskList.add(task);
        this.storage.updateFile(taskList);
        return "Meow! I've added this task:\n"
                + "\t" + task + "\nNow you have " + getNumberOfTasks()
                + " tasks in the list.";
    }

    /**
     * Adds a new deadline task to the list.
     *
     * @param taskInstruction The instruction containing the description and deadline of the task.
     * @return Response acknowledging completion of task
     * @throws MeejuException If the task description or deadline is empty, or the format is incorrect.
     */
    public String addDeadlineTask(String taskInstruction) throws MeejuException {
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }

        String taskDescription;
        String taskDeadline;
        try {
            taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /by"));
            taskDeadline = taskInstruction.substring(taskInstruction.indexOf(" /by") + 5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MeejuException("I'm having a bit of trouble understanding the task.\n"
                    + "Could you please explain it using the correct format?\n"
                    + "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM");
        }

        if (taskDescription.isEmpty() || taskDeadline.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }

        Deadline task = new Deadline(taskDescription, taskDeadline);
        this.taskList.add(task);
        this.storage.updateFile(taskList);
        return "Meow! I've added this task:\n"
                + "\t" + task + "\nNow you have "
                + getNumberOfTasks() + " tasks in the list.";

    }

    /**
     * Adds a new event task to the list.
     *
     * @param taskInstruction The instruction containing the description, start time, and end time of the event task.
     * @return Response acknowledging completion of task
     * @throws MeejuException If the task description, start time, or end time is empty, or the format is incorrect.
     */
    public String addEventTask(String taskInstruction) throws MeejuException {
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
        } catch (StringIndexOutOfBoundsException e) {
            throw new MeejuException("I'm having a bit of trouble understanding the task. \n"
                    + "Could you please explain it using the correct format?\n"
                    + "The Correct format is -> event <desc> /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM");
        }

        if (taskDescription.isEmpty() || taskStart.isEmpty() || taskEnd.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }

        Event task = new Event(taskDescription, taskStart, taskEnd);
        this.taskList.add(task);
        this.storage.updateFile(taskList);
        return "Meow! I've added this task:\n"
                + "\t" + task + "\nNow you have " + getNumberOfTasks()
                + " tasks in the list.";

    }

    /**
     * Deletes a task from the list based on its number in the list.
     *
     * @param taskNumber The number of the task to delete.
     * @return Response acknowledging completion of task
     * @throws MeejuException If the task number is invalid or does not exist.
     */
    public String deleteTask(String taskNumber) throws MeejuException {
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
        this.storage.updateFile(taskList);
        return "Meow! I've removed this task:\n"
                + "\t" + taskToDelete
                + "\nNow you have " + getNumberOfTasks() + " tasks in the list.";

    }

    /**
     * Finds and lists all those tasks whose task description contains the specified keyword.
     *
     * @param keyword The search keyword.
     * @return Response acknowledging completion of task
     * @throws MeejuException If blank string is provided.
     */
    public String findTask(String keyword) throws MeejuException {
        assert keyword != null : "Search keyword is null in findTask";

        if (keyword.isEmpty()) {
            throw new MeejuException("Meow! Please tell me what should i find");
        }
        int count = 1;
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTaskDescription().contains(keyword)) {
                taskString.append(count).append(". ").append(this.taskList.get(i)).append("\n");
                count++;
            }
        }
        if (count == 1) {
            return "Meow! No such tasks can be found.";
        }
        return taskString.toString();
    }
}
