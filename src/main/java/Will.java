import java.util.ArrayList;


public class Will {

    public static void main(String[] args) {


        ArrayList<Task> tasks = new ArrayList<>();
        Storage.load(tasks);

        String logo = "WILL";
        Ui.greetMsg(logo);

        new Parser(tasks);
    }
}
