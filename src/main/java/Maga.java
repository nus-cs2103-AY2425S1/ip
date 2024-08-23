import java.util.Scanner;

public class Maga {
    public static void main(String[] args) {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] arr = new String[100];
        int count = 0;
        while(!input.equalsIgnoreCase("bye")) {
            if(input.equalsIgnoreCase("list")) {
                for (int i = 0; i < count; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + arr[i]);
                }

                input = scanner.nextLine();
                continue;
            }

            arr[count] = input;
            count++;
            System.out.println("added: " + input);
            input = scanner.nextLine();
        }

        System.out.println("Yeah goodbye. Remember a vote for me is a vote for America!");
    }
}
