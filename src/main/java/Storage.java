// This class deals with loading tasks from the file, and saving tasks to the file

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File file;

    public Storage(String filePath) {

        // filePath represents the path to the file where tasks are stored. 
        this.file = new File(filePath);
    }


    // This method loads tasks from the file
    public List<Task> load() throws ConverSageException{
        List<Task> taskList = new ArrayList<>();

        // if file doesn't exist, means no task, so just return empty tasklist
        if (!this.file.exists()) {
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    taskList.add(Task.fromFileFormat(line));
                } catch (ConverSageException e) {
                    System.out.println("ERR: Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            throw new ConverSageException("Failed to load tasks from file!");
        }



        return taskList;
    }

    // this method saves tasks to the file
    public void save(List<Task> taskList) throws ConverSageException {

        // Now, we need to try to write to the file, create a FileWriter object using myTasksFile
        try (FileWriter writer = new FileWriter(this.file)) {
            for (Task currTask : taskList) {
                // write the currTask into file!
                writer.write(currTask.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            throw new ConverSageException("Failed to save tasks to file!");
        }
    }

}