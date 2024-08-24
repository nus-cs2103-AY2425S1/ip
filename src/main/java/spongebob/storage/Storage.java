package spongebob.storage;

import spongebob.exception.SpongebobException;
import spongebob.task.Deadline;
import spongebob.task.Event;
import spongebob.task.Task;
import spongebob.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main storage class to read and write data into a txtfile to save tasks,
 * keeps a cache of tasklist for use by Spongebob
 */
public class Storage {

    private String filePath;
    private List<String> data;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the txt file and parse the information into a list of tasks for use
     * @return list of tasks loaded from the text file
     * @throws SpongebobException user input error
     */
    public List<Task> load() throws SpongebobException {
        this.data = new ArrayList<>();
        List<Task> res = new ArrayList<>();
        List<String> args;
        Task newTask;

        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                this.data.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }


        for (String line : this.data) {

            try {
                args = Arrays.asList(line.split("\\|"));

                switch (args.get(0)) {
                    case "TODO":
                        newTask = new Todo(args.get(2));
                        if (args.get(1).equals("true")) {
                            newTask.markAsDone();
                        }
                        res.add(newTask);
                        break;

                    case "DEADLINE":
                        newTask = new Deadline(args.get(2), args.get(3));
                        if (args.get(1).equals("true")) {
                            newTask.markAsDone();
                        }
                        res.add(newTask);
                        break;

                    case "EVENT":
                        newTask = new Event(args.get(2), args.get(3), args.get(4));
                        if (args.get(1).equals("true")) {
                            newTask.markAsDone();
                        }
                        res.add(newTask);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Some parts are corrupted!");
            }
        }

        return res;
    }

    /**
     * adds task to tasklist
     * @param task A task created by the user
     */
    public void add(Task task) {
        this.data.add(task.save());
        this.write();
    }

    /**
     * delete task from tasklist
     * @param task A task created by the user
     */
    public void delete(Task task) {
        this.data.remove(task.save());
        this.write();
    }

    /**
     * updates task in tasklist
     * @param index index of task in list
     * @param task A task created by the user
     * @throws IndexOutOfBoundsException occurs when index given is greater than size of tasklist or a negative number
     */
    public void update(int index, Task task) throws IndexOutOfBoundsException{
        this.data.set(index, task.save());
        this.write();
    }

    /**
     * saves the contents of the tasklist into the textfile
     */
    private void write() {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder res = new StringBuilder();

            for (String line : this.data) {
                res.append(line + System.lineSeparator());
            }
            fw.write(res.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
