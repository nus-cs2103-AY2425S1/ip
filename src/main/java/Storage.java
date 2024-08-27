import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final String saveDirectory;
    private final String saveFileName;

    public Storage(String pathString){
        int lastSlash = pathString.lastIndexOf("/");
        String dirString = pathString.substring(0,lastSlash);
        String fileString = pathString.substring(lastSlash);
        this.saveDirectory = dirString;
        this.saveFileName = fileString;
    }

    private String getFullPath(){
        return this.saveDirectory+this.saveFileName;
    }

    public List<String> load() throws FilePermissionsException{
        File directory = new File(this.saveDirectory);
        File saveFile = new File(this.getFullPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException  e) {
                throw new FilePermissionsException("No permission to write to directory");
            }
        }
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(this.getFullPath()));
            return fileContent;
        } catch (IOException e) {
            throw new FilePermissionsException("No permission to read save file");
        }
    }

    public void save(List<String> taskList){
        try (FileWriter fileWriter = new FileWriter(getFullPath())) {
            for (String i:taskList){
                fileWriter.write(i+"\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. Please restart the application");
        }
    }
}
