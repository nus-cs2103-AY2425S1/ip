
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Save {
    public static void saveTasks(ArrayList<Task> ls) {
        String output = "";
        for (int i = 0 ; i < ls.size() ; i++) {
            output += (i + 1) + "." + ls.get(i).toString() + "\n";
        }

        try {
            File file = new File("./Count.txt");
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileWriter writer = new FileWriter("./Count.txt");
            writer.write(output);
            writer.close();

        } catch (IOException e) {
            System.out.println("File Creation failed.");
        }

    }
}
