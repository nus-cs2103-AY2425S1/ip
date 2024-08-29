package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String path = "src/main/data/task_list.txt";
    File f;
    Scanner sc;
    FileWriter fwA;
    FileWriter fw;

    public Storage() throws IOException {
        f = new File(path);
    }

    public void createFile() throws IOException {
        f.createNewFile();
    }

    public StringBuilder read() throws FileNotFoundException {
        sc = new Scanner(f);
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.nextLine() + "\n");
        }

        return sb;
    }

    public void mark(int lineNo) throws IOException {
        int count = 1;
        sc = new Scanner(f);
        fwA = new FileWriter(path,true);
        StringBuilder updated_text = new StringBuilder();
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            if (count == lineNo) {
                String[] tokens = s.split("\\[_]");
                String new_line = tokens[0] + "[X]" + tokens[1];
                updated_text.append(new_line).append("\n");
            } else {
                updated_text.append(s).append("\n");
            }
            count++;
        }

        clear();
        fwA.write(updated_text.toString());
        fwA.close();
    }

    public void unmark(int lineNo) throws IOException {
        int count = 1;
        sc = new Scanner(f);
        fwA = new FileWriter(path,true);
        StringBuilder updated_text = new StringBuilder();
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            if (count == lineNo) {
                String[] tokens = s.split("\\[X]");
                String new_line = tokens[0] + "[_]" + tokens[1];
                updated_text.append(new_line).append("\n");
            } else {
                updated_text.append(s).append("\n");
            }
            count++;
        }

        clear();
        fwA.write(updated_text.toString());
        fwA.close();
    }

    public void delete(int lineNo) throws IOException {
        int count = 1;
        int lineNumber = 1;
        sc = new Scanner(f);
        fwA = new FileWriter(path,true);
        StringBuilder updated_text = new StringBuilder();
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] tokens = s.split("\\.");
            if (count != lineNo) {
                updated_text.append(lineNumber).append(".").append(tokens[1]).append("\n");
                lineNumber++;
            }
            count++;
        }

        clear();
        fwA.write(updated_text.toString());
        fwA.close();
    }

    private void clear() throws IOException {
        fw = new FileWriter(path);
        fw.write("");
        fw.close();
    }

    public void write(String text) throws IOException {
        fwA = new FileWriter(path, true);
        fwA.write(text + "\n");
        fwA.close();
    }

//   public void delete() {
//
//   }
}
