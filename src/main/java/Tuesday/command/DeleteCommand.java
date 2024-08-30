package Tuesday.command;

import Tuesday.util.Storage;
import Tuesday.task.Task;
import Tuesday.util.Ui;

import java.io.*;

public class DeleteCommand extends Command {
    // index to delete
    private int index;

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
        ui.showDeleteMessage(index - 1);
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
                    if (i != (this.index-1)) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    }
                    line = br.readLine();
                    i++;
                }
                String everything = sb.toString();
                //System.out.println(everything);
                FileWriter wr = new FileWriter(new File("src/main/data/tuesday.txt"), false);
                wr.write(everything);
                //flushing & closing the writer
                wr.flush();
                wr.close();
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No file");
        } catch (IOException e) {
            System.out.println("Error: IOException");
        }
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
