import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final File DIR_DATA = new File("data");
    private final File PATH_TASKS = new File(DIR_DATA, "tasks.txt");

    public void updateTasks(String[] s) throws IOException {
        if (!DIR_DATA.exists()) {
            DIR_DATA.mkdir();
        }
        PATH_TASKS.delete();
        PATH_TASKS.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TASKS));
        for (String str : s) {
            writer.write(str + "\n");
        }
        writer.flush();
        writer.close();
    }

}
