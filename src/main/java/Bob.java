import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        String[] list;
        list = new String[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                for(int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                input = scanner.nextLine();
                continue;
            }
            System.out.println("added: " + input);
            list[count] = input;
            count++;
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
