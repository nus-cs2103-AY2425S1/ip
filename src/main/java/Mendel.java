import java.util.Scanner;

public class Mendel {
    public static void main(String[] args) {
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
        String currAction = "hello";
        new Welcome().speak();
        while (!currAction.equals("bye")) {
            Scanner sc = new Scanner(System.in);
            currAction = sc.nextLine();
            if (currAction.equals("bye")) {
                new Leave().speak();
            } else {
                new TaskStorage(currAction).speak();
            }

        }

    }
}
