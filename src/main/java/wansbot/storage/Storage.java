package wansbot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import wansbot.tasks.Deadlined;
import wansbot.tasks.Events;
import wansbot.tasks.TaskList;
import wansbot.tasks.Todos;
import wansbot.ui.UI;

/**
 * Class which gives WansBot functionality to read from existing tasklist or writing to a tasklist.
 */
public class Storage {
    private UI ui;

    /**
     * Initialises our storage with ui.
     *
     * @param ui UI is needed to print certain messages to console when Exception occur.
     */
    public Storage(UI ui) {
        this.ui = ui;
    }

    /**
     * Loads task from ./data/tasklist.txt. If file doesn't exist, WansBot will inform user as such.
     *
     * @return TaskList which is loaded from ./data/tasklist.txt.
     */
    public String loadTasks(TaskList taskList) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data/tasklist.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] splitInput = line.split(" ");
                String typeTask = splitInput[1];
                String nameTask = "";
                switch (typeTask) {
                case "T":
                    if (line.contains("[ X ]")) {
                        nameTask = line.substring(11);
                        Todos next = new Todos(nameTask);
                        next.finish();
                        taskList.add(next);
                    } else {
                        nameTask = line.substring(11);
                        Todos next = new Todos(nameTask);
                        taskList.add(next);
                    }
                    break;
                case "D":
                    String[] deadlineSplit = line.split("by: ");
                    LocalDate deadline = LocalDate.parse(deadlineSplit[1].substring(0,
                            deadlineSplit[1].length() - 1).trim(), DateTimeFormatter.ofPattern("MMM d yyyy"));
                    nameTask = deadlineSplit[0].substring(11, deadlineSplit[0].length() - 1);
                    if (line.contains("[ X ]")) {
                        Deadlined next = new Deadlined(nameTask, deadline);
                        next.finish();
                        taskList.add(next);
                    } else {
                        Deadlined next = new Deadlined(nameTask, deadline);
                        taskList.add(next);
                    }
                    break;
                case "E":
                    String[] splitUserStartDate = line.split("from: ", 3);
                    String[] splitUserEndDate = splitUserStartDate[1].split("to: ", 2);
                    if (line.contains("[ X ]")) {
                        Events next = new Events(splitUserStartDate[0].substring(11, splitUserStartDate[0]
                                .length() - 2), LocalDate.parse(splitUserEndDate[0].substring(0, splitUserEndDate[0]
                                        .length() - 1).trim(), DateTimeFormatter.ofPattern("MMM d yyyy")),
                                LocalDate.parse(splitUserEndDate[1].substring(0, splitUserEndDate[1].length() - 2)
                                                .trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        next.finish();
                        taskList.add(next);
                    } else {
                        Events next = new Events(splitUserStartDate[0].substring(11, splitUserStartDate[0]
                                .length() - 2), LocalDate.parse(splitUserEndDate[0].substring(0, splitUserEndDate[0]
                                .length() - 1).trim(), DateTimeFormatter.ofPattern("MMM d yyyy")),
                                LocalDate.parse(splitUserEndDate[1].substring(0, splitUserEndDate[1].length() - 2)
                                                .trim(), DateTimeFormatter.ofPattern("MMM d yyyy")));
                        taskList.add(next);
                    }
                    break;
                default:
                    assert false : "Your tasklist.txt is wrong. Delete it.";
                    // asserts internal invariant that txt file should be formatted in a certain way
                }
                line = reader.readLine();
            }
            reader.close();
            return ui.handleSuccessfulLoad(taskList);
        } catch (FileNotFoundException e) {
            return ui.handleFileNotFound();
        } catch (IOException e) {
            return ui.handleIoExceptionLoad();
        }
    }

    /**
     * Saves the current userTaskList to ./data/tasklist.txt. If ./data/tasklist.txt doesn't exist it will create the
     * path. If it exists. It will overwrite the tasklist.txt file.
     *
     * @param taskList Takes in the current userTaskList and writes it to ./data/tasklist.txt.
     */
    public String saveTasks(TaskList taskList) {
        try {
            if (!Files.exists(Paths.get("data"))) {
                File directory = new File("./data");
                directory.mkdirs();
            }
            File file = new File("data", "tasklist.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                writer.write(taskList.getTask(i) + "\n");
            }
            writer.close();
            if (taskList.numOfTasks() == 0) {
                return ui.handleUnsuccessfulSave(taskList);
            }
            return ui.handleSuccessfulSave(taskList);
        } catch (IOException e) {
            return ui.handleIoExceptionSave();
        }
    }
}
