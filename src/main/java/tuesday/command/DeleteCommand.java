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
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends Command {
    // index to delete
    private int index;
    private String responseMessage;

    /**
     * Constructor for DeleteCommand
     *
     * @param command Description of the command
     * @param index Index of the task to delete
     */
    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    /**
     * Deletes the task and prints the message
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        assert this.index > 0 : "The index can never be 0";
        assert Task.getTaskArrayList().size() >= this.index - 1 : "Task Array should not be empty";

        this.responseMessage = ui.showDeleteMessage(this.index - 1);
        Task.deleteTask(index - 1);
        this.deleteDataFromFile();
    }

    /**
     * Deletes the task from the data file
     */
    public void deleteDataFromFile() {
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/tuesday.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    if (i != (this.index - 1)) {
                        sb.append(line);
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
        assert this.responseMessage != null : "The execute() method must be called first";
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
