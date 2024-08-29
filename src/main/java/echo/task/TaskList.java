package echo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * The TaskList class manages a list of tasks,
 * including adding, removing, marking, unmarking, and retrieving tasks.
 */
public class TaskList {
    private List<Task> tasks;
    /**
     * Constructs a TaskList with an empty List<Task>.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }
    /**
     * Adds a new task to tasks based on the provided description, TaskType, and additional information.
     *
     * @param description the description of the task
     * @param type the type of the task (TaskType.TODO, TaskType.EVENT)
     * @param info additional information related to the task (e.g., start and end dates for an event)
     */
    public void addTask(String description, TaskType type, String info) {
        switch (type) {
        case TODO:
            tasks.add(new Todo(description));
            break;
        case EVENT:
            String[] parts = info.split("->");
            tasks.add(new Event(description, parts[0], parts[1]));
            break;
        }
    }
    /**
     * Searches for tasks in the task list that contain the specified substring
     * and returns them as a formatted string for printing.
     *
     * @param stringToFind The substring to search for within the tasks.
     * @return A formatted string containing the list of tasks that contain
     *         the specified substring, each prefixed with its position in the list.
     *         If no tasks contain the substring, an empty string is returned.
     */
    public String getFoundTasks(String stringToFind) {
        String foundTasks = "";
        int count = 1;
        for (Task task: tasks) {
            String taskString = task.getTaskString();
            if (taskString.contains(stringToFind)) {
                foundTasks += count + ". " + taskString + "\n";
                count++;
            }
        }
        return foundTasks;
    }
    /**
     * Returns a formatted string representing the tasks in the list,
     * intended for saving to a file.
     *
     * @return a string representing the tasks in the list
     */
    public String getTasksToSave() {
        String s = "";
        for (Task t : tasks) {
            s += t.getData() + "\n";
        }
        return s;
    }
    /**
     * Adds a task object directly to the list.
     *
     * @param t the task to be added
     */
    private void addTask(Task t) {
        tasks.add(t);
    }
    /**
     * Adds a Deadline task to tasks with the given description and deadline date.
     *
     * @param description the description of the deadline task
     * @param deadline the deadline date of the task
     */
    public void addDeadline(String description, LocalDate deadline) {
        tasks.add(new Deadline(description, deadline));
    }
    /**
     * Returns a formatted string representing all the tasks in the list.
     * Intended for printing by the Ui class.
     *
     * @return a string representing all the tasks in the list
     */
    public String getTasksString() {
        String tasksString = "";
        int count = 1;
        for (Task task : tasks) {
            tasksString += count + ". " + task.getTaskString() + "\n";
            count++;
        }
        return tasksString;
    }
    /**
     * Marks the task at the specified index of tasks as complete.
     *
     * @param index the 1-based index of the task to be marked
     */
    public void markTask(int index) {
        tasks.get(index - 1).completeTask();
    }
    /**
     * Returns a formatted string representing the task at the specified index.
     *
     * @param index the 1-based index of the task
     * @return a string representing the task at the specified index of tasks
     */
    public String getTaskString(int index) {
        return tasks.get(index - 1).getTaskString() + "\n";
    }
    /**
     * Unmarks the task at the specified index of tasks, indicating it is not complete.
     *
     * @param index the 1-based index of the task to be unmarked
     */
    public void unmarkTask(int index) {
        tasks.get(index - 1).uncompleteTask();
    }
    /**
     * Returns the number of tasks currently in the list.
     *
     * @return the number of tasks in the list
     */
    public int getNumTasks() {
        return tasks.size();
    }
    /**
     * Deletes the task at the specified index from tasks.
     *
     * @param index the 1-based index of the task to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * The Task class represents a basic task with a description and completion status.
     */
    private class Task {
        private Boolean isComplete = false;
        private String description;
        private TaskType type;
        /**
         * Constructs a Task with the specified description and type.
         *
         * @param description the description of the task
         * @param type the type of the task (TODO, EVENT, DEADLINE)
         */
        private Task(String description, TaskType type) {
            this.description = description;
            this.type = type;
        }
        /**
         * Returns a formatted string representing the task,
         * including its type, completion status, and description.
         *
         * @return a string representing the task
         */
        public String getTaskString() {
            String msg = "[" + this.type.getTypeSymbol() + "] [";
            if (isComplete) {
                msg += "X] ";
            } else {
                msg += " ] ";
            }
            msg += this.description;
            return msg;
        }
        /**
         * Prints the task's string representation.
         */
        public void printTask() {
            System.out.print(this.getTaskString());
        }
        /**
         * Marks the task as complete.
         */
        private void completeTask() {
            this.isComplete = true;
        }
        /**
         * Unmarks the task, indicating it is not complete.
         */

