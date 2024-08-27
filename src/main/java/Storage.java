import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void restoreTasks(TaskList tasks) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Save file not found");
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String taskLine = s.next();
            String input = s.nextLine();
            boolean isDone = input.strip().startsWith("[X]");
            switch (taskLine.charAt(1)) {
            case 'T':
                Task t = new ToDo(input.substring(4).strip());
                tasks.addToTaskList(t);

                if (isDone) {
                    t.markAsDone();
                }

                break;
            case 'D':
                String[] deadlineName = input.substring(4).strip().split(" \\(by: ");
                Task dl = new Deadline(deadlineName[0].stripIndent(), deadlineName[1].substring(0, deadlineName[1].length() - 1));
                tasks.addToTaskList(dl);

                if (isDone) {
                    dl.markAsDone();
                }

                break;
            case 'E':
                String[] eventName = input.substring(4).strip().split("\\(");
                String[] interval = eventName[1].split(" - ");
                Event e = new Event(eventName[0].stripIndent(), interval[0], interval[1].substring(0, interval[1].length()- 1));
                tasks.addToTaskList(e);

                if (isDone) {
                    e.markAsDone();
                }

                break;
            }
        }
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.getTaskCount(); i++) {
            fw.write(String.format("%s\n", tasks.getTask(i).toString()));
        }

        fw.close();
    }

    public void initializeDataDirectory() {
        if (!Files.exists(Path.of("data"))) {
            new File("data").mkdirs();
        }

        // Check if previous list exists
        Path filePath = Path.of(this.filePath);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Something went wrong during file creation");
            }
        }
    }
}
