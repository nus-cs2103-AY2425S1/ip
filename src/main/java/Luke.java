public class Luke {
    public static void main(String[] args) {
        String logo = """
                .____           __          \s
                |    |    __ __|  | __ ____ \s
                |    |   |  |  \\  |/ // __ \\\s
                |    |___|  |  /    <\\  ___/\s
                |_______ \\____/|__|_ \\\\___  >
                        \\/          \\/    \\/""";
        System.out.println("Hello from\n" + logo);

        System.out.println("""
                ____________________________________________________________
                i'm luke, your chatbot.
                say something and i will echo it (unless...you wanna say bye to me)
                ____________________________________________________________
                """);

        Bot bot = new Bot();
        bot.acceptCommand();
    }
}
