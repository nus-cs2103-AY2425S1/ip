import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException, DgptFileNotFoundException {
        List<Task> res = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String curr = s.nextLine();
                String[] parts = curr.split(" \\| ");
                switch (parts[0]) {
                case "T" -> {
                    ToDo i = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    res.add(i);

                }
                case "D" -> {
                    Deadline i = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    res.add(i);
                }
                case "E" -> {
                    Event i = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        i.mark();
                    }
                    res.add(i);
                }
                default -> throw new IOException("File format is invalid");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DgptFileNotFoundException("Could not find existing data");
        }
        return res;
    }

    public void save(TaskList taskList) throws IOException {
        File file = new File(this.filePath);

        // Handle the case where parent directory does not exist
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder s = new StringBuilder();
            for (Task t : taskList.getTaskList()) {
                if (t instanceof ToDo) {
                    s.append("T | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append("\n");
                } else if (t instanceof Deadline) {
                    s.append("D | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append(" | ")
                            .append(((Deadline) t).getDueDateString())
                            .append("\n");
                } else if (t instanceof Event) {
                    s.append("E | ")
                            .append(t.getIsDone() ? "1 | " : "0 | ")
                            .append(t.getDescription())
                            .append(" | ")
                            .append(((Event) t).getFromTime())
                            .append(" | ")
                            .append(((Event) t).getToTime())
                            .append("\n");
                }
            }
            fw.write(s.toString());
            fw.close();
        } catch (IOException e) {
            throw new IOException("An error occurred while writing to the file: " + e.getMessage(), e);
        }
    }
}
