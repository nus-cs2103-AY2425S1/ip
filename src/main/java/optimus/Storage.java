package optimus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    List<Task> loadFile() throws FileNotFoundException, OptimusException {
        File f = new File(filepath);
        List<Task> record = new ArrayList<>();

        if (!f.exists()) {
            System.out.println("No existing data file found in the given directory. A new record will be established.");
            return record;
        }

        Scanner s = new Scanner(f);
        processFileLines(s, record);
        s.close();

        return record;
    }

    private void processFileLines(Scanner s, List<Task> record) throws OptimusException {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task = createTask(taskType, description, parts);

            if (task != null) {
                if (isDone) {
                    task.setDone();
                }
                record.add(task);
            }
        }
    }

    private Task createTask(String taskType, String description, String[] parts) throws OptimusException {
        switch (taskType) {
            case "T":
                return new ToDos(description);
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                return new Deadlines(description, by);
            case "E":
                String from = parts.length > 3 ? parts[3] : "";
                String to = parts.length > 4 ? parts[4] : "";
                return new Events(description, from, to);
            default:
                return null;
        }
    }





    public void saveToFile(List<Task> record) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task task : record) {
            fw.write(task.toSaveString() + System.lineSeparator());
        }
        fw.close();
    }
}
