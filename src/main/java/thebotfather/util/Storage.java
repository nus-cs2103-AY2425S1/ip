package thebotfather.util;

import thebotfather.task.Deadline;
import thebotfather.task.Event;
import thebotfather.task.Task;
import thebotfather.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final String pathName;

    public Storage(String pathName) {
        this.pathName = pathName;
    }

    public TaskList load() throws TheBotFatherException {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            File file = new File(this.pathName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] instructions = line.split(" \\| ");
                switch (instructions[0]) {
                    case "T":
                        taskList.addTask(new Todo(instructions[2]));
                        break;
                    case "D":
                        taskList.addTask(new Deadline(instructions[2],
                                LocalDateTime.parse(instructions[3])));
                        break;
                    case "E":
                        taskList.addTask(new Event(instructions[2],
                                LocalDateTime.parse(instructions[3]),
                                LocalDateTime.parse(instructions[4])));
                        break;
                    default:
                        throw new Exception("Error");
                }
                if (Objects.equals(instructions[1], "1")) taskList.markAsDone(taskList.numberOfElements() - 1);
            }
        } catch (Exception e) {
            throw new TheBotFatherException("The file is corrupted");
        }
        return taskList;
    }

    public void toFile(TaskList taskList) throws TheBotFatherException {

        try {
            File file = new File(this.pathName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(taskList.toFile());
            fw.close();
        } catch (IOException e) {
            throw new TheBotFatherException("Check the file path, I am sure you messed it up somehow");
        }
    }
}
