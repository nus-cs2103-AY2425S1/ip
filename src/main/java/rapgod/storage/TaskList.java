package rapgod.storage;

import rapgod.tasks.Deadline;
import rapgod.tasks.Event;
import rapgod.tasks.Task;
import rapgod.tasks.ToDo;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

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
     * Displays the list of tasks with their indices using streams.
     */
    public String taskString() {
        StringBuilder sb = new StringBuilder("Displaying ListBot:\n");

        String tasks = IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, list.get(i)))
                .collect(Collectors.joining());

        sb.append(tasks);
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
    /**
     * Filters and displays tasks from the list that contain any of the specified keywords using streams.
     *
     * @param keywords An array of keywords to search for in the task descriptions.
     *                 The method will display tasks that contain at least one of these keywords.
     *                 This parameter can be an empty array, in which case no tasks will be displayed.
     */
    public String filteredTask(String ... keywords) {
        StringBuilder sb = new StringBuilder("Alright, check it out—these tasks match what you're lookin' for:\n");

        String matchingTasks = IntStream.range(0, list.size())
                .filter(i -> {
                    Task task = list.get(i);
                    for (String keyword : keywords) {
                        if (task.getDescription().contains(keyword)) {
                            return true;
                        }
                    }
                    return false;
                })
                .mapToObj(i -> String.format("%d. %s\n", i + 1, list.get(i)))
                .collect(Collectors.joining());

        sb.append(matchingTasks);
        return sb.toString();
    }


    public String markTaskByIndex(int index) {
        list.get(index - 1).setIsDone(true);
        StringBuilder sb = new StringBuilder();
        sb.append("Yeah, I got it. This task’s marked as done—like a champ:\n");
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
        sb.append("Alright, I've flipped it back to not done yet. Still in the game.\n");
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
        sb.append("Done deal. This task's outta here:\n");
        sb.append(list.get(index - 1));
        list.remove(index - 1);
        sb.append(String.format("Now you’re rockin’ with %d tasks in the list. Keep it rollin’!\n", list.size()));
        return sb.toString();
    }

    /**
     * Snoozes a deadline task by its index.
     *
     * @param index The index of the task to be deleted.
     */
    public String snoozeDeadline(int index, String snoozeDueField) {
        StringBuilder sb = new StringBuilder();
        Task task = list.get(index - 1);
        try {
            Deadline deadline = (Deadline) task;
            deadline.setDue(snoozeDueField);
        } catch (ClassCastException exc) {
            return "The type of task at " + index + " is not [D]\n";
        }
        sb.append("Noted. I've rescheduled this task:\n");
        sb.append(list.get(index - 1));
        return sb.toString();
    }

    /**
     * Snoozes an event task by its index.
     *
     * @param index The index of the task to be deleted.
     */
    public String snoozeEvent(int index, String snoozeFromField, String snoozeToField) {
        StringBuilder sb = new StringBuilder();
        Task task = list.get(index - 1);
        try {
            Event event = (Event) task;
            event.setTo(snoozeToField);
            event.setFrom(snoozeFromField);
        } catch (ClassCastException exc) {
            return "The type of task at " + index + " is not [E]\n";
        }
        sb.append("Noted. I've rescheduled this task:\n");
        sb.append(list.get(index - 1));
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
        sb.append(String.format("Now you’re rockin’ with %d tasks in the list. Keep it rollin’!\n", list.size()));
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
        sb.append(String.format("Now you’re rockin’ with %d tasks in the list. Keep it rollin’!\n", list.size()));
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
        sb.append(String.format("Now you’re rockin’ with %d tasks in the list. Keep it rollin’!\n", list.size()));
        return sb.toString();
    }

}
