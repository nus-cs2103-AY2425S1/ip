import java.util.Scanner;
import java.util.ArrayList;

public class Dash {
    public static void main(String[] args) {
        //Initialise scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Initialise array list to store date
        ArrayList<Task> list = new ArrayList<>();

        //Greeting
        String logo = " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  ________    | || |      __      | || |    _______   | || |  ____  ____  | |\n" +
                "| | |_   ___ `.  | || |     /  \\     | || |   /  ___  |  | || | |_   ||   _| | |\n" +
                "| |   | |   `. \\ | || |    / /\\ \\    | || |  |  (__ \\_|  | || |   | |__| |   | |\n" +
                "| |   | |    | | | || |   / ____ \\   | || |   '.___`-.   | || |   |  __  |   | |\n" +
                "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  |`\\____) |  | || |  _| |  | |_  | |\n" +
                "| | |________.'  | || ||____|  |____|| || |  |_______.'  | || | |____||____| | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'";
        String horizontalLine = "________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");

        //Echo loop
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println(horizontalLine);
                    int length = list.size();
                    for (int i = 0; i < length; i++) {
                        Task t = list.get(i);
                        int num = i + 1;
                        System.out.println(num +". [" + t.getStatusIcon() + "] " + t);
                    }
                    System.out.println(horizontalLine);
                } else if (input.startsWith("mark")) {
                    String[] string = input.split(" ", 2);
                    int markNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (markNum > num || markNum < 1) {
                        System.out.println("Invalid index provided.");
                    } else {
                        Task t = list.get(markNum - 1);
                        t.markTaskAsDone();
                        System.out.println(horizontalLine);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("   ["+ t.getStatusIcon() + "] " + t);
                        System.out.println(horizontalLine);
                    }
                } else if (input.startsWith("unmark")) {
                    String[] string = input.split(" ", 2);
                    int unmarkNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (unmarkNum > num || unmarkNum < 1) {
                        System.out.println("Invalid index provided.");
                    } else {
                        Task t = list.get(unmarkNum - 1);
                        t.unmarkTask();
                        System.out.println(horizontalLine);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("   ["+ t.getStatusIcon() + "] " + t);
                        System.out.println(horizontalLine);
                    }
                } else {
                    System.out.println(horizontalLine);
                    Task t = new Task(input);
                    list.add(t);
                    System.out.println("added: " + t);
                    System.out.println(horizontalLine);
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        //Exit
        System.out.println(horizontalLine);
        String goodbye = "   ___                _ _                  _ \n" +
                "  / _ \\___   ___   __| | |__  _   _  ___  / \\\n" +
                " / /_\\/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\/  /\n" +
                "/ /_\\\\ (_) | (_) | (_| | |_) | |_| |  __/\\_/ \n" +
                "\\____/\\___/ \\___/ \\__,_|_.__/ \\__, |\\___\\/   \n" +
                "                              |___/          ";
        System.out.println(goodbye);
        System.out.println(horizontalLine);
    }
}