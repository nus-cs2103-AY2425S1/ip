package chatgpt.storage;

import chatgpt.exception.ChatBotException;
import chatgpt.task.TaskList;
import chatgpt.task.Task;
import chatgpt.task.ToDos;
import chatgpt.task.Deadlines;
import chatgpt.task.Events;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  The Storage class handles reading of and saving data to a local text file.
 */
public class Storage {
    /** Represents the target data text file **/
    private File dataFile;
    /** Represents the file directory where the data text file resides **/
    private File dataDir;
    /** Enum of the possible tasks as initials for saving **/
    private enum Tasks {
        T,
        D,
        E

    }

    /**
     * Constructor for Storage that takes in the relative filepath of the
     * target save text file.
     *
     * @param filePath of the target save file
     */
    public Storage(String filePath) {
        dataFile = new File(new File(".")
                .getAbsolutePath() + filePath);
        dataDir = dataFile.getParentFile();

        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
    }

    /**
     * Returns the save data as a ArrayList of Task from the save file.
     *
     * @return ArrayList of Task from the save file
     * @throws ChatBotException if there is a problem reading from the save file
     */
    public ArrayList<Task> load() throws ChatBotException {
        ArrayList<Task> data = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNext()) {
                String taskType = fileReader.next();
                String[] inputs = fileReader.nextLine().split("\\|");

                String taskName;
                boolean isCompleted;
                switch(Tasks.valueOf(taskType)) {
                case T:
                    taskName = inputs[2];
                    isCompleted = inputs[1].equals("1");
                    data.add(new ToDos(taskName, isCompleted));
                    break;

                case D:
                    taskName = inputs[2];
                    isCompleted = inputs[1].equals("1");
                    String deadline = inputs[3];

                    data.add(new Deadlines(taskName, deadline, isCompleted));
                    break;

                case E:
                    taskName = inputs[2];
                    isCompleted = inputs[1].equals("1");
                    String startDate = inputs[3];
                    String endDate = inputs[4];

                    data.add(new Events(taskName, startDate, endDate, isCompleted));
                    break;
                }

            }
            return data;
        } catch (FileNotFoundException e) {
            throw new ChatBotException("Problem with reading from file");
        }
    }

    /**
     * Saves the given TaskList to the save text file.
     *
     * @param tasks is a list of all Tasks to be saved
     * @throws ChatBotException if there is a problem writing to the file
     */
    public void save(TaskList tasks) throws ChatBotException {
        try {
            dataFile.createNewFile();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.get(i).toPrint());
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ChatBotException("\tProblem with saving to file" +
                    "\n\tPlease fix the save file first");
        }
    }
}
