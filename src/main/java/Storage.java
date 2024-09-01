import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

// deal with loading, deleting and saving tasks to a .txt file
public class Storage {

    private static final String TASKLIST_FILEPATH = "./tasklist.txt";

    public Storage() {
    }

    public void updateTxt(int taskNumber, boolean isCompleted) throws UpdateTxtException {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new UpdateTxtException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new UpdateTxtException("An error occurred while reading the file");
        }

        String line = lines.get(taskNumber);
        String[] parts = line.split(" \\| ");
        parts[1] = Boolean.toString(isCompleted);
        lines.set(taskNumber, String.join(" | ", parts));

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new UpdateTxtException("An error occurred while updating the task");
        }
    }

    public List<Task> readTxt() throws TayooException{
        File f = new File(TASKLIST_FILEPATH);
        List<Task> taskArray = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                try {
                    //read file and add task to taskArray
                    String taskStr = s.nextLine();
                    Task taskToAdd = Parser.parseTask(taskStr);
                    taskArray.add(taskToAdd);
                } catch (ParserError e) {
                    //something
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new TayooException("Could not find .txt file during init");
        }
        return taskArray;
    }

    public void deleteTxt(int taskNumber) throws DeleteTxtException {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DeleteTxtException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new DeleteTxtException("An error occurred while reading the file");
        }

        lines.remove(taskNumber);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DeleteTxtException("An error occurred while deleting the task");
        }
    }

    public void addToTxt(Task taskToAdd) throws DeleteTxtException {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DeleteTxtException("Cannot find tasklist.txt");
        } catch (IOException e) {
            throw new DeleteTxtException("An error occurred while reading the file");
        }

        lines.add(taskToAdd.toTxt());
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DeleteTxtException("An error occurred while deleting the task");
        }
    }

    public boolean createTxt() throws TayooException{
        File f = new File(TASKLIST_FILEPATH);
        try {
            return f.createNewFile();
        } catch (IOException e) {
            throw new TayooException("An IOexception occurred while creating .txt file");
        }
    }

}
