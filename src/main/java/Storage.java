import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    // variable to store the file path
    private final String filePath;

    // constructor for the Storage class
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        /**
         * Loads the data from the text file (hard disk) and return teh task list (if exist)
         */
        ArrayList<Task> tasks = new ArrayList<>();
        File taskFile = new File(filePath);

        // check if the file exists. If it does not, make the directory
        if (!taskFile.exists()) {
            if (taskFile.getParentFile().mkdir()) {
                taskFile.createNewFile();
                return tasks;
            } else {
                // throw an error if there is issue creating directory
                throw new IOException("Problem creating the directory: " + taskFile.getParent());
            }
        }

        // if the file exists, need to buffer the content
        FileReader fr = new FileReader(filePath);
        BufferedReader buffer = new BufferedReader(fr);

        String eachLine = buffer.readLine();
        while (eachLine != null) {
            // dissect eachLine based on the expected format
            // for example: D | 0 | return book | June 6th, then split it by "|"
            String[] components = eachLine.split("\\|");

            String category =  components[0].trim();
            String desc = components[2];
            // note that D contains a fourth component and E contains a fourth and fifth component
            if (Objects.equals(category, "T")) {

                Todo task = new Todo(desc);
                task.isDone = components[1].trim().equals("1");

                tasks.add(task);
                eachLine = buffer.readLine();
            } else if (Objects.equals(category, "D")) {
                LocalDateTime by = LocalDateTime.parse(components[3].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                Deadline deadline = new Deadline(desc, by);
                deadline.isDone = components[1].trim().equals("1");

                tasks.add(deadline);
                eachLine = buffer.readLine();
            } else if (Objects.equals(category, "E")) {
                LocalDateTime to = LocalDateTime.parse(components[3].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                LocalDateTime from = LocalDateTime.parse(components[4].trim(), DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
                Event event = new Event(desc, to, from);
                event.isDone = components[1].trim().equals("1");

                tasks.add(event);
                eachLine = buffer.readLine();
            }
        }
        // close the BufferedReader
        buffer.close();
        // return the task list
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        /**
         * Save will update the file with the new input
         */
        FileWriter fileWrite = new FileWriter(filePath);
        BufferedWriter buffer = new BufferedWriter(fileWrite);
        for (Task eachTask : tasks) {
            String output = eachTask.saveFormat();
            System.out.println(output);
            buffer.write(output);
            buffer.newLine();
        }
        // close the buffer
        buffer.close();
    }

}
