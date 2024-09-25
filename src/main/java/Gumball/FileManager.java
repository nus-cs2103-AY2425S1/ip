package Gumball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private File file;
    private String path;

    /**
     *
     * @param path The location of the file which will store the information.
     * @throws IOException
     */
    FileManager(String path) throws IOException {
        this.path = path;
        file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     *
     * @param taskList The taskList which holds the information which will be stored in the file.
     * @throws IOException
     * @throws InputErrorException
     */
    public void updateFile(TaskList taskList) throws IOException, InputErrorException {
        FileWriter writer = new FileWriter(path);
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            Task task = taskList.getTask(i + 1);
            try {
                String temp = task.getStatusIcon() + " " + task.getOriginalInput();
                writer.write(temp + "\n");
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        writer.close();
    }

    /**
     *
     * @return The TaskList generated based on the information stored in the file.
     * @throws FileNotFoundException
     * @throws InputErrorException
     */
    public TaskList loadFile() throws FileNotFoundException, InputErrorException {
        Scanner s = new Scanner(file);
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            String input = s.nextLine();
            String command = input.substring(4);
            Task task = checkTaskType(command);
            taskList.add(task);
            checkIfMarked(taskList, input);
        }
        return taskList;
    }

    private Task checkTaskType(String input) throws TaskException {
        Task task = null;
        if (input.startsWith("todo")) {
            task = new ToDo(input);
        } else if (input.startsWith("deadline")) {
            task = new Deadline(input);
        } else if (input.startsWith("event")) {
            task = new Event(input);
        } else if (input.startsWith("fixed")) {
            task = new FixedDurationTask(input);
        }
        return task;
    }

    private void checkIfMarked(TaskList taskList, String input) throws InputErrorException {
        if (input.startsWith("[X]")) {
            taskList.mark(taskList.getNumOfTasks());
        }
    }
}
