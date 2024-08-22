import java.util.Scanner;

public class Tars {
    public static void main(String[] args) {
        //for welcome message of chatbot
        System.out.println("    _____________________________________________");
        System.out.println("    Hello! I'm Tars\n" + "    What can I do for you");
        System.out.println("    _____________________________________________");


        Scanner scanner = new Scanner(System.in); //initalising scanner to read inputs from user
        String entry = scanner.nextLine(); //storing string input in a variable

        //while loop to ensure termination of programme only when "bye" input
        while(!entry.equals("bye")){
            System.out.println("    _____________________________________________");
            System.out.println("    " + entry);
            System.out.println("    _____________________________________________");

            entry = scanner.nextLine(); //updating variable to next input entry
        }

        //exit message when given input "bye"
        System.out.println("    _____________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________________");
    }
}
