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
        for (int i = 0; i < taskList.getN(); i++) {
            Task task = taskList.getTask(i + 1);
            try {
                String temp = "";
                temp = temp + task.getStatusIcon() + " ";
                temp = temp + task.getOriginalInput();
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
            if (command.startsWith("todo")) {
                taskList.add(new ToDo(command));
            } else if (command.startsWith("deadline")) {
                taskList.add(new Deadline(command));
            } else if (command.startsWith("event")) {
                taskList.add(new Event(command));
            }
            if (input.startsWith("[X]")) {
                taskList.mark(taskList.getN());
            }
        }
        return taskList;
    }

}
