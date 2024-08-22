import java.util.Scanner;

public class Timo {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("Hello! I'm Timo\nWhat can I do for you?");
        System.out.println("----------------------------");

        //Scanner to receive input
        Scanner echo = new Scanner(System.in);

        //initialise array to store the values
        String[] arr = new String[100];
        int index = 1;


        String input = "";

        //boolean to know whether input = bye
        boolean is_bye = false;
        while (!is_bye) {
            input = echo.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("----------------------------");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("----------------------------");
                    is_bye = true;
                    break;

                case "list":
                    System.out.println("----------------------------");
                    for (int i = 1; i < index; i++) {
                        System.out.println(i + ". " + arr[i - 1]);
                    }
                    System.out.println("----------------------------");
                    break;

                default:
                    System.out.println("----------------------------");
                    System.out.println("added: " + input);
                    System.out.println("----------------------------");
                    arr[index - 1] = input;
                    index++;
                    break;
            }
        }



    }
}
