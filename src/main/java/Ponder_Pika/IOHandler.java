package Ponder_Pika;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Ponder_Pika.Task.Deadline;
import Ponder_Pika.Task.Event;
import Ponder_Pika.Task.Task;
import Ponder_Pika.Task.Todo;

public class IOHandler {
    private static final File FILE = new File("./data/pika.txt");

    public void create() throws PonderPikaException {
        Path path = Paths.get(FILE.getPath());
        try {
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new PonderPikaException(e.getMessage());
        }
    }

    public void saveData(List<Task> tasks) throws PonderPikaException {
        try {
            if (!FILE.exists()) {
                create();
            }

            FileWriter fw = new FileWriter(FILE);
            for (Task task : tasks) {
                String saveFormat = task.saveFullDetails();
                fw.write(saveFormat);
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new PonderPikaException(e.getMessage());
        }
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            if (!FILE.exists()) {
                return list;
            }

            FileReader fr = new FileReader(FILE);
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] splitlines = line.split("\\|");
                switch (splitlines[0].trim()) {
                case "D":
                    Deadline d = new Deadline(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(),
                            formatter));
                    if (splitlines[1].trim().equals("true")) {
                        d.markDone();
                    }

                    list.add(d);
                    break;
                case "E":
                    Event e = new Event(splitlines[2].trim(), LocalDateTime.parse(splitlines[3].trim(),formatter),
                            LocalDateTime.parse(splitlines[4].trim(),formatter));
                    if (splitlines[1].trim().equals("true")) {
                        e.markDone();
                    }

                    list.add(e);
                    break;
                case "T":
                    Todo t = new Todo(splitlines[2].trim());
                    if (splitlines[1].trim().equals("true")) {
                        t.markDone();
                    }

                    list.add(t);
                    break;
                }
            }
        } catch (IOException ignored) {
            // Ignored exception
        }

        return list;
    }
}