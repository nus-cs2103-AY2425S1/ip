import java.util.Scanner;
public class Shnoop {
    private boolean completion = false;
    private String mode;
    private String[] strArr;
    private int strArrPointer;
    public Shnoop() {
    }

    /**
     * Configures class based on desired mode.
     *
     * @param input String to indicate mode.
     */
    public Shnoop(String input) {
        if (input == "todo") {
            mode = "todo";
            strArr = new String[100];
            strArrPointer = 0;
        } else {
            mode = "echo";
        }
    }

    public void addTask(String input) {
        strArr[strArrPointer++] = input;
    }

    /**
     * Returns String representing action done.
     *
     * @param input Input given by user.
     * @return String action code.
     */
    public String parseInput(String input) {
        switch (mode) {
        // For Level-1 echo mode.
        case "echo":
            switch (input) {
            case "bye":
                System.out.println("\n✿ Snoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
                completion = true;
                return "exit";
            default:
                System.out.println("\n✿ Snoop ✿: " + input);
                return "echo";
            }
        // For Level-2 Add, List mode.
        case "todo":
            switch (input) {
            case "bye":
                System.out.println("\n✿ Snoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
                completion = true;
                return "exit";
            case "list":
                System.out.println("✿ Snoop ✿: Find, fresh, fierce and ready.");
                for (int i = 0; i < strArr.length; i ++) {
                    if (strArr[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + strArr[i]);
                }
                return "list";
            default:
                String x = "";
                int x1 = strArrPointer % 3;
                switch (x1) {
                case 1:
                    x = "You're unforgettable.";
                    break;
                case 2:
                    x = "Toned, tanned, fit and ready.";
                    break;
                default:
                    x = "You're undeniable.";
                }

                addTask(input);
                System.out.println("✿ Snoop ✿: " + x + " I'll add that in for ya. \nTask Added: " + input);
                return "add_task";
            }
        default:
            return "empty_input_bug";
        }


    }

    /**
     * Prints out introductory speech at start of application.
     */
    public void startIntroSpeech() {
        System.out.println("\n ... Greetings loved one ʚ♡ɞ Let's take a journey ... \n\n\n ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-"
                + "✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n"
                + " ✿ It's Shnoop, my dawg. I'm all up on ya. Whatchu need? ✿ \n"
                + " ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n");
    }

    public boolean isCompleted() {
        return completion;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Shnoop shnoop = new Shnoop("todo");

        shnoop.startIntroSpeech();

        while (!shnoop.isCompleted()) {
            input = scanner.next();
            String result = shnoop.parseInput(input);
        }
    }
}
