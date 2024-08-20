import java.util.Scanner;  // Import the Scanner class

public class Tuesday {
    public static String Msg_welcome() {
        return "_______________________________\n"
                + "Hello! I'm Tuesday, a randomly created bot.\n"
                + "What can I do for you?\n"
                + "_______________________________";
    }
    public static String Msg_bye() {
        return "Bye bye. Hope to see you again soon!\n"
                + "_______________________________";
    }
    public static String Msg_list() {
        return "list\n"
                + "_______________________________";
    }
    public static String Msg_blah() {
        return "blah\n"
                + "_______________________________";
    }

    public static void main(String[] args) {
        System.out.println(Msg_welcome());
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userName = "";
        while (!userName.equals("bye")) {
            userName = myObj.nextLine();  // Read user input
            //System.out.println(userName);
            if (userName.equals("list")) {
                System.out.println(Msg_list());
            } else if (userName.equals("blah")) {
                System.out.println(Msg_blah());
            }
        }
        System.out.println(Msg_bye());
    }
}
