import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    Storage(String filePath) {
        try {
            this.file = new File(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }

    ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            Task t = Parser.parseTask(s);
            tasks.add(t);
        }
        return tasks;
    }
}
