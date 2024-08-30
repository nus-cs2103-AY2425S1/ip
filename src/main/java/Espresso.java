import java.util.Scanner;
public class Espresso {
    public static void main(String[] args) {
        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Espresso");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(str);
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
