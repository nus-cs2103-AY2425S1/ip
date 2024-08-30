import java.io.*;

public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        ui.showDeleteMessage(index - 1);
        Task.deleteTask(index - 1);
        this.deleteDataFromFile();
    }

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
    @Override
    public boolean isExit() {
        return false;
    }
}
