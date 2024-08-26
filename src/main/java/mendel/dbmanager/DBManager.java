package mendel.dbmanager;

import mendel.discretetask.Deadline;
import mendel.discretetask.Event;
import mendel.discretetask.Todo;
import mendel.mendelexception.MendelException;
import mendel.metacognition.TaskStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class DBManager {
    private File db;

    public DBManager(String filePath) {
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
            Scanner s = new Scanner(this.db); // create a Scanner using the File as the source
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
}
