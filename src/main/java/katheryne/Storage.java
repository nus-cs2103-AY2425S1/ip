package katheryne;

import katheryne.exceptions.InvalidInputException;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void save(TaskList l) {
        try {
            String str = "";
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Task t : l.getTaskList()) {
                str = str + t.toSaveString() + '\n';
            }
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public TaskList load() throws InvalidInputException {
        TaskList list = null;
        try {
            File file = new File(path);

            Scanner sc = new Scanner(file);
            list = new TaskList();

            while (sc.hasNext()) {
                String command = sc.nextLine();
                String[] fullcommand = command.split("\\|\\|");
                String commandWord = fullcommand[0];
                Task task = null;
                if (commandWord.equals("D")) {
                    String[] des = fullcommand[2].split("|", 2);
                    task = new Deadline(des[0], des[1]);
                } else if (commandWord.equals("T")) {
                    task = new ToDo(fullcommand[2]);
                } else if (commandWord.equals("E")) {
                    String[] des = fullcommand[2].split("|", 2);
                    String fromTime = des[1].split("-")[0];
                    String toTime = des[1].split("-")[1];
                    String description = des[0];
                    task = new Event(description, fromTime, toTime);
                } else {
                    throw new InvalidInputException("Invalid Input Detected");
                }
                if (fullcommand[1].equals("1")) {
                    task.mark();
                }
                list.addTask(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, Katheryne cannot find the file: " + e.getMessage());
        }
        return list;
    }
}
