import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Handles operations relating to saving or loading tasks in the file.
 */


public class Storage {

    private String FILE_PATH;

    /**
     * Constructor for the Storage instance.
     * @param filePath File path to the location of the file where records are saved.
     */
    Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Updates Bob's records and prints out all existing records.
     *
     * @return
     */
    public ArrayList<Task> loadTaskList() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {

                boolean isFileCreated = file.createNewFile();

                if (!isFileCreated) {
                    System.out.println("Failed to create file when loading task.");
                }
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            ArrayList<Task> tempRecords = new ArrayList<>();

            System.out.println("\t====== Current Records =====\n");
            while (line != null && !line.equals("")) {
                Task task = this.loadTask(line);
                tempRecords.add(task);
                System.out.println("\t" + line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return tempRecords;
        } catch (IOException e) {
            super.toString();
        }
        return null;
    };

    /**
     * Parses the content from a line in the saved file to return the corresponding Task object.
     * @param lineInput A single line in the saved file.
     * @return Task based on the data specified in the lineInput.
     * @throws IOException
     */
    public Task loadTask(String lineInput) throws IOException {
        String[] parts = lineInput.split(" \\| ");
        boolean isDone = (parts[1].equals("1")) ? true : false;

        switch (parts[0]) {
        case "T":
//            System.out.println("parseDate description: " + parts[2]);
            return new Todo(parts[2], isDone);
        case "D":
            return new Deadline(parts[2], parts[3], isDone);
        case "E":
            return new Event(parts[2], parts[3], parts[4], parts[5], isDone);
        default:
            throw new IOException("unable to parse Data for loading.");
        }
    }

    /**
     * Saves tasks updates by user to storage
     */
    public void saveRecordsToStorage(ArrayList<Task> records) {

        File file = new File (FILE_PATH);
        try {
            File dataDir = file.getParentFile();
            if(dataDir != null && !dataDir.exists()) {
                boolean isDirCreated = dataDir.mkdir();
                if(!isDirCreated) {
                    System.out.println("Failed to create directory for saving records.");
                }
            }

            if(!file.exists()) {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    System.out.println("Failed to create new file when saving records.");
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task x: records) {
                String fileFormatLine = x.fileFormat();
                bufferedWriter.write(fileFormatLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
//            super.toString();
            System.err.println("Unable to save records to storage.");
        }
    }
}
