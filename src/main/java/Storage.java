import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class Storage {
    //deals with loading tasks from the file and saving tasks in the file
    private final String filePath;

    public Storage(String filePath) {
       this.filePath = filePath;

    }

    /**
     * Reads the file given by the filepath and parses each line into its corresponding task.
     * @return An ArrayList containing the list of processed tasks.
     * @throws IOException When file is not found or file cannot be read
     */
    public ArrayList<Task> loadData() throws JoeException {
        File file = new File(this.filePath);
        try {

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
        ArrayList<Task> tasks = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] lineArr = line.split("\\|");
            String taskType = lineArr[0];
            boolean isDone = Boolean.parseBoolean(lineArr[1]);
            String taskDesc = lineArr[2];
            switch (taskType) {
                case "T" -> {
                    tasks.add(new Todo(taskDesc, isDone));
                }
                case "D" -> {
                    LocalDate deadlineBy = LocalDate.parse(lineArr[3]);
                    tasks.add(new Deadline(taskDesc, deadlineBy).setIsDone(isDone));
                }
                case "E" -> {
                    LocalDate eventFrom = LocalDate.parse(lineArr[3]);
                    LocalDate eventTo = LocalDate.parse(lineArr[4]);
                    tasks.add(new Event(taskDesc, eventFrom, eventTo).setIsDone(isDone));
                }
            }
        }
        return tasks;
        } catch (IOException e) {
            throw new JoeException(e.getMessage());
        }
    }

    /**
     * Saves the given task list into hard disk.
     * @param taskList The task list to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void saveData(TaskList taskList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        ArrayList<Task> taskArr = taskList.toArrayList();
        for (Task task : taskArr) {
            writer.write(task.serialize());
            writer.newLine();
        }
        writer.close();
    }
}
