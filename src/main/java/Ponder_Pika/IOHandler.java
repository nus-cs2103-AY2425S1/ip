package Ponder_Pika;
import Ponder_Pika.Task.Deadline;
import Ponder_Pika.Task.Event;
import Ponder_Pika.Task.Task;
import Ponder_Pika.Task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for handling file operations.
 * It includes methods for creating a file, saving task data to a file, and loading task data from a file.
 */
public class IOHandler {
    private static final File file = new File("./data/pika.txt");

    /**
     * Creates the necessary directories or the file if it does not exist.
     * Throws a PonderPikaException if an I/O error.
     *
     * @throws PonderPikaException if an I/O error occurs while creating the file or directories
     */
    public void create() throws PonderPikaException {
        Path path = Paths.get(file.getPath());
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

    /**
     * Saves the provided list of tasks to the file.
     * Creates the file if it does not already exist.
     * Throws a PonderPikaException if an I/O error occurs.
     *
     * @param tasks the list of tasks to be saved
     * @throws PonderPikaException if an I/O error occurs while writing to the file
     */
    public void saveData(List<Task> tasks) throws PonderPikaException {
        try{
            if(!file.exists()){
                create();
            }
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                String saveFormat = task.saveFullDetails();
                fw.write(saveFormat);
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e){
            throw new PonderPikaException(e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a list.
     * If the file does not exist or is empty, returns an empty list.
     *
     * @return an ArrayList of tasks loaded from the file
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            if (!file.exists()) {
                return list;
            }
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                switch (split[0].trim()) {
                    case "D":
                        Deadline d = new Deadline(split[2].trim(), LocalDateTime.parse(split[3].trim(),formatter));
                        if (split[1].trim().equals("true")) {
                            d.markDone();
                        }
                        list.add(d);
                        break;
                    case "E":
                        Event e = new Event(split[2].trim(), LocalDateTime.parse(split[3].trim(),formatter), LocalDateTime.parse(split[4].trim(),formatter));
                        if (split[1].trim().equals("true")) {
                            e.markDone();
                        }
                        list.add(e);
                        break;
                    case "T":
                        Todo t = new Todo(split[2].trim());
                        if (split[1].trim().equals("true")) {
                            t.markDone();
                        }
                        list.add(t);
                        break;
                }
            }
        } catch (IOException ignored){

        }
        return list;

    }

}
