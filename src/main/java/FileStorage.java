import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileStorage {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); //creating file
        Scanner s = new Scanner(f); //create scanner for file
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void main(String[] args) {
        try {
            printFileContents("BuddyBot.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
