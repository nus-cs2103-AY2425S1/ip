import java.util.Scanner;

public class FRIDAY {
    private Boolean isActive;
    private String userInput, output, greeting, exitMessage, divider;
    private String[] storage = new String[100];
    private int storagePointer = 0;

    public FRIDAY() {
        //divider
        divider = "";
        for (int i = 0; i < 40; i++) {
            divider += "-";
        }
        divider += "\n";

        //preset messages
        greeting = divider + "Hello! I'm FRIDAY\nWhat can I do for you?\n" + divider;
        exitMessage = divider + "Bye. Hope to see you again soon!\n" + divider;

        //variable to store user input
        userInput = "";

        //variable to store output
        output = "";
    }

    public void start() {
        //initialize scanner object
        Scanner scanner = new Scanner(System.in);

        //start the bot
        isActive = true;

        //bot running
        System.out.println(greeting);
        while(isActive) {
            userInput = scanner.nextLine();
            //the keyword bye triggers exit bot
            if(userInput.toLowerCase().equals("bye")) {
                System.out.println(exitMessage);
                isActive = false;
                break;
            }
            if(userInput.toLowerCase().equals("list")) {
                display();
                continue;
            }
            add(userInput);
        }
    }

    //function to add string to storage array
    public void add(String input) {
        //add the input to the array
        storage[storagePointer] = input;
        storagePointer += 1;
        output = divider + "added: " + input + "\n" + divider;
        System.out.println(output);
    }

    //function to display storage in list format
    public void display() {
        String displayList = divider;
        for(int i = 1; i <= storagePointer; i++) {
            displayList += i + ". " + storage[i - 1] + "\n";
        }
        displayList += divider;
        output = displayList;
        System.out.println(output);
    }

    public static void main(String[] args) {
        FRIDAY bot = new FRIDAY();
        bot.start();
    }
}
