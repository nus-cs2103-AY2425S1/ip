import java.util.Scanner;

public class Mendel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
        String currAction = "hello";
        TaskManager taskManager = new TaskManager();
        new Welcome().speak();
        while (!currAction.equals("bye")) {
            currAction = sc.nextLine();
            taskManager.manage(currAction);
        }
    }
}
