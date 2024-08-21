import java.util.ArrayList;

/**
 * This class implements a task list.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private int numOfTasks;

    /**
     * Constructor for a TaskList. Initializes number of tasks to 0.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numOfTasks = 0;
    }

    /**
     * Method to add task to the task list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.numOfTasks += 1;
        UI.response("Added \'" + task + "\' task!");
        String msg = String.format("By the way, you have %d tasks now!", this.numOfTasks);
        UI.response(msg);
    }

    /**
     * Method to add To Do task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     */
    public void addToDo(ArrayList<String> tokens) {
        if (tokens.size() == 1) {
            UI.response("Failed. Specify a task for your todo!!!! D:");
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
    public void addDeadline(ArrayList<String> tokens) {
        if (tokens.size() == 1 || tokens.get(1).equals("/by")) {

            UI.response("Failed. Specify a task for your deadline task!!! D:");
        } else if (!(tokens.contains("/by")) || tokens.indexOf("/by") == tokens.size() - 1) {
            UI.response("Failed. Add /by [DATE] to specify when to complete your task by!!! ;=;");
        } else {
            String taskDescription = "";
            String deadline = "";
            int len = tokens.size();
            for (int i = 1; i < len; i++) {
                if (tokens.get(i).equals("/by")) {
                    i += 1;
                    while (i < len) {
                        deadline += tokens.get(i) + " ";
                        i += 1;
                    }
                } else {
                    taskDescription += tokens.get(i) + " ";
                }
            }
            Deadline deadlineTask = new Deadline(taskDescription, deadline);
            this.addTask(deadlineTask);
        }
    }

    /**
     * Method to add Event task to the task list from an array list of tokens specifying the task.
     *
     * @param tokens ArrayList with Strings specifying the task.
     */
    public void addEvent(ArrayList<String> tokens) {
        if (tokens.size() == 1 || tokens.get(1).equals("/from") || tokens.get(1).equals("/to")) {
            UI.response("Failed. Specify a task for your event task!!! D:");
        } else if (!(tokens.contains("/from")) || !(tokens.contains("/to")) ||
                (tokens.indexOf("/from") > tokens.indexOf("/to")) ||
                (tokens.indexOf("/from") + 1 == tokens.indexOf("/to")) ||
                tokens.indexOf("/to") == tokens.size() - 1) {
            UI.response("Failed. Add /from [DATE] /to [DATE] to specify the duration of your event!!! ;=;");
        } else {
            String taskDescription = "";
            String start = "";
            String end = "";
            int len = tokens.size();
            int i = 1;
            while (i < len && !(tokens.get(i).equals("/from"))) {
                taskDescription += tokens.get(i) + " ";
                i += 1;
            }
            i += 1;
            while (i < len && !(tokens.get(i).equals("/to"))) {
                start += tokens.get(i) + " ";
                i += 1;
            }
            i += 1;
            while (i < len) {
                end += tokens.get(i) + " ";
                i += 1;
            }
            Event event = new Event(taskDescription, start, end);
            this.addTask(event);
        }
    }


    /**
     * Method to display task list for user.
     */
    public void displayList() {
        UI.response("These are your tasks!");
        for (int i = 0; i < this.numOfTasks; i++) {
            Task currTask = this.taskList.get(i);
            String res;
            if (currTask.isComplete()) {
                res = String.format("%d.%s", i + 1, currTask);
            } else {
                res = String.format("%d.%s", i + 1, currTask);
            }
            UI.response(res);
        }
        UI.response("That's all your tasks for now :>>>");
    }

    /**
     * Signal that a task is complete and mark the description with an X.
     *
     * @param taskNum int to indicate which task to mark as complete.
     */
    public void markDoneTask(int taskNum) {
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            UI.response(String.format("I can't mark task %d cause it doesn't exist!!! ;-;", taskNum));
        } else {
            Task reqTask = this.taskList.get(taskNum - 1);
            reqTask.complete();
            UI.response("Oki, I'll mark the task as done *w*! Good job finishing the task!!");
            String res = String.format("%s", reqTask);
            UI.response(res);
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param taskNum int to indicate which task to delete.
     */
    public void deleteTask(int taskNum) {
        if (taskNum <= 0 || taskNum > this.numOfTasks) {
            UI.response(String.format("I can't delete task %d cause it doesn't exist!!! ;-;", taskNum));
        } else {
            UI.response(String.format("Oki, I've deleted %s task!", this.taskList.get(taskNum - 1)));
            this.taskList.remove(taskNum - 1);
            this.numOfTasks -= 1;
            UI.response(String.format("You have %d tasks left!!", this.numOfTasks));
        }
    }

}
