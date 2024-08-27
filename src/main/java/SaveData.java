import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveData {
    String filePath;

    public SaveData(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws TalkyException {
        ArrayList<Task> userTasks = new ArrayList<>();
        File saveData = new File(filePath);
        Parser parser = new Parser();
        try {
            Scanner sc = new Scanner(saveData);
            while(sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split(" ");
                switch (taskDetails[0]) {
                case "ToDo":
                    ToDo newTodo = new ToDo(taskDetails[1]);
                    newTodo.setMark(Boolean.parseBoolean(taskDetails[2]));
                    userTasks.add(newTodo);
                    break;
                case "Deadline":
                    String saveBy = String.join(" ", taskDetails[2], taskDetails[3]);
                    ArrayList<LocalDateTime> deadlineDate = parser.parseDate(saveBy);
                    LocalDateTime by = deadlineDate.get(0);
                    Deadline newDeadline = new Deadline(taskDetails[1], by);
                    newDeadline.setMark(Boolean.parseBoolean(taskDetails[3]));
                    userTasks.add(newDeadline);
                    break;
                case "Event":
                    String saveFrom = String.join(" ", taskDetails[2], taskDetails[3]);
                    String saveTo = String.join(" ", taskDetails[4], taskDetails[5]);
                    ArrayList<LocalDateTime> eventDates = parser.parseDate(saveFrom, saveTo);
                    LocalDateTime from = eventDates.get(0);
                    LocalDateTime to = eventDates.get(1);
                    Event newEvent = new Event(taskDetails[1], from, to);
                    newEvent.setMark(Boolean.parseBoolean(taskDetails[6]));
                    userTasks.add(newEvent);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException err) {
            throw new TalkyException("Save Data Does not Exist, Creating New One");
        } catch (TalkyException err) {
            throw new TalkyException(err.getMessage());
        }
        return userTasks;
    }

    public void saveData(TaskList tasks) throws TalkyException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.toSaveFormat());
            fw.close();
        } catch (IOException e) {
            throw new TalkyException("Error Saving Data");
        }
    }
}
