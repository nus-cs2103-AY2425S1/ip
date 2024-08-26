import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final File DIR_DATA = new File("data");
    private final File PATH_TASKS = new File(DIR_DATA, "tasks.txt");

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
        updateTasks();

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
            updateTasks();
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
            updateTasks();
            System.out.println("Mel counts " + tasks.size()
                    + " stuffs memorized XD");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + tasks.size() + " things");
        }
    }

    private void updateTasks() {
        if (!DIR_DATA.exists()) {
            DIR_DATA.mkdir();
        }
        try {
            PATH_TASKS.delete();
            PATH_TASKS.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TASKS));
            for (Task t : tasks) {
                writer.write(t.toString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Mel ran into an error"
                    + " creating save file :(");
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
