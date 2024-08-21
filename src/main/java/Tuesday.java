import java.util.Scanner;  // Import the Scanner class

public class Tuesday {
    private static String[] listArray = new String[100];
    private static int count = 0;
    private static String Msg_welcome() {
        return "_______________________________\n"
                + "Hello! I'm Tuesday, a randomly created bot.\n"
                + "What can I do for you?\n"
                + "_______________________________";
    }
    private static String Msg_bye() {
        return "_______________________________"
                + "\nBye bye. Hope to see you again soon!\n"
                + "_______________________________";
    }
    private static String Msg_list() {
        String message = "";
        for (int n = 0; n < count; n++) {
            message += (n+1) +". "+ listArray[n] + "\n";
        }
        return "_______________________________\n"
                + message
                + "_______________________________";
    }
    private static String Msg_blah() {
        return "_______________________________\n"
                + "blah\n"
                + "_______________________________";
    }

    private static String add_stuff(String item) {
        listArray[count] = item;
        count++;
        return "_______________________________\n"
                + "added: " + item
                + "\n_______________________________";
    }

    public static void main(String[] args) {
        System.out.println(Msg_welcome());
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "";
        //String[] userInputArr;
        while (true) {
            userInput = myObj.nextLine();  // Read user input
            //userInputArr = userInput.split(" ", 2);
            if (userInput.equals("bye"))
                break;

            if (userInput.equals("list")) {
                System.out.println(Msg_list());
            } else if (userInput.equals("blah")) {
                System.out.println(Msg_blah());
            } else {
                System.out.println(add_stuff(userInput));
            }
        }
        System.out.println(Msg_bye());
    }
}
