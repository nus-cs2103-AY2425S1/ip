package mendel.dbmanager;

import mendel.discretetask.Deadline;
import mendel.discretetask.Event;
import mendel.discretetask.Task;
import mendel.discretetask.Todo;
import mendel.mendelexception.ServerError;
import mendel.metacognition.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final File db;

    public Storage(String filePath) throws ServerError {
        this.filePath = filePath;
        File db = new File(filePath);
        this.db = db;
        if (!db.exists()) {
            try {
                String[] dirLst = filePath.split("/");
                new File(dirLst[0]).mkdir();
                db.createNewFile();
            } catch (IOException e) {
                throw new ServerError("File cannot be created. Check data directory");
            }
        }
    }

    public void loadInto(TaskList taskStorage) throws ServerError {
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
                    throw new ServerError(String.format("Unidentifiable task type %s. Ensure correct task type", lineSegments[0]));
                }

            }
        } catch (FileNotFoundException e) {
            throw new ServerError("File cannot be found. Check database is not removed");
        }
    }

    public void create(Task task, boolean isNew) throws ServerError {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            if (isNew) {
                fw.write(task.parseDetailsForDB());
            } else {
                fw.write("\n" + task.parseDetailsForDB());
            }
            fw.close();
        } catch (IOException e) {
            throw new ServerError("File cannot be found. Check database is not removed");
        }
    }

    public void update(TaskList taskStorage) {
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
            throw new ServerError("File cannot be found. Check database is not removed");
        }
    }
}
