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
     * Marks a task as done by its index.
     *
     * @param index The index of the task to be marked as done.
     */
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
