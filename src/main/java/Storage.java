import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DeltaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File("./" + filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] details = sc.nextLine().replace("\n", "").split(" \\| ");
                Task task;
                if (details[0].equals("T")) {
                    task = new Todo(details[2]);
                } else if (details[0].equals("D")) {
                    task = new Deadline(details[2], details[3]);
                } else {
                    task = new Event(details[2], details[3], details[4]);
                }
                if (details[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        }
        catch (FileNotFoundException e) {
            throw new DeltaException("OOPS!!! Save File not found!");
        }
        return tasks;
    }

    public void save(TaskList taskList) throws DeltaException {
        File file = new File("./" + filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            boolean directoryCreatedSuccessfully = directory.mkdir();
            if (!directoryCreatedSuccessfully) {
                throw new DeltaException("""
                        OOPS!!! Save Directory unable to be created!
                        \t Please check Save Directory path:
                        \t """ + directory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            try {
                boolean fileCreatedSuccessfully = file.createNewFile();
                if (!fileCreatedSuccessfully) {
                    throw new DeltaException("""
                            OOPS!!! Save File unable to be created!
                            \t Please check Save File path:
                            \t """ + file.getAbsolutePath());
                }
            }
            catch (IOException e) {
                throw new DeltaException("""
                        OOPS!!! Save File unable to be created!
                        \t Please check Save File path:
                        \t """ + file.getAbsolutePath());
            }
        }

        ArrayList<Task> tasks = taskList.getTasks();
        String fileContents = "";
        for (Task task : tasks) {
            fileContents += task.saveDetails() + "\n";
        }

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(fileContents);
            fw.close();
        }
        catch (IOException e) {
            throw new DeltaException("OOPS!!! List unable to save!");
        }
    }
}
