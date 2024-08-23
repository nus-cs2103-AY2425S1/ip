import java.util.Scanner;
import Components.*;

public class FlyChat {
    private static final String lineBreak = "\n--------------------\n";
    private static StorageList storageList = new StorageList();
    public static void main(String[] args) {
        start();
        listManipulation();
        end();
    }

    private static void start() {
        System.out.println(lineBreak);
        System.out.println("Hello! I'm FlyChat\nWhat can I do for you?\n");
        System.out.println(lineBreak);
    }

    private static void end() {
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }

    private static void listManipulation() {
        Scanner userInput = new Scanner(System.in);
        String inputString = userInput.nextLine();
        while (!inputString.equals("bye")) {
            if(inputString.equals("list")) {
                System.out.println(lineBreak);
                storageList.announceItems();
                System.out.println(lineBreak);
            } else {
                storageList.add(inputString);
                System.out.println(lineBreak);
                System.out.println("added: " + inputString);
                System.out.println(lineBreak);
            }
            inputString = userInput.nextLine();
        }
        userInput.close();
    }

}
