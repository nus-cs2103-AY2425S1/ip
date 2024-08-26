import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

public class Storage {

    private String filePath;

    private static final String DEADLINE_DATE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DumplingException {
        String projectRootDir = System.getProperty("user.dir");
        String dataDirPath = Paths.get(projectRootDir, "data").toString();
        File dataDirFile = new File(dataDirPath);
        if (!dataDirFile.exists()) {
            dataDirFile.mkdir();
        }
        this.filePath = Paths.get(dataDirPath, "dumplingData.txt").toString();
        File dataFile = new File(this.filePath);
        List<Task> items = new ArrayList<>();
        try {
            // load data
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" \\| ");
                Pair<Task, String> pair;
                switch (line[0]) {
                    case "T":
                        pair = this.add(String.format("todo %s", line[2]), CommandEnum.TODO, items.size());
                        items.add(pair.getFirst());
                        break;
                    case "D":
                        pair = this.add(
                                String.format("deadline %s /by %s", line[2], line[3]),
                                CommandEnum.DEADLINE,
                                items.size());
                        items.add(pair.getFirst());
                        break;
                    case "E":
                        String[] fromAndTo = line[3].split("-");
                        pair = this.add(
                                String.format("event %s /from %s /to %s", line[2], fromAndTo[0], fromAndTo[1]),
                                CommandEnum.EVENT,
                                items.size());
                        items.add(pair.getFirst());
                        break;
                    default:
                        throw new DumplingException(
                                String.format("%s is not a valid task type. Data might be corrupted.", line[0]));
                }
                if (line[1].equals("1")) {
                    items.get(items.size() - 1).markAsDone();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // no data to load
        }
        return items;
    }

    public Pair<Task, String> add(String description, CommandEnum command, int numItems) throws DumplingException {
        String[] splitDescription = description.split(" ");
        String taskDescription;
        Task task;
        Pair<String, Integer> taskDescriptionIdxPair;
        switch (command) {
            case DEADLINE:
                try {
                    taskDescriptionIdxPair = Parser.formSubSection(splitDescription, 1, DEADLINE_DATE_SEPARATOR);
                    taskDescription = taskDescriptionIdxPair.getFirst();
                    Pair<String, Integer> deadlineIdxPair = Parser.formSubSection(
                            splitDescription, taskDescriptionIdxPair.getSecond() + 1, "");
                    String deadline = deadlineIdxPair.getFirst();
                    task = new Deadline(taskDescription, deadline);
                } catch (DateTimeParseException e) {
                    throw new DumplingException("Please enter the date in the correct format of YYYY-MM-DD.");
                }
                break;
            case EVENT:
                taskDescriptionIdxPair = Parser.formSubSection(splitDescription, 1, EVENT_FROM_SEPARATOR);
                taskDescription = taskDescriptionIdxPair.getFirst();
                Pair<String, Integer> fromIdxPair = Parser.formSubSection(
                        splitDescription, taskDescriptionIdxPair.getSecond() + 1, EVENT_TO_SEPARATOR);
                Pair<String, Integer> toIdxPair = Parser.formSubSection(
                        splitDescription, fromIdxPair.getSecond() + 1, "");
                String from = fromIdxPair.getFirst();
                String to = toIdxPair.getFirst();
                task = new Event(taskDescription, from, to);
                break;
            default:
                taskDescriptionIdxPair = Parser.formSubSection(splitDescription, 1, "");
                taskDescription = taskDescriptionIdxPair.getFirst();
                task = new ToDo(taskDescription);
                break;
        }
        if (taskDescription.isEmpty()) {
            throw new DumplingException(
                    "Like a dumpling, tasks cannot be empty! Please provide a descriptive name."
            );
        }
        String message = "     Got it. I've added this task:\n" +
                String.format("       %s\n", task.toString()) +
                String.format("     Now you have %d %s in the list.",
                        numItems + 1, (numItems + 1 == 1 ? "task" : "tasks"));
        return new Pair<>(task, message);
    }

    public void save(TaskList tasks) throws DumplingException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            String data = tasks.getTasksForSaving();
            if (data.isEmpty()) {
                fileWriter.close();
                File dataFile = new File(this.filePath);
                dataFile.delete();
                return;
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new DumplingException("There was an issue saving the data!");
        }
    }
}
