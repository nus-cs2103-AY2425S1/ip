import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage{

    private final String filePath;
    private final File f;

    public Storage (String filePath) throws IOException {
        this.filePath = filePath;
        this.f = new File(filePath);
        if (!f.exists()) {
            if(!f.createNewFile()) {
                System.out.println(
                        "Welp! Sumo unable to save data due to unknown error!\n"
                        + "Please exit and try again if u wanna save"
                );
            }
        }
    }

    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath, false);
            for (Task task : tasks) {
                fw.write(task.savedString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Sumo cannot save latest change.");
        }
    }

    public String[] load() throws FileNotFoundException {
            Scanner s = new Scanner(f);
            s.useDelimiter("\\A");
            return s.hasNext()
                    ? s.next().split("\n")
                    : new String[0];
    }
}
