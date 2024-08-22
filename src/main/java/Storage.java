import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {

    private enum Status {
        INCOMPLETE(false),
        COMPLETE(true);

        private final boolean status;

        Status(boolean status) {
            this.status = status;
        }

        public boolean getStatus() {
            return status;
        }
    }

    private String link;

    public Storage(TaskList taskList) {
        try {
            Files.createDirectories(Paths.get("../data"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.link = "../data/tasks.txt";
        readFromFile(taskList);
    }

    public void saveToFile(TaskList taskList) {
        File file = new File(link);
        BufferedWriter writer;

        try {
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            boolean first = true;
            for (Task task : taskList.retrieveTasks()) {
                if (!first) {
                    writer.newLine();
                }
                writer.write(convertTaskToString(task));
                first = false;
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFromFile(TaskList taskList) {
        File file = new File(this.link);
        BufferedReader reader;

        try {
            file.createNewFile();
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                taskList.addTask(parseInput(line));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException | ZaibotException e) {
            System.out.println(e.getMessage());
        }
    }

    public Task parseInput(String input) throws ZaibotException {
        String[] tokens = input.split(" \\| ");

        Task task;

        Status status = Status.valueOf(tokens[1].toUpperCase());
        String name = tokens[2];

         task = switch (tokens[0].trim()) {
            case "T" -> new ToDoTask(name);
            case "D" -> new DeadlineTask(name, LocalDateTime.parse(tokens[3]));
            case "E" -> new EventTask(name, LocalDateTime.parse(tokens[3]), LocalDateTime.parse(tokens[4]));
            default -> throw new ZaibotException("Saved file data not in expected format.");
        };

        task.setCompletionStatus(status.getStatus());

        return task;
    }

    public String convertTaskToString(Task task) {
        return task.toSaveString();
    }
}
