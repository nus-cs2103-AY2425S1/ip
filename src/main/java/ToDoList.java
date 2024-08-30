import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<Task> toDoList = new ArrayList<>();
    private static final String FILE_PATH = "./data/edith.txt";
    private static final File DIRECTORY = new File("./data");

    public void add(Task task) {
        toDoList.add(task);
        saveTasks();
    }

    public void mark(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.get(taskNumber - 1).check();
        saveTasks();
    }

    public void unmark(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.get(taskNumber - 1).uncheck();
        saveTasks();
    }

    public String getTask(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        return toDoList.get(taskNumber - 1).toString();
    }

    public int getNumberofTasks() {
        return this.toDoList.size();
    }

    public void delete(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.remove(taskNumber - 1);
    }

    public void saveTasks() {
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdirs();
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : toDoList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("oops! an error occurred while saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> properties = List.of(line.split(" \\| "));
                if (Objects.equals(properties.get(0), "T")) {
                    ToDoTask task = new ToDoTask(properties.get(2));
                    if (Objects.equals(properties.get(1), "1")) {
                        task.check();
                    }
                    toDoList.add(task);
                }
                else if (Objects.equals(properties.get(0), "D")) {
                    DeadlineTask task = new DeadlineTask(properties.get(2), properties.get(3));
                    if (Objects.equals(properties.get(1), "1")) {
                        task.check();
                    }
                    toDoList.add(task);
                }
                else if (Objects.equals(properties.get(0), "E")){
                    EventTask task = new EventTask(properties.get(2), properties.get(3), properties.get(4));
                    if (Objects.equals(properties.get(1), "1")) {
                        task.check();
                    }
                    toDoList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" psa: you currently do not have the file edith.txt under the data directory. i'll create" +
                    "one for you right now!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(" oops!!! there's something wrong with edith.txt's format... please check it! for now," +
                    "i will remove the tasks that have formatting issues.");
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < toDoList.size(); i++) {
            str = str + " " + (i + 1) + "." + toDoList.get(i).toString() + "\n";
        }
        return str;
    }
}
