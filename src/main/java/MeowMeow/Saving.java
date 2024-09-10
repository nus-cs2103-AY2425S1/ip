package MeowMeow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;

public class Saving {

    private Path saveFilePath;
    private String pathName;
    private File file;

    private TaskList taskList;

    public Saving(String pathName) {
        this.pathName = pathName;
        this.saveFilePath = Path.of(pathName);
        this.taskList = new TaskList();
    }

    public void load(String filePath) throws FileNotFoundException {
        this.file = new File(filePath);
        Scanner s = new Scanner(this.file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            switch (parts[0]) {
                case "T":
                    ToDo todo = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markDone();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markDone();
                    }
                    taskList.add(event);
                    break;
            }
        }
        s.close();
    }

    public void getData() throws IOException {
        try {
            load(pathName);
            //System.out.println("loaded file");
            //System.out.println("full path: " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            Files.createDirectories(saveFilePath.getParent());
            Files.createFile(saveFilePath);
            this.file = new File(pathName);
            //System.out.println("created file");
        }
    }

    public void saveData() throws IOException {
        try {
            FileWriter fw = new FileWriter(this.pathName);
            for (Task task : taskList) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
            //System.out.println("saved tasks");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}