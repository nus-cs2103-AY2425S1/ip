import java.util.Scanner;

public class ScoobyDoo {
    public static final String name = "Scooby-Doo";
    public static void main(String[] args) {


        String greet = String.format("Hello! I'm %s\nWhat can I do for you?", name);
        printFormattedResponse(greet);
        String input = "";
        Scanner scanIn = new Scanner(System.in);
        while (true) {
            input = scanIn.nextLine();
            if (input.equals("bye")) {
                printFormattedResponse("Bye. Hope to see you again soon!");
                break;
            }
            printFormattedResponse(input);
        }
        scanIn.close();
    }

    //will auto break
    public static void printFormattedResponse(String response) {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
        System.out.println(response);
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println("\n");

    }

    public static String getUserInput() {
        String input;
        Scanner scanIn = new Scanner(System.in);
        input = scanIn.nextLine();
        scanIn.close();
        return input;
    }

}

