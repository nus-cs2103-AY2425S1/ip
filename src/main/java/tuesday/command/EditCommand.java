package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

import java.io.*;

public class EditCommand extends Command {
    private String responseMessage;
    private String priority;
    private int index;

    /**
     * Constructor for AddCommand
     *
     * @param commandType Type of command to be called
     */
    public EditCommand(String commandType, String postfix) {
        super(commandType);
        String[] postfixSplit = postfix.split(" /priority ", 2);
        this.index = Integer.parseInt(postfixSplit[0]);
        this.priority = postfixSplit[1];
    }

    /**
     * Checks the command type and invokes its corresponding methods
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        Task.getTaskArrayList().get(this.index - 1).setPriority(this.priority);
        this.responseMessage = "Task has been successfully updated.";
        this.editDataFromFile();
    }

    /**
     * Replaces a specific line in the data file and update the priority
     */
    private void editDataFromFile() {
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
                    } else {
                        String newData = line.substring(0, 8)
                                + this.priority
                                + line.substring(9);
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

    /**
     * Gets the response message of the command
     *
     * @return Response message of the command
     */
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
