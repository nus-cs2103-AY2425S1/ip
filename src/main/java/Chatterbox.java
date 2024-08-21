import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
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
        String[] userList = new String[100];
        int current  = 0;

        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")){
                System.out.println("____________________________________________________________");

                for (int i  = 0; i < current; i++) {
                    System.out.println(String.format(i+1 +". %s", userList[i]));
                }
                System.out.println("____________________________________________________________");

            }else {
                userList[current] = response;
                current++;
                System.out.println("added: " + response);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println(goodBye());


        scanner.close();

    }
}
