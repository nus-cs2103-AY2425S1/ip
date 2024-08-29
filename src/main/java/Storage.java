import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    
    public ArrayList<String> load() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                lines.add(nextLine);
            }


        } catch (IOException e) {
        }
        return lines;
    }

}
