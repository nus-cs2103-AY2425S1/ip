public class UI {
    public void greeting() {
        System.out.println("\t\t\t---------------------------------------------------------------\n"
                                   + "\t\t\tHello! I'm EchoChat\n\t\t\tWhat can I do for you?\n\n" + "\t\t\t---------------------------------------------------------------");
    }

    public void echo(String command) {
        System.out.println("\t\t\t---------------------------------------------------------------\n"
                                   + "\t\t\t" + command + "\n\t\t\t---------------------------------------------------------------");
    }

    public void exit() {
        System.out.println("\t\t\t---------------------------------------------------------------\n"
                                   + "\t\t\tBye. Hope to see you again soon!\n"
                                   + "\t\t\t---------------------------------------------------------------\n");
    }
}
