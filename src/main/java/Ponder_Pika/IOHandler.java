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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOHandler {
    private static final File file = new File("./data/pika.txt");

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

    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
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
                        Deadline d = new Deadline(split[2].trim(), split[3].trim());
                        if (split[1].trim().equals("true")) {
                            d.markDone();
                        }
                        list.add(d);
                        break;
                    case "E":
                        Event e = new Event(split[2].trim(),split[3].trim(), split[4].trim());
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
