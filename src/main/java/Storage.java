import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();

    public void add(String str) {
        Task task = new Task(str);
        list.add(task);
    }

    public void mark(int idx) {
        if (idx < 0 || idx >= list.size()) {
            System.out.println("Mel's memory explodes in anger?! " +
                    "Mel only recalled " + list.size() + " things");
            return;
        }
        list.get(idx).mark();
    }

    public void unmark(int idx) {
        if (idx < 0 || idx >= list.size()) {
            System.out.println("Mel's memory explodes in anger?! " +
                    "Mel only recalled " + list.size() + " things");
            return;
        }
        list.get(idx).unmark();
    }

    public void printAll() {
        int i = 0;
        for (Task t : list) {
            System.out.println(++i + "." + t);
        }
    }

}
