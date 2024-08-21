import java.util.Scanner;

public class FRIDAY {
    private Boolean isActive;
    private String userInput, output, greeting, exitMessage, divider;
    private Task[] storage = new Task[100];
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
            //collect user input
            userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            String keyword = parts[0];

            //keywords trigger respective actions
            switch(keyword) {
                //keywords
                case("mark"):
                    storage[Integer.valueOf(parts[1]) - 1].check();
                    String marked = divider + "Nice! I've marked this task as done\n" + storage[Integer.valueOf(parts[1]) - 1] + "\n" + divider;
                    System.out.println(marked);
                    break;
                case("unmark"):
                    storage[Integer.valueOf(parts[1]) - 1].uncheck();
                    String unmarked = divider + "Ok, I've marked this task as not done yet\n" + storage[Integer.valueOf(parts[1]) - 1] + "\n" + divider;
                    System.out.println(unmarked);
                    break;
                case("bye"):
                    System.out.println(exitMessage);
                    isActive = false;
                    break;
                case("list"):
                    display();
                    break;

                //if there is no input then nothing added to list
                case(""):
                    break;
                //to handle all normal inputs less empty strings
                default:
                    Task newItem = new Task(userInput);
                    add(newItem);
            }
/*
            //the keyword bye triggers exit bot
            if(userInput.toLowerCase().equals("bye")) {
                System.out.println(exitMessage);
                isActive = false;
                break;
            }

            //keyword list should display the full list
            if(userInput.toLowerCase().equals("list")) {
                display();
                continue;
            }
 */
        }
    }

    //function to add string to storage array
    public void add(Task input) {
        //add the input to the array
        storage[storagePointer] = input;
        storagePointer += 1;
        output = divider + "added: " + input.getDescription() + "\n" + divider;
        System.out.println(output);
    }

    //function to display storage in list format
    public void display() {
        String displayList = divider;
        for(int i = 1; i <= storagePointer; i++) {
            displayList += i + ". " + storage[i - 1] + "\n";
        }
        displayList += divider;
        output = "Here are the tasks in your list:\n" + displayList;
        System.out.println(output);
    }

    public void markAsDone(Task task, int index) {
        Task markAsDone = storage[index];
        markAsDone.check();
    }

    public void markAsUndone(Task task, int index) {
        Task markAsUndone = storage[index];
        markAsUndone.uncheck();
    }

    public static void main(String[] args) {
        FRIDAY bot = new FRIDAY();
        bot.start();
    }
}
