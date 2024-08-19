import java.util.Scanner;

public class Mendel {
    public static void main(String[] args) {
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
        String currAction = "hello";
        while (!currAction.equals("bye")) {
            if (currAction.equals("hello")) {
                new Welcome().speak();
            } else {
                new Task(currAction).speak();
            }
            Scanner sc = new Scanner(System.in);
            currAction = sc.nextLine();
        }
        new Leave().speak();
    }
}
