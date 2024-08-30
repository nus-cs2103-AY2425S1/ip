package storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import exception.InputFormatException;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException{
        try {
            ArrayList<String> stringList = readFile();
            return getTasksFromFileString(stringList);
        } catch (FileNotFoundException e ) {
            createFile();
            return new ArrayList<>();
        }
    }

    private void createFile() {
        File f = new File(this.filePath);
        try {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An file error occurred while trying to create new data file");
            //e.printStackTrace();
        }
    }

    public void writeFile(String text) {
        try {
            FileWriter fw = new FileWriter(this.filePath); // create a FileWriter in append mode
            fw.write(text );
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private ArrayList<Task> getTasksFromFileString(ArrayList<String> taskString) throws IOException{
        ArrayList<Task> arrayList = taskString.stream().map(str -> {
            try {
                return this.getTaskFromString(str);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList<Task>::new));
        if (arrayList.contains(null)) {
            throw new IOException("Error parsing file");
        }
        return arrayList;
    }

    private ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> taskString = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            taskString.add(s.nextLine());
        }
        return taskString;
    }


    private Task getTaskFromString(String str) throws IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (str.startsWith("T")) {
                String[] strings = str.split("[|]");
                Task tmp = new Todo(strings[2].trim());
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            if (str.startsWith("E")) {
                String[] strings = str.split("[|]");
                Task tmp = new Event(strings[2], LocalDateTime.parse(strings[3].trim(), formatter),LocalDateTime.parse(strings[4].trim(), formatter));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            if (str.startsWith("D")) {
                String[] strings = str.split("[|]");
                Task tmp = new Deadline(strings[2], LocalDateTime.parse(strings[3].trim(), formatter));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            else {
                throw new InputFormatException("");
            }
        }  catch (InputFormatException e) {
            throw new IOException("Trouble getting data from database :" + e.getMessage());
        }
    }
}
