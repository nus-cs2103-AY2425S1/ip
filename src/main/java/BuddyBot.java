import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BuddyBot {


    //testing

    //second test
    //test
    //test
    //test
    static FileStorage store = new FileStorage("BuddyBot.txt");
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
                    myList.get(num - 1).status = TaskStatus.DONE;
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
                        try {
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date = LocalDate.parse(time, format);
                            Deadline additionD = new Deadline(description, date);
                            myList.add(i, additionD);
                            System.out.println("Got it. I've added this task: \n" + additionD);
                            System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            input = myObj.nextLine();
                        }
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
                        try {
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate startTime = LocalDate.parse(start, format);
                            LocalDate endTime = LocalDate.parse(end, format);
                            Event additionE = new Event(description, startTime, endTime);
                            myList.add(i, additionE);
                            System.out.println("Got it. I've added this task: \n" + additionE);
                            System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            input = myObj.nextLine();
                        }
                    }
                } else if (input.startsWith("todo")) { //NORMAL case (TODOs)
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        Task additionT = new Todo(description);
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
        store.writeToTxt(writtenList(myList));
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

    public static String writtenList(ArrayList<Task> arr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != null) {
                result.append(arr.get(i).toString()).append("\n");
            }
        }
        return result.toString();
    }

    /*public static void writeToTxt(String myTasks) { //Using this method
        try  {
            FileWriter myWriter = new FileWriter("BuddyBot.txt");
            myWriter.write(myTasks);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
