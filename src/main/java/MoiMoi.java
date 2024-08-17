import java.util.ArrayList;
import java.util.Scanner;

public class MoiMoi {

    private static final String logo = "        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⣀⣀⡀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠋⠀⠀⠀⠈⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⢀⣠⠴⠞⠛⠉⠉⠉⠉⠉⠉⠛⠒⠾⢤⣀⣀⣠⣤⠀⠀⠀\n"
            + "        ⠀⠀⠀⣠⡶⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⢭⡀⠀⠈⣷⠀⠀⠀\n"
            + "        ⠀⠀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⢀⡟⠀⠀⠀\n"
            + "        ⠀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⡅⠀⠀⠀\n"
            + "        ⢸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣄⣀⠀\n"
            + "        ⣾⠀⠀⣠⣤⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⣄⠀⠀⠀⠀⠀⠀⠸⡇⠉⣷\n"
            + "        ⣿⠀⠰⣿⣿⣿⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⣧⡴⠋\n"
            + "        ⣿⠀⠀ // ⠀    v ⠀⠀⠀  //⠀⠀⠀⠀⠀⢰⡏⠀⠀\n"
            + "        ⢸⡄⠀   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⠀⠀⡼⣄⠀⠀\n"
            + "        ⠀⢳⡄⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⠀⢀⡼⠁⢸⡇⠀\n"
            + "        ⠀⠀⠙⢦   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⣀⡴⠟⠒⠚⠋⠀⠀\n"
            + "        ⠀⠀⠀⠀⠈⠛⠾⢤⣤⣀⣀⡀⠀⠀⠀⠀⣀⣈⣇⡤⣷⠚⠉⠀⠀⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⠀⠀⣰⠇⠀⠩⣉⠉⠉⠉⣩⠍⠁⠀⢷⣟⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⠀⠀⡟⠐⠦⠤⠼⠂⠀⠸⠥⠤⠔⠂⠘⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⠀⣸⣧⡟⠳⠒⡄⠀⠀⠀⡔⠲⠚⣧⣀⣿⠿⠷⣶⡆⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⠀⠻⣄⢀⠀⠀⡗⠀⠀⠀⡇⠄⢠⠀⣼⠟⠀⢀⣨⠇⠀⠀⠀⠀⠀\n"
            + "        ⠀⠀⠀⠀⠀⠀⠀⠙⢶⠬⠴⢧⣤⣤⣤⣽⣬⡥⠞⠛⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀\n\n";
    private static final String moiMoiHeader = "⋆⭒˚.⋆MoiMoi⋆⭒˚.⋆\n";
    private static final String userHeader = "⋆⭒˚.⋆User⋆⭒˚.⋆";
    private static final String greeting = MoiMoi.logo + MoiMoi.moiMoiHeader
            + "Hello, master! How may I help you today? ><\n\n" + MoiMoi.userHeader;
    private static final String exitMessage = MoiMoi.moiMoiHeader
            + "I'll always be here for you~ See ya, master! ^^\n";
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void run() {

        System.out.println(MoiMoi.greeting);
        String input = sc.nextLine();
        String[] commandArgs = input.split(" ", 2);

        while (!input.equals("bye")) {
            System.out.print("\n" + MoiMoi.moiMoiHeader);
            if (input.equals("list")) {
                this.list();
            } else if (commandArgs[0].equals("mark")) {
                this.mark(Integer.parseInt(commandArgs[1]) - 1);
            } else if (commandArgs[0].equals("unmark")) {
                this.unmark(Integer.parseInt(commandArgs[1]) - 1);
            } else {
                this.addTask(input);
            }
            System.out.println("\n" + MoiMoi.userHeader);
            input = sc.nextLine();
            commandArgs = input.split(" ", 2);
        }

        System.out.print("\n" + MoiMoi.exitMessage);

    }

    public void addTask(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
        System.out.println("Aight! Task added: " + task.toString());
    }

    public void mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        System.out.println("YAY!! One down!!\n" + task.toString());
    }

    public void unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        System.out.println("Oof, it's OK! Let's get it done soon ;)\n" + task.toString());
    }

    public void list() {
        System.out.println("Here's your list of tasks!");
        int index;
        for (Task task : this.tasks) {
            index = this.tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.toString());
        }
    }

    public static void main(String[] args) {
        MoiMoi moiMoi = new MoiMoi();
        moiMoi.run();
    }

}
