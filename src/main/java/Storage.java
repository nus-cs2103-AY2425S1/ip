import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private final Path filePath;

    Storage(String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        Path folder = Paths.get(currentDirectory, "data");
        Path filePath = Paths.get(folder.toString(), fileName);

        if (Files.notExists(folder)) {
            Files.createDirectory(folder);
        }

        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        this.filePath = filePath;
    }

    public ArrayList<String> retrieve() throws IOException {
        ArrayList<String> records = new ArrayList<>();
        String record;

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(
                        filePath.toFile()
                )
        );

        while ((record = bufferedReader.readLine()) != null) {
            records.add(record);
        }

        return records;
    }

    public boolean save(ArrayList<String> records) {
        try {
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (String record : records) {
                fileWriter.write(record + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
