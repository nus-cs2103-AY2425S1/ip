import java.util.Scanner;
import java.util.InputMismatchException;
import Components.StorageList;

public class FlyChat {
    private static final String lineBreak = "\n--------------------\n";
    private static StorageList storageList = new StorageList();

    /*  Scanner declared here as any instance of closing a Scanner(System.in) instance also closes
        System.in, so we should only declare 1 scanner instance in main */
    private static Scanner userInput = new Scanner(System.in);
    private static boolean operatingStatus = true;
    public static void main(String[] args) {
        start();
        while(operatingStatus) {
            try {
                listManipulation();
            } catch(InputMismatchException e) {
                System.out.println(lineBreak);
                System.out.println("I'm not sure what task you want me to do :((");
                System.out.println(lineBreak);
            }
        }
        end();
    }

    private static void start() {
        System.out.println(lineBreak);
        System.out.println("Hello! I'm FlyChat\nWhat can I do for you?");
        System.out.println(lineBreak);
    }

    private static void end() {
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }

    private static void listManipulation() throws InputMismatchException {
        String inputString = userInput.nextLine();

        //while loop continues scanning until "bye" is typed by user
        while (!inputString.equals("bye")) {
            //when user types "list", list of tasks is returned
            if(inputString.equals("list")) {
                System.out.println(lineBreak);
                storageList.announceItems();
                System.out.println(lineBreak);
            //when user types "mark [num]", the task with index num is marked as done
            } else if (inputString.startsWith("mark ")) {
                System.out.println(lineBreak);
                storageList.mark(Integer.valueOf(inputString.replaceAll("[^0-9]", "")));
                System.out.println(lineBreak);
            //when user types "unmark [num]", the task with index num is marked as not done
            } else if (inputString.startsWith("unmark ")) {
                System.out.println(lineBreak);
                storageList.unmark(Integer.valueOf(inputString.replaceAll("[^0-9]", "")));
                System.out.println(lineBreak);
            //when user types todo/deadline/event at the start, a new corresponding task is created
            } else if (inputString.startsWith("todo") || inputString.startsWith("deadline") || 
                        inputString.startsWith("event")) {
                System.out.println(lineBreak);
                storageList.addTask(inputString);
                System.out.println(lineBreak);
            } else {
                throw new InputMismatchException("Invalid input command");
            }
            inputString = userInput.nextLine();
        }
        operatingStatus = false;
    }

}
