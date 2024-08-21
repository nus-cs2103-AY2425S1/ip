public class KukiShinobu {
    private final String name = "Kuki Shinobu";

    public static void main(String[] args) {
        KukiShinobu kuki = new KukiShinobu();
        kuki.greet();
        kuki.goodbye();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        KukiShinobu.printHorizontalLine();
        System.out.println("Hello! I'm " + this.name + "!");
        System.out.println("What can I do for you?");
        KukiShinobu.printHorizontalLine();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        KukiShinobu.printHorizontalLine();
    }

    public void fancyGreet() {
        String logo = " ____  __.      __   .__       _________.__    .__             ___.          \n"
                + "|    |/ _|__ __|  | _|__|     /   _____/|  |__ |__| ____   ____\\_ |__  __ __ \n"
                + "|      < |  |  \\  |/ /  |     \\_____  \\ |  |  \\|  |/    \\ /  _ \\| __ \\|  |  \\\n"
                + "|    |  \\|  |  /    <|  |     /        \\|   Y  \\  |   |  (  <_> ) \\_\\ \\  |  /\n"
                + "|____|__ \\____/|__|_ \\__|    /_______  /|___|  /__|___|  /\\____/|___  /____/ \n"
                + "        \\/          \\/               \\/      \\/        \\/           \\/      \n";
        System.out.println("Hello from\n" + logo);
    }


}
