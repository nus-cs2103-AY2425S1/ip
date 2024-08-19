public class GreetBot {
    public static void main(String[] args) {
        new GreetBot().run();
    }

    /* solution below inspired by main function in:
    https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java
    */
    private void run() {
        System.out.println("Hello! I'm GreetBot");
        System.out.println("What can I do for you?");
        
        while (true) {
            System.out.println("-----------------------");

            System.out.println("Bye. Hope to see you again soon!");
            break;
        }
    }
}
