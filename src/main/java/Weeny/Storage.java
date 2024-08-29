package weeny;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Storage {
    public void createFileIfNotExist(String dir, String file) {
        File dataDir = new File(dir);
        File taskFile = new File(dataDir, file);
        try {
            if (dataDir.mkdir()) {
                // Create directory successful
            }
            if (taskFile.createNewFile()) {
                // Create file successful
            }
        } catch (IOException e) {
            System.err.println("Error creating directory or file" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Task> loadTask(String fileName) {
        List<Task> taskList= new ArrayList<>();
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(
                    Paths.get(fileName),
                    StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            // do something
            e.printStackTrace();
        }
        Iterator<String> itr = lines.iterator();
        while (itr.hasNext()) {
            String[] processTask = itr.next().split(" \\| ");
            String description = processTask[2];
            Task currentTask = null;
            if (processTask[0].equals("T")) {
                currentTask = new Todo(description);
            } else if (processTask[0].equals("D")) {
                currentTask = new Deadlines(description, processTask[3]);
            } else if (processTask[0].equals("E")) {
                // Split string into startDate and endDate string
                int split = processTask[3].indexOf('-');
                if (split == -1) {
                    throw new IllegalArgumentException("Invalid event time format " + processTask[3]);
                }
                String startDatestring = processTask[3].substring(0, split).trim();
                String endDatestring = processTask[3].substring(split + 1).trim();
                currentTask = new Events(description, startDatestring, endDatestring);
            } else {
                // should not reach here
            }
            if (Integer.parseInt(processTask[1]) == 1) {
                currentTask.setMark();
            }
            taskList.add(currentTask);
        }
        return taskList;
    }

    public void saveTask(String path, List<Task> tasks) {
        Iterator<Task> taskIterator = tasks.iterator();
        try {
            FileWriter fileWriter = new FileWriter(path);
            while (taskIterator.hasNext()) {
                fileWriter.write(taskIterator.next().toOutput() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in writing");
            e.printStackTrace();
        }
    }


}
