public class Greeting {
    private String chatbotName;

    public Greeting(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

    }
    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
