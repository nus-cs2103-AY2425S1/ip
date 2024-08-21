import java.util.Scanner;

public class Joe {
    private static final String line =
            "____________________________________________________________";
    private static String[] userInputs = new String[100];
    private static int inputs = 0;
    private static void bye() {
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }

    public static void echo() {
        Scanner reader = new Scanner(System.in);
        int count = 0;
        String userCmd = reader.nextLine();
        String joesThoughts = null;

        while (!userCmd.equalsIgnoreCase("bye")) {
            count++;
            switch (count) {
                case 1:
                    joesThoughts = "Joe: I'm just an echo chamber...";
                    break;
                case 5:
                    joesThoughts =  "Joe: I'm being overworked here ;~;";
                    break;
                case 10:
                    joesThoughts = "Joe: Please type bye";
                    break;
                case 15:
                    System.out.printf("%s\nJoe: I'm shutting this operation down!\n", line);
                    break;
                default:
                    joesThoughts = null;
            }

            if (count == 15) {
                break;
            }

            if (joesThoughts != null) {
                System.out.printf("%s\n%s\n%s\n%s\n", line, userCmd, joesThoughts, line);
            } else {
                System.out.printf("%s\n%s\n%s\n", line, userCmd, line);
            }

            userCmd = reader.nextLine();
        }
        bye();
    }

    public static void add(String s) {
        System.out.println(line);
        if (inputs < 100) {
            userInputs[inputs++] = s;
            System.out.printf("added: %s\n", s);
        } else {
            System.out.println("Input has reached max capacity!");
        }
        System.out.println(line);
    }

    public static void list() {
        System.out.println(line);
        for (int i = 0; i < inputs; i++) {
            System.out.printf("%d. %s\n", i+1, userInputs[i]);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        System.out.printf("%s\nHello! I'm Joe\nWhat can I do for you?\n%s\n",
                line, line);
        Scanner reader = new Scanner(System.in);
        String userIn = reader.nextLine();
        while (!userIn.equalsIgnoreCase("bye")) {
            if (userIn.equalsIgnoreCase("list")) {
                list();
            } else {
                add(userIn);
            }
            userIn = reader.nextLine();
        }

        bye();
    }
}
