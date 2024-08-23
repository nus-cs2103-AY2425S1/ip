import java.util.Scanner;
public class Shnoop {
    private boolean completion = false;
    public Shnoop() {
    }

    ;

    public String parseInput(String input) {
        switch (input) {
        case "bye":
            System.out.println("\n✿ Snoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
            completion = true;
            return "exit";

        default:
            System.out.println("\n✿ Snoop ✿: " + input);
            return "echo";
        }
    }

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
        Shnoop shnoop = new Shnoop();

        shnoop.startIntroSpeech();

        while (!shnoop.isCompleted()) {
            input = scanner.next();
            String result = shnoop.parseInput(input);
        }
    }
}