        private void uncompleteTask() {
            this.isComplete = false;
        }
        /**
         * Returns a string representing the task's data,
         * suitable for saving to a file.
         *
         * @return a string representing the task's data
         */
        public String getData() {
            return type.getTypeSymbol() + " | " + (isComplete ? 1 : 0) + " | " + description;
        }
    }
    /**
     * The Deadline class represents a task with a deadline.
     */
    private class Deadline extends Task {
        private LocalDate deadline;
        /**
         * Constructs a Deadline task with the specified description and deadline date.
         *
         * @param description the description of the deadline task
         * @param deadline the deadline date of the task
         */
        public Deadline(String description, LocalDate deadline) {
            super(description, TaskType.DEADLINE);
            this.deadline = deadline;
        }
        /**
         * Returns a formatted string representing the deadline task,
         * including its description and deadline date.
         *
         * @return a string representing the deadline task
         */
        @Override
        public String getTaskString() {
            return super.getTaskString() +
                String.format(
                    " (by: %s)",
                    this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                );
        }
        /**
         * Prints the deadline task's string representation.
         */
        @Override
        public void printTask() {
            System.out.println(this.getTaskString());
        }
        /**
         * Returns a string representing the deadline task's save format,
         * intended for saving to a file.
         *
         * @return a string representing the deadline task's save format
         */
        @Override
        public String getData() {
             return super.getData() + " | " + this.deadline;
        }
    }
    /**
     * The Todo class represents a basic task without any additional information.
     */
    private class Todo extends Task {
        /**
         * Constructs a Todo task with the specified description.
         *
         * @param description the description of the todo task
         */
        public Todo(String description) {
            super(description, TaskType.TODO);
        }
        /**
         * Returns a string representing the todo task's save format,
         * intended for saving to a file.
         *
         * @return a string representing the todo task's data
         */
        @Override
        public String getData() {
            return super.getData();
        }
    }
    /**
     * The Event class represents a task with a start and end time.
     */
    private class Event extends Task {
        private String start;
        private String end;
        /**
         * Constructs an Event task with the specified description, start time, and end time.
         *
         * @param description the description of the event task
         * @param start the start date/time of the event
         * @param end the end date/time of the event
         */
        public Event(String description, String start, String end) {
            super(description, TaskType.EVENT);
            this.start = start;
            this.end = end;
        }
        /**
         * Returns a formatted string representing the event task,
         * including its description, start time, and end time.
         *
         * @return a string representing the event task
         */
        @Override
        public String getTaskString() {
            return super.getTaskString() +
                    String.format(
                            " (from: %s to: %s)",
                            this.start,
                            this.end
                    );
        }
        /**
         * Prints the event task's string representation.
         */
        @Override
        public void printTask() {
            System.out.println(this.getTaskString());
        }
        /**
         * Returns a string representing the event task's save format,
         * intended for saving to a file.
         *
         * @return a string representing the event task's save format
         */
        @Override
        public String getData() {
            return super.getData() + " | " + this.start + "->" + this.end;
        }
    }
}
