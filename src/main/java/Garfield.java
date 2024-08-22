public class Garfield {
    public static void main(String[] args) {
        String logo = """
                 ██████╗  █████╗ ██████╗ ███████╗██╗███████╗██╗     ██████╗
                ██╔════╝ ██╔══██╗██╔══██╗██╔════╝██║██╔════╝██║     ██╔══██╗
                ██║  ███╗███████║██████╔╝█████╗  ██║█████╗  ██║     ██║  ██║
                ██║   ██║██╔══██║██╔══██╗██╔══╝  ██║██╔══╝  ██║     ██║  ██║
                ╚██████╔╝██║  ██║██║  ██║██║     ██║███████╗███████╗██████╔╝
                 ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝╚══════╝╚═════╝
                """;

        Garfield.line(75);
        System.out.println("Meowlo! I'm\n\n" + logo);
        System.out.println("What can I do for you?");
        Garfield.line(75);
        System.out.println("Bye. Hope you bring me more tuna next time. Meow!");
        Garfield.line(75);

    }

    private static void line(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
}