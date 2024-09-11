package vecrosen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handles tasks regarding saving and loading from the savefile.
 */
public class Storage {
    /** Task types currently supported by Vecrosen */
    private enum TaskType { TODO, DEADLINE, EVENT };

    /**
     * Saves the task list to the specified file.
     *
     * @param file The file to save to.
     * @param list The list of tasks.
     */
    public static void save(File file, ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < list.size(); ++i) {
                Task t = list.get(i);
                fw.write(t.getDescription());
                fw.write("\n" + t.isDone());
                if (t.getClass() == Task.class) {
                    fw.write(" " + TaskType.TODO.ordinal());
                } else if (t.getClass() == Deadline.class) {
                    fw.write(" " + TaskType.DEADLINE.ordinal() + '\n');
                    Deadline d = (Deadline) t;
                    fw.write(d.getBy());
                } else if (t.getClass() == Event.class) {
                    fw.write(" " + TaskType.EVENT.ordinal() + '\n');
                    Event e = (Event) t;
                    fw.write(e.getBegin() + '\n');
                    fw.write(e.getEnd());
                } else {
                    System.err.println("Unrecognized task type when saving!");
                }
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Savefile cannot be accessed!");
        }
    }

    /**
     * Loads the data from the file into the list.
     *
     * @param file The file to read from.
     * @param list The array to hold the list of tasks.
     */
    public static void load(File file, ArrayList<Task> list) {
        if (!file.exists()) {
            return;
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String description = s.nextLine();
                boolean isDone = s.nextBoolean();
                int tth = s.nextInt();
                TaskType tt = TaskType.values()[tth];
                s.nextLine();
                switch (tt) {
                case EVENT:
                    String begin = s.nextLine();
                    String end = s.nextLine();
                    list.add(new Event(description, begin, end));
                    break;
                case DEADLINE:
                    String by = s.nextLine();
                    list.add(new Deadline(description, by));
                    break;
                case TODO:
                    list.add(new Task(description));
                    break;
                default:
                    System.err.println("TaskType not recognized when loading");
                }
                list.get(list.size() - 1).setDone(isDone);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File exists but cannot be found...?");
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.err.println(list.size());
            list.clear();
            throw e;
        }
    }
}
