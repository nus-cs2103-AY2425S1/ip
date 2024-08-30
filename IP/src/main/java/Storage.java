import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final String STORAGEFILEPATH = "Meerkat.txt";
    private Parser parser ;

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void readFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String thisTask = sc.nextLine();
            // reads the save file, loads taskList in parser with data
            parser.parseSaveFile(thisTask);
        }
    }
}
