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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    private static final String DIR_PATH = "data";
    private final String kittyDataPath;
    private final File kittyTasksData;

    public Storage(String fileName) {
        kittyDataPath = DIR_PATH + "/" + fileName;
        kittyTasksData = new File(kittyDataPath);
    }

    public void initializeTaskList(TaskList tasks) throws FileNotFoundException, IOException {
        if (!kittyTasksData.exists()) {
            File dir = new File(DIR_PATH);
            boolean isCreated = dir.mkdirs();
            isCreated = kittyTasksData.createNewFile();
            assert isCreated;
        }

        Scanner scanKittyTasks = new Scanner(kittyTasksData);

        while (scanKittyTasks.hasNext()) {
            Task tmp = createTaskFromInput(scanKittyTasks.nextLine());
            if (tmp != null) {
                tasks.addTask(tmp);
            }
        }
    }
    private Task createTaskFromInput(String str) {
        Task tmp;

        Pattern recordPattern =
                Pattern.compile("^([TDE])\\s*~!!\\s*(\\d+)\\s*~!!\\s(.+?)\\s*"
                        + "(?:#\\s*(.+))?\\s*"
                        + "(?:~!!\\s*(\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}))?\\s*"
                        + "(?:~!!\\s*(\\d{4}/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}))?\\s*$");
        Matcher matcher = recordPattern.matcher(str);

        if (!matcher.matches()) {
            return null;
        }

        String type = matcher.group(1);
        String status = matcher.group(2);
        String name = matcher.group(3);
        String tag = matcher.group(4);
        String firstDateTime = matcher.group(5);
        String secondDateTime = matcher.group(6);

        switch (type) {
        case "T" -> tmp = new Todo(name);
        case "D" -> tmp = new Deadline(name,
                LocalDateTime.parse(firstDateTime, Parser.DATE_TIME_FORMAT));
        case "E" -> tmp = new Event(name,
                LocalDateTime.parse(firstDateTime, Parser.DATE_TIME_FORMAT),
                LocalDateTime.parse(secondDateTime, Parser.DATE_TIME_FORMAT));
        default -> {
            return null;
        }
        }

        if (status.equals("1")) {
            tmp.mark();
        }

        if (tag != null) {
            tmp.updateTag(tag);
        }

        return tmp;
    }

    public void addContent(String content) throws IOException {
        FileWriter fw = new FileWriter(kittyDataPath, true);
        fw.write(content);
        System.out.println("Write Success!");
        fw.close();
    }

    public void rewriteFile(String content) throws IOException {
        FileWriter fw = new FileWriter(kittyDataPath);
        fw.write(content);
        fw.close();
    }
}
