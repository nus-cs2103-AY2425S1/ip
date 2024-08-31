import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final static String FILEPATH = "./tarsTasks.txt";

    DeadlineParser deadlineParser = new DeadlineParser();
    EventParser eventParser = new EventParser();

    private List<String> readFile() {
        List<String> savedTasks = new ArrayList<>();

        try {
            File saved = new File(FILEPATH);

            if (!saved.createNewFile()) {
                Scanner scanner = new Scanner(saved);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    savedTasks.add(data);

                }
                scanner.close();
            }

        } catch (IOException e) {
            throw new TarsException("An expected error occurred when creating file");
        }

        return savedTasks;
    }

    private List<Task> convertToTask(List<String> taskString){

        List<Task> tasks = new ArrayList<>();

        for (String s: taskString) {
            String[] taskInfo = s.split("\\|", 5);

            Task t;

            String taskType = taskInfo[0];
            String taskDone = taskInfo[1];
            String taskName = taskInfo[2];
            if (taskName.isEmpty()) {
                throw new TarsException("Invalid file format");
            }

            //noinspection EnhancedSwitchMigration
            switch(taskType) {
                case "T":
                    t = new ToDo(taskName);
                    break;
                case "D":
                    LocalDate date = deadlineParser.validateCommand(new String[]{"by", taskInfo[3]});
                    t = new Deadline(taskInfo[2], date);
                    break;
                case "E":
                    LocalDate[] dates = eventParser.validateCommand(new String[]{"from", taskInfo[3]}, new String[]{"to", taskInfo[4]});
                    t = new Event(taskName, dates[0], dates[1]);
                    break;

                default:
                    throw new TarsException("Invalid file format");
            }

            if (taskDone.equals("1")) {
                t.markDone();
            }

            tasks.add(t);
        }

        return tasks;
    }

    public List<Task> updateTasks() {
        return convertToTask(readFile());
    }
    public void saveTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        try (FileWriter writer = new FileWriter(FILEPATH);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            for (Task t : tasks) {
                String taskInfo = t.saveTask();
                bufferedWriter.write(taskInfo);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new TarsException("Unexpected error occurred when writing to file");
        }
    }

}
