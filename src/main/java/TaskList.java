import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;

public class TaskList {
    final static Path FILE_PATH = Path.of("./data/tasks.csv");
    final static Path DIRECTORY_PATH = Path.of("./data");
    protected int curr = 0, end = 0, length = 0;
    private static ArrayList<Task> tasks = new ArrayList<>(1028);
    protected void add(Task task) {
        tasks.add(task);
        end = (end + 1)%tasks.size();
        length++;
        save();
    }
    private Task poll() {
        Task task = tasks.get(curr);
        tasks.remove(curr);
        curr = (curr + 1)%tasks.size();
        length--;
        return task;
    }

    protected void update(int index, Task.Status status) {
        Task task = tasks.get((curr + index - 1) % tasks.size());
        task.updateStatus(status);
        save();
    }

    private Task peek() {
        return tasks.get(curr);
    }

    protected Task get(int i) {
        return tasks.get((curr + i - 1) % tasks.size());
    }

    protected void remove(int i) {
        tasks.remove((curr + i - 1)%tasks.size());
        length--;
        save();
    }

    /**
     * Loads the tasklist saved to file. If the file is not found, or the directory does not
     * exist, then it
     */
    protected static void load() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(FILE_PATH)) {
            final String DELIMITER = ",";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(DELIMITER);
                //To handle the different types of tasks separately.
                switch (tokens[0]) {
                    case "Event" -> tasks.add(new Event(tokens[1],tokens[2],tokens[3],tokens[4]));
                    case "Deadline" -> tasks.add(new Deadline(tokens[1],tokens[2],tokens[3]));
                    case "Todo" -> tasks.add(new Todo(tokens[1],tokens[2]));
                }
            }
        } catch (FileNotFoundException e) {
            //Checks if the directory exists. If not, creates the directory.
            if (Files.notExists(DIRECTORY_PATH)) {
                try {
                    Files.createDirectories(DIRECTORY_PATH);
                } catch (IOException e2) {
                    System.out.println("Oh no! I can't save your file.\n" +
                            "Check out the error message to see what went wrong\n");
                    e2.printStackTrace();
                }
            }

            //Checks if the save file already exists. If not, creates the file.
            if (Files.notExists(FILE_PATH)) {
                try {
                    Files.createFile(FILE_PATH);
                } catch (IOException e2) {
                    System.out.println("Oh no! I can't save your file.\n" +
                            "Check out the error message to see what went wrong\n");
                    e2.printStackTrace();
                }
            }
        } catch (IndexOutOfBoundsException e) { //File is likely corrupted.
            try {
                Files.delete(FILE_PATH);
                Files.createFile(FILE_PATH);
            } catch (IOException ex) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Oh no, I can't read the saved file. \n" +
                    "Check the error message to see why");
            e.printStackTrace();
        } ;
    }

    private static void save() {
        //Checks if the directory exists. If not, creates the directory.
        if (Files.notExists(DIRECTORY_PATH)) {
            try {
                Files.createDirectories(DIRECTORY_PATH);
            } catch (IOException e) {
                System.out.println("Oh no! I can't save your file.\n" +
                        "Check out the error message to see what went wrong\n");
                e.printStackTrace();
            }
        }
        //Checks if the save file already exists. If not, creates the file.
        if (Files.notExists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
            } catch (IOException e) {
                System.out.println("Oh no! I can't save your file.\n" +
                        "Check out the error message to see what went wrong\n");
                e.printStackTrace();
            }
        }

        //Writes all tasks to file, overwriting the old file
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Oh no! I can't save your file. \n " +
                    "Check out the error message to see what went wrong");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(String.format("%d. ", i + curr + 1));
            str.append(tasks.get((curr + i)% tasks.size()).toString());
            str.append("\n");
        }
        return str.toString();
    }
}
