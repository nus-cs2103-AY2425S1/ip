import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Peter {
    static final String FILE_PATH = "tasks/data.txt";

    static void updateUser(String name, int size) {
        System.out.println("I've added: " + name);
        System.out.println(String.format("Now you have %d in the list!", size));
    }

    static void writeTaskToFile(String details) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(details + "\n"); // for readibility
            fw.close();
        } catch (IOException e) {
            System.out.println("Some error has occured, please try again.");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Peter!\nWhat can I do for you?\n");

        // Init Globals
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(FILE_PATH);
        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // use "," as delimiters
                String[] splits = line.split(",");
                switch (splits[0]) {
                case "T":
                    tasks.add(new ToDos(splits[2]));
                    break;
                case "D":
                    tasks.add(new Deadlines(splits[2], splits[3].strip()));
                    break;
                case "E":
                    tasks.add(new Event(splits[2], splits[3].strip(), splits[4].strip()));
                    break;
                }
                if (splits[1].strip() == "0") {
                    tasks.get(tasks.size() - 1).markAsNotDone();
                } else {
                    tasks.get(tasks.size() - 1).markAsNotDone();
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // Admin Message
            System.out.println("File has been generated.");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("Some error has occured, please try again.");
                System.out.println(e.getMessage());
            }
        }
        
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine().strip();
            if (command.equals("bye")) {
                break;
            }
            try {
                if (command.startsWith("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    int count = 1;
                    for (Task t : tasks) {
                        System.out.println((count) + String.format(".%s ", t.toString()));
                        count++;
                    }
                } else if (command.startsWith("unmark")) {
                    // extract integer value
                    String intValue = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(intValue);

                    Task t = tasks.get(index - 1);
                    t.markAsNotDone();
                    ArrayList<String> fileContents = new ArrayList<>();

                    try {
                        Scanner fileScanner = new Scanner(f);
                        while (fileScanner.hasNextLine()) {
                            fileContents.add(fileScanner.nextLine());
                        }
                        fileScanner.close();

                        String s = fileContents.get(index - 1);
                        fileContents.set(index - 1, s.replaceFirst("0|1", "0"));
                        FileWriter fw = new FileWriter(f);
                        for (String line : fileContents) {
                            fw.write(line + "\n");
                        }
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("Some error has occured, please try again.");
                        System.out.println(e.getMessage());
                    }

                    System.out.println("OK! I've marked the task as not done yet: ");
                    System.out.println(t.toString());
                } else if (command.startsWith("mark")) {
                    // extract integer value
                    String intValue = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(intValue);

                    Task t = tasks.get(index - 1);
                    t.markAsDone();
                    ArrayList<String> fileContents = new ArrayList<>();

                    try {
                        Scanner fileScanner = new Scanner(f);
                        while (fileScanner.hasNextLine()) {
                            fileContents.add(fileScanner.nextLine());
                        }
                        fileScanner.close();

                        String s = fileContents.get(index - 1);
                        fileContents.set(index - 1, s.replaceFirst("0|1", "1"));
                        FileWriter fw = new FileWriter(f);
                        for (String line : fileContents) {
                            fw.write(line + "\n");
                        }
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("Some error has occured, please try again.");
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Good job! I've marked this task as done: ");
                    System.out.println(t.toString());
                } else if (command.startsWith("delete")) {
                    // extract integer value
                    String intValue = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(intValue);

                    Task t = tasks.get(index - 1);
                    tasks.remove(index - 1);
                    ArrayList<String> fileContents = new ArrayList<>();

                    try {
                        Scanner fileScanner = new Scanner(f);
                        while (fileScanner.hasNextLine()) {
                            fileContents.add(fileScanner.nextLine());
                        }
                        fileScanner.close();

                        fileContents.remove(index - 1);
                        FileWriter fw = new FileWriter(f);
                        for (String line : fileContents) {
                            fw.write(line + "\n");
                        }
                        fw.close();
                    } catch (IOException e) {
                        System.out.println("Some error has occured, please try again.");
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Noted! I've removed this task:");
                    System.out.println(t.toString());
                } else {
                    String name = "";
                    if (command.startsWith("todo")) {
                        name = command.substring(4).strip();
                        if (name.isEmpty()) {
                            throw new BadDescriptionException(TaskTypes.TODO);
                        }
                        tasks.add(new ToDos(name));
                        updateUser(name, tasks.size());
                        writeTaskToFile(String.format("T, %d, %s", 0, name));
                    } else if (command.startsWith("deadline")) {
                        String[] splits = command.split("/");
                        name = splits[0].substring(8).strip();
                        if (splits.length != 2 || name.isEmpty()) {
                            throw new BadDescriptionException(TaskTypes.DEADLINE);
                        }
                        // specific to deadlines
                        String details = splits[1].replace("by", "");
                        try {
                            tasks.add(new Deadlines(name, details.strip()));
                        } catch (DateTimeParseException ex) {
                            throw new DateTimeException(name);
                        }
                        updateUser(name, tasks.size());
                        writeTaskToFile(String.format("D, %d, %s, %s", 0, name, tasks.get(tasks.size() - 1).getWriteTaskInfo()));
                    } else if (command.startsWith("event")) {
                        String[] splits = command.split("/");
                        name = splits[0].substring(5).strip();
                        if (splits.length != 3 || name.isEmpty()) {
                            throw new BadDescriptionException(TaskTypes.EVENT);
                        }
                        // specific to events
                        String startDetails = splits[1].replace("from", "");
                        String endDetails = splits[2].replace("to", "");
                        try {
                            tasks.add(new Event(name, startDetails.strip(), endDetails.strip()));
                        } catch (DateTimeParseException ex) {
                            throw new DateTimeException(name);
                        }
                        updateUser(name, tasks.size());
                        writeTaskToFile(String.format("E, %d, %s, %s", 0, name, tasks.get(tasks.size() - 1).getWriteTaskInfo()));
                    } else {
                        throw new UnknownCommandException();
                    }
                }
            } catch (UnknownCommandException | BadDescriptionException | DateTimeException e) {
                System.out.println(e.getMessage());
            }
        }

        // EXIT
        System.out.println("\nBye, hope to see you again soon!");
        scanner.close();
    }
}
