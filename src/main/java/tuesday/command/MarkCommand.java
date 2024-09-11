package tuesday.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents a command to mark as done or not done based on the index given
 */
public class MarkCommand extends Command {
    // variables
    private int taskIndex;
    private boolean isMarked;
    private String responseMessage;

    /**
     * Constructor for MarkCommand
     *
     * @param commandType Type of command
     * @param taskIndex The index of the task
     * @param isMarked Boolean for the task to be changed into
     */
    public MarkCommand(String commandType, int taskIndex, boolean isMarked) {
        super(commandType);
        this.taskIndex = taskIndex;
        this.isMarked = isMarked;
    }

    /**
     * Marks the specific task and prints the message
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        assert this.taskIndex > 0 : "The index can never be 0";
        assert Task.getTaskArrayList().size() >= this.taskIndex - 1 : "Task Array should not be empty";
        this.changeDoneInTask();
        this.getMarkMessage(ui);
        this.changeDataFromFile();
    }

    private void changeDoneInTask() {
        Task.getTaskArrayList().get(this.taskIndex - 1).changeDone(this.isMarked);
    }

    private void getMarkMessage(Ui ui) {
        this.responseMessage = ui.showMarkMessage(this.taskIndex - 1, this.isMarked);
    }

    /**
     * Replaces a specific line in the data file and update the mark
     */
    private void changeDataFromFile() {
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/tuesday.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    if (i != (this.taskIndex - 1)) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    } else {
                        String newData = line.substring(0, 4)
                                + Task.getTaskArrayList().get(this.taskIndex - 1).getDone1()
                                + line.substring(5);
                        sb.append(newData);
                        sb.append(System.lineSeparator());
                    }
                    line = br.readLine();
                    i++;
                }
                String everything = sb.toString();
                FileWriter fw = new FileWriter(new File("src/main/data/tuesday.txt"), false);
                fw.write(everything);

                //flushing & closing the writer
                fw.flush();
                fw.close();
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No file");
        } catch (IOException e) {
            System.out.println("Error: IOException");
        }
    }

    public String getString() {
        return this.responseMessage;
    }

    /**
     * Use to exit the program
     *
     * @return false and do not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
