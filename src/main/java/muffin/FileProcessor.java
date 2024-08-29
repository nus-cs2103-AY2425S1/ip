import muffin.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public ArrayList<Task> readFromFile(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (file.exists()) {
                List<String> lines = Files.readAllLines(Paths.get(filePath));

                for (String line: lines) {
                    String[] parts = line.split("\\|");

                    switch (parts[0]) {
                    case "T":
                        Task t = new Todo(parts[2]);
                        markStatus(parts[1], t);
                        tasks.add(t);
                        break;

                    case "D":
                        Task d = new Deadline(parts[2], parts[3]);
                        markStatus(parts[1], d);
                        tasks.add(d);
                        break;

                    case "E":
                        Task e = new Event(parts[2], parts[3], parts[4]);
                        markStatus(parts[1], e);
                        tasks.add(e);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public void markStatus(String input, Task task) {
        if (input.equals("1")) {
            task.isDone = true;
        }
    }

    public void writeToFile(String filePath, ArrayList<Task> tasks) {
        try {
            ArrayList<String> content = new ArrayList<>();
            for (Task task: tasks) {
                String s = "";
                if (task instanceof Todo) {
                    s = String.format("T|%s|%s", checkStatus(task), task.description);
                } else if (task instanceof Deadline d) {
                    s = String.format("D|%s|%s|%s", checkStatus(d), d.description, d.by);
                } else if (task instanceof Event e) {
                    s = String.format("E|%s|%s|%s|%s", checkStatus(e), e.description, e.from, e.to);
                }
                content.add(s);
            }
            Files.write(Paths.get(filePath), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String checkStatus(Task task) {
        if (task.isDone) {
            return "1";
        } else {
            return "0";
        }
    }
}
