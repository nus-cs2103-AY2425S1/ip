package tina;

import tina.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
public class Storage {
    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public void write(ArrayList<Task> list) throws TinaException{
        try {
            FileWriter writer = new FileWriter(filename);
            for (Task task : list) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new TinaException("error occurred while writing the task list to the file");
        }
    }

    public ArrayList<Task> read() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(filename);
//            System.out.println("Looking for file at: " + file.getAbsolutePath());
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseLine(line);
                list.add(task);
            }
            reader.close();
        } catch (IOException e) {
            throw new TinaException("error occurred while reading the task list from the file");
        }
        return list;
    }
}
