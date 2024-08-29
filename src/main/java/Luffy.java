import java.util.ArrayList;
import java.util.Scanner;

public class Luffy {

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
                Deadline deadlineTask = new Deadline(taskAndDeadline[0].trim(), taskAndDeadline[1].trim());
                textList.add(deadlineTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s", deadlineTask.stringIsDone()));
                System.out.println(String.format("Now you have %d tasks in the list.", textList.size()));
                showLine();

            } else if (echoPhrase.startsWith("event ")) {

                showLine();
                String[] eventDetails = echoPhrase.substring(6).split("/");
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
