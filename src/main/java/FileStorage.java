import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileStorage {

    public String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }
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

    public void writeToTxt(String myTasks) { //Using this method
        try  {
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.write(myTasks);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
