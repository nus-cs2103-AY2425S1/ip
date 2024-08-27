import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tako {
    public static void main(String[] args) {

        try {
            File dataFile = new File("data");
            File textFile = new File("data/Tako.txt");
            if (!dataFile.exists()) {
                //created new data path
                dataFile.mkdirs();
            } else {
                //do nothing
            }
            if (textFile.createNewFile()) {
                //created new Tako.txt file
            } else {
                //load file
                Scanner scanner = new Scanner(textFile);
                while (scanner.hasNext()) {
                    System.out.println(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            System.out.println("Error in finding a file");
        }

        String name = "Tako";
        ArrayList<Task> listOfTask = new ArrayList<Task>();

        Scanner input = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + "\n" +
                           "What can I do for you?\n");

        String command = input.nextLine();

        while(!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    //list command
                    listStorage(listOfTask);
                } else if (command.length() > 4 && command.substring(0, 5).equals("mark ")) {
                    //mark command
                    int taskNumber = Integer.parseInt(command.substring(5));
                    markTask(listOfTask, taskNumber);
                } else if (command.length() > 6 && command.substring(0, 7).equals("unmark ")) {
                    //unmark command
                    int taskNumber = Integer.parseInt(command.substring(7));
                    unmarkTask(listOfTask, taskNumber);
                } else if (command.length() > 4 && command.substring(0, 5).equals("todo ")) {
                    //to-do command
                    String toDoTaskDescription = command.substring(5);
                    addStorage(listOfTask, new ToDo(toDoTaskDescription));
                } else if (command.length() > 8 && command.substring(0, 9).equals("deadline ")) {
                    //deadline command
                    int markerOfBy = command.indexOf("/by");
                    String deadlineDescription = command.substring(9, markerOfBy - 1);
                    String by = command.substring(markerOfBy + 4);
                    addStorage(listOfTask, new Deadline(deadlineDescription, by));
                } else if (command.length() > 5 && command.substring(0, 6).equals("event ")) {
                    //event command
                    int markerOfFrom = command.indexOf("/from");
                    int markerOfTo = command.indexOf("/to");
                    String eventDescription = command.substring(6, markerOfFrom - 1);
                    String from = command.substring(markerOfFrom + 6, markerOfTo - 1);
                    String to = command.substring(markerOfTo + 4);
                    addStorage(listOfTask, new Event(eventDescription, from, to));
                } else if (command.length() > 6 && command.substring(0, 7).equals("delete ")) {
                    int taskNumber = Integer.parseInt(command.substring(7));
                    deleteTask(listOfTask, taskNumber);
                    System.out.println("Now you have " + listOfTask.size() + " tasks in the list.");
                } else {
                    throw new InvalidCommandException(command);
                }
                try {
                    FileWriter fileWriter = new FileWriter("./data/Tako.txt");
                    fileWriter.write(listStorage(listOfTask));
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("File does not exists");
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.message());
            } finally {
                command = input.nextLine();
            }
        }

        //bye command
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String listStorage(ArrayList<Task> listOfTask) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < listOfTask.size(); i++) {
            output += (i + 1) + "." + listOfTask.get(i).toString() + "\n";
        }
        System.out.println(output);
        return output;
    }

    public static void addStorage(ArrayList<Task> listOfTask, Task newTask) {
        listOfTask.add(newTask);
        System.out.println(newTask.addedConfirmation(listOfTask));
    }

    public static void markTask(ArrayList<Task> listOfTask, int taskNumber) {
        listOfTask.get(taskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + listOfTask.get(taskNumber - 1).toString());
    }

    public static void unmarkTask(ArrayList<Task> listOfTask, int taskNumber) {
        listOfTask.get(taskNumber - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + "  " + listOfTask.get(taskNumber - 1).toString());
    }

    public static void deleteTask(ArrayList<Task> listOfTask, int taskNumber) {
        System.out.println("Noted. I've removed this task:\n " +
                           "  " + listOfTask.get(taskNumber - 1));
        listOfTask.remove(taskNumber - 1);
    }
}
