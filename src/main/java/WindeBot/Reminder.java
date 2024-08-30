package WindeBot;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Reminder {
    private static ArrayList<Task> schedule;
    private static Hashtable<LocalDate, ArrayList<Task>> calendar;

    Reminder() {
        this.schedule = new ArrayList<Task>();
        this.calendar = new Hashtable<LocalDate, ArrayList<Task>>();
    }

    Reminder(ArrayList<Task> schedule, Hashtable<LocalDate, ArrayList<Task>> calendar) {
        this.schedule = schedule;
        this.calendar = calendar;
    }

    Reminder(ArrayList<Task> schedule) {
        this.schedule = schedule;
        this.calendar = new Hashtable<LocalDate, ArrayList<Task>>();
        for (Task task : schedule) {
            ArrayList<Task> taskList;
            if (calendar.containsKey(task.getDate())) {
                taskList = calendar.get(task.getDate());
            } else {
                taskList = new ArrayList<Task>();
            }
            taskList.add(task);
            calendar.put(task.getDate(), taskList);
        }
    }

    public static void addEvent(Event task) {
        schedule.add(task);
        ArrayList<Task> taskList;
        if (calendar.containsKey(task.getDate())) {
            taskList = calendar.get(task.getDate());
        } else {
            taskList = new ArrayList<Task>();
        }
        taskList.add(task);
        calendar.put(task.getDate(), taskList);
        //return new Reminder(reminder, calendar);
    }

    public static void addDeadline(Deadline task) {
        schedule.add(task);
        ArrayList<Task> taskList;
        if (calendar.containsKey(task.getDate())) {
            taskList = calendar.get(task.getDate());
        } else {
            taskList = new ArrayList<Task>();
        }
        taskList.add(task);
        calendar.put(task.getDate(), taskList);
    }

    public static void addTodo(Todos task) {
        schedule.add(task);
    }

    public static Task remove(int i) {
        Task task = schedule.remove(i);
        if (task.getClass() != Todos.class) {
            LocalDate date = task.getDate();
            ArrayList<Task> taskList = calendar.get(date);
            taskList.remove(task);
            calendar.put(date, taskList);
        }
        return task;
    }

    public static void mark(int i) {
        Task task = schedule.remove(i);
        if (task.getClass() != Todos.class) {
            LocalDate date = task.getDate();
            ArrayList<Task> taskList = calendar.get(date);
            taskList.remove(task);
            task.mark();
            taskList.add(task);
            calendar.put(date, taskList);
        }
        task.mark();
        schedule.add(i, task);
    }

    public static void unmark(int i) {
        Task task = schedule.remove(i);
        if (task.getClass() != Todos.class) {
            LocalDate date = task.getDate();
            ArrayList<Task> taskList = calendar.get(date);
            taskList.remove(task);
            task.unmark();
            taskList.add(task);
            calendar.put(date, taskList);
        }
        task.unmark();
        schedule.add(i, task);
    }

    public static ArrayList<Task> getTasksOnDate(LocalDate date) {
        return calendar.get(date);
    }

    public static ArrayList<Task> getSchedule() {
        return schedule;
    }

    public static Task getTask(int i) {
        return schedule.get(i);
    }

    public static int size() {
        return schedule.size();
    }
}
