import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class Daniel {
    public static void handleList(List<Task> array){
        System.out.println("Here are the tasks in your list");
        int i = 1;
        for ( Task element : array) {
            System.out.println(i + "." + element.toString());
            i += 1;
        }
    }
    public static void loadTask(String input, List<Task> array) {
        if (input.startsWith("todo")) {
            Todo x = new Todo(input.substring(5));
            array.add(x);
        } else if (input.startsWith("deadline")) {
            String[] split = input.substring(9).split(" /by ");
            Deadline x = new Deadline(split[0], split[1]);
            array.add(x);
        } else if (input.startsWith("event")) {
            String[] split = input.substring(6).split(" /from | /to ");
            Event x = new Event(split[0], split[1], split[2]);
            array.add(x);
        }
    }
    public static void handleTask(String input, List<Task> array) throws WrongKeyword, MissingArg{
        if (input.startsWith("todo")) {
            try {
                Todo x = new Todo(input.substring(5));
                array.add(x);
                System.out.println("Got it. I've added this task:\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            } catch(Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] split = input.substring(9).split(" /by ");
                Deadline x = new Deadline(split[0], split[1]);
                array.add(x);
                System.out.println("Got it. I've added this task;\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("event")) {
            try {
                String[] split = input.substring(6).split(" /from | /to ");
                Event x = new Event(split[0], split[1], split[2]);
                array.add(x);
                System.out.println("Got it. I've added this task;\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else {
            throw new WrongKeyword("Wrong keyword");
        }
    }
    public static void createFolder() {
        File newFolder = new File("./data");
        newFolder.mkdirs();
    }
    public static void createFile() {
        File file = new File("./data/daniel.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void loadFile(File file, List<Task> array) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        array.add(new Todo(parts[2], parts[1].equals("1")));
                        break;
                    case "D":
                        array.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                        break;
                    case "E":
                        array.add(new Event(parts[2], parts[3], parts[4], parts[1].equals("1")));
                        break;
                }
            }
            s.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void writeFile(File file, List<Task> array) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : array) {
                fw.write(task.save() + '\n');
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Daniel\nWhat can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        boolean val = true;
        List<Task> array = new ArrayList<>();
        File folder = new File("./data");
        File file = new File("./data/daniel.txt");
        if (folder.exists()) {
            if (!file.exists()) {
                createFile();
            } else {
                loadFile(file, array);
            }
        } else {
            createFolder();
            createFile();
        }

        while(val){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                val = false;
                System.out.println("Bye hope to see you again soon");
            } else if (input.equals("list")) {
                handleList(array);
            } else if (input.startsWith("mark")) {
                int index = input.charAt(input.length() - 1) - '0';
                array.get(index - 1).markAsDone();
                System.out.println("Nice I have marked this task as done:");
                System.out.println(array.get(index - 1).toString());
            } else if (input.startsWith("unmark")) {
                int index = input.charAt(input.length() - 1) - '0';
                array.get(index - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(array.get(index - 1).toString());
            } else if (input.startsWith("delete")) {
                int index = input.charAt(input.length() - 1) - '0';
                Task t = array.get(index - 1);
                array.remove(index - 1);
                System.out.println("Noted. I've removed this task:\n" + t.toString());
                System.out.println("Now you have " + array.size() +" tasks in the list.");
            }else {
                try {
                    handleTask(input, array);
                } catch (WrongKeyword | MissingArg e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        writeFile(file, array);
    }
}
