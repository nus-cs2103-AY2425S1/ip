import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();

    public void add(String str) {
        Task task;
        if (str.contains("todo")) {
            task = new ToDo(str);
        } else if (str.contains("deadline")) {
            task = new Deadline(str);
        } else if (str.contains("event")) {
            task = new Event(str);
        } else {
            task = new Task(str);
        }
        list.add(task);
        System.out.println("  " + task);
        System.out.println("Mel counts " + list.size()
                + " stuffs memorized XD");
    }

    public void mark(String str) {
        String[] temp = str.split(" ");
        String m = temp[0];
        int idx = Integer.parseInt(temp[1]) - 1;
        if (idx < 0 || idx >= list.size()) {
            System.out.println("Mel's memory explodes in anger?! " +
                    "Mel only recalled " + list.size() + " things");
            return;
        }
        if (Objects.equals(m, "mark")) {
            System.out.println("Mel sees you completed your task!");
            list.get(idx).mark();
        } else {
            System.out.println("Mel wonders how you undid your task...");
            list.get(idx).unmark();
        }
    }

    public void printAll() {
        int i = 0;
        for (Task t : list) {
            System.out.println(++i + "." + t);
        }
    }

}
