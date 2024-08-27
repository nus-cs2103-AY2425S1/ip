public class Luke {
    public static void main(String[] args) {
        String logo = " .____           __\n"
                + "|    |    __ __|  | __ ____\n"
                + "|    |   |  |  \\  |/ // __ \\\n"
                + "|    |___|  |  /    <\\  ___/\n"
                + "|_______ \\____/|__|_ \\\\___  >\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("""
                ____________________________________________________________
                i'm luke, your glorified task manager.
                ____________________________________________________________
                """);

        Bot bot = new Bot();
        if (bot.loadData()) {
            bot.acceptCommand();
        }
    }
}
