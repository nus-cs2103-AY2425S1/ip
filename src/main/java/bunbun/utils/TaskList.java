package bunbun.utils;

import bunbun.exceptions.BunbunException;
import bunbun.exceptions.InvalidCommandFormatException;
import bunbun.exceptions.InvalidDateFormatException;
import bunbun.exceptions.InvalidTaskFormatException;
import bunbun.exceptions.MissingTaskException;
import bunbun.exceptions.TaskNumOutOfBoundsException;
import bunbun.tasks.*;

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
    private Ui ui;
    private int numOfTasks;


    /**
     * Instantiates a task list, keeping track of the Storage
     * for the task list and the UI to print out results.
     *
     * @param s Storage to store persistent data from task list.
     * @param ui UI to print out results of methods.
     */
    public TaskList(Storage s, Ui ui) {
        this.taskList = s.toArrayList();
        this.ui = ui;
        this.numOfTasks = this.taskList.size();
    }

    /**
     * Returns number of tasks in task list.
     *
     * @return int of number of tasks in task list.
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    /**
     * Returns the task at index i in the task list ArrayList.
     *
     * @param i int index of task to be returned.
     * @return Task at index i of TaskList ArrayList.
     */
    public Task getTaskByIndex(int i) {
        return this.taskList.get(i);
    }

    /**
     * Adds task to the task list.
     *
     * @param task task to be added.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        this.numOfTasks += 1;
        String res = "Added \'" + task + "\' task!\n";
        res += String.format("By the way, you have %d tasks now!", this.numOfTasks);
        return this.ui.response(res);
    }

    /**
     * Adds To Do task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     * @throws InvalidCommandFormatException if the command specifying the task is invalid due to
     * no task specified.
     */
    public String addToDo(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1) {
            throw new MissingTaskException("Failed. Specify a task for your todo!!!! D:");
        } else {
            assert tokens.size() > 1 : "there should be more than 1 token";
            String taskDescription = "";
            int len = tokens.size();
            for (int i = 1; i < len; i++) {
                taskDescription += tokens.get(i) + " ";
            }
            ToDo todo = new ToDo(taskDescription);
            return this.addTask(todo);
        }
    }

    /**
     * Adds TimeBox task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     * @return String response that task was added.
     * @throws BunbunException if the command specifying the task is invalid due to no task specified
     * or incorrect formatting.
     */
    public String addTimeBox(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1 || tokens.get(1).equals("/in")) {
            throw new MissingTaskException("Failed. Specify a task for your deadline task!!!! D:");
        } else if (!(tokens.contains("/in")) || tokens.indexOf("/in") != tokens.size() - 2) {
            throw new InvalidTaskFormatException(
                    "Failed. Add /in [TIME IN HOURS] to specify when to complete your task by!!! ;=;");
        } else {
            String taskDescription = "";
            int i = 1;
            while (!tokens.get(i).equals("/in")) {
                taskDescription += tokens.get(i) + " ";
                i += 1;
            }
            int duration = Integer.parseInt(tokens.get(i+1));
            TimeBox timeBoxTask = new TimeBox(taskDescription, duration);
            return this.addTask(timeBoxTask);
        }
    }

    /**
     * Adds Deadline task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     * @throws BunbunException if the command specifying the task is invalid.
     */
    public String addDeadline(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1 || tokens.get(1).equals("/by")) {
            throw new MissingTaskException("Failed. Specify a task for your deadline task!!!! D:");
        } else if (!(tokens.contains("/by")) || tokens.indexOf("/by") != tokens.size() - 2) {
            throw new InvalidTaskFormatException(
                    "Failed. Add /by [DATE] to specify when to complete your task by!!! ;=;");
        } else {
            assert (tokens.size() > 1 && !tokens.get(1).equals("/by")
                    && tokens.contains("/by") && !(tokens.indexOf("/by") == tokens.size() - 1))
                    : "Accepted wrong format for deadline task";
            String taskDescription = "";
            int i = 1;
            while (!tokens.get(i).equals("/by")) {
                taskDescription += tokens.get(i) + " ";
                i += 1;
            }
            LocalDate deadline = DateTimeHandler.isValidLocalDate(tokens.get(i+1));
            if (deadline == null) {
                throw new InvalidDateFormatException("Failed. Specify your date in yyyy-MM-dd format!! ;^;");
            }

            Deadline deadlineTask = new Deadline(taskDescription, deadline);
            return this.addTask(deadlineTask);
        }
    }

    /**
     * Adds Event task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     * @throws BunbunException if the command specifying the task is invalid.
     */
    public String addEvent(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1 || tokens.get(1).equals("/from") || tokens.get(1).equals("/to")) {
            throw new MissingTaskException("Failed. Specify a task for your event task!!!! D:");
        } else if (!(tokens.contains("/from")) || !(tokens.contains("/to"))
                || (tokens.indexOf("/from") != tokens.indexOf("/to") - 2)
                || tokens.indexOf("/to") != tokens.size() - 2) {
            throw new InvalidTaskFormatException(
                    "Failed. Add /from [DATE] /to [DATE] to specify the duration of your event!!! ;=;");
        } else {
            String taskDescription = "";
            int i = 1;
            while (!tokens.get(i).equals("/from")) {
                taskDescription += tokens.get(i) + " ";
                i += 1;
            }
            i += 1;
            LocalDate start = DateTimeHandler.isValidLocalDate(tokens.get(i));
            LocalDate end = DateTimeHandler.isValidLocalDate(tokens.get(i+2));
            if (start == null || end == null) {
                throw new InvalidDateFormatException("Failed. Specify your date in yyyy-MM-dd format!! ;^;");
            }
            Event event = new Event(taskDescription, start, end);
            return this.addTask(event);
        }
    }


    /**
     * Displays task list for user.
     */
    public String displayList() {
        String res = "These are your tasks!\n";
        for (int i = 0; i < this.numOfTasks; i++) {
            Task currTask = this.taskList.get(i);
            res += String.format("%d.%s\n", i + 1, currTask);
            this.ui.response(res);
        }
        res += "That's all your tasks for now :>>>";
        return this.ui.response(res);
    }

    /**
     * Signals that a task is complete and marks the description with an X.
     *
     * @param tokens ArrayList with Strings specifying the task to mark complete.
     */
    public String markDoneTask(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1) {
            throw new InvalidCommandFormatException("Specify 1! positive integer to mark task as complete D:");
        }

        int taskNum = Integer.parseInt(tokens.get(1));
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            throw new TaskNumOutOfBoundsException(
                    String.format("I can't mark task %d cause it doesn't exist!!! ;-;", taskNum));
        } else {
            assert (taskNum > 0 && taskNum <= this.numOfTasks) : "Task does not exist!";
            Task reqTask = this.taskList.get(taskNum - 1);
            reqTask.complete();
            assert (reqTask.isComplete()) : "Task should be marked as complete";
            String res = "Oki, I'll mark the task as done *w*! Good job finishing the task!!\n";
            res += String.format("%s", reqTask);
            return this.ui.response(res);
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param tokens ArrayList with Strings specifying the task to delete.
     */
    public String deleteTask(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1) {
            throw new InvalidCommandFormatException("Specify 1! positive integer to delete task!");
        }

        int taskNum = Integer.parseInt(tokens.get(1));
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            throw new TaskNumOutOfBoundsException(
                    String.format("I can't delete task %d cause it doesn't exist!!! ;-;", taskNum));
        }
        String res = String.format("Oki, I've deleted %s task!\n", this.taskList.get(taskNum - 1));
        this.taskList.remove(taskNum - 1);
        this.numOfTasks -= 1;
        res += String.format("You have %d tasks left!!", this.numOfTasks);
        return this.ui.response(res);
    }

    /**
     * Displays tasks in task list that contain word searched by user.
     *
     * @param tokens ArrayList with Strings specifying the word to search for
     */
    public String searchAndDisplay(ArrayList<String> tokens) throws BunbunException {
        if (tokens.size() == 1) {
            throw new InvalidCommandFormatException("Failed. Key in word to find!! :<");
        }

        String word = "";
        for (int i = 1; i < tokens.size(); i++) {
            word += tokens.get(i);
        }
        this.ui.response(String.format("These are the tasks containing %s!", word));
        ArrayList<Task> tasksContainingWord = new ArrayList<>();

        for (int i = 0; i < this.numOfTasks; i++) {
            Task currTask = this.taskList.get(i);
            String currTaskDescription = currTask.toString();
            if (currTaskDescription.contains(word)) {
                tasksContainingWord.add(currTask);
            }
        }

        String res = "";
        for (int i = 0; i < tasksContainingWord.size(); i++) {
            Task currTask = tasksContainingWord.get(i);
            res += String.format("%d.%s\n", i + 1, currTask);
        }
        return this.ui.response(res);
    }

}
