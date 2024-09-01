import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadTest() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner saveScanner = new Scanner(file);
        while (saveScanner.hasNextLine()){
            System.out.println(saveScanner.nextLine());
        }
    }



    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner saveScanner = new Scanner(file);
            while (saveScanner.hasNextLine()) {
                String input = saveScanner.nextLine();
                String[] parts = input.split("\\|");

                String type = parts[0];
                String marked = parts[1];
                String info = parts[2];

                switch (type) {
                    case "T":
                        Todo todo = new Todo(info);
                        if (marked.equals("1")) {
                            todo.setDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        String[] deadlineDetails = info.split(" /by ", 2);
                        if (deadlineDetails.length == 2) {
                            Deadline deadlineTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                            if (marked.equals("1")) {
                                deadlineTask.setDone();
                            }
                            tasks.add(deadlineTask);
                        }
                        break;
                    case "E":
                        String[] eventDetails = info.split(" /from ", 2);
                        if (eventDetails.length == 2) {
                            String[] times = eventDetails[1].split(" /to ", 2);
                            if (times.length == 2) {
                                Events eventTask = new Events(eventDetails[0], times[0], times[1]);
                                if (marked.equals("1")) {
                                    eventTask.setDone();
                                }
                                tasks.add(eventTask);
                            }
                        }
                        break;
                }
            }
        }
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(TaskList.listToString(tasks));
        fw.close();
    }
}
