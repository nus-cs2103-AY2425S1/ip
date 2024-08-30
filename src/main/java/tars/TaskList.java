package tars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    static String line = "    _____________________________________________";

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    public void addTask(String[] task, String entry) {
        if (task[0].equals("mark")) {
            Integer index = Integer.parseInt(task[task.length - 1]); //to convert string format of number to Integer
            taskList.get(index - 1).mark(); //marking TASK as done

            System.out.println(line);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("        " + taskList.get(index - 1) + "\n" + line);
        } else if (task[0].equals("unmark")) {
            Integer index = Integer.parseInt(task[task.length - 1]); //convert str format of number to Integer
            taskList.get(index - 1).unmark(); //unmarking TASK as not done

            System.out.println(line);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("        " + taskList.get(index - 1) + "\n" + line);
        } else if (task[0].equals("todo")) {
            StringBuilder strBuild = new StringBuilder();

            for (int i = 1; i < task.length; i++) {
                strBuild.append(task[i]).append(" ");
            }

            ToDos todo = new ToDos(strBuild.toString().trim());
            taskList.add(todo);

            System.out.println(line);
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + todo);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list");
            System.out.println(line);
        } else if (task[0].equals("deadline")) {
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

            Deadline deadlineTask = null;

            if (dateStr.toString().length() == 17) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(dateStr.toString().trim(), format);
                deadlineTask = new Deadline(strBuild.toString().trim(), dateTime);

                taskList.add(deadlineTask);

                System.out.println(line);
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + deadlineTask);
                System.out.println("    Now you have " + taskList.size() + " tasks in the list" + "\n" + line);
            } else {
                System.out.println(line + "\n" + "  Please state date and time of deadline" +
                                    "\n" + "in YYYY-dd-MM HH:mm format" + "\n" + line);
            }
        } else if (task[0].equals("event")) {
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

                System.out.println(line);
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + eventTask);
                System.out.println("    Now you have " + taskList.size() + " tasks in the list" + "\n" + line);
            } else {
                System.out.println(line + "\n" + "  Please state date and time of from and to of event"
                                    + "in YYYY-dd-MM HH:mm format" + "\n" + line);
            }
        } else if (task[0].equals("delete")) {
            Integer index = Integer.parseInt(task[task.length - 1]);
            Task temp = taskList.get(index - 1);
            taskList.remove(taskList.get(index - 1));

            System.out.println(line);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("        " + temp);
            System.out.println("    Now you have " + taskList.size() + " tasks in the list" + "\n" + line);
        } else {
            Task t = new Task(entry);
            taskList.add(t);

            System.out.println(line);
            System.out.println("    added: " + entry + "\n" + line);
        }
    }
}
