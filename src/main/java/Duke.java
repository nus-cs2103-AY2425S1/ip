import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String separationLine = "_________________________________________";
        String chatBotName = "Sughandi";

        System.out.println(separationLine);
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println(separationLine);

        while(true) {
            String instruction = scanner.nextLine();
            switch (instruction.toUpperCase()) {
                case "LIST":
                    System.out.println("list");
                    System.out.println(separationLine);
                    break;
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(separationLine);
                    break;
                default:
                    System.out.println("blah");
                    System.out.println(separationLine);
                    break;
            }
            if (instruction.equalsIgnoreCase("bye")) {
                break;
            }
        }

        scanner.close();
    }
}
