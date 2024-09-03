package edith.task;

import edith.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This TaskList class manages a list of tasks.
 * It provides methods to add, delete, retrieve, and list tasks,
 * as well as to list tasks that are due or starting on a specific date.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param listOfTasks The list of tasks to initialize this TaskList with.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        listOfTasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task at the specified index.
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param ui The Ui object used to display the tasks.
     */
    public void listTasks(Ui ui) {
        if (listOfTasks.isEmpty()) {
            ui.showIndentedMessage("Great news, you have no outstanding tasks! Have a break!");
        } else {
            ui.showIndentedMessage("Here are the tasks in your list:");
            for (int i = 0; i < listOfTasks.size(); i++) {
                ui.showIndentedMessage((i + 1) + ") " + listOfTasks.get(i));
            }
            ui.showLineBreak();
        }
    }

    /**
     * Lists tasks that are due or starting on a specific date.
     *
     * @param date The date to filter tasks by.
     * @param ui   The Ui object used to display the tasks.
     */
    public void listTasksOnDate(String date, Ui ui) {
        int index = 1;
        boolean isDue = false;
        boolean isStartingOn = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        ui.showIndentedMessage("Here are your tasks due by " + date + ":");
        for (Task task : listOfTasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDueDate().isEqual(localDate)) {
                    ui.showIndentedMessage(index + ") " + deadline);
                    index++;
                    isDue = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getEndTime().isEqual(localDate)) {
                    ui.showIndentedMessage(index + ") " + event);
                    index++;
                    isDue = true;
                }
            }
        }

        if (!isDue) {
            ui.showIndentedMessage("NOTHING\n");
        } else {
            System.out.println("\n");
        }

        ui.showIndentedMessage("Here are your events starting on " + date + ":");
        for (Task task : listOfTasks) {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().isEqual(localDate)) {
                    ui.showIndentedMessage(index + ") " + event);
                    index++;
                    isStartingOn = true;
                }
            }
        }

        if (!isStartingOn) {
            ui.showIndentedMessage("NOTHING");
            ui.showLineBreak();
        } else {
            ui.showLineBreak();
        }
    }

    /**
     * Finds and lists tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @param ui The Ui object used to display the tasks.
     */
    public void findTasksByKeyword(String keyword, Ui ui) {
        int index = 1;
        boolean hasKeyword = false;

        ui.showIndentedMessage("Here are the matching tasks in your list:");
        for (Task task : listOfTasks) {
            if (task.containsKeyword(keyword)) {
                ui.showIndentedMessage(index + ") " + task);
                index++;
                hasKeyword = true;
            }
        }

        if (!hasKeyword) {
            ui.showIndentedMessage("NOTHING");
            ui.showLineBreak();
        } else {
            ui.showLineBreak();
        }
    }
}
