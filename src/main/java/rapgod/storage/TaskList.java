package rapgod.storage;

import rapgod.tasks.Deadline;
import rapgod.tasks.Event;
import rapgod.tasks.Task;
import rapgod.tasks.ToDo;

import java.util.List;

/**
 * Represents a list of tasks.
 * Provides methods to manage tasks including adding, marking, unmarking, deleting, and displaying tasks.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list The list of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Displays the list of tasks with their indices.
     */
    public String taskString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Displaying ListBot:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return sb.toString();
    }

    /**
     * Filters and displays tasks from the list that contain any of the specified keywords.
     * <p>
     * This method iterates through the list of tasks and checks if the description of each
     * task contains any of the provided keywords. If a match is found, the task is displayed
     * with its index.
     * </p>
     *
     * @param keywords An array of keywords to search for in the task descriptions.
     *                 The method will display tasks that contain at least one of these keywords.
     *                 This parameter can be an empty array, in which case no tasks will be displayed.
     */
    public String filteredTask(String ... keywords) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < keywords.length; j++) {
                if (list.get(i).getDescription().contains(keywords[j])) {
                    sb.append(String.format("%d. %s\n", i + 1, list.get(i)));
                    break;
                }
            }
        }
        return sb.toString();
    }

    public String markTaskByIndex(int index) {
        list.get(index - 1).setIsDone(true);
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(list.get(index - 1));
        return sb.toString();
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public String unmarkTaskByIndex(int index) {
        list.get(index - 1).setIsDone(false);
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(list.get(index - 1));
        return sb.toString();
    }

    /**
     * Deletes a task by its index.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTaskByIndex(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(list.get(index - 1));
        list.remove(index - 1);
        sb.append(String.format("Now you have %d tasks in the list\n", list.size()));
        return sb.toString();
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param deadlineDesc The description of the deadline task.
     * @param due The due date of the deadline task.
     */
    public String addDeadlineTask(String deadlineDesc, String due) {
        list.add(new Deadline(deadlineDesc, due));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Got it. I've added this task: \n%s\n", list.get(list.size() - 1)));
        sb.append(String.format("Now you have %d tasks in the list\n", list.size()));
        return sb.toString();
    }

    /**
     * Adds an event task to the list.
     *
     * @param eventDesc The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public String addEventTask(String eventDesc, String from, String to){
        list.add(new Event(eventDesc, from, to));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Got it. I've added this event: \n%s\n", list.get(list.size() - 1)));
        sb.append(String.format("Now you have %d tasks in the list\n", list.size()));
        return sb.toString();
    }

    /**
     * Adds a todo task to the list.
     *
     * @param todoDesc The description of the todo task.
     */
    public String addToDoTask(String todoDesc) {
        list.add(new ToDo(todoDesc));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Got it. I've added this task: \n%s\n", list.get(list.size() - 1)));
        sb.append(String.format("Now you have %d tasks in the list\n", list.size()));
        return sb.toString();
    }

}
