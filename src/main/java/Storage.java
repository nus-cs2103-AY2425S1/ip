import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    Storage(String filePath) throws IOException {
        String directory = filePath.substring(0, filePath.lastIndexOf("/"));
        new File(directory).mkdir();
        this.file = new File(filePath);
        file.createNewFile();
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            input.add(s.nextLine());
        }

        return input;
    }

    public void save(ArrayList<String> data) throws IOException {
        resetSaveFile();

        FileWriter fw = new FileWriter(file, true);
        for(String d: data) {
            fw.write(d + System.lineSeparator());
        }

        fw.close();
    }

    public void resetSaveFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.close();
    }
}
