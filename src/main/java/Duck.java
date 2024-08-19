import java.util.ArrayList;
import java.util.Scanner;

public class Duck {
    static ArrayList<String> ls = new ArrayList<>();
    public static void main(String[] args) {
        String logo = """
                        ,---,                                  ,-. \s
                      .'  .' `\\                            ,--/ /| \s
                    ,---.'     \\          ,--,           ,--. :/ | \s
                    |   |  .`\\  |       ,'_ /|           :  : ' /  \s
                    :   : |  '  |  .--. |  | :    ,---.  |  '  /   \s
                    |   ' '  ;  :,'_ /| :  . |   /     \\ '  |  :   \s
                    '   | ;  .  ||  ' | |  . .  /    / ' |  |   \\  \s
                    |   | :  |  '|  | ' |  | | .    ' /  '  : |. \\ \s
                    '   : | /  ; :  | : ;  ; | '   ; :__ |  | ' \\ \\\s
                    |   | '` ,/  '  :  `--'   \\'   | '.'|'  : |--' \s
                    ;   :  .'    :  ,      .-./|   :    :;  |,'    \s
                    |   ,.'       `--`----'     \\   \\  / '--'      \s
                    '---'                        `----'            \s
                """;


        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duck");
        System.out.println("What can I do for you? \n");

        getInputTillBye();
        System.out.println("Bye. Hope to see you again soon!");

    }

    // obtain user input till he inputs bye, ignoring case
    public static void getInputTillBye() {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        while (!message.equalsIgnoreCase("bye")) {
            reactTo(message);
            in = new Scanner(System.in);
            message = in.nextLine();
        }
    }

    public static void reactTo(String message) {
        if (message.equalsIgnoreCase("list")) {
            int idx = 1;
            for (String str : ls) {
                System.out.println(idx++ + ". " + str);
            }
        } else {
            ls.add(message);
            System.out.println("added: " + message);
        }
    }


}
