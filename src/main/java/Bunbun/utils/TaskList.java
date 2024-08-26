package Bunbun.utils;
import Bunbun.exceptions.*;
import Bunbun.tasks.*;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * This class implements a task list.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private UI ui;
    private int numOfTasks;


    /**
     * Constructor for a TaskList. Initializes number of Bunbun.tasks to 0.
     */
    public TaskList(Storage s, UI ui) {
        this.taskList = s.toArrayList();
        this.ui = ui;
        this.numOfTasks = this.taskList.size();
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    public Task getTaskByIndex(int i) {
        return this.taskList.get(i);
    }

    /**
     * Method to add task to the task list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.numOfTasks += 1;
        this.ui.response("Added \'" + task + "\' task!");
        String msg = String.format("By the way, you have %d tasks now!", this.numOfTasks);
        this.ui.response(msg);
    }

    /**
     * Method to add To Do task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     */
    public void addToDo(ArrayList<String> tokens) throws MissingTaskException {
        if (tokens.size() == 1) {
            throw new MissingTaskException("Failed. Specify a task for your todo!!!! D:");
        } else {
            String taskDescription = "";
            int len = tokens.size();
            for (int i = 1; i < len; i++) {
                taskDescription += tokens.get(i) + " ";
            }
            ToDo todo = new ToDo(taskDescription);
            this.addTask(todo);
        }
    }

    /**
     * Method to add Deadline task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     */
    public void addDeadline(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1 || tokens.get(1).equals("/by")) {
            throw new MissingTaskException("Failed. Specify a task for your deadline task!!!! D:");
        } else if (!(tokens.contains("/by")) || tokens.indexOf("/by") == tokens.size() - 1) {
            throw new InvalidTaskFormatException(
                    "Failed. Add /by [DATE] to specify when to complete your task by!!! ;=;");
        } else {
            String taskDescription = "";
            LocalDate deadline = null;
            int len = tokens.size();
            Boolean failed = false;
            for (int i = 1; i < len; i++) {
                if (tokens.get(i).equals("/by")) {
                    i += 1;
                    deadline = DateTimeHandler.isValidLocalDate(tokens.get(i));
                    if (deadline == null || tokens.size() > i + 1) {
                        failed = true;
                    }
                } else {
                    taskDescription += tokens.get(i) + " ";
                }
            }
            if (!failed) {
                Deadline deadlineTask = new Deadline(taskDescription, deadline);
                this.addTask(deadlineTask);
            } else {
                throw new InvalidDateFormatException("Failed. Specify your date in yyyy-MM-dd format!! ;^;");
            }
        }
    }

    /**
     * Method to add Event task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     */
    public void addEvent(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1 || tokens.get(1).equals("/from") || tokens.get(1).equals("/to")) {
            throw new MissingTaskException("Failed. Specify a task for your event task!!!! D:");
        } else if (!(tokens.contains("/from")) || !(tokens.contains("/to")) ||
                (tokens.indexOf("/from") > tokens.indexOf("/to")) ||
                (tokens.indexOf("/from") + 1 == tokens.indexOf("/to")) ||
                tokens.indexOf("/to") == tokens.size() - 1) {
            throw new InvalidTaskFormatException(
                    "Failed. Add /from [DATE] /to [DATE] to specify the duration of your event!!! ;=;");
        } else {
            String taskDescription = "";
            LocalDate start = null;
            LocalDate end = null;
            int len = tokens.size();
            int i = 1;
            Boolean failed = false;
            while (i < len && !(tokens.get(i).equals("/from"))) {
                taskDescription += tokens.get(i) + " ";
                i += 1;
            }
            i += 1;
            start = DateTimeHandler.isValidLocalDate(tokens.get(i));
            if (start == null || !tokens.get(i + 1).equals("/to")) {
                failed = true;
            } else {
                i += 2;
                end = DateTimeHandler.isValidLocalDate(tokens.get(i));
                if (end == null || tokens.size() > i + 1) {
                    failed = true;
                }
            }

            if (!failed) {
                Event event = new Event(taskDescription, start, end);
                this.addTask(event);
            } else {
                throw new InvalidDateFormatException("Failed. Specify your date in yyyy-MM-dd format!! ;^;");
            }
        }
    }


    /**
     * Method to display task list for user.
     */
    public void displayList() {
        this.ui.response("These are your Bunbun.tasks!");
        for (int i = 0; i < this.numOfTasks; i++) {
            Task currTask = this.taskList.get(i);
            String res;
            if (currTask.isComplete()) {
                res = String.format("%d.%s", i + 1, currTask);
            } else {
                res = String.format("%d.%s", i + 1, currTask);
            }
            this.ui.response(res);
        }
        this.ui.response("That's all your Bunbun.tasks for now :>>>");
    }

    /**
     * Signal that a task is complete and mark the description with an X.
     *
     * @param taskNum int to indicate which task to mark as complete.
     */
    public void markDoneTask(int taskNum) throws TaskNumOutOfBoundsException {
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            throw new TaskNumOutOfBoundsException(
                    String.format("I can't mark task %d cause it doesn't exist!!! ;-;", taskNum));
        } else {
            Task reqTask = this.taskList.get(taskNum - 1);
            reqTask.complete();
            this.ui.response("Oki, I'll mark the task as done *w*! Good job finishing the task!!");
            String res = String.format("%s", reqTask);
            this.ui.response(res);
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param taskNum int to indicate which task to delete.
     */
    public void deleteTask(int taskNum) throws TaskNumOutOfBoundsException {
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            throw new TaskNumOutOfBoundsException(
                    String.format("I can't delete task %d cause it doesn't exist!!! ;-;", taskNum));
        } else {
            this.ui.response(String.format("Oki, I've deleted %s task!", this.taskList.get(taskNum - 1)));
            this.taskList.remove(taskNum - 1);
            this.numOfTasks -= 1;
            this.ui.response(String.format("You have %d tasks left!!", this.numOfTasks));
        }
    }

}
