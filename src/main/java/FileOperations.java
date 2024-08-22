import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {
    public static boolean intToBool(int i) {
        return i == 1;
    }

    public static ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dir = new File("./data");
//            if (!dir.exists()) {
//                dir.mkdir();
//                System.out.println("Created new directory to store data.");
//            }
            File f = new File("./data/reo.txt");
//            if (f.createNewFile()) {
//                System.out.println("Created new file to store list.");
//                return tasks;
//            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] split = line.split("\\|");

                switch (split[0].trim()) {
                    case "T":
                        boolean isDoneT = intToBool(Integer.parseInt(split[1].trim()));
                        String nameT = split[2].trim();
                        tasks.add(new Todo(nameT, isDoneT));
                        break;
                    case "E":
                        boolean isDoneE = intToBool(Integer.parseInt(split[1].trim()));
                        String nameE = split[2].trim();
                        String from = split[3].trim();
                        String to = split[4].trim();
                        tasks.add(new Event(nameE, isDoneE, to, from));
                        break;
                    case "D":
                        boolean isDoneD = intToBool(Integer.parseInt(split[1].trim()));
                        String nameD = split[2].trim();
                        String deadline = split[3].trim();
                        tasks.add(new Deadline(nameD, isDoneD, deadline));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Error!");
        }

        return tasks;
    }

    public static void writeFile(Task t) {
        try {
            FileWriter fw = new FileWriter("./data/reo.txt", true);
            String text = t.toFileString();
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        }
    }

    public static void removeLine(int i) {
        try {
            File f = new File("./data/reo.txt");
            File temp = new File("./data/temp.txt");
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (i != lineCount) {
                    System.out.println(line);
                    tw.write(line + System.lineSeparator());
                }
                lineCount ++;
                // System.out.println(lineCount);
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }

    public static void editLine(int i, String cmd) {
        try {
            File f = new File("./data/reo.txt");
            File temp = new File("./data/temp.txt");
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (i != lineCount) {
                    // System.out.println(line);
                    tw.write(line + System.lineSeparator());
                } else {
                    String[] split = line.split("\\|");
                    if (cmd == "mark") {
                        split[1] = " 1 ";
                    } else {
                        split[1] = " 0 ";
                    }

                    String newLine = String.join("|", split);
                    tw.write(newLine + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }

    }
}
