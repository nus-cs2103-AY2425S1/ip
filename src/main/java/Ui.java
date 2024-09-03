import java.util.Scanner;
public class Ui {
    private final static String TOOTHLESS_LOGO =
            """
                     _____            _   _     _
                    |_   _|___   ___ | |_| |__ | | ___  ___ ___
                      | |/ _ \\ / _ \\| __| '_ \\| |/ _ \\/ __/ __|
                      | | (_) | (_) | |_| | | | |  __/\\__ \\__ \\
                      |_|\\___/ \\___/ \\__|_| |_|_|\\___||___/___/
                    """;
    private final static String DIVIDER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
    private final Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void welcome() {
        System.out.println("Welcome to the dragon's den!\n" + TOOTHLESS_LOGO);
        System.out.println("Toothless:\n" +
                "Greetings, Dragon Rider!\n\n" +
                "I'm Toothless, your friendly dragon companion.\n" +
                "What adventure shall we embark on today?\n\n" + DIVIDER);

    }

    public void greeting() {
        System.out.println("Toothless:\nHello! I'm Toothless\nHow can I help today dragon rider?\n\n" + DIVIDER);
    }

    public void bye() {
        System.out.println("Toothless:\n" +
                "Until next time, dragon rider!\n" +
                "Toothless the Night Fury, signing off.\n" +
                "See you soon!\n\n" + DIVIDER);
    }

    public void addTaskMessage(Task task, int size) {
        System.out.println("Toothless:\nYour task\n\t\t" +
                task +
                "\nadded to the quest board!\n\n" +
                "Now there is " + size + " quests in your quest board.\n\n" + DIVIDER);
    }

    public void unknownCommand() {
        System.out.println("Toothless:\nI'm sorry, I do not understand what you mean.\n" +
                "Please enter a valid command.\n\n" + DIVIDER);
    }
}
