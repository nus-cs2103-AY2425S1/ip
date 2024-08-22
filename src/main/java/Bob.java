import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String blankline = "____________________________________________________________ \s";
        String skeleton = """
                ____________________________________________________________ \s
                 Hello! I'm Bob \s
                 What can I do for you? \s
                ____________________________________________________________ \s
                """;
        System.out.println(skeleton);

        String[] taskList = new String[100];
        Scanner scanner = new Scanner(System.in);
        int position = 0;
        
        boolean exit = false;
        while (!exit) {
            System.out.print("Text: ");
            String input = scanner.nextLine();

            // check for the input case
            switch (input.toLowerCase()) {
                case "bye":
                    exit = true;
                    break;
                case "list":
                    String retString = "";
                    for (int i = 0; i < position; i++) {
                        retString += taskList[i] + "\n";
                    }
                    System.out.println(retString + "\n" + blankline);
                    break;
                default:
                    taskList[position] = position + ". " + input;
                    System.out.println("added: " + input + "\n" + blankline);
                    position++;
                    break;
            }
        }
        System.out.println(blankline + "\n" + "Bye. Hope to see you again soon!");

    }
}
