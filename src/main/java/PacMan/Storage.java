package pacman;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String path) {
        this.filePath = path;
        createDirectory(this.filePath.substring(0, this.filePath.lastIndexOf('/')));
    }

    private void createDirectory(String path) {
        new java.io.File(path).mkdirs();
    }


    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String type = data.split("/")[0];
                boolean isTaskDone = data.split("/")[1].equals("1");
                String task = data.split("/")[2];
                switch (type) {
                case "T" -> list.add(new Todo(task));
                case "D" -> {
                    String by = data.split("/")[3];
                    list.add(new Deadline(task, by));
                }
                case "E" -> {
                    String from = data.split("/")[3];
                    String to = data.split("/")[4];
                    list.add(new Event(task, from, to));
                }
                default -> {
                    System.out.println("Invalid type");
                }
                }
                list.get(list.size() - 1).setMarkDone(isTaskDone);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void write(String list) {
        try {
            File file = new File(this.filePath);
            FileWriter fw = new FileWriter(this.filePath);
            file.delete();
            file.createNewFile();
            fw.write(list);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
