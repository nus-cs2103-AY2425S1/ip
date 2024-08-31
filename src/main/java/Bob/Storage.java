package bob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import bob.task.Task;

class Storage {

    /**
     * Saves the tasks to a file.
     *
     * @param tasks
     * @throws IOException
     */
    public static void saveTasks(List<Task> tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "bob.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
        }
    }

    public static void findTasks(List<Task> tasks, String keyword) {
        String out = "Here are the matching tasks in your list: \n";
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                out += (i + 1) + ". " + task + "\n";
                count++;
            }
        }
        if (count == 0) {
            out = "There are no matching tasks in your list.";
        }
        Ui.dialogue(out);
    }
}
