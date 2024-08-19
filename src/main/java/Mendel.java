import java.util.Scanner;

public class Mendel {
    public static void main(String[] args) {
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
        String currAction = "hello";
        TaskStorage taskStorage = new TaskStorage();
        new Welcome().speak();
        while (!currAction.equals("bye")) {
            Scanner sc = new Scanner(System.in);
            currAction = sc.nextLine();
            if (currAction.equals("bye")) {
                new Leave().speak();
            } else if (currAction.equals("list")){
                taskStorage.speak();
            } else {
                taskStorage.add(currAction);
            }

        }

    }
}
