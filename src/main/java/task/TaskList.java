package task;

import java.time.LocalDateTime;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(taskList);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void removeTask(int i) {
        this.tasks.remove(i);
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public void setTaskAsCompleted(int i) {
        this.tasks.get(i).markAsCompleted();
    }

    public void setTaskAsNotCompleted(int i) {
        this.tasks.get(i).markAsNotCompleted();
    }

     /**
     * Creates and returns an enumeration of the tasks added and stored by the user.
     * 
     * @return an enumeration of the tasks in the list.
     */
    public String taskListToString() {
        int numTasks = this.tasks.size();
        if (numTasks == 0) {
            return "No tasks";
        }

        try {
            String[] tempTasks = new String[numTasks];

            for (int i = 1; i <= numTasks; i++) {
                tempTasks[i - 1] = "(" + i + ") " + this.tasks.get(i - 1).toString();
            }

            return String.join("\n", tempTasks);
        } catch (NullPointerException e) {
            return "Oops! It seems like there's an invalid task in your list!\nI can't display the list yet.";
        }
    }

    /**
     * Creates and returns the list of tasks in a format
     * that is used to store in the text file.
     * 
     * @return the list of tasks in a writeable format.
     */
    public String taskListToFile() {
        return this.tasks.stream().map(
                    t -> t.toEasyString()
                ).reduce("----TASKS----", (x, y) -> x + "\n" + y);
    }

    /**
     * Searches for the tasks that have a particular word/phrase in them.
     * 
     * @param query the phrase in question.
     * @return the tasks that contain the phrase.
     */
    public TaskList search(String query) {
        return new TaskList(
            new ArrayList<>(this.tasks.stream().filter(t -> t.contains(query)).toList())
        );
    }

    /**
     * Updates the given task details (description and/or dates).
     * Date updates are only applicable to deadline or event tasks.
     * 
     * @param taskIndex the task to update
     * @param newDesc a new description (null if no change needed)
     * @param fromDate a new fromDate (null if no change needed)
     * @param toDate a new toDate (null if no change needed) 
     * @throws NullPointerException if the pointer is out of range
     */
    public void update(int taskIndex, String newDesc, LocalDateTime fromDate, LocalDateTime toDate)
            throws NullPointerException {
        this.getTask(taskIndex).update(newDesc, fromDate, toDate);
    }
}
