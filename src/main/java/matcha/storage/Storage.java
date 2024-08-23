package matcha.storage;

import matcha.parser.Parser;

import matcha.task.Task;

import matcha.exception.MatchaException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;




public class Storage {
    private static String FILE_PATH;
    public Storage(String filePath) {
        Storage.FILE_PATH = filePath;
    }

    private static File initFile() throws IOException {
       File file = new File(FILE_PATH);

       if (!file.getParentFile().exists()) {
           //create directory if it does not exist
           file.getParentFile().mkdirs();
       }

       if (!file.exists()) {
           //create file if it does not exist
           file.createNewFile();
       }

       return file;
    }



    public ArrayList<Task> loadTasks() throws MatchaException {
        try {
          File file = Storage.initFile();
          ArrayList<Task> tasks = new ArrayList<>();
          Scanner scanner = new Scanner(file);

          //parse each line of file and add to tasks
          while (scanner.hasNext()) {
                Task task = Parser.parseFileData(scanner.nextLine());
                tasks.add(task);
          }

          //close scanner once done adding tasks
          scanner.close();
          return tasks;

        } catch (IOException e) {
            throw new MatchaException("Oh no! Error loading tasks from file.");
        }
    }

    public void saveTasks(ArrayList<Task> tasks) throws MatchaException {
        try {
            File file = Storage.initFile();
            FileWriter writer = new FileWriter(FILE_PATH);

            //write each task to file
            for (Task task : tasks) {
                writer.write(task.toSaveString() + "\n");
            }

            //close writer once done saving tasks
            writer.close();

        } catch (IOException e) {
            throw new MatchaException("Oh no! Error saving tasks to file.");
        }

    }
}
