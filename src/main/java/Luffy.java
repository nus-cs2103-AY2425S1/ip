import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Luffy {

    public static void checkValidArguments(String[] textList, int expectedLength) throws LuffyException {
        if (textList.length != expectedLength && expectedLength == 2) {
            throw new LuffyException("Your deadline task requires a deadline!");
        } else if (textList.length != expectedLength && expectedLength == 3) {
            throw new LuffyException("Your event task requires a start time and end time!");
        }
    }

    public static void validCommand(String command) throws LuffyException {
        command = command.trim();
        String[] validCommands = {"mark", "unmark", "todo", "deadline", "event"};
        String[] parsedCommands = command.split(" ", 2);

       if (Arrays.stream(validCommands).noneMatch(parsedCommands[0]::equals)) {
           if (command.equals("bye") || command.equals("list")) {
               return;
           }
           throw new LuffyException("Invalid command! This command either does not exist, or has not been implemented yet.");
       } else if (parsedCommands.length <= 1) {
            throw new LuffyException("Incomplete command! Please provide more information to your command.");
       } else if (parsedCommands.length > 1){
           String[] details = parsedCommands[1].split("/", 3);
           if (parsedCommands[1].startsWith("deadline") && details.length < 1) {
               throw new LuffyException("Your deadline tasks requires a deadline!");
           } else if (parsedCommands[1].startsWith("event") && details.length < 2) {
               throw new LuffyException("Your event tasks requires a start time and end time");
           }
       } else {
           return;
       }
    }

    public static void main(String[] args) {

        // Variables
        ArrayList<Task> textList = new ArrayList<>();

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
                return;

            } else if (echoPhrase.equals("list")) {

                for (int i = 0; i < textList.size(); i++) {
                    System.out.println(String.format(" %d.%s", i + 1, textList.get(i).stringIsDone()));
                }
                showLine();

            } else if (echoPhrase.startsWith("mark ")) {

                showLine();
                int index = Integer.parseInt(echoPhrase.substring(5)) - 1;
                textList.get(index).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  %s", textList.get(index).stringIsDone()));
                showLine();

            } else if (echoPhrase.startsWith("unmark ")) {

                showLine();
                int index = Integer.parseInt(echoPhrase.substring(7)) - 1;
                textList.get(index).markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("  %s", textList.get(index).stringIsDone()));
                showLine();

            } else if(echoPhrase.startsWith("todo ")) {

                showLine();
                Todo todoTask = new Todo(echoPhrase.substring(5));
                textList.add(todoTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s", todoTask.stringIsDone()));
                System.out.println(String.format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else if (echoPhrase.startsWith("deadline ")) {

                showLine();
                String[] taskAndDeadline = echoPhrase.substring(9).split("/");
                try {
                    checkValidArguments(taskAndDeadline, 2);
                } catch (LuffyException e) {
                    System.out.println(e.getMessage());
                    showLine();
                    continue;
                }
                Deadline deadlineTask = new Deadline(taskAndDeadline[0].trim(), taskAndDeadline[1].trim());
                textList.add(deadlineTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s", deadlineTask.stringIsDone()));
                System.out.println(String.format("Now you have %d tasks in the list.", textList.size()));
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
                Event eventTask = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2]);
                textList.add(eventTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s", eventTask.stringIsDone()));
                System.out.println(String.format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else {

                textList.add(new Task(echoPhrase));
                showLine();
                System.out.println(String.format(" added: %s", echoPhrase));
                showLine();

            }
        }
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }
}
