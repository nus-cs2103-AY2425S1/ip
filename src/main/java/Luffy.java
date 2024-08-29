import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import static java.lang.String.format;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDateTime;


public class Luffy {

    private static final String DEST_FILE = "./LuffyData/TaskData";

    public static void checkValidArguments(String[] textList, int expectedLength) throws LuffyException {
        if (textList.length != expectedLength && expectedLength == 2) {
            throw new LuffyException("Your deadline task requires a deadline!");
        } else if (textList.length != expectedLength && expectedLength == 3) {
            throw new LuffyException("Your event task requires a start time and end time!");
        }
    }

    public static void validCommand(String command) throws LuffyException {
        command = command.trim();
        String[] validCommands = {"mark", "unmark", "todo", "deadline", "event", "delete"};
        String[] parsedCommands = command.split(" ", 2);

       if (Arrays.stream(validCommands).noneMatch(parsedCommands[0]::equals)) {
           if (command.equals("bye") || command.equals("list")) {
               return;
           }
           throw new LuffyException("Invalid command! This command either does not exist, or has not been implemented yet.");
       } else if (parsedCommands.length == 1) {
            throw new LuffyException("Incomplete command! Please provide more information to your command.");
       } else if (parsedCommands.length > 1){
           String[] details = parsedCommands[1].split("/", 3);
           if (parsedCommands[1].startsWith("deadline") && details.length < 1) {
               throw new LuffyException("Your deadline tasks requires a deadline!");
           } else if (parsedCommands[1].startsWith("event") && details.length < 2) {
               throw new LuffyException("Your event tasks requires a start time and end time");
           }
       }
    }

    public static void main(String[] args) {

        // Variables
        Storage taskCache = new Storage(DEST_FILE);
        ArrayList<Task> textList = new ArrayList<>();
        try {
            textList = taskCache.loadFromFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Interactions
        showLine();
        System.out.println(" Hello! I'm Luffy");
        System.out.println(" What can I do for you?");
        showLine();

        while (true) {

            Scanner echo = new Scanner(System.in);
            String echoPhrase = echo.nextLine();

            try {
                validCommand(echoPhrase);
            } catch (LuffyException e) {
                showLine();
                System.out.println(e.getMessage());
                showLine();
                continue;
            }

            if (echoPhrase.equals("bye")) {

                showLine();
                System.out.println(" Bye. Hope to see you again!");
                showLine();
                break;

            } else if (echoPhrase.equals("list")) {

                for (int i = 0; i < textList.size(); i++) {
                    System.out.println(format(" %d.%s", i + 1, textList.get(i).stringIsDone()));
                }
                showLine();

            } else if (echoPhrase.startsWith("mark ")) {

                showLine();
                int index = Integer.parseInt(echoPhrase.substring(5)) - 1;
                textList.get(index).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(format("  %s", textList.get(index).stringIsDone()));
                showLine();

            } else if (echoPhrase.startsWith("unmark ")) {

                showLine();
                int index = Integer.parseInt(echoPhrase.substring(7)) - 1;
                textList.get(index).markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(format("  %s", textList.get(index).stringIsDone()));
                showLine();

            } else if(echoPhrase.startsWith("todo ")) {

                showLine();
                Todo todoTask = new Todo(echoPhrase.substring(5), false);
                textList.add(todoTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(format("  %s", todoTask.stringIsDone()));
                System.out.println(format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else if (echoPhrase.startsWith("deadline ")) {

                showLine();
                String[] taskAndDeadline = echoPhrase.substring(9).split("/", 2);
                try {
                    checkValidArguments(taskAndDeadline, 2);
                } catch (LuffyException e) {
                    System.out.println(e.getMessage());
                    showLine();
                    continue;
                }

                if (taskAndDeadline[1].startsWith("by") || taskAndDeadline[1].startsWith("By")) {
                    String dateAndTime = taskAndDeadline[1].substring(3).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
                    LocalDateTime timeObj = LocalDateTime.parse(dateAndTime, formatter);

                }

                Deadline deadlineTask = new Deadline(taskAndDeadline[0].trim(), taskAndDeadline[1].trim(), false);
                textList.add(deadlineTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(format("  %s", deadlineTask.stringIsDone()));
                System.out.println(format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else if (echoPhrase.startsWith("event ")) {

                showLine();
                String[] eventDetails = echoPhrase.substring(6).split("/");
                try {
                    checkValidArguments(eventDetails, 3);
                } catch (LuffyException e) {
                    showLine();
                    System.out.println(e.getMessage());
                    showLine();
                    continue;
                }
                Event eventTask = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2], false);
                textList.add(eventTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(format("  %s", eventTask.stringIsDone()));
                System.out.printf(format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else if (echoPhrase.startsWith("delete ")) {

                showLine();
                int index = Integer.parseInt(echoPhrase.substring(7)) - 1;
                textList.get(index).markDone();
                System.out.println("Noted. I've removed this task:");
                System.out.println(format("  %s", textList.get(index).stringIsDone()));
                textList.remove(index);
                System.out.println(format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else {

                textList.add(new Task(echoPhrase, false));
                showLine();
                System.out.println(format(" added: %s%n", echoPhrase));
                showLine();

            }
        }

        try {
            Storage storageSystem = new Storage(DEST_FILE);
            storageSystem.saveToFile(textList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }
}
