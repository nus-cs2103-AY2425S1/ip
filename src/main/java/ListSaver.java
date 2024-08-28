import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ListSaver {
    private final String filePath;

    public ListSaver(String filePath) {
        this.filePath = filePath;
    }

    public String getList() {
        String list = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                list += line + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO Error found: " + e);
        }

        return list;
    }

    public void saveList(String list) { // to be edited

    }
}