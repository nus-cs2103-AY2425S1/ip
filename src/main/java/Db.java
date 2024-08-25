import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Db {
    private File file;
    public Db(String filePath) {
        try {
            file = new File(filePath);
            if (!file.isFile()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEntry(String str) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(str);
            fw.write("\r\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Scanner getFileScanner() {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e){
            //should not happen
            return null;
        }
    }
}