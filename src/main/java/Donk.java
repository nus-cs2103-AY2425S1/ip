import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Donk {

    private static boolean validNum(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String greeting = " ____________________________________________________________\n" +
                " Hello! I'm Donk, the super intelligent chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "    Bye bro, catch 'ya later\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try {
            tasks = readFile("./save.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read save file");
        }

        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();
                String[] inputArray = userInput.split("\\s+");
                String command = inputArray[0];
                if (userInput.isBlank()) {
                    continue;
                } else if (userInput.equals("bye")) {
                    String filePath = "./save.txt";
                    File file = new File(filePath);
                    if (!file.exists()) {
                        try {
                            // Attempt to create the file
                            if (file.createNewFile()) {
                                System.out.println("Save file created successfully.");
                            } else {
                                System.out.println("Failed to create the file.");
                            }
                        } catch (IOException e) {
                            System.out.println("An error occurred while creating the file: " + e.getMessage());
                            e.printStackTrace();
                            return;
                        }
                    }
                    writeToFile("./save.txt", tasks);
                    System.out.println(byeMsg);
                    break;
                } else if (userInput.equals("list")) {
                    if (tasks.size() == 0) {
                        System.out.println("    You've got not tasks yet bro. Add todo, deadline, or event tasks.");
                    }
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("    " + i + ": " + tasks.get(i - 1).toString());
                    }
                } else if (inputArray[0].equals("mark") && inputArray[1].matches("\\d+")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    System.out.println("    Yo I've marked this thingy as done");
                    tasks.get(index).markDone();
                    System.out.println("    " + tasks.get(index).toString());
                } else if (inputArray[0].equals("unmark") && inputArray[1].matches("\\d+")) {
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    System.out.println("    Aights now it's unmarked again");
                    tasks.get(index).unmarkDone();
                    System.out.println("    " + tasks.get(index).toString());
                } else if (command.equals("delete")) {
                    if (inputArray.length < 2) {
                        throw new IllegalArgumentException ("Please provide the index of the task to delete");
                    }
                    if (!validNum(inputArray[1])) {
                        throw new IllegalArgumentException("Please provide a valid index");
                    }
                    int index = Integer.parseInt(inputArray[1]) - 1;
                    String temp = tasks.get(index).toString();
                    tasks.remove(index);
                    System.out.println("    Alright bro I deleted that for you");
                    System.out.println("    deleted: " + temp);
                    System.out.println("    You now have " + tasks.size() + " tasks");
                }else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {

                    Task t;
                    if (inputArray[0].equals("todo")) {
                        if (inputArray.length < 2 || userInput.length() < 5) {
                            throw new TodoException("Bruh todo task cannot be empty.\nExample:\n    todo homework");
                        }
                        t = new ToDo(userInput.substring(5));

                        tasks.add(t);
                    } else if (inputArray[0].equals("deadline")) {
                        String[] split = userInput.split("/by");
                        if (split.length < 2) {
                            System.out.println("invalid format, require /by <date-time>");
                            continue;
                        }
                        String bef = split[0].substring(9);
                        String aft = split[1];
                        t = new Deadline(bef, aft.strip());
                        tasks.add(t);

                    } else {
                        String[] split1 = userInput.split("/start");
                        if (split1.length < 2) {
                            System.out.println("    Invalid Format man. You need a /start and a /end");
                            continue;
                        }
                        String[] split2 = split1[1].split("/end");
                        if (split1.length < 2 || split2.length < 2) {
                            System.out.println("    Invalid Format man. You need a /start and a /end");
                            continue;
                        }
                        String start = split2[0];
                        String end = split2[1];
                        String description = split1[0].substring(6);
                        t = new Event(description, start.strip(), end.strip());
                        tasks.add(t);

                    }
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + t.toString());
                    System.out.println("    You now have " + tasks.size() + " tasks");
                } else {
                    throw new Exception("Ehhh not sure what this is man");
                }

            } catch (TodoException e) {
                System.out.println("    " + e.getMessage());
            } catch (Exception e) {
                System.out.println("    " + e.getMessage());

            }
        }
    }

    private static void writeToFile(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toFileSaveString() + "\n");
        }

        fw.close();
    }


    private static List<Task> readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] data = line.split("\\|");
            if (line.charAt(0) == 'T') {
                tasks.add(new ToDo(data[2]));
            } else if (line.charAt(0) == 'D') {
                tasks.add(new Deadline(data[2], data[3]));
            } else if (line.charAt(0) == 'E') {
                tasks.add(new Event(data[2], data[3], data[4]));
            }
            if (line.charAt(2) == '1') {
                tasks.get(tasks.size() - 1).markDone();
            }
        }
        return tasks;
    }


}

