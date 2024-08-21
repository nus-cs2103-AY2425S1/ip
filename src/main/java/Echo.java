import java.util.Scanner;
import java.util.ArrayList;
public class Echo {
    public static void main(String[] args) {
        ArrayList<String> commands = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String userReply = "";
        System.out.println("-------------------------------------");
        System.out.println("\tHello: I'm Echo\n\tWhat can I do for you?");
        System.out.println("-------------------------------------");
        while (!userReply.equals("bye")) {
            userReply = input.nextLine();
            if (userReply.equals("bye")) {   //Terminate chatbot if user types in "bye"
                System.out.println("-------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("-------------------------------------");
            } else if (userReply.equals("list")){ //Iterate through the String array and list out the commands
                System.out.println("-------------------------------------");
                for(int i = 0; i < commands.size(); i ++) {
                    System.out.println("\t" + (i + 1) + ". " + commands.get(i));
                }
                System.out.println("-------------------------------------");
            } else { //Add the input provided into the String array and return a message "added:   "
                System.out.println("-------------------------------------");
                commands.add(userReply);
                System.out.println("\tadded: " + userReply);
                System.out.println("-------------------------------------");
            }
        }
    }
}
