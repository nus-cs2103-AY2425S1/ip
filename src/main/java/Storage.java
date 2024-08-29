import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> toDo = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        File taskListFile = new File(filePath);
        try {
            Scanner s = new Scanner(taskListFile);
            while (s.hasNextLine()) {
                String taskMessage = s.nextLine();
                if (taskMessage.isEmpty()) {
                    continue;
                }
                Task task = TaskList.EMPTY_TASK;
                switch (taskMessage.charAt(1)) {
                    case 'T':
                        task = new TodoTask(Parser.splitTaskInfo(taskMessage)[1]);
                        toDo.add(task);
                        break;
                    case 'D':
                        task = new DeadlineTask(Parser.splitTaskInfo(taskMessage)[1]);
                        toDo.add(task);
                        break;
                    case 'E':
                        task = new EventTask(Parser.splitTaskInfo(taskMessage)[1]);
                        toDo.add(task);
                        break;
                    default:
                        System.out.println("Cannot read: " + taskMessage);
                }
                if(taskMessage.charAt(4) == 'X') {
                    task.markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            try {
                taskListFile.createNewFile();
            } catch (IOException eIO) {
                System.out.println(eIO.getMessage());
            }
        }
        return toDo;
    }

    public void save(TaskList toDo) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 1; i <= toDo.size(); i++) {
                fileWriter.write(toDo.get(i - 1) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
