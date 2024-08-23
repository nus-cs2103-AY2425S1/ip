import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BuddyBot {
    public static void main(String[] args) {
        //Scanner object
        Scanner myObj = new Scanner(System.in);
        //Opening statement
        System.out.println("__________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");

        Task[] myList = new Task[100];
        //taking in user input
        String input = myObj.nextLine();

        for (int i = 0; i < 100; i++) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) { //LIST command
                System.out.println("Here are the tasks in your list:" + "\n");
                read(myList);
                i--;
                input = myObj.nextLine();
            } else if (input.startsWith("mark")) { //MARK command
                String last = input.substring(input.replaceAll("[0-9]+$","").length());
                try { // Non-integer exception
                    int num = Integer.parseInt(last);
                    myList[num - 1].isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    read(myList);
                } catch (NumberFormatException e) {
                    System.out.println("This is an invalid input!");
                } finally {
                    i--;
                    input = myObj.nextLine();
                }
            } else {
                if (input.startsWith("deadline")) { //DEADLINE case
                    String[] parts = input.substring(9).split("/by");
                    String description = parts[0].trim();
                    String time = parts[1].trim();
                    Deadline additionD = new Deadline(description, time);
                    myList[i] = additionD;
                    System.out.println("added: " + additionD);
                    input = myObj.nextLine();
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split("/from|/to");
                    String description = parts[0].trim();
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    Event additionE = new Event(description, start, end);
                    myList[i] = additionE;
                    System.out.println("added: " + additionE);
                    input = myObj.nextLine();

                } else { //NORMAL case (TODOs)
                    Task additionT = new Task(input);
                    myList[i] = additionT;
                    System.out.println("Got it. I've added this task: \n" + additionT);
                    input = myObj.nextLine();
                }
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void read(Task[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else {
                System.out.println(i+ 1 + "." + arr[i].toString() + "\n");
            }
        }
    }

    /*public static String findTask(String str) {
        if (str.contains("/")) {
            if (str.startsWith("deadline")) {
                String[] parts = str.substring(9).split("/by");
                String description = parts[0].trim();
                String time = parts[1].trim();
            } else if (str.startsWith("event")) {
                String[] parts = str.substring(6).split("/from|/to");
                String description = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
            }
        } else if (str.contains(
            return str.substring(5);
        }
        return "";
    }*/

}
