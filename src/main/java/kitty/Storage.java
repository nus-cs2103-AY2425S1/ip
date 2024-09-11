package kitty;

import kitty.tasks.Deadline;
import kitty.tasks.Event;
import kitty.tasks.Task;
import kitty.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {

    private static final String DIR_PATH = "data";
    private static final String KITTY_DATA_PATH = "data/Kitty.txt";
    private static final File KITTY_TASKS_DATA = new File(KITTY_DATA_PATH);

    public Storage() {
    }

    public void addContent(String content) throws IOException {
        FileWriter fw = new FileWriter(KITTY_DATA_PATH, true);
        fw.write(content);
        fw.close();
    }

    public void rewriteFile(String content) throws IOException {
        FileWriter fw = new FileWriter(KITTY_DATA_PATH);
        fw.write(content);
        fw.close();
    }

    public void initializeTaskList(TaskList tasks) throws FileNotFoundException, IOException {
        if (!KITTY_TASKS_DATA.exists()) {
            File dir = new File(DIR_PATH);
            boolean isCreated = dir.mkdirs();
            isCreated = KITTY_TASKS_DATA.createNewFile();
            assert isCreated;
        }

        Scanner scanKittyTasks = new Scanner(KITTY_TASKS_DATA);
        while (scanKittyTasks.hasNext()) {
            Task tmp = createTaskFromInput(scanKittyTasks.nextLine());
            if (tmp != null) {
                tasks.addTask(tmp);
            }
        }
    }
    private Task createTaskFromInput(String str) {
        String[] aux = str.split("~!!");
        Task tmp;

        switch (aux[0].trim()) {
        case "T" -> tmp = new Todo(aux[2]);
        case "D" -> tmp = new Deadline(aux[2],
                LocalDateTime.parse(aux[3], Parser.DATE_TIME_FORMAT));
        case "E" -> tmp = new Event(aux[2],
                LocalDateTime.parse(aux[3], Parser.DATE_TIME_FORMAT),
                LocalDateTime.parse(aux[4], Parser.DATE_TIME_FORMAT));
        default -> {
            return null;
        }
        }
        if (aux[1].trim().equals("1")) {
            tmp.mark();
        }
        return tmp;
    }
}
