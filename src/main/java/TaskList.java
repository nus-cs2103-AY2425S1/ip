import java.util.*;

/**
 * Represents a list of tasks. The TaskList class manages the creation,
 * manipulation, and display of tasks, including Todos, Deadlines, and Events.
 *
 * @author Jordan Chan
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Abstract class representing a generic task. Specific tasks like
     * Todo, Deadline, and Event inherit from this class.
     */
    private abstract class Task {
        protected String name;
        protected boolean isDone;

        /**
         * Constructs a Task with a given description.
         *
         * @param description The description of the task.
         * @throws EmptyInputException if the description is empty.
         */
        public Task(String description) throws EmptyInputException {
            if (description.length() > 0 && description.charAt(0) != '/') {
                this.name = description;
                this.isDone = false;
            } else {
                throw new EmptyInputException();
            }
        }

        /**
         * Returns the status icon of the task.
         *
         * @return "X" if the task is done, otherwise a space.
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        /**
         * Marks the task as completed.
         */
        public void complete() {
            this.isDone = true;
        }

        /**
         * Marks the task as not completed.
         */
        public void uncomplete() {
            this.isDone = false;
        }

        /**
         * Returns a string representation of the task, including its status icon and name.
         *
         * @return The string representation of the task.
         */
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.name;
        }
    }

    /**
     * Represents a Todo task.
     */
    private class Todo extends Task {
        /**
         * Constructs a Todo task with a given description.
         *
         * @param description The description of the Todo task.
         * @throws EmptyInputException if the description is empty.
         */
        public Todo(String description) throws EmptyInputException {
            super(description);
        }

        /**
         * Returns a string representation of the Todo task.
         *
         * @return The string representation of the Todo task.
         */
        @Override
        public String toString() {
            return "[T]" + "[" + this.getStatusIcon() + "] " + this.name;
        }
    }

    /**
     * Represents a Deadline task with a specific deadline.
     */
    private class Deadline extends Task {
        private String deadline;

        /**
         * Constructs a Deadline task with a given description.
         *
         * @param description The description of the Deadline task.
         * @throws EmptyInputException if the description is empty.
         */
        public Deadline(String description) throws EmptyInputException {
            super(description);
            int slashIndex = description.indexOf("/");

            this.name = description.substring(0, slashIndex).trim();

            // Extract the substring after the slash and trim it
            String temp = description.substring(slashIndex + 1).trim();

            // Reformat the deadline to the desired format
            deadline = "(by: " + temp.substring(3).trim() + ")";
        }

        /**
         * Returns a string representation of the Deadline task, including its deadline.
         *
         * @return The string representation of the Deadline task.
         */
        @Override
        public String toString() {
            return "[D]" + "[" + this.getStatusIcon() + "] " + this.name + " " + deadline;
        }
    }

    /**
     * Represents an Event task with a specific time window.
     */
    private class Event extends Task {
        private String window;

        /**
         * Constructs an Event task with a given description.
         *
         * @param description The description of the Event task.
         * @throws EmptyInputException if the description is empty.
         */
        public Event(String description) throws EmptyInputException {
            super(description);

            String[] parts = description.split("/");

            this.name = parts[0].trim();

            // Extract the first and second parts after the backslashes, if they exist
            String fromPart = parts.length > 1 ? parts[1].trim() : "";
            String toPart = parts.length > 2 ? parts[2].trim() : "";

            fromPart = fromPart.replaceFirst("^from\\s*", "").trim();
            toPart = toPart.replaceFirst("^to\\s*", "").trim();

            // Add "from:" and "to:" labels
            fromPart = "from: " + fromPart;
            toPart = "to: " + toPart;

            // Reformat the output
            window = "(" + fromPart + " " + toPart + ")";
        }

        /**
         * Returns a string representation of the Event task, including its time window.
         *
         * @return The string representation of the Event task.
         */
        @Override
        public String toString() {
            return "[E]" + "[" + this.getStatusIcon() + "] " + this.name + " " + window;
        }
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list based on the input string.
     *
     * @param task The task description.
     * @throws DelphiException If the task is invalid or an error occurs.
     */
    public void addTask(String task) throws DelphiException {
        if (!task.equals("list")) {
            if (Parser.checkStringPrefix(task, 4, "todo")) {
                Task tsk;
                if (task.length() > 4) {
                    tsk = new Todo(task.substring(5));
                } else {
                    tsk = new Todo("");
                }
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (Parser.checkStringPrefix(task, 8, "deadline")) {
                Task tsk;
                if (task.length() > 8) {
                    tsk = new Deadline(task.substring(9));
                } else {
                    tsk = new Todo("");
                }
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (Parser.checkStringPrefix(task, 5, "event")) {
                Task tsk;
                if (task.length() > 5) {
                    tsk = new Event(task.substring(6));
                } else {
                    tsk = new Event("");
                }
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new InvalidInputException();
            }
        } else {
            printTasks();
        }
    }

    /**
     * Removes a task from the list based on the index.
     *
     * @param i The index of the task to be removed.
     * @return The removed task, or null if the index is invalid.
     */
    public Task removeTask(int i) {
        if (i <= tasks.size()) {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            return t;
        } else {
            return null; //may want to add error handling for invalid indexes here
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on the index.
     *
     * @param i The index of the task to be marked as done.
     */
    public void markTaskAsDone(int i) {
        if (i <= tasks.size()) {
            tasks.get(i - 1).complete();
        }
        //may want to add error handling for invalid indexes here
    }

    /**
     * Marks a task as undone based on the index.
     *
     * @param i The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int i) {
        if (i <= tasks.size()) {
            tasks.get(i - 1).uncomplete();
        }
        //may want to add error handling for invalid indexes here
    }

    /**
     * Returns the string representation of a task based on the index.
     *
     * @param i The index of the task.
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public String getTask(int i) {
        if (i <= tasks.size()) {
            return tasks.get(i - 1).toString();
        } else {
            return "";
        }
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
}