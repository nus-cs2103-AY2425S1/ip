import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    enum TaskType {
        T, D, E
    }
    File file;
    ArrayList<Task> tasks = new ArrayList<>();

    public Storage(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
        this.file = file;
    }

    public ArrayList<Task> load() {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                try {
                    addTask(line);
                } catch (InvalidFormatException e) {
                    System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return this.tasks;
    }

    private void addTask(String line) throws InvalidFormatException {
        if (!line.matches("^[TDE] .+")) {
            throw new InvalidFormatException("Invalid task type found in file");
        }
        String[] input = line.split("\\|", 3);
        TaskType type = TaskType.valueOf(input[0].trim());
        boolean marked = Integer.parseInt(input[1].trim()) == 1;
        String content = input[2].trim();
        Task task = null;
        switch (type) {
            case T:
                if (!line.matches("^T \\| [01] \\| .+")) {
                    throw new InvalidFormatException("Format should be T | [1/0] | [description]");
                }
                task = new ToDo(content);
                break;
            case D: {
                if (!line.matches("^D \\| [01] \\| .+ \\| \\d{4}-\\d{2}-\\d{2}")) {
                    throw new InvalidFormatException("Format should be D | [1/0] | [description] | yyyy-mm-dd");
                }
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
                if (!line.matches("^E \\| [01] \\| .+ \\| \\d{4}-\\d{2}-\\d{2} \\| \\d{4}-\\d{2}-\\d{2}")) {
                    throw new InvalidFormatException("Format should be E | [1/0] | [description] | yyyy-mm-dd | yyyy-mm-dd");
                }
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
            this.tasks.add(task);
        }
    }
}
