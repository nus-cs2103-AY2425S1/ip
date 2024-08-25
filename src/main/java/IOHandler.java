import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

public class IOHandler {

    public static void initializeTaskFile() {
        File newDir = new File("src/main/data");
        newDir.mkdir();
        try {
            File taskFile = new File("src/main/data/Bunbun.txt");
            taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Task parseTask(String taskDescription) {
        ArrayList<String> info = new ArrayList<>(Arrays.asList(taskDescription.split(";")));
        String type = info.get(0);
        Boolean isComplete = (info.get(1).equals("true")) ? true : false;
        Task task;
        if (type.equals("todo")) {
            task = new ToDo(info.get(2));
        } else if (type.equals("deadline")) {
            LocalDate date = DateTimeHandler.isValidLocalDate(info.get(3));
            task = new Deadline(info.get(2), date);
        } else {
            LocalDate startDate = DateTimeHandler.isValidLocalDate(info.get(3));
            LocalDate endDate = DateTimeHandler.isValidLocalDate(info.get(4));
            task = new Event(info.get(2), startDate, endDate);
        }
        if (isComplete) {
            task.complete();
        }
        return task;
    }

    public static ArrayList<Task> toArrayList() {
        File taskFile = new File("src/main/data/Bunbun.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                String taskDescription = s.nextLine();
                taskList.add(parseTask(taskDescription));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public static void writeTask(Task task) {
        try {
            FileWriter fw = new FileWriter("src/main/data/Bunbun.txt", true);
            String taskDescription = task.genFileString();
            fw.write(taskDescription);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAllFromList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter("src/main/data/Bunbun.txt");
            fw.write("");
            fw.close();
            fw = new FileWriter("src/main/data/Bunbun.txt", true);
            for (int i = 0; i < taskList.getNumOfTasks(); i++) {
                writeTask(taskList.getTaskByIndex(i));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
