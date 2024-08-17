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
    private ArrayList<String> tasks = new ArrayList<String>();

    public void run() {

        System.out.println(MoiMoi.greeting);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.print("\n" + MoiMoi.moiMoiHeader);
            if (input.equals("list")) {
                this.list();
            }
            else {
                this.addTask(input);
            }
            System.out.println("\n" + MoiMoi.userHeader);
            input = sc.nextLine();
        }

        System.out.print("\n" + MoiMoi.exitMessage);

    }

    public void addTask(String task) {
        this.tasks.add(task);
        System.out.println("Aight! Task added: " + task);
    }

    public void list() {
        System.out.println("Here's your list of tasks!");
        int index;
        for (String task : this.tasks) {
            index = this.tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task);
        }
    }

    public static void main(String[] args) {
        MoiMoi moiMoi = new MoiMoi();
        moiMoi.run();
    }

}
