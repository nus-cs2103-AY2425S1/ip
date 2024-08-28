import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReadWrite {
    public static void createFile(String fileName) {
        File f = new File(fileName);
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

    public static void writeFile(String filePath, String text) {
        try {
            FileWriter fw = new FileWriter(filePath); // create a FileWriter in append mode
            fw.write(text );
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public static ArrayList<Task> getTaskListFromFile(ArrayList<String> taskString) throws FileNotFoundException {
        return taskString.stream().map(str -> {
            try {
                return FileReadWrite.fromFileStringToTask(str);
            } catch (InputFormatException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList<Task>::new));
    }

    public static ArrayList<String> readFile(String filePath) throws FileNotFoundException {
        ArrayList<String> taskString = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            taskString.add(s.nextLine());
        }
        return taskString;
    }


    private static Task fromFileStringToTask(String str) throws InputFormatException{
        try {
            if (str.startsWith("T")) {
                String[] strings = str.split("[|]");
                Task tmp = new Todo(String.format("todo %s", strings[2].trim()));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            if (str.startsWith("E")) {
                String[] strings = str.split("[|]");
                Task tmp = new Event(String.format("event %s /from %s /to %s", strings[2], strings[3],strings[4]));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            if (str.startsWith("D")) {
                String[] strings = str.split("[|]");
                Task tmp = new Deadline(String.format("deadline %s /by %s ", strings[2], strings[3]));
                if (strings[1].equals("1")) tmp.markAsDone();
                return tmp;
            }
            else {
                throw new InputFormatException("");
            }
        }  catch (InputFormatException e) {
            throw new InputFormatException("Trouble getting data from database");
        }
    }

//    public static void main (String[] args) {
//        String file2 = "data/lines.txt";
//        createFile(file2);
//        appendToFile(file2, "first line" + System.lineSeparator() + "second line");
//
//    }
}
