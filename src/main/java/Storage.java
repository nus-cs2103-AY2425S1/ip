import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {

    private static final String line =
            "____________________________________________________________";
    private final File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public String[] load() throws FileNotFoundException {
        System.out.println(line);
        System.out.println("Attempting to sync your data......");
        Scanner sc = new Scanner(file);
        String[] lines = new String[100];
        int i = 0;
        while (sc.hasNextLine()) {
            lines[i] = sc.nextLine();
            i++;
        }
        return lines;
    }
}
