import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(int EXPECTED_SIZE) {
        this.tasks = new ArrayList<>(EXPECTED_SIZE);
    }

    public void add_list(Task task) {
        System.out.println("Ya la, adding this task to your list!");
        this.tasks.add(task);
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", this.tasks.size());
    }

    public void delete_list(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task task = this.tasks.remove(index);
        System.out.println("Deleting now hor!");
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", this.tasks.size());
    }

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

    public void mark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task curr = this.tasks.get(index);
        System.out.println("Solid lah, marked already");
        curr.mark();
        System.out.printf("\t%s\n", curr);
    }

    public void unmark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task curr = this.tasks.get(index);
        System.out.println("Walao, ok la I unmark already...");
        curr.unmark();
        System.out.printf("\t%s\n", curr);
    }

    public int getSize() {
        return this.tasks.size();
    }
}
