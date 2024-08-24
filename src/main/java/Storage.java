import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Storage {

    private final String PATH;

    public Storage(String path) {
        this.PATH = path;
    }

    public ArrayList<Task> loadTask() throws IOException, LamaException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" \\| ");
                switch (words[0]) {
                case "T":
                    Todo todo = new Todo(words[2]);
                    if (words[1].equals("1")) {
                        todo.markAsDone();
                    }
                    list.add(todo);
                    break;

                case "D":
                    try {
                        LocalDate date = LocalDate.parse(words[3]);
                        Deadline deadline = new Deadline(words[2], date);
                        if (words[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        list.add(deadline);
                    } catch (DateTimeException e) {
                        throw new LamaException("Date format should follow yyyy-mm-dd");
                    }
                    break;

                case "E":
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        LocalDateTime from = LocalDateTime.parse(words[3], formatter);
                        LocalDateTime to = LocalDateTime.parse(words[4], formatter);
                        Event event = new Event(words[2], from, to);
                        if (words[1].equals("1")) {
                            event.markAsDone();
                        }
                        list.add(event);
                    } catch (DateTimeException e) {
                        throw new LamaException("Date time format should follow yyyy-MM-dd HHmm");
                    }
                    break;

                default:
                    throw new LamaException("Invalid data format");
                }
            }
            scanner.close();
        }

        return list;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH);

        for (Task task : tasks) {
            fileWriter.write(task.toFile() + "\n");
        }

        fileWriter.close();
    }

    public void addTask(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH, true);
        fileWriter.write(task.toFile() + "\n");
        fileWriter.close();
    }
}
