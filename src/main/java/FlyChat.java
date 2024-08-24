import java.util.Scanner;
import Components.StorageList;

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

        //while loop continues scanning until "bye" is typed by user
        while (!inputString.equals("bye")) {
            //when user types "list", list of tasks is returned
            if(inputString.equals("list")) {
                System.out.println(lineBreak);
                storageList.announceItems();
                System.out.println(lineBreak);
            //when user types "mark [num]", the task with index num is marked as done
            } else if (inputString.matches("^mark [0-9]*")) {
                System.out.println(lineBreak);
                storageList.mark(Integer.valueOf(inputString.replaceAll("[^0-9]", "")));
                System.out.println(lineBreak);
            //when user types "unmark [num]", the task with index num is marked as not done
            } else if (inputString.matches("^unmark [0-9]*")) {
                System.out.println(lineBreak);
                storageList.unmark(Integer.valueOf(inputString.replaceAll("[^0-9]", "")));
                System.out.println(lineBreak);
            //when user types non-keyword phrases, a new task is created with the phrase as text
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
