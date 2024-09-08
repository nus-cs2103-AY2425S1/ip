package hinlok.tasks;

import hinlok.exceptions.HinlokException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }


    public int getSize() {
        return taskList.size();
    }

    public Task getTaskByIndex(int idx) {
        return taskList.get(idx);
    }

    public String getNumberInList() {
        return "The task list has " + taskList.size() + " task currently";
    }

    /**
     * Adds a new Todo object into the task list.
     *
     * @param taskDetails
     * @throws HinlokException
     */
    public void addTodo(String taskDetails) throws HinlokException {
        if (taskDetails.trim().isEmpty()) {
            throw new HinlokException("You did not give me a todo task bro");
        }
        taskList.add(new Todo(taskDetails, false));
        System.out.println(getNumberInList());
    }

    /**
     * Adds a new deadline object into task list.
     * @param taskDetails
     * @throws HinlokException
     */
    public void addDeadline(String taskDetails) throws HinlokException {
        String regex = "^(.*?) /by (\\d{4})-(\\d{2})-(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDetails);
        if (!matcher.matches()) {
            throw new HinlokException("your Deadline format is wrong bro");
        } else {
            String[] deadlineDetails = taskDetails.split(" /by ", 2);
            String deadlineName = deadlineDetails[0];
            LocalDate date = LocalDate.parse(deadlineDetails[1]);
            taskList.add(new Deadline(deadlineName, date, false));
            System.out.println("Added a Deadline: " + deadlineName);
            System.out.println(getNumberInList());
        }

    }

    /**
     * Adds an Event object to task list.
     *
     * @param taskDetails
     * @throws HinlokException
     */
    public void addEvent(String taskDetails) throws HinlokException {
        String regex = "^(.*?)/from (.*?) /to (.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDetails);
        if (!matcher.matches()) {
            throw new HinlokException("your Event format is wrong bro");
        } else {
            String[] eventDetails = taskDetails.split(" /from | /to ", 3);
            String eventName = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            taskList.add(new Event(eventName, from, to, false));
            System.out.println("Added a Event: " + eventName);
            System.out.println(getNumberInList());
        }
    }


    public void markTask(int i) {
        Task markedTask = taskList.get(i);
        markedTask.markAsDone();
        System.out.println("Roger sir, I marked this todo as done:");
        System.out.println(markedTask);
    }

    public void unmarkTask(int i) {
        Task markedTask = taskList.get(i);
        markedTask.markAsUndone();
        System.out.println("Roger sir, I marked this task as undone:");
        System.out.println(markedTask);
    }

    public void deleteTask(int i) {
        String temp = taskList.get(i).toString();
        taskList.remove(i);
        System.out.println("Roger sir, I removed " + temp);
        System.out.println(getNumberInList());
    }

    public void findDeadlineByDate(String date) {
        System.out.println("These are the deadline due on that day:\n");
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                System.out.println(((Deadline) task).getBy());
                if (date.equals(((Deadline) task).getBy())) {
                    System.out.println(task);

                }
            }

        }

    }
}
