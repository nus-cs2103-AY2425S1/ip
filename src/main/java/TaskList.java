import java.util.ArrayList;

/**
 * Class to store tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for task list.
     * @param EXPECTED_SIZE expected number of tasks to store
     */
    public TaskList(int EXPECTED_SIZE) {
        this.tasks = new ArrayList<>(EXPECTED_SIZE);
    }

    /**
     * Adds task to the list and prints list adding message.
     * @param task Task object to be added
     */
    public void add_list(Task task) {
        System.out.println("Ya la, adding this task to your list!");
        this.tasks.add(task);
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", this.tasks.size());
    }

    /**
     * Deletes Task from the list at specified index.
     * @param index Integer index to delete at
     * @throws OutOfListException Thrown if invalid index is given, contains current task size
     */
    public void delete_list(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task task = this.tasks.remove(index);
        System.out.println("Deleting now hor!");
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", this.tasks.size());
    }

    /**
     * Prints the list and shows task index (starting from 1), task type (T, D or E) and whether it is marked ([X]) or
     * unmarked ([ ]).
     */
    public void show_list() {
        if (this.tasks.isEmpty()) {
            System.out.println("Nothing in list lah!");
        } else {
            System.out.println("Here's your list lor!");
            Task curr;
            for (int i = 0; i < tasks.size(); i++) {
                curr = tasks.get(i);
                System.out.printf("%d. %s\n", i + 1, curr);
            }
        }
    }

    /**
     * Marks task as completed at specified index
     * @param index Integer index to mark task at
     * @throws OutOfListException Thrown if invalid index is given, contains current task size
     */
    public void mark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task curr = this.tasks.get(index);
        System.out.println("Solid lah, marked already");
        curr.mark();
        System.out.printf("\t%s\n", curr);
    }

    /**
     * Unmrks task as completed at specified index
     * @param index Integer index to unmark task at
     * @throws OutOfListException Thrown if invalid index is given, contains current task size
     */
    public void unmark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task curr = this.tasks.get(index);
        System.out.println("Walao, ok la I unmark already...");
        curr.unmark();
        System.out.printf("\t%s\n", curr);
    }
}
