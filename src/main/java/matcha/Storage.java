package matcha;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import matcha.task.Task;
import matcha.task.Todo;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.exception.MatchaException;


public class Storage {
    private static String FILE_PATH;

    public Storage(String filePath) {
        Storage.FILE_PATH = filePath;
    }

    private static File initFile() throws IOException {
       File file = new File(FILE_PATH);

       if(!file.getParentFile().exists()) {
           //create directory if it does not exist
           file.getParentFile().mkdirs();
       }

       if (!file.exists()) {
           //create file if it does not exist
           file.createNewFile();
       }

       return file;
    }

    public Task parseData(String data) throws MatchaException {
        String[] taskInfo = data.split(" \\| ");
        String taskType = taskInfo[0];
        boolean isTaskDone = taskInfo[1].equals("1");
        Task task;

        try {
            switch (taskType) {
            case "T":
                task = new Todo(taskInfo[2]);
                if(isTaskDone) {
                    task.markDone();
                }
                break;
            case "D":
                task = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3]));
                if(isTaskDone) {
                    task.markDone();
                }
                break;
            case "E":
                task = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3]),
                        LocalDateTime.parse(taskInfo[4]));
                if(isTaskDone) {
                    task.markDone();
                }
                break;
            default:
                throw new MatchaException("Oh no! Task data was saved in the wrong format.");
            }
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date format! Please format the Date as 'YYYY-MM-DD' and Time as 'HHMM'");
        }

        return task;
    }

    public ArrayList<Task> loadTasks() throws MatchaException {
        try {
          File file = Storage.initFile();
          ArrayList<Task> tasks = new ArrayList<>();
          Scanner scanner = new Scanner(file);

          //parse each line of file and add to tasks
          while (scanner.hasNext()) {
                Task task = parseData(scanner.nextLine());
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
