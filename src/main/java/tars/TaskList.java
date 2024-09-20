package tars;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a taskList which handles the list of the tasks being added by the User
 * Handles operations of adding tasks to list based on input of Task type
 *
 * @author SKarthikeyan28
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Constructs TaskList object while initialising task list and Ui objects
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Gets arraylist of tasks from taskList object
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Marks task done or un marks task as not done based on user input
     *
     * @param index
     * @param status
     */
    public String markTask(int index, int status) {
        String result = "";
        if (status == 1) {
            taskList.get(index - 1).mark();
            result = " Nice! I've marked this task as done:\n" + taskList.get(index - 1);
        } else if (status == 0) {
            taskList.get(index - 1).unmark();
            result = " OK, I've marked this task as not done yet:\n" + taskList.get(index - 1);
        }
        return result;
    }

    /**
     * Deletes task from master list of tasks based on index of task given
     *
     * @param index
     */
    public String deleteTask(int index) {
        if (index < 0 || index > taskList.size()) {
            return "Please state index of task within the list of Tasks";
        }
        Task temp = taskList.get(index - 1);
        taskList.remove(temp);

        String result = " Noted. I've removed this task:\n" + temp + "\n" + " Now you have "
                + taskList.size() + " tasks in the list\n";

        return result;
    }

    /**
     * Gets Description of task based on input given by user
     *
     * @param task
     * @param end
     */
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

    /**
     * Forms Date of task based on input given by user for event or deadline
     *
     * @param task
     * @param end
     */
    public String getDate(String[] task, int start, int end) {
        StringBuilder strBuild = new StringBuilder();
        for (int i = start; i < end; i++) {
            strBuild.append(task[i]).append(" ");
        }
        return strBuild.toString().trim();
    }

    /**
     * Adds todos task to list and prints out statement on confirmation of addition task
     *
     * @param task
     */
    public String addToDos(String[] task) {
        String result = "";
        String description = this.getDescription(task, null);

        assert !description.isEmpty() : "Description for ToDos cannot be empty";
        ToDos todo = new ToDos(description);
        taskList.add(todo);
        result = ui.printTask(todo, taskList.size());
        return result;
    }

    /**
     * Adds deadline task to list and prints out statement on confirmation of addition
     * Handles format of deadline task input required, like specific format of deadline to be stated
     *
     * @param task
     */
    public String addDeadline(String[] task) {
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
            //Checked with Chat-GPT on taking date as input and storing as LocalDateTime object
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
     */
    public String addEvent(String[] task) {
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
            //Checked with Chat-GPT on taking date as input and storing as LocalDateTime object
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fromTime = LocalDateTime.parse(fromDate.trim(), format);
            LocalDateTime toTime = LocalDateTime.parse(toDate.trim(), format);

            Event eventTask = new Event(description, fromTime, toTime);
            taskList.add(eventTask);
            result = ui.printTask(eventTask, taskList.size());
        } catch (DateTimeException e) {
            result = " Please state date and time of from and to of event" + " in YYYY-MM-dd HH:mm format\n";
        }
        return result;
    }
}
