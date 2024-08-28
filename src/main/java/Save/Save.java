package Save;
import java.io.*;

public class Save {
    private File saveFile;
    private final String savePath = "C:\\Users\\chian\\Desktop\\ip\\saves\\save.txt";

    public Save(){
        this.saveFile = new File("C:\\Users\\chian\\Desktop\\ip\\saves\\save.txt");
    }

    /**
     * Returns the path of the save file
     * @return     the path of the save file as a string
     */
    public String getPath() {
        return this.savePath;
    }

    /**
     * Writes the supplied string to the save file
     * This method is to be used when writing to the
     * save file, and throws an exception if
     * @param s the string to be written to the save file
     * @throws IOException if error occurs while writing to file
     */
    public void writeToSave(String s) {
        try {
            FileWriter writer = new FileWriter(this.savePath);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the save file");
        }
    }

    /**
     * Writes the supplied string to the save file
     * This method is to be used when writing to the
     * save file, and throws an exception
     * @throws IOException if error occurs while reading from file
     * @throws IllegalArgumentException if invalid file is supplied
     */
    public void loadTasksFromSave() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.saveFile))){
            String line;
            while ((line = reader.readLine()) != null) {
                ;
            }

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading from save file");
        }
    }
}