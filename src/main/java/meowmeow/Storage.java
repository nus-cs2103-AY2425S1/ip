package meowmeow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;

/**
 * Represents a saving and loading system
 */
public class Storage {

    private Path saveFilePath;
    private String pathName;
    private File file;

    private TaskList tasks;

    public Storage(String pathName) {
        this.pathName = pathName;
        this.saveFilePath = Path.of(pathName);
        this.tasks = new TaskList();
    }

    /**
     * Reads the save file and fills the TaskList with the Tasks from the save file.
     *
     * @param filePath File path of the save file.
     * @throws FileNotFoundException If the file cannot be found at that file path.
     */
    public void load(String filePath) throws FileNotFoundException {
        this.file = new File(filePath);
        Scanner scanner = new Scanner(this.file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            switch (parts[0]) {
            case "T":
                this.loadTodo(parts[2], parts[1]);
                break;
            case "D":
                this.loadDeadline(parts[2], parts[3], parts[1]);
                break;
            case "E":
                this.loadEvent(parts[2], parts[3], parts[4], parts[1]);
                break;
            }
        }
        scanner.close();
    }

    public void loadTodo(String description, String doneStatus) {
        ToDo todo = new ToDo(description);
        if (doneStatus.equals("1")) {
            todo.markDone();
        }
        tasks.add(todo);
    }

    public void loadDeadline(String description, String by, String doneStatus) {
        Deadline deadline = new Deadline(description, by);
        if (doneStatus.equals("1")) {
            deadline.markDone();
        }
        tasks.add(deadline);
    }

    public void loadEvent(String description, String from, String to, String doneStatus) {
        Event event = new Event(description, from, to);
        if (doneStatus.equals("1")) {
            event.markDone();
        }
        tasks.add(event);
    }

    /**
     * Loads the data from the file path if a save file exists at that path.
     * If the save file does not exist at that path, new directories and a file are created at that file path.
     *
     * @throws IOException If an I/O error occurs when creating new directories or file.
     */
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

    /**
     * Writes each Task in the TaskList into the save file at pathName.
     *
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void saveData() throws IOException {
        try {
            FileWriter fw = new FileWriter(this.pathName);
            for (Task task : tasks) {
                fw.write(task.convertToFileFormat() + "\n");
            }
            fw.close();
            //System.out.println("saved tasks");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    /**
     * Returns the TaskList which has no Tasks if there are no saved Tasks
     * or if load has not been called.
     * Returns the TaskList which contains the saved Tasks if load has run
     * successfully and the save file was not empty.
     *
     * @return TaskList.
     */
    public TaskList getTaskList() {
        return this.tasks;
    }
}