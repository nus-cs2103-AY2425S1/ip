import java.util.Scanner;
public class Chatterbox {
    final static String botName = "Chatterbox";
    public static String greeting() {
        return String.format("""
 ____________________________________________________________
 Hello! I'm %s
 What can I do for you?
____________________________________________________________
""", Chatterbox.botName);
    }

    public static String goodBye() {
        return """
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
                """;
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting());
        String input = "";
        Boolean done = false;

        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            } else {
                System.out.println(response);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println(goodBye());




    }
}
