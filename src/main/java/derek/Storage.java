package derek;

import derek.task.DeadlineTask;
import derek.task.EventTask;
import derek.task.Task;
import derek.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String dataFilePathName = "./data/derek.txt";
    private TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }
    public TaskList readFile() {
        try {
            File tasks = new File(dataFilePathName);
            if (!tasks.exists()) {
                throw new FileNotFoundException("Data file not found");
            } else if (tasks.isDirectory()) {
                throw new FileNotFoundException("Path is a directory");
            }
            Scanner getList = new Scanner(tasks);
            while (getList.hasNext()) {
                String task = getList.nextLine();
                taskList.populateTaskList(task);
            }
            this.overrideFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;

    }

    public void overrideFile() throws IOException {
        FileWriter writer = new FileWriter(dataFilePathName);
        writer.write("");
        writer.close();

    }

    public void storeInFile() {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String isCompleted = task.isCompleted();
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    String deadline = deadlineTask.getDeadline();
                    String taskName = deadlineTask.getName();
                    writeToFile("D|" + isCompleted + "|" + taskName + "|" + deadline + "\n");
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    String startTime = eventTask.getStartTime();
                    String endTime = eventTask.getEndTime();
                    String taskName = eventTask.getName();
                    writeToFile("E|" + isCompleted + "|" + taskName + "|" + startTime + "|" + endTime + "\n");
                } else {
                    String taskName = task.getName();
                    writeToFile("T|" + isCompleted + "|" + taskName + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(String textToAdd) throws IOException{
        FileWriter writer = new FileWriter(dataFilePathName, true);
        writer.write(textToAdd);
        writer.close();

    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public int getTaskListSize() {
        return this.taskList.size();
    }



}

