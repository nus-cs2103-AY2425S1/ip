import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;
    public Storage(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
            // file = new ...
        }
        this.file = file;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (!line.matches("^T \\| [01] \\| .+") ||
                        !line.matches("^D \\| [01] \\| .+ \\| \\d{4}-\\d{2}-\\d{2}") ||
                        !line.matches("^E \\| [01] \\| .+ \\| \\d{4}-\\d{2}-\\d{2} \\| \\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Invalid task found in file");
                    continue;
                }
                String[] input = line.split("\\|", 3);
                Dudu.TaskType type = Dudu.TaskType.valueOf(input[0].trim());
                boolean marked = Integer.parseInt(input[1].trim()) == 1;
                String content = input[2].trim();
                Task task = null;
                switch (type) {
                    case T:
                        task = new ToDo(content);
                        break;
                    case D: {
                        String[] tmp = content.split("\\|");
                        String description = tmp[0].trim();
                        try {
                            LocalDate date = LocalDate.parse(tmp[1].trim());
                            task = new Deadline(description, date);
                        } catch (DateTimeParseException e) {
                            System.out.println(e);
                        }
                        break;
                    } case E: {
                        String[] tmp = content.split("\\|");
                        String description = tmp[0].trim();
                        try {
                            LocalDate from = LocalDate.parse(tmp[1].trim());
                            LocalDate to = LocalDate.parse(tmp[2].trim());
                            task = new Event(description, from, to);
                        } catch (DateTimeParseException e) {
                            System.out.println(e);
                        }
                        break;
                    }
                }
                if (task != null) {
                    if (marked) {
                        task.markCompleted();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return tasks;
    }
}
