package Tuesday.command;

import Tuesday.util.Storage;
import Tuesday.task.Task;
import Tuesday.util.Ui;

import java.io.*;

public class MarkCommand extends Command{
    private int taskIndex;
    private boolean isMarked;
    public MarkCommand(String commandType, int taskIndex, boolean isMarked) {
        super(commandType);
        this.taskIndex = taskIndex;
        this.isMarked = isMarked;
    }
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        Task.taskArrayList.get(this.taskIndex - 1).changeDone(this.isMarked);
        ui.showMarkMessage(this.taskIndex - 1, this.isMarked);
        changeDataFromFile();
    }

    public void changeDataFromFile() {
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/tuesday.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    if (i != (this.taskIndex-1)) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    } else {
                        String newData = line.substring(0,4) + Task.taskArrayList.get(this.taskIndex - 1).getDone1()
                                + line.substring(5);
                        sb.append(newData);
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

    @Override
    public boolean isExit() {
        return false;
    }
}
