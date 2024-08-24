package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    private final static String FILE_PATH = "./data/savedData.txt";

//    public static void readSavedData() throws FileNotFoundException {
//        File f = new File(FILE_PATH); // create a File for the given file path
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//        while (s.hasNext()) {
//            System.out.println(s.nextLine());
//        }
//    }

    public static void saveData(String latestList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(latestList);
        System.out.println("Data synced");
        fw.close();
    }

}
