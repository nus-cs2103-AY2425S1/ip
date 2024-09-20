package misc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the data file whenever tasklist is updated
     * @param s The string to write to the data file
     */
    public void updateDataFile(String s) {
        try {
            File dataFile = new File(this.filePath);

            // check for file
            if (!dataFile.exists()) {
                if (dataFile.createNewFile()) {
                    System.out.println("File created: " + dataFile.getName());
                } else {
                    System.out.println("Failed to create the file.");
                }
            }
            
            // Always write to the file, whether it exists or is newly created
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(s);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
