package luffy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




/**
 * Represents a file storing system that
 * writes and loads from existing file to restore data
 */
public class Storage {

    private static final String DEST_FILE = "./LuffyData/TaskData";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this.filePath = DEST_FILE;
    }
    /**
     * This method reads a task list and saves the content of the
     * task list into a text file
     *
     * @param taskList the task list to be saved
     * @throws IOException if there is issues with the input to file
     */
    public void saveToFile(TaskList taskList) throws IOException {

        File saveFile = new File(this.filePath);
        FileWriter fileStream = new FileWriter(saveFile);
        BufferedWriter fileInfo = new BufferedWriter(fileStream);

        for (Task task : taskList.getTasks()) {
            fileInfo.write(String.format("%s%n", task.dataToSave()));
        }

        fileInfo.flush();
        fileInfo.close();
    }

    /**
     * Returns a task list that contains all previous tasks that user
     * has saved into the text file
     *
     * @return the task list with the data from the existing save file
     * @throws FileNotFoundException if file does not exist in the directory
     */
    public TaskList loadFromFile() throws FileNotFoundException {

        TaskList loadedTasks = new TaskList();
        BufferedReader fileReader = new BufferedReader(new FileReader(this.filePath));

        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {

                String[] taskInfo = currentLine.split("\\|");
                String taskToDo = taskInfo[2].trim();
                boolean isDone = Integer.parseInt(taskInfo[1].trim()) == 1;

                switch (taskInfo[0].trim()) {
                case "TO-DO":
                    loadedTasks.addTask(new Todo(taskToDo, isDone));
                    break;
                case "DEADLINE":
                    String deadline = taskInfo[3].trim().substring(4);
                    loadedTasks.addTask(new Deadline(taskToDo, deadline, isDone));
                    break;
                case "EVENT":
                    String start = taskInfo[3].trim().substring(7);
                    String end = taskInfo[4].trim().substring(5);
                    loadedTasks.addTask(new Event(taskToDo, start, end, isDone));
                    break;
                default:
                    break;
                }
                currentLine = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File has not content");
        }
        return loadedTasks;
    }
}
