package yapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage, which reads and write from a file to load or save the history of Tasks in the list.
 */
public class Storage {
    private File file;

    /**
     * Creates an instance of Storage.
     *
     * @param file The file object that Storage will read and write to.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Writes the current list of tasks into the file.
     *
     * @param listOfTasks List of task to write into the file.
     */
    public void writeHistory(ArrayList<Task> listOfTasks) {
        try {
            FileWriter filewriter = new FileWriter(file);
            for (Task task : listOfTasks) {
                filewriter.write(task.toFile() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write history");
        }
    }

    /**
     * Loads the history of Tasks into the current list of task.
     *
     * @return TaskList with the loaded list of tasks.
     */
    public TaskList loadHistory() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        try {
            if (!this.file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            if (this.file.canRead()) {
                Scanner sc = new Scanner(this.file);
                while (sc.hasNextLine()) {
                    String nextline = sc.nextLine();
                    this.loadTask(listOfTasks, nextline);
                }
            } else {
                throw new YapperException("Unable to read file");
            }
        } catch (IOException var4) {
            System.out.println("Unable to load history");
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }

        return new TaskList(listOfTasks, this);
    }

    /**
     * Loads a task into the current list of tasks.
     *
     * @param listOfTasks List of tasks for task to be added into.
     * @param input       String to be decoded into a Task
     * @throws YapperException If String has the wrong format and is unable to be decoded.
     */
    public void loadTask(ArrayList<Task> listOfTasks, String input) throws YapperException {
        String typeOfTask = input.substring(0, 1);
        String[] taskParameter = input.substring(2).split("--");
        String fileDoneSymbol;
        String taskName;
        if (typeOfTask.equals("T")) {
            if (taskParameter.length != 2) {
                throw new YapperException("Unable to read file: Invalid ToDo Parameters");
            }

            fileDoneSymbol = taskParameter[0];
            taskName = taskParameter[1];
            boolean isDone = fileDoneSymbol.equals("D");
            ToDo toDo = new ToDo(taskName);
            toDo.setIsDone(isDone);
            listOfTasks.add(toDo);
        } else {
            String fromString;
            if (typeOfTask.equals("D")) {
                if (taskParameter.length != 3) {
                    throw new YapperException("Unable to read file: Invalid Deadline Parameters");
                }

                fileDoneSymbol = taskParameter[0];
                taskName = taskParameter[1];
                fromString = taskParameter[2];
                boolean isDone = fileDoneSymbol.equals("D");
                LocalDateTime byDateTime = LocalDateTime.parse(fromString);
                Deadline deadline = new Deadline(taskName, byDateTime);
                deadline.setIsDone(isDone);
                listOfTasks.add(deadline);
            } else {
                if (!typeOfTask.equals("E")) {
                    throw new YapperException("Unable to read file: Invalid Task type");
                }

                if (taskParameter.length != 4) {
                    throw new YapperException("Unable to read file: Invalid Event Parameters");
                }

                fileDoneSymbol = taskParameter[0];
                taskName = taskParameter[1];
                fromString = taskParameter[2];
                String toString = taskParameter[3];
                boolean isDone = fileDoneSymbol.equals("D");
                LocalDateTime fromDateTime = LocalDateTime.parse(fromString);
                LocalDateTime toDateTime = LocalDateTime.parse(toString);
                Event event = new Event(taskName, fromDateTime, toDateTime);
                event.setIsDone(isDone);
                listOfTasks.add(event);
            }
        }
    }
}
