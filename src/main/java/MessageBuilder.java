public class MessageBuilder {
    private void buildMessage(String message) {
        System.out.println("______________________________________________________________________");
        System.out.println(message);
        System.out.println("*uwaeh*"); // Puke will always vomit after saying something
        System.out.println("______________________________________________________________________");
    }

    public void sendGreetingMessage() {
        buildMessage("HELLO???!!! I'm Puke and I'm Puk *uwaeh* ing... .-.");
    }

    public void sendGoodbyeMessage() {
        buildMessage("BYE!!! *UWAGHhhHH* !!! see Ya *uWahghgh*");
    }

    public void sendMessage(String message) {
        buildMessage(message);
    }
}
