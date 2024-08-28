import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private File file;
    private String path;

    FileManager(String path) throws IOException {
        this.path = path;
        file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void updateFile(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (int i = 0; i < taskList.getN(); i++) {
            Task task = taskList.getTask(i);
            try {
                String temp = "";
                temp = temp + task.getStatusIcon() + " ";
                temp = temp + task.getOriginalInput();
                writer.write(temp + "\n");
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        writer.close();
    }

    public TaskList loadFile() throws FileNotFoundException, InputErrorException {
        Scanner s = new Scanner(file);
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            String input = s.nextLine();
            String command = input.substring(4);
            if (command.startsWith("todo")) {
                taskList.add(new ToDos(command));
            } else if (command.startsWith("deadline")) {
                taskList.add(new Deadlines(command));
            } else if (command.startsWith("event")) {
                taskList.add(new Events(command));
            }
            if (input.startsWith("[X]")) {
                taskList.mark(taskList.getN());
            }
        }
        return taskList;
    }

}
