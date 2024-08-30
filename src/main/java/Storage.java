import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public TaskList loadFile() {
        TaskList taskList = new TaskList();
        int i = 1;
        Scanner scanner;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            Printer.prettyPrint(new String[] {"OOPS! The save file could not be found."});
            return taskList;
        }
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            String[] input = text.split(" \\| ");
            if (input[0].equals("T")) {
                taskList.todo(input[2]);
            } else if (input[0].equals("D")) {
                taskList.deadline(input[2], input[3]);
            } else if (input[0].equals("E")) {
                taskList.event((input[2]), input[3], input[4]);
            }

            if (input[1].equals("1")) {
                taskList.mark(i);
            }
            i++;
        }

        Printer.prettyPrint(new String[] {"Saved tasks have been successfully loaded."});
        return taskList;
    }

    public void saveFile(TaskList taskList) {
        FileWriter writer;
        try {
            writer = new FileWriter(this.file);
            writer.write(taskList.getSaveFormat());
            writer.close();
        } catch (IOException e) {
            Printer.prettyPrint(new String[]{"OOPS! There was a problem saving the file."});
        }

        Printer.prettyPrint(new String[]{"Your tasks have been successfully saved."});
    }
}
