public class Ui {
    public Ui() {}

    protected static void print(String str) {
        System.out.println("    " + str);
    }

    protected static void printGreeting() {
        Ui.printLine();
        print("Hello! I'm The BotFather");
        print("I’m gonna make you an offer you can’t refuse.");
        Ui.printHorse();
        Ui.printLine();
    }

    protected static void printLine() {
        print("—————————————————————————————————————————————————————————————————");
    }

    protected static void printGoodBye() {
        print("What are you worried about, if I wanted to kill you, you'd be dead already.");
    }

    private static void printHorse() {
        System.out.println("\t                                 |\\    /|");
        System.out.println("\t                              ___| \\,,/_/");
        System.out.println("\t                           ---__/ \\/    \\");
        System.out.println("\t                          __--/     (D)  \\");
        System.out.println("\t                          _ -/    (_      \\");
        System.out.println("\t                         // /       \\_ /  -\\");
        System.out.println("\t   __-------_____--___--/           / \\_ O o)");
        System.out.println("\t  /                                 /   \\__/");
        System.out.println("\t /                                 /");
        System.out.println("\t||          )                   \\_/\\");
        System.out.println("\t||         /              _      /  |");
        System.out.println("\t| |      /--______      ___\\    /\\  :");
        System.out.println("\t| /   __-  - _/   ------    |  |   \\ \\");
        System.out.println("\t |   -  -   /                | |     \\ )");
        System.out.println("\t |  |   -  |                 | )     | |");
        System.out.println("\t  | |    | |                 | |    | |");
        System.out.println("\t  | |    < |                 | |   |_/");
        System.out.println("\t  < |    /__\\                <  \\");
        System.out.println("\t  /__\\                       /___\\");
    }
}
