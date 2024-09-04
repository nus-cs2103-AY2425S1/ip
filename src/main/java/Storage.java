import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File filePath;
    public Storage(String filePath) {
        this.filePath = new File(filePath);
        createFile();
    }
    public Task[] load() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner scanner = new Scanner(this.filePath);
            while (scanner.hasNext()) {
                String line =scanner.nextLine();
                if (line.isEmpty()){
                    continue;
                }
                tasks.add(Parser.loadTask(line));
            }
            return tasks.toArray(new Task[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return new Task[0];
        }

    }

    private void createFile() {
        String temp = this.filePath.toPath().toString();
        int lastIndex = temp.lastIndexOf("\\");
        try {
            if (lastIndex != -1) {
                String firstPart = temp.substring(0, lastIndex);
                new File(firstPart).mkdirs();
                filePath.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with data file");
        }
    }

    public void save(String data) {
        try {
            FileWriter fw = new FileWriter(this.filePath.toPath().toAbsolutePath().toString());
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
