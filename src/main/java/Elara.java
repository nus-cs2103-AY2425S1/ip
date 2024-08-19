import java.util.Scanner;

public class Elara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int count = 0;

        System.out.println("Hello! I'm Elara");
        System.out.println("What can I do for you?");

        while (true) {
            String text = scanner.nextLine();
            if (text.toLowerCase().strip().equals("bye")) {
                break;
            } else if (text.toLowerCase().strip().equals("list")) {
                for (int i = 0; i < count; i++) {
                    if (list[i] == null) {
                        break;
                    }
                    int j = i + 1;
                    System.out.println(j + ". " + list[i]);
                }
            } else {
                System.out.println("added: " + text);
                list[count] = text;
                count++;
            }
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
