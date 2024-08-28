import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Bwead {

    public static String name = "Bwead";
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> texts = new ArrayList<>();
    public static File saved = new File("./src/main/java/historyFile.txt");

    public static void main(String[] args) throws BweadException, IOException {
        System.out.println("Hello from " + name + "!");

        if (!saved.exists()) {
            System.out.println("Please create historyFile.txt at this file path: ./src/main/java/historyFile.txt");
        }

        if (saved.canRead()) {
            Scanner s = new Scanner(saved); // create a Scanner using the File as the source
            while (s.hasNext()) {
                texts.add(getTaskfromString(s.nextLine()));
            }
        }

        while (true) {
            String input = scanner.nextLine();

            boolean oneword = input.indexOf(" ") == -1;

            try {
                if (oneword) {
                    if (input.equals("todo")) {
                        throw new BweadException("OOPS!!! The description of a todo cannot be empty.");
                    } else if (input.equals("deadline")) {
                        throw new BweadException("OOPS!!! The description of a deadline cannot be empty.");
                    } else if (input.equals("event")) {
                        throw new BweadException("OOPS!!! The description of an event cannot be empty.");
                    } else if (!input.equals("bye") && !input.equals("list")) {
                        throw new BweadException("i don't know what that means :(");
                    }
                } else {
                    if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")
                    && !input.startsWith("delete") && !input.startsWith("mark") && !input.startsWith("unmark")) {
                        throw new BweadException("i don't know what that means");
                    }
                }
            } catch (BweadException e) {
                System.out.println(e.getMessage());
            }

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printlist();
            } else if (input.startsWith("mark")) {
                int toadd = Integer.valueOf(input.split(" ")[1]);
                Task task = texts.get(toadd - 1);
                task.setDone(true);
                updateFile();
                System.out.println("Nice! I've marked this task as done: " + task.text);
            } else if (input.startsWith("unmark")) {
                int toadd = Integer.valueOf(input.split(" ")[1]);
                Task task = texts.get(toadd - 1);
                task.setDone(false);
                updateFile();
                System.out.println("OK, I've marked this task as not done yet: " + task.text);
            } else if (input.startsWith("todo ")) {
                input = input.replace("todo ", "");
                Todo task = new Todo(input);
                texts.add(task);
                updateFile();
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String input1 = input.replace("deadline ", "");
                String dateString = input.split("/by ")[1];
                System.out.println(dateString);
                LocalDate date = LocalDate.parse(dateString);
                int slash = input1.indexOf("/");
                Deadline task = new Deadline(input1.substring(0, slash -1), date);
                texts.add(task);
                updateFile();
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                input = input.replace("event ", "");
                String name = input.split("/from")[0];
                String startString = input.split("/from ")[1].split(" /to")[0];
                LocalDate start = LocalDate.parse(startString);
                String endString = input.split("/from ")[1].split("/to ")[1];
                LocalDate end = LocalDate.parse(endString);
                Event task = new Event(name, start, end);
                texts.add(task);
                updateFile();
                System.out.println("Got it. I've added this task: " + task.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            } else if (input.startsWith("delete")) {
                int index = Integer.valueOf(input.split(" ")[1]);
                Task toremove = texts.get(index - 1);
                texts.remove(toremove);
                updateFile();
                System.out.println("Noted. I've removed this task: " + toremove.toString());
                System.out.println("Now you have " + texts.size() + " tasks in the list.");
            }
        }
    }

    public static void printlist() {
        for (int i = 1; i <= texts.size(); i++) {
            Task task = texts.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }

    public static void updateFile() throws IOException {
        FileWriter fw = new FileWriter(saved);
        for (int i = 1; i <= texts.size(); i++) {
            Task task = texts.get(i-1);
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static Task getTaskfromString(String string) {
        char type = string.charAt(1);
        if (type == 'T') {
            String text = string.substring(7);
            return new Todo(text);
        } else if (type == 'D') {
            String text = string.substring(7);
            String text1 = text.split("by: ")[0];
            text1 = text1.substring(0, text1.length() - 2);
            String dateString = text.split("by: ")[1];
            dateString = dateString.substring(0, dateString.length() - 1);
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d uuuu"));
            return new Deadline(text1, date);
        } else if (type == 'E') {
            String text = string.substring(7);
            String text1 = text.split("from: ")[0];
            text1 = text1.substring(0, text1.length() - 2);
            String dates = text.split("from: ")[1];
            String startString = dates.split(" to: ")[0];
            String endString = dates.split(" to: ")[1];
            endString = endString.substring(0, endString.length() - 1);
            return new Event(text1 + " ", LocalDate.parse(startString, DateTimeFormatter.ofPattern("MMM d uuuu"))
                    , LocalDate.parse(endString, DateTimeFormatter.ofPattern("MMM d uuuu")));
        } else {
            return new Task("dh");
        }
    }
}
