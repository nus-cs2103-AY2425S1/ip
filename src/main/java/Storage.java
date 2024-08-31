import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Storage {
    private final String ioPath;

    public Storage(String ioPath) {
        this.ioPath = ioPath;
    }

    public ArrayList<String> load() {
        File file = new File(ioPath);
        ArrayList<String> lines = new ArrayList<>();

        try {
            if (file.exists()) {
                // if file already exists or is successfully created
                Scanner sc = new Scanner(file);

                while (sc.hasNext()) {
                    lines.add(sc.nextLine());
                }

                // clean up
                sc.close();
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong when reading file: " + e.getMessage());
        }

        return lines;
    }

    public void save(Collection<String> lines) throws IOException {
        FileWriter fw = new FileWriter(this.ioPath, false);
        for (String line : lines) {
            fw.write(line + "\n");
        }
        fw.close();
    }
}
