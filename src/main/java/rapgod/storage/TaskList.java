package rapgod.storage;

import rapgod.tasks.Task;
import rapgod.tasks.ToDo;
import rapgod.tasks.Deadline;
import rapgod.tasks.Event;

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
    public void showList() {
        System.out.println("-----------------------------------------------");
        System.out.println("Displaying ListBot:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, list.get(i));
        }

        System.out.println("-----------------------------------------------");
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
    public void filterAndShowList(String ... keywords) {
        System.out.println("-----------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < keywords.length; j++) {
                if (list.get(i).getDescription().contains(keywords[j])) {
                    System.out.printf("%d. %s\n", i + 1, list.get(i));
                    break;
                }
            }
        }
        System.out.println("-----------------------------------------------");
    }

    public void markTaskByIndex(int index) {
        list.get(index - 1).setIsDone(true);
        System.out.println("-----------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index - 1));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTaskByIndex(int index) {
        list.get(index - 1).setIsDone(false);
        System.out.println("-----------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index - 1));
        System.out.println("-----------------------------------------------");
    }

    /**
     * Deletes a task by its index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTaskByIndex(int index) {
        System.out.println("-----------------------------------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(index - 1));
        list.remove(index - 1);
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param deadlineDesc The description of the deadline task.
     * @param due The due date of the deadline task.
     */
    public void addDeadlineTask(String deadlineDesc, String due) {
        list.add(new Deadline(deadlineDesc, due));
        System.out.println("-----------------------------------------------");
        System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Adds an event task to the list.
     *
     * @param eventDesc The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public void addEventTask(String eventDesc, String from, String to){
        list.add(new Event(eventDesc, from, to));
        System.out.println("-----------------------------------------------");
        System.out.printf("Got it. I've added this event: \n%s\n", list.get(list.size() - 1));
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

    /**
     * Adds a todo task to the list.
     *
     * @param todoDesc The description of the todo task.
     */
    public void addToDoTask(String todoDesc) {
        list.add(new ToDo(todoDesc));
        System.out.println("-----------------------------------------------");
        System.out.printf("Got it. I've added this task: \n%s\n", list.get(list.size() - 1));
        System.out.printf("Now you have %d tasks in the list\n", list.size());
        System.out.println("-----------------------------------------------");
    }

}
