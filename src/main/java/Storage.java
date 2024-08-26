import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class handles reading and writing data to a file,
 * as well as ensuring the necessary directory and file exist.
 */
public class Storage {
    private final String directoryPath;
    private final String filePath;

    /**
     * Constructs a Storage object with specified directory and file paths.
     *
     * @param directoryPath The relative path to the directory.
     * @param filePath The relative path to the file within the directory.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }


    /**
     * Sets up the storage by ensuring the directory and file exist.
     * Creates them if they do not exist.
     */
    public void setUp() {
        try {
            // create directory if it doesnt exist
            File folder = new File(this.directoryPath);
            if(!folder.exists()) {
                folder.mkdirs();
            }

            // create file if it doesnt exist
            File saveFile = new File(this.filePath);
            if(!saveFile.exists()){
                saveFile.createNewFile();
            }
        } catch (IOException e){
            System.out.println("Hmmm, an error occurred while setting up list storage.");
            e.printStackTrace();
        }
    }

    /**
     * Reads the file and processes each line of data.
     * If the file does not exist, prints a file-not-found message.
     *
     * @return data returns
     */
    public String readFile() {

        try {
            File file = new File(this.filePath);
            String data = "";
            if(file.exists()){
                Scanner scanner = new Scanner(file).useDelimiter("\\A");;
                while(scanner.hasNextLine()) {
                    data = scanner.next();
                    return data;
                }
                scanner.close();
            } else {
                System.out.println("File not found");
            }
        } catch (IOException e) {
            System.out.println("Urm, an error occurred while reading the file.");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Writes a line of content to the file, appending it to any existing data.
     * IOException If the file does not exist, prints a file-not-found message.
     *
     * @param data The content to write to the file.
     */
    public void writeFile(String data) {
        try {
            FileWriter writer = new FileWriter(filePath, false); // false for overwrite
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("urm, an error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
