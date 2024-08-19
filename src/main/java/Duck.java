import java.util.Scanner;

public class Duck {
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

    public static void getInputTillBye() {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        while (!message.equalsIgnoreCase("bye")) {

            System.out.println(message + "\n");
            in = new Scanner(System.in);
            message = in.nextLine();
        }
    }
}
