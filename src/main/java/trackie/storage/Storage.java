package trackie.storage;

import trackie.tasks.Deadline;
import trackie.tasks.Event;
import trackie.tasks.Task;
import trackie.tasks.Todo;

import java.io.*;
import java.util.Scanner;

public class Storage {
    File fPtr;
    public Storage(String filepath) {
        fPtr = new File(filepath);
    }

    //loads data from txt file into memory
    public void load(TaskList t) {
        try {
            Scanner sc = new Scanner(fPtr);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] arguments = s.split("\\|");

                // add tasks according to their types
                if (arguments[0].equals("T")) {
                    t.tasks.add(new Todo(arguments[2], Integer.parseInt(arguments[1])));
                } else if (arguments[0].equals("D")) {
                    t.tasks.add(new Deadline(arguments[2], arguments[3], Integer.parseInt(arguments[1])));
                } else if (arguments[0].equals("E")) {
                    t.tasks.add(new Event(arguments[2], arguments[3], arguments[4], Integer.parseInt(arguments[1])));
                }
            }
            t.listTasks();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //saves data from memory to txt file
    public void save(TaskList t) {
        try {
            PrintWriter pw = new PrintWriter(fPtr);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Task x : t.tasks) {
            String data = "";
            int status = x.getStatusIcon().equals("X") ? 1 : 0;
            if (x instanceof Todo) {
                data = String.format("T|%d|%s", status, x.getDescription());
            } else if (x instanceof Deadline) {
                Deadline d = (Deadline) x;
                data = String.format("D|%d|%s|%s", status, d.getDescription(), d.getDeadline());
            } else if (x instanceof Event) {
                Event e = (Event) x;
                data = String.format("E|%d|%s|%s|%s", status, e.getDescription(), e.getStart(), e.getEnd());
            }

            try {
                FileWriter fw = new FileWriter(fPtr, true);
                fw.write(data);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
