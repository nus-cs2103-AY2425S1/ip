import java.util.Scanner;

public class Talker {
    public static void main(String[] args) {
        String name = "Talker";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner reader = new Scanner(System.in);

        String[] list = new String[100];
        int pointer = 0;

        while (true) {
            String input = reader.nextLine();

            System.out.println(line);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < pointer; i++) {
                    System.out.printf("%d. %s\n", i+1, list[i]);
                }
                System.out.println(line);
            } else {
                list[pointer] = input;
                pointer++;
                System.out.printf("added: %s\n", input);
                System.out.println(line);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(line);
    }
}
