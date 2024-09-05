import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    private String[] quotes = new String[] {
        "You're unforgettable.",
                "Coded, tanned, fit and ready.",
                "You're undeniable."
    };

    /**
     * Prints out introductory speech at start of application.
     */
    public void showWelcome() {
        System.out.println("\n ... Greetings loved one ʚ♡ɞ Let's take a journey ... \n\n\n ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-"
                + "✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n"
                + " ✿ It's Shnoop, my dawg. I'm all up on ya. Whatchu need? ✿ \n"
                + " ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n");
    }

    public void showLoadingError() {
        System.out.println(" ✿ Shnoop is currently experiencing some difficulties, let's have a fresh start. ✿ \n");
    }

    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    public void goodbye() {
        System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
    }

    /**
     * Returns a quote from the quote bank.
     *
     * @param idx Should be a changing number.
     * @return Quote from quote bank.
     */
    public String getRandomQuote(int idx) {
        return quotes[idx];
    }

    public void addTask(Task task, int size) {
        System.out.println("✿ Shnoop ✿: " + x + " I'll add that in for ya. \nTask Added: " + task);
        System.out.println("✿ Shnoop ✿: You've got " + size + " doggy-dogs on the stereo.");
    }
}
