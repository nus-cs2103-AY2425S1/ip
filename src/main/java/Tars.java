import java.util.ArrayList;
import java.util.Scanner;

public class Tars {
    public static void main(String[] args) {
        //welcome/introduction message
        System.out.println("    _____________________________________________");
        System.out.println("    Hello! I'm Tars\n" + "    What can I do for you");
        System.out.println("    _____________________________________________");


        Scanner scanner = new Scanner(System.in); //initalising scanner to read inputs from user
        String entry = scanner.nextLine(); //storing string input in a variable

        //store all input entries
        ArrayList<String> itemsList = new ArrayList<>();

        //while loop to ensure termination of programme only when "bye" input
        while(!entry.equals("bye")) {
            //using for loop to list all entries from arraylist
            if (entry.equals("list")) {
                System.out.println("    _____________________________________________");
                for (int i = 0; i < itemsList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + itemsList.get(i));
                }
                System.out.println("    _____________________________________________");

                entry = scanner.nextLine(); //updating variable to next input entry
            } else {
                itemsList.add(entry); //adding each input entry to list

                System.out.println("    _____________________________________________");
                System.out.println("    added: " + entry);
                System.out.println("    _____________________________________________");

                entry = scanner.nextLine(); //updating variable to next input entry
            }
        }

        //exit message when given input "bye"
        System.out.println("    _____________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________________");
    }
}
