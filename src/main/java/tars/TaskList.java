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
    private ArrayList<Task> taskList;
    static String LINE = "    _____________________________________________";

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    /**
     * Adds task to list based on input given or marks/unmarks task based on action required
     * Calls respective method to add Task based on type identified of task stated in input
     *
     * @param task
     * @param entry
     */
    public void addTask(String[] task, String entry) {
        if (task[0].equals("mark")) {
            Integer index = Integer.parseInt(task[task.length - 1]); //convert string format of number to Integer
            taskList.get(index - 1).mark(); //marking TASK as done

            System.out.println(LINE);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("        " + taskList.get(index - 1) + "\n" + LINE);
        } else if (task[0].equals("unmark")) {
            Integer index = Integer.parseInt(task[task.length - 1]); //convert str format of number to Integer
            taskList.get(index - 1).unmark(); //unmarking TASK as not done

            System.out.println(LINE);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("        " + taskList.get(index - 1) + "\n" + LINE);
        } else if (task[0].equals("todo")) {
            this.addToDos(task, entry);
        } else if (task[0].equals("deadLINE")) {
           this.addDeadline(task, entry);
        } else if (task[0].equals("event")) {
            this.addEvent(task, entry);
        } else if (task[0].equals("delete")) {
            Integer index = Integer.parseInt(task[task.length - 1]);
            Task temp = taskList.get(index - 1);
            taskList.remove(taskList.get(index - 1));

            System.out.println(LINE);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("        " + temp);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list" + "\n" + LINE);
        } else {
            Task t = new Task(entry);
            taskList.add(t);

            System.out.println(LINE + "\n"  + "    added: " + entry + "\n" + LINE);
        }
    }

    /**
     * Adds todos task to list and prints out statement on confirmation of addition task
     *
     * @param task
     * @param entry
     */
    public void addToDos(String[] task, String entry){
        StringBuilder strBuild = new StringBuilder();

        for (int i = 1; i < task.length; i++) {
            strBuild.append(task[i]).append(" ");
        }

        ToDos todo = new ToDos(strBuild.toString().trim());
        taskList.add(todo);

        System.out.println(LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + todo);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Adds deadline task to list and prints out statement on confirmation of addition
     * Handles format of deadline task input required, like specific format of deadline to be stated
     *
     * @param task
     * @param entry
     */
    public void addDeadline(String[] task, String entry){
        StringBuilder strBuild = new StringBuilder();
        StringBuilder dateStr = new StringBuilder();

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

        Deadline deadLINETask = null;

        if (dateStr.toString().length() == 17) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr.toString().trim(), format);
            deadLINETask = new Deadline(strBuild.toString().trim(), dateTime);

            taskList.add(deadLINETask);

            System.out.println(LINE);
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + deadLINETask);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list" + "\n" + LINE);
        } else {
            System.out.println(LINE + "\n" + "  Please state date and time of deadLINE" +
                    "\n" + "in YYYY-dd-MM HH:mm format" + "\n" + LINE);
        }
    }

    /**
     * Adds event task to list and prints out statement on confirmation of addition
     * Handles format of event task input required, like specific format of from and to date/time to be stated
     *
     * @param task
     * @param entry
     */
    public void addEvent(String[] task, String entry){
        StringBuilder strBuild = new StringBuilder();
        StringBuilder toStr = new StringBuilder();
        StringBuilder fromStr = new StringBuilder();

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

        if (fromStr.toString().length() == 17 && toStr.toString().length() == 17) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(fromStr.toString().trim(), format);
            LocalDateTime toTime = LocalDateTime.parse(toStr.toString().trim(), format);

            Event eventTask = new Event(strBuild.toString(), dateTime, toTime);
            taskList.add(eventTask);

            System.out.println(LINE);
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + eventTask);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list" + "\n" + LINE);
        } else {
            System.out.println(LINE + "\n" + "  Please state date and time of from and to of event"
                    + "in YYYY-dd-MM HH:mm format" + "\n" + LINE);
        }
    }
}
