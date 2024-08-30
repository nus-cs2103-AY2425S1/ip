public class Ui {

    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("No save file found, starting new task list");
    }

    public void greet() {
        String greetMsg = " ____________________________________________________________\n" +
                " Hello! I'm Donk, the super intelligent chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(greetMsg);
    }

    public void bye() {
        String byeMsg = "    Bye bro, catch 'ya later\n" +
                "____________________________________________________________\n";
        System.out.println(byeMsg);
    }

}
