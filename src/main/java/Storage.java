import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    private final ArrayList<Task> tasks = new ArrayList<>();

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
        tasks.add(task);
        System.out.println("  " + task);
        System.out.println("Mel counts " + tasks.size()
                + " stuffs memorized XD");
    }

    public void mark(String str) {
        String[] temp = str.split(" ");
        String m = temp[0];
        int idx = Integer.parseInt(temp[1]) - 1;
        try {
            if (Objects.equals(m, "mark")) {
                System.out.println("Mel sees you completed your task!");
                tasks.get(idx).mark();
            } else {
                System.out.println("Mel wonders how you undid your task...");
                tasks.get(idx).unmark();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + tasks.size() + " things");
        }
    }

    public void delete(String str) {
        String[] temp = str.split(" ");
        int idx = Integer.parseInt(temp[1]) - 1;
        try {
            System.out.println("Mel helps you forget:\n"
                    + "  " + tasks.get(idx));
            tasks.remove(idx);
            System.out.println("Mel counts " + tasks.size()
                    + " stuffs memorized XD");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + tasks.size() + " things");
        }
    }

    public void printAll() {
        if (tasks.isEmpty()) {
            System.out.println("Mel remembers... nothing?!");
        } else {
            System.out.println("Mel remembers all your stuff~");
            int i = 0;
            for (Task t : tasks) {
                System.out.println(++i + "." + t);
            }
        }
    }

}
