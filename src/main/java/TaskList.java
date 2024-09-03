import java.util.ArrayList;

/**
 * TaskList represents a list of tasks that is stored in the chat application.
 */
public class TaskList {
    ArrayList<Task> list;
    private final static String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";

    /**
     * Constructor for TaskList.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Prints the tasks in the task list.
     */
    public void printTask() {
        System.out.println("""
                Toothless:
                Here are the tasks on the quest board:

                |-------------Quest Board -----------------|
                """);

        if (list.isEmpty()) {
            System.out.println("There are no quests on the quest board!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i).toString());
            }
        }

        System.out.println("\n|------------------------------------------|\n\n" + DIVIDER);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) throws ToothlessExceptions {
        if(index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                    DIVIDER);
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsDone();
        System.out.println("Toothless:\nGood job! You had completed this quest!\n" +
                currentTask + "\n\n" + DIVIDER);
    }

    /**
     * Mark a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void markUndone(int index) throws ToothlessExceptions {
        if(index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                    DIVIDER);
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsUndone();
        System.out.println("Toothless:\nOops! Quest is back in play!\n" +
                currentTask + "\n\n" + DIVIDER);
    }

    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @throws ToothlessExceptions If the index is out of range.
     */
    public void deleteTask(int index) throws ToothlessExceptions {
        if(index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n" +
                    DIVIDER);
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        int newTaskSize = list.size() - 1;
        System.out.println("Toothless:\nThe quest\n\t\t" +
                currentTask + "\nis removed from the quest board!\n\n" +
                "Now there is " + newTaskSize + " quests in your quest board.\n\n" + DIVIDER);
        list.remove(fixedIndex);
    }

}
