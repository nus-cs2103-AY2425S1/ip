import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();

    public void add(String str) throws MelException {
        Task task;
        try {
            if (str.contains("todo")) {
                task = new ToDo(str);
            } else if (str.contains("deadline")) {
                task = new Deadline(str);
            } else if (str.contains("event")) {
                task = new Event(str);
            } else {
                throw new MelException("Mel is confused... " +
                        "Mel doesn't understand you :((");
            }
        } catch (TaskException e) {
            System.out.println(e);
            return;
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
        try {
            if (Objects.equals(m, "mark")) {
                System.out.println("Mel sees you completed your task!");
                list.get(idx).mark();
            } else {
                System.out.println("Mel wonders how you undid your task...");
                list.get(idx).unmark();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + list.size() + " things");
        }
    }

    public void delete(String str) {
        String[] temp = str.split(" ");
        int idx = Integer.parseInt(temp[1]) - 1;
        try {
            System.out.println("Mel helps you forget:\n"
                    + "  " + list.get(idx));
            list.remove(idx);
            System.out.println("Mel counts " + list.size()
                    + " stuffs memorized XD");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + list.size() + " things");
        }
    }

    public void printAll() {
        if (list.isEmpty()) {
            System.out.println("Mel remembers... nothing?!");
        } else {
            System.out.println("Mel remembers all your stuff~");
            int i = 0;
            for (Task t : list) {
                System.out.println(++i + "." + t);
            }
        }
    }

}
