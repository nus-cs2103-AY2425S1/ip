import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Park {
    public static void main(String[] args) {
        String filePath = "./data/park.txt";
        File f = new File(filePath);
        ArrayList<Task> arr = new ArrayList<Task>();
        try {
            Scanner fsc = new Scanner(f);
            while (fsc.hasNext()) {
                String[] s = fsc.nextLine().split("/");
                if (s[0].equals("T")) {
                    Task t = new ToDo(s[2]);
                    if (s[1].equals("[X]")) {
                        t.mark();
                    }
                    arr.add(t);
                } else if (s[0].equals("D")) {
                    Task t = new Deadline(s[2], s[3]);
                    if (s[1].equals("[X]")) {
                        t.mark();
                    }
                    arr.add(t);
                } else {
                    Task t = new Event(s[2], s[3], s[4]);
                    if (s[1].equals("[X]")) {
                        t.mark();
                    }
                    arr.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                File dir = f.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                f.createNewFile();
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Hello! I'm Park
                What can I do for you?""");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                System.exit(0);
            } else if (s.equals("list")) {
                if (arr.isEmpty()) {
                    System.out.println("There are no tasks in your list.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    int n = 1;
                    for (Task t : arr) {
                        System.out.print(n);
                        System.out.println("." + t.toString());
                        n++;
                    }
                }
            } else if (s.startsWith("mark")) {
                try {
                    String strn = s.substring(5);
                    int n = Integer.parseInt(strn);
                    Task t = arr.get(n - 1);
                    String oldLine = t.getSaveVersion();
                    t.mark();
                    String newLine = t.getSaveVersion();
                    arr.set(n - 1, t);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    try {
                        List<String> lines = Files.readAllLines(Paths.get(filePath));
                        List<String> modifiedLines = new ArrayList<String>();
                        for (String line : lines) {
                            if (line.equals(oldLine)) {
                                modifiedLines.add(newLine);
                            } else {
                                modifiedLines.add(line);
                            }
                        }
                        Files.write(Paths.get(filePath), modifiedLines);
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("invalid or missing index");
                }
            } else if (s.startsWith("unmark")) {
                try {
                    String strn = s.substring(7);
                    int n = Integer.parseInt(strn);
                    Task t = arr.get(n - 1);
                    String oldLine = t.getSaveVersion();
                    t.unmark();
                    String newLine = t.getSaveVersion();
                    arr.set(n - 1, t);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    try {
                        List<String> lines = Files.readAllLines(Paths.get(filePath));
                        List<String> modifiedLines = new ArrayList<String>();
                        for (String line : lines) {
                            if (line.equals(oldLine)) {
                                modifiedLines.add(newLine);
                            } else {
                                modifiedLines.add(line);
                            }
                        }
                        Files.write(Paths.get(filePath), modifiedLines);
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("invalid or missing index");
                }
            } else if (s.startsWith("delete")) {
                try {
                    String strn = s.substring(7);
                    int n = Integer.parseInt(strn);
                    Task t = arr.get(n - 1);
                    String desc = t.getDescription();
                    String lineToDelete = t.getSaveVersion();
                    System.out.println("Noted. I've removed this task:" + desc);
                    arr.remove(n - 1);
                    int noTasks = arr.size();
                    String strNoTasks = Integer.toString(noTasks);
                    System.out.println("Now you have " + strNoTasks + " tasks in the list");
                    try {
                        List<String> lines = Files.readAllLines(Paths.get(filePath));
                        List<String> modifiedLines = new ArrayList<String>();
                        for (String line : lines) {
                            if (!line.equals(lineToDelete)) {
                                modifiedLines.add(line);
                            }
                        }
                        Files.write(Paths.get(filePath), modifiedLines);
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("invalid or missing index");
                }
            } else if (s.startsWith("todo")) {
                try {
                    String desc = s.substring(5);
                    Task t = new ToDo(desc);
                    arr.add(t);
                    int n = arr.size();
                    String strn = Integer.toString(n);
                    try {
                        FileWriter fw = new FileWriter(filePath, true);
                        fw.write(t.getSaveVersion() + "\n");
                        fw.close();
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + strn + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("please provide a description");
                }
            } else if (s.startsWith("deadline")) {
                try {
                    String[] str = s.split(" /by ");
                    String desc = str[0].substring(9);
                    String by = str[1];
                    Task t = new Deadline(desc, by);
                    arr.add(t);
                    int n = arr.size();
                    String strn = Integer.toString(n);
                    try {
                        FileWriter fw = new FileWriter(filePath, true);
                        fw.write(t.getSaveVersion() + "\n");
                        fw.close();
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + strn + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("please use the format: desc /by deadline");
                }
            } else if (s.startsWith("event")) {
                try {
                    String[] str = s.split(" /");
                    String desc = str[0].substring(6);
                    String start = str[1].substring(5);
                    String end = str[2].substring(3);
                    Task t = new Event(desc, start, end);
                    arr.add(t);
                    int n = arr.size();
                    String strn = Integer.toString(n);
                    try {
                        FileWriter fw = new FileWriter(filePath, true);
                        fw.write(t.getSaveVersion() + "\n");
                        fw.close();
                    } catch (IOException io) {
                        System.out.println(io.getMessage());
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + strn + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("please use the format: desc /from start /to end");
                }
            } else {
                System.out.println("I have no idea what that means");
            }
        }
    }
}
