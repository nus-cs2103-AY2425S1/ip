package david.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    List<Task> tasks;

    public TaskList(List<Task> t) {
        this.tasks = t;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getSize() {
        return tasks.size();
    }

    public void deleteTask(int n) {
        tasks.remove(n);
    }

    public void markTaskAsDone(int n) {
        Task t = tasks.get(n);
        t.markAsDone();
    }

    public void markTaskAsUndone(int n) {
        Task t = tasks.get(n);
        t.markAsUnDone();
    }

    public Task getTask(int n) {
        return this.tasks.get(n);
    }

    /**
     * Finds all events realted to the string specified by the user
     * @param s String specified by the user
     * @return  String containing all events matching the string specified by the user
     */
    public String findEvent(String s) {
        String events = " ____________________________________________________________\n"
                + "Here are the matching tasks  in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String[] eventDetails = t.getTask().split(" ");
            List<String> eventArr = new ArrayList<>(Arrays.asList(eventDetails));
            if (eventArr.contains(s)) {
                events += t + "\n";
            }
        }

        events += " ____________________________________________________________\n";
        return events;
    }
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "All is good :) There is no tasks for the time being.";
        }
        String str = "";
        for (int i = 0; i<tasks.size(); i++) {
            str += i+1 + ": " + tasks.get(i) + "\n";
        }
        return str;
    }
}
