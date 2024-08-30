import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws ParkException {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                File dir = f.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                f.createNewFile();
            } catch (IOException e) {
                throw new ParkException("Error creating file");
            }
            return new TaskList();
        }
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            TaskList tasklist = new TaskList();
            for (String line : lines) {
                Task t = decode(line);
                tasklist.add(t);
            }
            return tasklist;
        } catch (IOException e) {
            throw new ParkException("Error loading file");
        }
    }

    public void append(Task t) throws ParkException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(t.encode() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new ParkException("Error writing to file");
        }
    }

    public void modify(String oldLine, String newLine) throws ParkException {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            List<String> modifiedLines = new ArrayList<String>();
            for (String line : lines) {
                if (line.equals(oldLine)) {
                    modifiedLines.add(newLine);
                } else {
                    modifiedLines.add(line);
                }
            }
            Files.write(Path.of(filePath), modifiedLines);
        } catch (IOException e) {
            throw new ParkException("Error writing to file");
        }
    }

    public void delete(Task t) throws ParkException {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            List<String> modifiedLines = new ArrayList<String>();
            for (String line : lines) {
                if (!line.equals(t.encode())) {
                    modifiedLines.add(line);
                }
            }
            Files.write(Path.of(filePath), modifiedLines);
        } catch (IOException e) {
            throw new ParkException("Error writing to file");
        }
    }

    public Task decode(String line) throws ParkException {
        try {
            String[] s = line.split("/");
            switch (s[0]) {
                case "T" -> {
                    Task t = new ToDo(s[2]);
                    if (s[1].equals("[X]")) {
                        t.mark();
                    }
                    return t;
                }
                case "D" -> {
                    Task t = new Deadline(s[2], s[3]);
                    if (s[1].equals("[X]")) {
                        t.mark();
                    }
                    return t;
                }
                case "E" -> {
                    Task t = new Event(s[2], s[3], s[4]);
                    if (s[1].equals("[X]")) {
                        t.mark();
                    }
                    return t;
                }
                default -> throw new ParkException("file corrupted");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ParkException("file corrupted");
        }
    }
}
