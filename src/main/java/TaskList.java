import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public void addToList(String task) {
        printLine();
        System.out.println("    added: " + task);
        list.add(new Task(task));
        printLine();
        System.out.println();
    }

    private void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

     public void printList() {
        printLine();
        System.out.println("    So here's the tasks in your list, you should proooobably do them");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        printLine();
    }

    public void markTaskAsDone(int index) {
        Task task = list.get(index - 1);

        printLine();
        task.markAsDone();
        System.out.println("    Yes boss, marked the task as done.");
        System.out.println("     " + task.toString());
        printLine();
    }

    public void unmarkTaskAsUndone(int index) {
        Task task = list.get(index - 1);

        printLine();
        task.unmarkAsUndone();
        System.out.println("    Interesting choice but I've marked the task as not done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
