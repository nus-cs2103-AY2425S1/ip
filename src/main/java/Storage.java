import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    String filePath;
    ArrayList<Task> arrayListTasks = new ArrayList<>();
    TaskList tasks;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws FileNotFoundException {
        File f = new File(this.filePath);
        char taskType;
        boolean isDone;
        String description;
        String by;
        String from;
        String to;

        if (f.exists()) {
            Scanner readFile = new Scanner(f);
            while (readFile.hasNextLine()) {
                String item = readFile.nextLine();
                taskType = item.charAt(1);
                isDone = item.charAt(4) == 'X';

                if (taskType == 'T') {
                    // If todos
                    int startIndex = item.indexOf("] ") + 1;
                    description = item.substring(startIndex).trim();

                    Todo todo = new Todo(description);
                    todo.setStatus(isDone);
                    this.arrayListTasks.add(todo);

                } else if (taskType == 'D') {
                    // If deadline
                    int startIndex = item.indexOf("] ") + 1;
                    int endIndex = item.indexOf("(");
                    description = item.substring(startIndex, endIndex).trim();

                    startIndex = item.indexOf(":") + 1;
                    endIndex = item.indexOf(')');
                    by = item.substring(startIndex, endIndex).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH mm ss");
                    LocalDateTime d1 = LocalDateTime.parse(by, formatter);
                    Deadline deadline = new Deadline(description, d1);
                    deadline.setStatus(isDone);
                    this.arrayListTasks.add(deadline);

                } else {
                    // If event
                    int startIndex = item.indexOf("] ") + 1;
                    int endIndex = item.indexOf('(');
                    description = item.substring(startIndex, endIndex).trim();

                    startIndex = item.indexOf(":") + 1;
                    endIndex = item.indexOf(", to:");
                    from = item.substring(startIndex, endIndex).trim();
                    startIndex = item.indexOf("to: ") + 4;
                    endIndex = item.indexOf(')');
                    to = item.substring(startIndex, endIndex).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH mm ss");
                    LocalDateTime d1 = LocalDateTime.parse(from, formatter);
                    LocalDateTime d2 = LocalDateTime.parse(to, formatter);

                    Event event = new Event(description, d1, d2);
                    event.setStatus(isDone);
                    this.arrayListTasks.add(event);
                }
            }
        }

        this.tasks = new TaskList(this.arrayListTasks);
        return this.tasks;
    }

    public void write(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.getTask(i);
            fw.write(task.toString() + "\n");
        }

        fw.close();
    }

}
