package henry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import henry.task.Task;
import henry.task.Todo;
import henry.task.Deadline;
import henry.task.Event;

/**
 * Deals with loading tasks from the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns file with the specified file path
     *
     * @return file if exist in computer, otherwise create new file
     */
    public File getFile() throws IOException {
        File file = new File(this.filePath);
        //if file does not exist, create new file and directory
        if (!file.exists()) {
            file.createNewFile();
            file.mkdirs();
        }

        return file;
    }

    /**
     * Returns the path of the file
     *
     * @return the path of the file
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Returns an ArrayList of tasks recorded in the hard disk
     *
     * @return an ArrayList of tasks that are in the form of
     *                 event, deadline and todo
     */
    public ArrayList<Task> load() throws HenryException {
        // create a Scanner using the File as the source
        try{
            Scanner scanner1 = new Scanner(this.getFile());

            ArrayList<Task> recordedTasks = new ArrayList<>();

            while (scanner1.hasNext()) {
                String input = scanner1.nextLine();
                String[] words = input.split(" \\| ");
                if (words[0].equals("T")) {
                    recordedTasks.add(new Todo(words[2],
                            (Integer.parseInt(words[1]) != 0)));
                } else if (words[0].equals("D")) {
                    recordedTasks.add(new Deadline(words[2], words[3],
                            (Integer.parseInt(words[1]) != 0)));
                } else if (words[0].equals("E")) {
                    String[] duration = words[3].split("-");
                    String startTime = duration[0];
                    String endTime = duration[1];
                    recordedTasks.add(new Event(words[2], startTime,
                            endTime, (Integer.parseInt(words[1]) != 0)));
                }
            }
            return recordedTasks;
        } catch (IOException e) {
            throw new HenryException("There is no data saved previously.");
        }

    }
}
