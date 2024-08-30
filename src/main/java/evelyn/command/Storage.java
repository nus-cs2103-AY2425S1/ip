package evelyn.command;

import evelyn.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Houses all logic for storing and retrieving data from Evelyn.
 */
public class Storage {
    private String dataPathFile;
    private File file;

    /**
     * Constructor for a Storage object.
     * @param dataPathFile The file path of the data to be stored
     */
    public Storage(String dataPathFile) {
        this.dataPathFile = dataPathFile;
        this.file = new File(dataPathFile);
    }

    /**
     * Writes a task into the .txt save file
     * @param task Task to be written.
     * @throws IOException Exception thrown should there be difficulty carrying out this task.
     */
    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(dataPathFile, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Refreshes the file to be ready to save new data.
     * @throws IOException
     */
    public void saveData() throws IOException {
        this.file.delete();
        this.file.createNewFile();
    }

    /**
     * Checks if file exists
     * @return True if file exists and false otherwise.
     */
    public boolean fileExists() {
        return this.file.exists();
    }

    /**
     * If file does not exist, this function creates a new file.
     * @throws IOException Exception thrown if there is a difficulty creating file.
     */
    public void createNewDataFile() throws IOException{
        this.file.createNewFile();
    }






    /**
     * Initialises a FileReader object to read the contents of the file.
     * @return Returns FileReader object of the file.
     * @throws IOException Throws exception if there is difficulty reading file.
     */
    public FileReader getFileReader() throws IOException{
        return new FileReader(dataPathFile);
    }
}
