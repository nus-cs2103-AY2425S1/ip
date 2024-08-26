import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileReading {
    protected static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int i = 1;
        while (s.hasNext()) {
            System.out.println(i + ". " + s.nextLine());
            i++;
        }
    }

    protected static void addFileContentsToList(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            list.add(new Task(s.nextLine()));
        }
    }
}
