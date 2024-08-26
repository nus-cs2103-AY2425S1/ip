import java.util.Scanner;

public class Dave {

    public static void main(String[] args) {
        String logo = " ____    _    __     ______\n"
                + "|  _ \\  / \\   \\ \\   / / ___|\n"
                + "| | | |/ _ \\   \\ \\ / /|  _|\n"
                + "| |_| / ___ \\   \\ V / | |___\n"
                + "|____/_/   \\_\\   \\_/  |_____|\n";
        String horizontal = "__________________________________________________________";
        String[] dataList = new String[100];
        Boolean[] booleanList = new Boolean[100];
        int dataIndex = 0;
        String state;
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal);
        System.out.println("Hello! I'm Dave.");
        System.out.println("What can I do for you?");
        System.out.println(horizontal);
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true)
        {
            userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                continue;
            }

            switch (userInput) {
                case "bye":
                    System.out.println(horizontal);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(horizontal);
                    scanner.close();
                    return;

                case "list":
                    System.out.println(horizontal);
                    for (int i = 0; i < dataIndex; i++) {
                        state = booleanList[i] == null ? "" : "X";
                        String statement = String.format("%d.[%s] %s", i+1, state, dataList[i]);
                        System.out.println(statement);
                    }
                    System.out.println(horizontal);
                    break;

                default:

                    dataList[dataIndex] = userInput;
                    booleanList[dataIndex] = true;
                    dataIndex ++;
                    System.out.println(horizontal);
                    System.out.printf("added: %s%n", userInput);
                    System.out.println(horizontal);
                    break;
            }
        }
    }
}
