public class Jade {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        UI ui = new UI(taskManager);
        ui.run();
    }
}
