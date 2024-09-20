package chatgpt.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chatgpt.exception.ChatBotException;
import chatgpt.task.Deadlines;
import chatgpt.task.Events;
import chatgpt.task.Task;
import chatgpt.task.TaskList;
import chatgpt.task.ToDos;

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
                String[] inputs = fileReader.nextLine()
                        .split("\\|");
                Task savedTask = parseSaveData(taskType, inputs);
                data.add(savedTask);
            }
            return data;
        } catch (FileNotFoundException e) {
            throw new ChatBotException("Problem with reading from file");
        }
    }

    /**
     * Returns a Task based on the given saved taskType and inputs.
     *
     * @param taskType of the saved Task
     * @param inputs of the saved Task
     * @return Task representing the saved data
     * @throws ChatBotException if there are problems with the input or format
     */
    private Task parseSaveData(String taskType, String[] inputs)
            throws ChatBotException {
        String taskName = inputs[2];
        String taskNote = inputs[3];
        boolean isCompleted = inputs[1].equals("1");

        switch(Tasks.valueOf(taskType)) {
        case T:
            return new ToDos(taskName, taskNote, isCompleted);

        case D:
            String deadline = inputs[4];

            return new Deadlines(taskName, taskNote, deadline, isCompleted);

        case E:
            String startDate = inputs[4];
            String endDate = inputs[5];

            return new Events(taskName, taskNote, startDate, endDate, isCompleted);
        default:
            assert false : "Data stored as wrong format";
            return null;
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
            throw new ChatBotException("\tProblem with saving to file"
                    + "\n\tPlease fix the save file first");
        }
    }
}
