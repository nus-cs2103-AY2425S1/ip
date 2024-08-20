import java.util.ArrayList;
import java.util.Scanner;

public class SilverWolf {
    // using arraylist
    private static ArrayList<String> list = new ArrayList<>();

    // output the list
    public static void outputList(){
        int index = 1;
        for(String str : list){
            System.out.println(index + ". "+list.get((index-1)));
            index++;
        }
    }

    public static void main(String[] args) {
        // initialize the scanner to read user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("  _________.__.__                       __      __      .__   _____ \n");
        System.out.print(" /   _____/|__|  |___  __ ___________  /  \\    /  \\____ |  |_/ ____\\\n");
        System.out.print(" \\_____  \\ |  |  |\\  \\/ // __ \\_  __ \\ \\   \\/\\/   /  _ \\|  |\\   __\\ \n");
        System.out.print(" /        \\|  |  |_\\   /\\  ___/|  | \\/  \\        (  <_> )  |_|  |   \n");
        System.out.print("/_______  /|__|____/\\_/  \\___  >__|      \\__/\\  / \\____/|____/__|   \n");
        System.out.print("        \\/                   \\/               \\/                    ");

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Silver Wolf\n" +
                " What can I help you with?\n" +
                "____________________________________________________________\n");
        while(true) {
            // reading the user input
            String input = scanner.nextLine();

            // exit if the user types "bye" or "list"
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Till we meet again!");
                System.out.println("____________________________________________________________");
                break;
                // display the list that the user entered
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                outputList();
                System.out.println("____________________________________________________________");

            } else {
                // echo the input back to the user
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                list.add(input);
                System.out.println("____________________________________________________________");

            }
        }

        // close the scanner
        scanner.close();

    }
}
