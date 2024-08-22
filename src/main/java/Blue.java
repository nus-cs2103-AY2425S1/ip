import java.util.Scanner;
public class Blue {

    public static void farewell() {
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("_     /)---(\\          /~~~\\");
        System.out.println("\\\\   (/ . . \\)        /  .. \\");
        System.out.println(" \\\\__)-\\(*)/         (_,\\  |_)");
        System.out.println(" \\_       (_         /   \\@/    /^^^\\");
        System.out.println(" (___/-(____) _     /      \\   / . . \\");
        System.out.println("              \\\\   /  `    |   V\\ Y /V");
        System.out.println("               \\\\/  \\   | _\\    / - \\");
        System.out.println("                \\   /__'|| \\\\_  |    \\");
        System.out.println("                 \\_____)|_).\\_).||(__V");
        System.out.println("--------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Note note = new Note();
        int count = 0;

        // Greet the user
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Blue! Woof Woof! Yap Yap!");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------");

        String input = "";

        while (true) {
            input = scanner.nextLine();

            //if bye is typed, break out of loop
            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            //if list is typed, print out corresponding list of tasks
            if (input.equalsIgnoreCase("list")) {
                note.printList();
                continue;
            }

            if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    note.mark(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'mark <number>'.");
                }
                continue;
            }

            if (input.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    note.unmark(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number format! Please use 'mark <number>'.");
                }
                continue;
            }

            //print out added... and add the item to the myList field in the Note object
            note.addToList(input);
            System.out.println("--------------------------------------------");
            System.out.printf("added: %s%n", input);
            System.out.println("--------------------------------------------");
        }

        // Farewell message
        Blue.farewell();
        // Close the scanner
        scanner.close();
    }
}
