package tars;

import java.time.DateTimeException;
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
    private static final String LINE = " _____________________________________________";
    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
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
            result = LINE + "\n" + " Nice! I've marked this task as done:"
                    + taskList.get(index - 1) + "\n" + LINE;
        } else if (status == 0) {
            taskList.get(index - 1).unmark();
            result = LINE + " OK, I've marked this task as not done yet:"
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
        if (index < 0 || index > taskList.size()) {
            return "Please state index of task within the list of Tasks";
        }
        Task temp = taskList.get(index - 1);
        taskList.remove(temp);

        String result = LINE + "\n" + " Noted. I've removed this task:" + temp + "\n" + " Now you have "
                + taskList.size() + " tasks in the list\n" + LINE;

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
        String description = this.getDescription(task, null);

        assert !description.isEmpty() : "Description for ToDos cannot be empty";
        ToDos todo = new ToDos(description);
        taskList.add(todo);
        result = ui.printTask(todo, taskList.size());
        return result;
    }

    public String getDescription(String[] task, String end) {
        StringBuilder strBuild = new StringBuilder();
        for (int i = 1; i < task.length; i++) {
            if (!task[i].equals(end)) {
                strBuild.append(task[i]).append(" ");
            } else {
                break;
            }
        }
        return strBuild.toString().trim();
    }

    public String getDate(String[] task, int start, int end) {
        StringBuilder strBuild = new StringBuilder();
        for (int i = start; i < end; i++) {
            strBuild.append(task[i]).append(" ");
        }
        return strBuild.toString().trim();
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
        String result = "";
        int dateIndex = 0;
        for (int i = 1; i < task.length; i++) {
            if (task[i].equals("/by")) {
                dateIndex = i + 1;
            }
        }
        String description = getDescription(task, "/by");
        String date = getDate(task, dateIndex, task.length);
        Deadline deadlineTask = null;

        assert !description.isEmpty() : "Description for Deadline cannot be empty";
        assert date.length() != 17 : "Deadline date format is invalid";

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date.trim(), format);

            deadlineTask = new Deadline(description, dateTime);
            taskList.add(deadlineTask);
            result = ui.printTask(deadlineTask, taskList.size());

        } catch (DateTimeException e) {
            result = " Please state date and time of deadline\n" + "in YYYY-MM-dd HH:mm format";
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
        String result = " ";
        String description = getDescription(task, "/from");
        int fromIndex = 0;
        int toIndex = 0;

        for (int i = 1; i < task.length; i++) {
            if (task[i].equals("/from")) {
                fromIndex = i + 1;
            } else if (task[i].equals("/to")) {
                toIndex = i + 1;
            }
        }
        String fromDate = getDate(task, fromIndex, toIndex - 1);
        String toDate = getDate(task, toIndex, task.length);

        assert !description.isEmpty() : "Description for Event cannot be empty";
        assert fromDate.length() != 16 : "Event date format is invalid";
        assert toDate.length() != 16 : "Event date format is invalid";

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fromTime = LocalDateTime.parse(fromDate.trim(), format);
            LocalDateTime toTime = LocalDateTime.parse(toDate.trim(), format);

            Event eventTask = new Event(description, fromTime, toTime);
            taskList.add(eventTask);
            result = ui.printTask(eventTask, taskList.size());
        } catch (DateTimeException e) {
            result = LINE + "\n" + " Please state date and time of from and to of event"
                     + "in YYYY-MM-dd HH:mm format\n" + LINE;
        }
        return result;
    }
}
