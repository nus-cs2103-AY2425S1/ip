package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class represents a taskList which handles the list of the tasks being added by the User
 * Handles operations of adding tasks to list based on input of Task type
 *
 * @author csk
 * @version 1
 */
public class TaskList {
    private static final String LINE = "    _____________________________________________";
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Marks task done or unmarks task as not done based on user input
     *
     * @param index
     * @param status
     * @return String (message to convey action taken by application)
     */
    public String markTask(int index, int status) {
        String result = "";
        if (status == 1) {
            taskList.get(index - 1).mark();
            result = LINE + "\n" + "    Nice! I've marked this task as done:"
                    + taskList.get(index - 1) + "\n" + LINE;
        } else if (status == 0) {
            taskList.get(index - 1).unmark();
            result = LINE + "    OK, I've marked this task as not done yet:"
                    + taskList.get(index - 1) + "\n" + LINE;
        }
        return result;
    }

    /**
     * Deletes task from master list of tasks based on index of task given
     *
     * @param index
     * @return
     */
    public String deleteTask(int index) {
        Task temp = taskList.get(index - 1);
        taskList.remove(temp);

        String result = LINE + " Noted. I've removed this task:" + temp + "\n" + " Now you have "
                + taskList.size() + " tasks in the list" + "\n" + LINE;

        return result;
    }

    /**
     * Adds todos task to list and prints out statement on confirmation of addition task
     *
     * @param task
     * @param entry
     */
    public String addToDos(String[] task, String entry) {
        String result = "";
        StringBuilder strBuild = new StringBuilder();

        for (int i = 1; i < task.length; i++) {
            strBuild.append(task[i]).append(" ");
        }

        assert !strBuild.isEmpty() : "Description for ToDos cannot be empty";

        ToDos todo = new ToDos(strBuild.toString().trim());
        taskList.add(todo);

        result = LINE + " Got it. I've added this task:" + todo + "\n" + " Now you have "
                            + taskList.size() + " tasks in the list" + LINE;

        return result;
    }

    /**
     * Adds deadline task to list and prints out statement on confirmation of addition
     * Handles format of deadline task input required, like specific format of deadline to be stated
     *
     * @param task
     * @param entry
     * @return string representation of task
     */
    public String addDeadline(String[] task, String entry) {
        StringBuilder strBuild = new StringBuilder();
        StringBuilder dateStr = new StringBuilder();
        String result = "";

        int next = 2; //index to check for "/by" keyword
        for (int i = 1; i < task.length; i++) {
            if (task[i].equals("/by")) {
                next = i;
            } else if (i < next) {
                strBuild.append(task[i]).append(" ");
                next++;
            } else {
                dateStr.append(task[i]).append(" ");
            }
        }

        Deadline deadlineTask = null;

        assert !strBuild.isEmpty() : "Description for Deadline cannot be empty";
        assert dateStr.toString().length() != 17 : "Deadline date format is invalid";

        if (dateStr.toString().length() == 17) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr.toString().trim(), format);
            deadlineTask = new Deadline(strBuild.toString().trim(), dateTime);

            taskList.add(deadlineTask);

            result = LINE + "    Got it. I've added this task:" + deadlineTask + "    Now you have "
                    + taskList.size() + " tasks in the list" + "\n" + LINE;

        } else {
            result = LINE + "\n" + "  Please state date and time of deadLINE"
                    + "\n" + "in YYYY-dd-MM HH:mm format" + "\n" + LINE;
        }

        return result;
    }

    /**
     * Adds event task to list and prints out statement on confirmation of addition
     * Handles format of event task input required, like specific format of from and to date/time to be stated
     *
     * @param task
     * @param entry
     */
    public String addEvent(String[] task, String entry) {
        StringBuilder strBuild = new StringBuilder();
        StringBuilder toStr = new StringBuilder();
        StringBuilder fromStr = new StringBuilder();
        String result = " ";

        int next = 2;
        int toNext = 3;
        for (int i = 1; i < task.length; i++) {
            if (task[i].equals("/from")) {
                next = i;
                toNext++;
            } else if (task[i].equals("/to")) {
                toNext = i;
            } else if (i < next) {
                strBuild.append(task[i]).append(" ");
                next++;
                toNext++;
            } else if (i < toNext) {
                fromStr.append(task[i]).append(" ");
                toNext++;
            } else {
                toStr.append(task[i]).append(" ");
            }
        }

        assert !strBuild.isEmpty() : "Description for Event cannot be empty";
        assert toStr.toString().length() != 17 : "Deadline date format is invalid";
        assert fromStr.toString().length() != 17 : "Deadline date format is invalid";

        if (fromStr.toString().length() == 17 && toStr.toString().length() == 17) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(fromStr.toString().trim(), format);
            LocalDateTime toTime = LocalDateTime.parse(toStr.toString().trim(), format);

            Event eventTask = new Event(strBuild.toString(), dateTime, toTime);
            taskList.add(eventTask);

            result = LINE + "    Got it. I've added this task:" + eventTask
                    + "    Now you have " + taskList.size() + " tasks in the list" + "\n" + LINE;
        } else {
            result = LINE + "\n" + "  Please state date and time of from and to of event"
                    + "in YYYY-dd-MM HH:mm format" + "\n" + LINE;
        }

        return result;
    }
}
