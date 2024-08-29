package evelyn.command;

import evelyn.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private String dataPathFile;
    private File file;
    public Storage(String dataPathFile) {
        this.dataPathFile = dataPathFile;
        this.file = new File(dataPathFile);
    }

    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(dataPathFile, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    public void saveData() throws IOException {
        this.file.delete();
        this.file.createNewFile();
    }

    public boolean fileExists() {
        return this.file.exists();
    }

    public void createNewDataFile() throws IOException{
        this.file.createNewFile();
    }

    public FileReader getFileReader() throws  IOException{
        return new FileReader(dataPathFile);
    }
}
