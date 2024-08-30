import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    protected String filePath;
    private BufferedReader readerFile;
    protected FileWriter writtenFile;

    public Storage(String filePath) throws FileNotFoundException, IOException {
        this.filePath = filePath;

        File taskFile = new File(String.valueOf(filePath));
        this.readerFile = new BufferedReader(new FileReader(taskFile));
        this.writtenFile = new FileWriter(taskFile, true);
    }
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            String line;

            while ((line = this.readerFile.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                boolean isDone = (arr[1].equals("1"));

                if (arr[0].equals("T")) {
                    listOfTasks.add(new ToDoTask(arr[2], isDone));

                } else if (arr[0].equals("D")) {
                    listOfTasks.add(new DeadlineTask(arr[2], isDone, LocalDate.parse(arr[3])));

                } else if (arr[0].equals("E")) {
                    String[] timings = arr[4].split("-");
                    String startTime = timings[0];
                    String endTime = timings[1];
                    listOfTasks.add(new EventTask(arr[2], isDone, LocalDate.parse(arr[3]), startTime, endTime));
                }
            }
        } finally {
            this.readerFile.close();
        }

        return listOfTasks;
    }

    public void writeFile(String text) {
        try {
            this.writtenFile.append(text);
            this.writtenFile.flush();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void overwriteFile(TaskList tasks) {
        try {
            File taskFile = new File(filePath);
            FileWriter overwrittenFile = new FileWriter(taskFile);

            for (int j = 0; j < tasks.getTotalNumber(); j++) {
                Task task = tasks.getTask(j);
                overwrittenFile.write(task.writeTask());
//                String description = task.description;
//                String status = (task.isDone ? "1" : "0");
//
//                if (task.date == null && task.startTime == null) {
//                    String text = "T | " + status + " | " + description + "\n";
//                    overwrittenFile.write(text);
//                } else if (task.date != null && task.startTime == null) {
//                    LocalDate date = task.date;
//                    String text = "D | " + status + " | " + description +
//                            " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n";
//                    overwrittenFile.write(text);
//                } else {
//                    LocalDate date = task.date;
//                    String startTime = task.startTime;
//                    String endTime = task.endTime;
//                    String text = "E | " + status + " | " + description +
//                            " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
//                            " | " + startTime + " | " + endTime + "\n";
//                    overwrittenFile.write(text);
//                }
            }
            overwrittenFile.flush();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
