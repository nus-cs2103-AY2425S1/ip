import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class BuddyBot {
    public static void main(String[] args) {
        //Scanner object
        Scanner myObj = new Scanner(System.in);
        //Opening statement
        System.out.println("__________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");

        ArrayList<Task> myList = new ArrayList<Task>(100);
        //taking in user input
        String input = myObj.nextLine();

        for (int i = 0; i < 100; i++) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) { // LIST command
                System.out.println("Here are the tasks in your list:" + "\n");
                read(myList);
                i--;
                input = myObj.nextLine();
            } else if (input.startsWith("mark")) { //MARK command
                String last = input.substring(input.replaceAll("[0-9]+$","").length());
                try { // Non-integer exception
                    int num = Integer.parseInt(last);
                    if (num > myList.size() || num <= 0) {
                        throw new ListTooShortException();
                    }
                    myList.get(num - 1).isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    read(myList);
                } catch (NumberFormatException | ListTooShortException e) {
                    System.out.println("This is an invalid input!");
                } finally {
                    i--;
                    input = myObj.nextLine();
                }
            } else {
                if (input.startsWith("deadline")) { // DEADLINE case
                    String[] parts = input.substring(8).split("/by");
                    String description = parts[0].trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        String time = parts[1].trim();
                        Deadline additionD = new Deadline(description, time);
                        myList.add(i, additionD);
                        System.out.println("Got it. I've added this task: \n" + additionD);
                        System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        input = myObj.nextLine();
                    }
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split("/from|/to");
                    String description = parts[0].trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        String start = parts[1].trim();
                        String end = parts[2].trim();
                        Event additionE = new Event(description, start, end);
                        myList.add(i, additionE);
                        System.out.println("Got it. I've added this task: \n" + additionE);
                        System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        input = myObj.nextLine();
                    }
                } else if (input.startsWith("todo")) { //NORMAL case (TODOs)
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        Task additionT = new Task(description);
                        myList.add(additionT);
                        System.out.println("Got it. I've added this task: \n" + additionT);
                        System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        input = myObj.nextLine();
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        String index = input.substring(6).trim();
                        int num = Integer.parseInt(index);
                        if (num  > myList.size() || num <= 0) {
                            throw new ListTooShortException();
                        }
                        Task removed = myList.get(num - 1);
                        delete(myList, num);
                        System.out.println("Got it. I've removed this task: \n" + removed);
                        System.out.println("Now you have " + count(myList) + " tasks in the list.");
                    } catch (NumberFormatException | ListTooShortException e) { // non-integer input
                        System.out.println("This is an invalid input!");
                    } finally {
                        i--;
                        input = myObj.nextLine();
                    }
                } else {
                    System.out.println("Sorry I don't understand...");
                    i--;
                    input = myObj.nextLine();
                }
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void read(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == null) {
                break;
            } else {
                System.out.println(i+ 1 + "." + arr.get(i).toString() + "\n");
            }
        }
    }

    public static int count(ArrayList<Task> arr) {
        return arr.size();
    }

    public static void delete(ArrayList<Task> arr, int num) {
        arr.remove(num - 1);
    }
}
