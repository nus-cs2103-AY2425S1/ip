package mendel.dbmanager;

import mendel.discretetask.Deadline;
import mendel.discretetask.Event;
import mendel.discretetask.Task;
import mendel.discretetask.Todo;
import mendel.mendelexception.MendelException;
import mendel.metacognition.TaskStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class DBManager {
    private final String filePath;
    private final File db;

    public DBManager(String filePath) {
        this.filePath = filePath;
        File db = new File(filePath);
        this.db = db;
        if (!db.exists()) {
            try {
                String[] dirLst = filePath.split("/");
                new File(dirLst[0]).mkdir();
                db.createNewFile();
            } catch (IOException e) {
                System.out.println("here");
            }
        }
    }

    public void loadInto(TaskStorage taskStorage) {
        try {
            Scanner s = new Scanner(this.db);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] lineSegments = line.split(" \\| ");
                if (lineSegments[0].equals("T")) {
                    taskStorage.silencedAdd(Todo.loadOf(lineSegments[1].equals("1"), lineSegments[2]));
                } else if (lineSegments[0].equals("D")) {
                    taskStorage.silencedAdd(Deadline.loadOf(lineSegments[1].equals("1"), lineSegments[2], lineSegments[3]));
                } else if (lineSegments[0].equals("E")) {
                    taskStorage.silencedAdd(Event.loadOf(lineSegments[1].equals("1"), lineSegments[2], lineSegments[3],
                            lineSegments[4]));
                } else {
                    throw new MendelException("OOPS! The database is corrupted");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void create(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write("\n" + task.parseDetailsForDB());
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void update(TaskStorage taskStorage) {
        try {
            FileWriter fwRedo = new FileWriter(this.filePath);
            fwRedo.close();
            FileWriter fw = new FileWriter(this.filePath, true);
            int counter = 0;
            while (taskStorage.hasTask(counter)) {
                Task task = taskStorage.getTask(counter);
                if (counter == 0) {
                    fw.write(task.parseDetailsForDB());
                } else {
                    fw.write("\n" + task.parseDetailsForDB());
                }

                counter++;
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
