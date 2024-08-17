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
        String command = Parser.inputToCommand(input);

        while (!command.equals("bye")) {
            System.out.print("\n" + MoiMoi.moiMoiHeader);
            switch (command) {
                case "todo":
                    this.todo(Parser.inputToArgs(input));
                    break;
                case "deadline":
                    this.deadline(Parser.inputToArgs(input));
                    break;
                case "event":
                    this.event(Parser.inputToArgs(input));
                    break;
                case "mark":
                    this.mark(Parser.inputToArgs(input));
                    break;
                case "unmark":
                    this.unmark(Parser.inputToArgs(input));
                    break;
                case "list":
                    this.list();
                    break;
                default:
                    System.out.println("(MoiMoi exception to be thrown later)");
            }
            System.out.println("\n" + MoiMoi.userHeader);
            input = sc.nextLine();
            command = Parser.inputToCommand(input);
        }

        System.out.print("\n" + MoiMoi.exitMessage);

    }

    public void todo(String description) {
        Todo task = new Todo(description);
        this.tasks.add(task);
        System.out.println("Aight! Todo task added: " + task.toString()
                + "\nWe have " + tasks.size() + " tasks in the bag~");
    }

    public void deadline(String description) {
        String[] descBy = description.split(" /by ", 2);
        Deadline task = new Deadline(descBy[0], descBy[1]);
        this.tasks.add(task);
        System.out.println("Got it! Deadline task added: " + task.toString()
                + "\nWe have " + tasks.size() + " tasks in the bag~");
    }

    public void event(String description) {
        String[] descFromTo = description.split(" /from ", 2);
        String[] fromTo = descFromTo[1].split(" /to ", 2);
        Event task = new Event(descFromTo[0], fromTo[0], fromTo[1]);
        this.tasks.add(task);
        System.out.println("Here you go! Event task added: " + task.toString()
                + "\nWe have " + tasks.size() + " tasks in the bag~");
    }

    public void mark(String index) {
        Task task = tasks.get(Integer.parseInt(index) - 1);
        task.mark();
        System.out.println("YAY!! One down!!\n" + task.toString());
    }

    public void unmark(String index) {
        Task task = tasks.get(Integer.parseInt(index) - 1);
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
