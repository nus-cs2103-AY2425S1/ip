import java.util.Scanner;
public class Shnoop {
    private boolean completion = false;
    private String mode;
    private Task[] tasks;
    private int arrPointer;
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
            tasks = new Task[100];
            arrPointer = 0;
        } else {
            mode = "echo";
        }
    }

    public void addTask(Task task) {
        tasks[arrPointer++] = task;
    }

    /**
     * Determines if a String can be converted into an Integer
     *
     * @param str String to be converted
     * @return True if String is an Integer
     */
    public boolean canBeInteger(String str) {
        if (str == null || str == "") {
            return false;
        }

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Converts String to Integer if it can be converted.
     *
     * @param str String to be converted
     * @return Integer form of String, otherwise, arbitrary Integer Maximum Value
     */
    public int convertStrToInteger(String str) {
        if (canBeInteger(str)) {
            return Integer.parseInt(str);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Returns String representing action done.
     * Performs a series of actions depending on input and mode.
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
                System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
                completion = true;
                return "exit";
            default:
                System.out.println("\n✿ Shnoop ✿: " + input);
                return "echo";
            }

        // For Level-2 Add, List mode.
        case "todo":
            // For mark specific
            if (input.length() >= 6 && input.substring(0, 5).equals("mark ")) {
                if (canBeInteger(input.substring(5, input.length()))) {
                    boolean result = tasks[convertStrToInteger(input.substring(5, input.length())) - 1].markTask();
                    if (result) {
                        System.out.println("✿ Shnoop ✿: Warm, wet and wild! I've marked this task as done: ");
                    } else {
                        System.out.println("✿ Shnoop ✿: Daisy dukes! This task was already done my love: ");
                    }
                    System.out.println(tasks[convertStrToInteger(input.substring(5, input.length())) - 1]
                            .getTaskWithStatus());
                    return "mark_task";
                }
            } else if (input.length() >= 8 && input.substring(0, 7).equals("unmark ")) {
                if (canBeInteger(input.substring(7, input.length()))) {
                    boolean result = tasks[convertStrToInteger(input.substring(7, input.length())) - 1].unmarkTask();
                    if (result) {
                        System.out.println("✿ Shnoop ✿: Melted this popsicle! I've unmarked this task as done: ");
                    } else {
                        System.out.println("✿ Shnoop ✿: Daisy dukes! This task was never done my love: ");
                    }
                    System.out.println(tasks[convertStrToInteger(input.substring(7, input.length())) - 1]
                            .getTaskWithStatus());
                    return "mark_task";
                }
            }

            // Other than marks
            switch (input) {
            case "bye":
                System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
                completion = true;
                return "exit";
            case "list":
                System.out.println("✿ Shnoop ✿: Find, fresh, fierce and ready.");
                for (int i = 0; i < tasks.length; i ++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + tasks[i].getTaskWithStatus());
                }
                return "list";
            default:
                String x = "";
                int x1 = arrPointer % 3;
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

                addTask(new Task(input));
                System.out.println("✿ Shnoop ✿: " + x + " I'll add that in for ya. \nTask Added: " + input);
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
            input = scanner.nextLine();
            String result = shnoop.parseInput(input);
        }
    }
}
