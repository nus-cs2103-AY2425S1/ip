import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Save {
    final String FILE_PATH = "SusanToDoList.txt";

    public Save() {
    }

    public void SaveList(List<Task> newList) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            StringBuilder updatedList = new StringBuilder();
            for (Task task : newList) {
                updatedList.append(task.toString());
                updatedList.append("\n");
            }
            fw.write(updatedList.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Create a to do list first!");
        }
    }
}
