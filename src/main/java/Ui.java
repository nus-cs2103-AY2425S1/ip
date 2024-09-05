import java.util.Scanner;

public class Ui {

    private static final String logo = " ,--.--------.    ,----.    ,-,--.             ,---.      \n" +
                                       "/==/,  -   , -\\,-.--` , \\ ,-.'-  _\\  _.-.    .--.'  \\     \n" +
                                       "\\==\\.-.  - ,-./==|-  _.-`/==/_ ,_.'.-,.'|    \\==\\-/\\ \\    \n" +
                                       " `--`\\==\\- \\  |==|   `.-.\\==\\  \\  |==|, |    /==/-|_\\ |   \n" +
                                       "      \\==\\_ \\/==/_ ,    / \\==\\ -\\ |==|- |    \\==\\,   - \\  \n" +
                                       "      |==|- ||==|    .-'  _\\==\\ ,\\|==|, |    /==/ -   ,|  \n" +
                                       "      |==|, ||==|_  ,`-._/==/\\/ _ |==|- `-._/==/-  /\\ - \\ \n" +
                                       "      /==/ -//==/ ,     /\\==\\ - , /==/ - , ,|==\\ _.\\=\\.-' \n" +
                                       "      `--`--``--`-----``  `--`---'`--`-----' `--`         \n" +
                                       "\n";

    private boolean flag = false;
    private Scanner scanner;

    public Ui() {
        setScanner(new Scanner(System.in));
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showLoadingError() {
        System.out.println("Regrettably, the storage file you have indicated cannot be " +
                           "located within the depths of the system.");
    }

    public void greet() {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm\n" + logo +
                "How may I be of service to you in this moment?\n" +
                "____________________________________________________________\n");

    }

    public void display(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i));
        }
    }

    public void taskAddOrDeleteDisplay(Task task, String addOrDelete, TaskList tasks) {
        System.out.println("Got it. I've " + addOrDelete + "ed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public String out() {
        return scanner.nextLine();
    }
}
