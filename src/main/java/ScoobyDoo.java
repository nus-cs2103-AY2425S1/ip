
import java.util.Scanner;

public class ScoobyDoo {
    public static final String name = "Scooby-Doo";
    private static final String[] list = new String[100];
    private static int listNum = 0;

    public static void main(String[] args) {
        //greeting
        printFormattedResponse(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        //loop
        String input;
        Scanner scanIn = new Scanner(System.in);
        while (true) {
            input = scanIn.nextLine();
            if (input.equals("bye")) {
                printFormattedResponse("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                String listString = "";
                for (int i = 0; i < listNum; i++) {
                    listString = listString.concat( String.format("%d. %s\n", i + 1, list[i]));
                }
                printFormattedResponse(listString);
                continue;
            }

            if (checkListSize(listNum)) {
                addTask(input);
                printFormattedResponse("Added: " + input);
            }
            else {
                printFormattedResponse("Too many tasks !!!");
            }
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

    //return true if list size less than 100
    private static boolean checkListSize(int num) {
        return !(num > 100);
    }

    private static void addTask(String task) {
        list[listNum] = task;
        listNum++;
    }
}

