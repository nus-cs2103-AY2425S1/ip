public class Kafka {

    public void greet() {
        String message = """
            Hello. Kafka here.
            I have been waiting for your arrival.
            """;
        System.out.println(message);
    }

    public void exit() {
        String message = "Farewell. I look forward to our next meeting, wherever destiny may lead us. ";
        System.out.println(message);
    }
    public static void main(String[] args) {
        String logo = """
                 __  __            __     _
                |  |/  /  ____   _/  /_  | |      ____
                |     /  / _  | |_    _| | |/ /  / _  |
                |     \\ | |_| |   |  |   |   <  | |_| |
                |__|\\__\\ \\____|   |__|   |_|\\ \\  \\____|
                """;
        System.out.println("Hello from\n" + logo);
        Kafka kafka = new Kafka();
        kafka.greet();
        kafka.exit();
    }
}
