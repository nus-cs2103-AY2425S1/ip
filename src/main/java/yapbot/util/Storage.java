package yapbot.util;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    public ArrayList<Task> load() throws YapBotException {
        try {

            // Creates the file if it does not exist
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            Scanner s = new Scanner(file);
            ArrayList<Task> result = new ArrayList<>();

            while (s.hasNext()) {
                String[] taskData = s.nextLine().split("/");
                String taskType = taskData[0];

                switch (taskType) {
                    case "T": {
                        int isDone = Integer.parseInt(taskData[1]);
                        String taskDetails = taskData[2];
                        Task task;

                        if (isDone == 1) {
                            task = new ToDo(taskDetails, true);
                        } else {
                            task = new ToDo(taskDetails);
                        }

                        result.add(task);
                        break;
                    }

                    case "D": {
                        int isDone = Integer.parseInt(taskData[1]);
                        String taskDetails = taskData[2];
                        LocalDateTime deadline = LocalDateTime.parse(taskData[3]);
                        Task task;

                        if (isDone == 1) {
                            task = new Deadline(taskDetails, deadline, true);
                        } else {
                            task = new Deadline(taskDetails, deadline, false);
                        }
                        result.add(task);
                        break;
                    }

                    case "E": {
                        int isDone = Integer.parseInt(taskData[1]);
                        String taskDetails = taskData[2];
                        LocalDateTime from = LocalDateTime.parse(taskData[3]);
                        LocalDateTime to = LocalDateTime.parse(taskData[4]);
                        Task task;

                        if (isDone == 1) {
                            task = new Event(taskDetails, from, to, true);
                        } else {
                            task = new Event(taskDetails, from, to, false);
                        }
                        result.add(task);
                        break;
                    }

                    // Handles the case where the tasktype may not exist due to file corruption
                    default: {
                        this.file.delete();

                        this.file.createNewFile();

                        throw new YapBotException("Save data detected...load failed.\nCorrupted data found."
                                + "\nYapBot will execute without prior data.");
                    }

                }

            }

            s.close();
            return result;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new YapBotException("Error, save file could not be created."
                    + "\nYour tasks from this session will not be saved.");
        } catch (NumberFormatException | DateTimeParseException e) {
            // Covers parsing errors due to file corruption

            this.file.delete();

            try {
                this.file.createNewFile();
            } catch (IOException ignored) {
                // IOException would have been thrown already if the file cannot be created.
            }

            throw new YapBotException("Save data detected...load failed.\nCorrupted data found."
                    + "\nYapBot will execute without prior data.");
        }
    }

    public boolean save(String saveableTasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(saveableTasks);
        fileWriter.close();

        return true;
    }
}
