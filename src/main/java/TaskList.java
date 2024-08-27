import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList {
    private final Mel mel;
    private final Storage storage;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(Mel mel, Storage storage) {
        this.mel = mel;
        this.storage = storage;
    }

    public void taskAction(String input) throws MelException, TaskException {
        if (Objects.equals(input, "list")) {
            printAll();
        } else if (input.contains("mark")) {
            mark(input);
        } else if (input.contains("delete")) {
            delete(input);
        } else {
            mel.println("Mel remembers...");
            add(input);
        }
    }

    private void add(String str) throws MelException, TaskException {
        Task task;
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
        tasks.add(task);
        updateTasks();

        mel.println("  " + task);
        mel.println("Mel counts " + tasks.size()
                + " stuffs memorized XD");
    }

    private void delete(String str) {
        String[] temp = str.split(" ");
        int idx = Integer.parseInt(temp[1]) - 1;
        try {
            mel.println("Mel helps you forget:\n"
                    + "  " + tasks.get(idx));
            tasks.remove(idx);
            updateTasks();
            mel.println("Mel counts " + tasks.size()
                    + " stuffs memorized XD");
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + tasks.size() + " things");
        }
    }

    private void mark(String str) {
        String[] temp = str.split(" ");
        String m = temp[0];
        int idx = Integer.parseInt(temp[1]) - 1;
        try {
            if (Objects.equals(m, "mark")) {
                mel.println("Mel sees you completed your task!");
                tasks.get(idx).mark();
            } else {
                mel.println("Mel wonders how you undid your task...");
                tasks.get(idx).unmark();
            }
            updateTasks();
        } catch (IndexOutOfBoundsException e) {
            mel.println("Mel's brain explodes in anger?! " +
                    "Mel recalls only " + tasks.size() + " things");
        }
    }

    private void updateTasks() {
        int i = 0;
        String[] s = new String[tasks.size()];
        for (Task t : tasks) {
            s[i] = t.toString();
            i++;
        }
        try {
            storage.updateTasks(s);
        } catch (IOException e) {
            mel.println("Mel ran into an error"
                    + " creating save file :(");
        }
    }

    private void printAll() {
        if (tasks.isEmpty()) {
            mel.println("Mel remembers... nothing?!");
        } else {
            mel.println("Mel remembers all your stuff~");
            int i = 0;
            for (Task t : tasks) {
                mel.println(++i + "." + t);
            }
        }
    }
}
