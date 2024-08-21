public class Phenex {
    public static void main(String[] args) {
        String logo = "  _____    _    _   ______   _   _   ______  __   __ \n"
                + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
                + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V / \n"
                + " | |      | |  | | | |____  | |\\  | | |____   / . \\ \n"
                + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";

        String line = "____________________________________________________________ \n";
        String greetMsg = "Hello! I'm \n"
                + logo
                + line
                + "What can I do for you? \n"
                + line;
        String farewellMsg = "Bye. Hope to see you again soon!";

        System.out.println(greetMsg);
        System.out.println(farewellMsg);
    }
}
