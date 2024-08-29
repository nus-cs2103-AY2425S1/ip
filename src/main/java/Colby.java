import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Colby {
    private static void appendToFile(String fileName, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        fw.write(textToAppend);
        fw.close();
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void printFileContents(File file) throws FileNotFoundException {
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void checkFile(String fileName) {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<Task>();
        int n = 0;

        String logo = "  ____      _ _\n"
                + " / ___|___ | | |__  _   _\n"
                + "| |   / _ \\| | '_ \\| | | |\n"
                + "| |__| (_) | | |_) | |_| |\n"
                + "\\____\\___/|_|_.__/ \\__, /\n"
                + "                   |___/\n";

        System.out.println("Hello! I'm\n" + logo + "\n" + "What can I do for you?\n");

        while (true) {
            try {
                String task = "";
                if (scanner.hasNextLine()) {
                    task = scanner.nextLine();
                } else {
                    break;
                }

                if (task.equalsIgnoreCase("bye")) {
                    System.out.println("  Bye bye! Hope to see you again soon! :)");
                    System.out.println("_________________________________________________");
                    break;

                } else if (task.equalsIgnoreCase("list")) {
                    System.out.println("Here's all the tasks you have to do:");

                    for (int i = 0; i < (n); i++) {
                        System.out.println("  " + (i + 1) + ". " + store.get(i).toString());
                    }


                } else if (task.split(" ")[0].equalsIgnoreCase("mark")) {
                    Integer change = Integer.parseInt(task.split(" ")[1]);

                    store.get(change - 1).markAsDone();

                    System.out.println("  Great job! I have now marked this task as done!");
                    System.out.println("    [" + store.get(change - 1).getStatusIcon() + "] " + store.get(change - 1).description);
                } else if (task.split(" ")[0].equalsIgnoreCase("unmark")) {
                    Integer change = Integer.parseInt(task.split(" ")[1]);

                    store.get(change - 1).markAsUndone();

                    System.out.println("  Alright, I have marked this task as not done yet.");
                    System.out.println("    [" + store.get(change - 1).getStatusIcon() + "] " + store.get(change - 1).description);

                } else if (task.split(" ")[0].equalsIgnoreCase("todo")) {
                    if (task.length() < 5) {
                        throw new EmptyDescriptionException("WAIT!! You have to add in the description of your task as well");
                    }

                    store.add(new ToDo(task));
                    String lastWord = "tasks";
                    if (n == 0) {
                        lastWord = "task";
                    }
                    checkFile("Data.text");
                    String upload = store.get(n).toString() + "\n";
                    appendToFile("Data.text", upload);

                    System.out.println("  Alright, I have added this task to the list:\n"
                            + "    " + store.get(n).toString() + "\n"
                            + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                    n++;

                } else if (task.split(" ")[0].equalsIgnoreCase("deadline")) {
                    String[] parts = task.split("/");
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("WAIT!! You have to add in the description of your task as well");
                    }

                    String[] temp = task.split("/");
                    String end = task.split("/")[1].split(" ", 2)[1];
                    store.add(new Deadline(task, end));

                    String lastWord = "tasks";
                    if (n == 0) {
                        lastWord = "task";
                    }

                    String upload = store.get(n).toString() + "\n";
                    appendToFile("Data.text", upload);

                    System.out.println("  Alright, I have added this task to the list:\n"
                            + "    " + store.get(n).toString() + "\n"
                            + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                    n++;

                } else if (task.split(" ")[0].equalsIgnoreCase("event")) {
                    String[] parts = task.split("/");
                    if (parts.length < 3) {
                        throw new EmptyDescriptionException("WAIT!! You have to add in the description of your task as well");
                    }

                    String[] temp = task.split("/");
                    String start = temp[1].split(" ", 2)[1];
                    String end = temp[2].split(" ", 2)[1];

                    store.add(new Event(task, start, end));

                    String lastWord = "tasks";
                    if (n == 0) {
                        lastWord = "task";
                    }

                    String upload = store.get(n).toString() + "\n";
                    appendToFile("Data.text", upload);

                    System.out.println("  Alright, I have added this task to the list:\n"
                            + "    " + store.get(n).toString() + "\n"
                            + "  Your list now has " + (n + 1) + " " + lastWord + " :)");
                    n++;
                } else if (task.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(task.split(" ")[1]);

                    System.out.println("  Okay, I have removed this task from your list:\n" +
                                            "    " + store.get(taskNumber - 1).toString());

                    store.remove(taskNumber - 1);
                    n--;

                } else if (!task.startsWith("todo") && !task.startsWith("deadline") && !task.startsWith("event")) {
                    throw new RandomWordException("Sorry!! I'm not sure how to add that to the list for you, try specifying the type of task!");
                }

            } catch (Exception e) {
                String message = e.getMessage();
                System.out.println(message);
            }
        }
        scanner.close();
    }
}
