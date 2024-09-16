package utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
 * A representation of a task list for Chatterbox.
 */
public class StoredList {
    private static final String LINE_BREAK = "____________________________________________________________\n";
    private final ArrayList<Task> data;
    /**
     * Initialisation of a list for storing task in Chatterbox.
     */
    public StoredList() {
        this.data = new ArrayList<>();
    }

    /**
     * Adds a task in to the task list.
     * @param item A task to be added.
     * @return The message if successful in adding a Task.
     */
    public String addItem(Task item) {
        StringBuilder message = new StringBuilder();
        this.data.add(item);
        message.append(LINE_BREAK);
        message.append("Got it, I've added this task: \n");
        message.append(item).append("\n");
        message.append("Now you have ").append(this.getSize()).append(" task in the list.\n");
        message.append(LINE_BREAK);
        return message.toString();
    }

    public Task getItem(int index) {
        return this.data.get(index);
    }

    public int getSize() {
        return this.data.size();
    }

    /**
     * Removes a task from the task list.
     * @param index The index to the task,
     * @return The message if successful in removing a Task.
     */
    public String removeItem(int index) {
        assert index < this.getSize() : "Accessing item not in list";
        String message = LINE_BREAK
                + "Noted. I've removed this task:\n"
                + this.getItem(index) + "\n"
                + "Now you have " + this.getSize() + " task in the list.\n"
                + LINE_BREAK;
        this.data.remove(index);
        return message;
    }

    /**
     * Finds an item within the stored task that matches/ contains the given name.
     * @param name Name or part of the name to be found
     * @return A message containing all task found.
     */
    public String findItem(String name) {
        StringBuilder message = new StringBuilder();
        int count = 1;
        message.append(LINE_BREAK);
        for (Task item : data) {
            if (item.getName().contains(name)) {
                message.append(count).append(". ").append(item).append("\n");
            }
        }
        message.append(LINE_BREAK);
        return message.toString();
    }

    /**
     * Displays all task that is close to a specific date/ time.
     */
    public String reminder(LocalDateTime referenceTime) {
        assert referenceTime != null;
        StoredList upcomingTask = new StoredList();
        StoredList overdueTask = new StoredList();
        StringBuilder message = new StringBuilder();
        message.append(LINE_BREAK);
        for (Task item : data) {
            LocalDateTime endTime;
            if (item instanceof Event event) {
                endTime = event.getEndTime();
            } else if (item instanceof Deadline deadline) {
                endTime = deadline.getEndTime();
            } else {
                endTime = null;
            }

            if (endTime != null) {
                if (isOverdueTask(endTime, referenceTime)) {
                    if (!item.getCompleted()) {
                        overdueTask.addItem(item);
                    }
                } else if (isUpComingTask(endTime, referenceTime)) {
                    upcomingTask.addItem(item);
                }
            }
        }
        message.append("Reminders: \n");
        message.append(LINE_BREAK);
        message.append("Overdue Tasks: \n");
        message.append(overdueTask);
        message.append("Upcoming Tasks: \n");
        message.append(upcomingTask);
        return message.toString();
    }

    /**
     * Checks if the task is to be done within 1 week of the reference time.
     * @param taskTime The task end time.
     * @param referenceTime The reference time.
     * @return True if task is due within 1 week, false otherwise.
     */
    public boolean isUpComingTask(LocalDateTime taskTime, LocalDateTime referenceTime) {
        Duration duration = Duration.between(referenceTime, taskTime);
        return duration.toDays() >= 0 && duration.toDays() <= 7;
    }

    /**
     * Checks if the task is overdue.
     * @param taskTime The task end time.
     * @param referenceTime The reference time.
     * @return True if task is overdue, false otherwise.
     */
    public boolean isOverdueTask(LocalDateTime taskTime, LocalDateTime referenceTime) {
        Duration duration = Duration.between(referenceTime, taskTime);
        return duration.toDays() < 0;
    }

    /**
     * The string representation of a task list for Chatterbox.
     * @return A string representation of StoredList.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder(LINE_BREAK);
        for (int i = 0; i < this.getSize(); i++) {
            message.append(i + 1).append(". ").append(this.getItem(i).toString()).append("\n");
        }
        message.append(LINE_BREAK);
        return message.toString();
    }
}
