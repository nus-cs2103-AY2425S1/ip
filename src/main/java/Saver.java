import TaskObj.Deadlines;
import TaskObj.Events;
import TaskObj.Task;
import TaskObj.Todos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Saver {
    private static Task formatterToTask(String task) {
        String[] taskDesc = task.split("\\|");
        Boolean curIsCompleted = Integer.parseInt(taskDesc[1].strip()) == 1;
        switch (taskDesc[0].strip()) {
            // A to-do task
            case "T":
                return new Todos(taskDesc[2].strip(), curIsCompleted);
            // A deadline task
            case "D":
                LocalDate curDate = LocalDate.parse(taskDesc[3].strip());
                return new Deadlines(taskDesc[2].strip(), curDate, curIsCompleted);
            // An event task
            case "E":
                LocalDate fromDate = LocalDate.parse(taskDesc[3].strip());
                LocalDate toDate = LocalDate.parse(taskDesc[4].strip());
                return new Events(taskDesc[2].strip(), fromDate, toDate, curIsCompleted);
            default:
                return null;
        }
    }

    private static String formatterToText(Task task) {
        return task.toTextString();
    }

    // When start up program, read from miloData.txt -> add to array list
    public static void readData(ArrayList<Task> todoList) {
        try {
            File f = new File("./src/data/miloData.txt");
            Scanner s = new Scanner(f);
            // Iterates through lines in a file
            while (s.hasNext()) {
                // Format each text line into a task
                Task curTask = formatterToTask(s.nextLine());
                System.out.println(curTask.toString());

                // Adds each task to the array list
                todoList.add(curTask);
            }
            Printer.printList(todoList);
            Printer.printList(todoList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // When any changes made
    public static void saveData(ArrayList<Task> todoList) {
        try {
            FileWriter fw = new FileWriter("./src/data/miloData.txt");
            for (int i = 0; i < Task.taskNumber; i++) {
                fw.write(formatterToText(todoList.get(i)) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
