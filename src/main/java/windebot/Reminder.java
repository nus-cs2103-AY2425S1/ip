package windebot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todos;

/**
 * The Reminder class is responsible for managing the list of tasks (schedule) and their organization in a calendar.
 * Tasks are stored both in a list and a hash table for easy retrieval by date.
 */

public class Reminder {
    private static ArrayList<Task> schedule;
    private static Hashtable<LocalDate, ArrayList<Task>> calendar;

    /**
     * Constructs a Reminder object with an empty task list and calendar.
     */

    Reminder() {
        this.schedule = new ArrayList<Task>();
        this.calendar = new Hashtable<LocalDate, ArrayList<Task>>();
    }

    /**
     * Constructs a Reminder object with the specified task list and calendar.
     *
     * @param schedule The list of tasks.
     * @param calendar The hash table mapping dates to tasks.
     */

    Reminder(ArrayList<Task> schedule, Hashtable<LocalDate, ArrayList<Task>> calendar) {
        this.schedule = schedule;
        this.calendar = calendar;
    }

    /**
     * Constructs a Reminder object with the specified task list.
     * Initializes the calendar based on the tasks' dates.
     *
     * @param schedule The list of tasks.
     */

    Reminder(ArrayList<Task> schedule) {
        this.schedule = schedule;
        this.calendar = new Hashtable<LocalDate, ArrayList<Task>>();
        this.schedule.forEach(task -> {
            calendar.computeIfAbsent(task.getDate(), k -> new ArrayList<>()).add(task);
        });
        /*
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
         */
    }

    /**
     * Adds an Event task to the schedule and calendar.
     *
     * @param task The Event task to add.
     */

    public static void addEvent(Event task) {
        schedule.add(task);
        calendar.computeIfAbsent(task.getDate(), key -> new ArrayList<>()).add(task);
        /*
        ArrayList<Task> taskList;
        if (calendar.containsKey(task.getDate())) {
            taskList = calendar.get(task.getDate());
        } else {
            taskList = new ArrayList<Task>();
        }
        taskList.add(task);
        calendar.put(task.getDate(), taskList);

         */
    }

    /**
     * Adds a Deadline task to the schedule and calendar.
     *
     * @param task The Deadline task to add.
     */

    public static void addDeadline(Deadline task) {
        schedule.add(task);
        calendar.computeIfAbsent(task.getDate(), key -> new ArrayList<>()).add(task);
        /*
        ArrayList<Task> taskList;
        if (calendar.containsKey(task.getDate())) {
            taskList = calendar.get(task.getDate());
        } else {
            taskList = new ArrayList<Task>();
        }
        taskList.add(task);
        calendar.put(task.getDate(), taskList);
         */
    }

    /**
     * Adds a Todos task to the schedule.
     *
     * @param task The Todos task to add.
     */

    public static void addTodo(Todos task) {
        schedule.add(task);
    }

    /**
     * Removes a task from the schedule and calendar.
     *
     * @param i The index of the task to remove.
     * @return The removed task.
     */

    public static Task remove(int i) {
        Task task = schedule.remove(i);
        if (task.getClass() != Todos.class) {
            LocalDate date = task.getDate();
            calendar.computeIfPresent(date, (key, value) -> {
                value.remove(task);
                return value.isEmpty() ? null : value;
            });
            /*
            ArrayList<Task> taskList = calendar.get(date);
            taskList.remove(task);
            calendar.put(date, taskList);
             */
        }
        return task;
    }

    /**
     * Marks a task as completed and updates both the schedule and calendar.
     *
     * @param i The index of the task to mark as completed.
     */

    public static void mark(int i) {
        Task task = schedule.remove(i);
        if (task.getClass() != Todos.class) {
            LocalDate date = task.getDate();
            calendar.computeIfPresent(date, (key, value) -> {
                value.remove(task);
                task.mark();
                value.add(task);
                return value;
            });
            /*
            ArrayList<Task> taskList = calendar.get(date);
            taskList.remove(task);
            task.mark();
            taskList.add(task);
            calendar.put(date, taskList);
             */
        }
        task.mark();
        schedule.add(i, task);
    }

    /**
     * Marks a task as not completed and updates both the schedule and calendar.
     *
     * @param i The index of the task to mark as not completed.
     */

    public static void unmark(int i) {
        Task task = schedule.remove(i);
        if (task.getClass() != Todos.class) {
            LocalDate date = task.getDate();
            calendar.computeIfPresent(date, (k, v) -> {
                v.remove(task);
                task.unmark();
                v.add(task);
                return v;
            });
            /*
            ArrayList<Task> taskList = calendar.get(date);
            taskList.remove(task);
            task.unmark();
            taskList.add(task);
            calendar.put(date, taskList);
             */
        }
        task.unmark();
        schedule.add(i, task);
    }

    /**
     * Retrieves the list of tasks that occur on a specific date.
     *
     * @param date The date to retrieve tasks for.
     * @return The list of tasks on the specified date.
     */

    public static ArrayList<Task> getTasksOnDate(LocalDate date) {
        return calendar.getOrDefault(date, new ArrayList<>());
    }

    public static ArrayList<Task> getSchedule() {
        return schedule;
    }

    /**
     * Retrieves a task from the schedule by index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */

    public static Task getTask(int i) {
        return schedule.get(i);
    }

    public static int size() {
        return schedule.size();
    }
}
