import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    private String filePath = "data/lemonSaves.txt";
    File f;
    Scanner fileScanner;

    public boolean loadTasks(TaskList tasks) {
        try {
            f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            fileScanner = new Scanner(f);

            while (fileScanner.hasNextLine()) {
                String[] temp = fileScanner.nextLine().split("\\|");
                switch (temp[0]) {
                    case "T":
                        tasks.addNewTask(new Todo(temp[2], Boolean.parseBoolean(temp[1])));
                        break;
                    case "D":
                        tasks.addNewTask(new Deadline(temp[2],
                                LocalDate.parse(temp[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                Boolean.parseBoolean(temp[1])));
                        break;
                    case "E":
                        tasks.addNewTask(new Event(temp[2],
                                LocalDate.parse(temp[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                LocalDate.parse(temp[4], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                Boolean.parseBoolean(temp[1])));
                        break;
                }
            }

            return true;
        } catch (IOException e) {
            System.out.print(" Im sowwy... Something went wrong, QwQ. Unable to create file.\n" +
                    " I dont think i can do this anymore");
            return false;
        } catch (DescriptionException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileString());
            }

            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to save into file.\n" +
                    " Please make sure that \"lemonSaves.txt\" exists properly in\n" +
                    filePath);
            return false;
        }
    }
}
