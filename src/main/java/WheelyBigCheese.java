import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class WheelyBigCheese {
    public static final String LIST_FILE_PATH = "./data/list.txt";
    private static final String greeting = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ending = "Schwooo Weeeeee!!! Shutting down.....";
    private ArrayList<Task> tasks;

    WheelyBigCheese() throws CheeseException {
        if (!loadTasks()) throw new CheeseException("Error loading tasks");
    }

    /**
     * Return or create list file
     * @return File
     * @throws IOException In case of IOException
     */
    private File getListFile() throws IOException {
        File f = new File(LIST_FILE_PATH);
        if (!f.exists()) {
            boolean created = f.createNewFile();
            System.out.println(created);
        }
        return f;
    }
    private boolean loadTasks() {
        tasks = new ArrayList<>();
        try {
            File f = getListFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String data = s.nextLine();
                tasks.add(loadTask(data));
            }
        } catch (IOException | CheeseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    private Task loadTask(String s) throws CheeseException {
        String[] data = s.split(",");
        return switch (data[0]) {
            case "T" -> new Task(data);
            case "D" -> new Deadline(data);
            case "E" -> new Event(data);
            default -> throw new CheeseException("Incorrect data format");
        };
    }
    /**
     * Add and save new task
     * @param t task to be added
     * @return if save is successful
     */
    public boolean save(Task t) throws IOException {
        try (FileWriter fw = new FileWriter(getListFile(), true)) {
            fw.write(t.dataString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        tasks.add(t);
        return true;
    }

    /**
     * Update task and save
     * @param tokens input tokens
     * @param delete if delete task
     * @return task updated/deleted
     * @throws CheeseException Missing/Incorrect input
     * @throws IOException File problem
     */
    public Task update(String[] tokens, boolean delete) throws CheeseException, IOException {
        int idx = getIdx(tokens);
        File original = getListFile();
        File tmp = new File("tmp.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp, true))) {
            for (int i = 0; i < tasks.size(); i++) {
                if (i == idx && delete) continue;
                bw.append(tasks.get(i).dataString()).append(System.lineSeparator());
            }
        }

        boolean deleted = original.delete();
        boolean renamed = tmp.renameTo(original);

        return delete ? tasks.remove(idx): tasks.get(idx);
    }

    public Task mark(String[] inputTokens, boolean done) throws CheeseException, IOException {
        int idx = getIdx(inputTokens);
        Task t = tasks.get(idx);
        t.setDone(done);
        update(inputTokens, false);
        return t;
    }

    /**
     * Helper function to get idx of item in list
     * @param inputTokens input from user
     * @return int
     * @throws CheeseException Missing/Incorrect input
     */
    private int getIdx(String[] inputTokens) throws CheeseException {
        if (inputTokens.length != 2) throw new CheeseException("Need location of cheese");
        int idx;
        try {
            idx = Integer.parseInt(inputTokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CheeseException(e.getMessage());
        }
        if (idx >= tasks.size() || idx < 0) throw new CheeseException("Incorrect location of cheese");
        return idx;
    }

    /**
     * Method to format the output of the bot
     * @param s String to say
     */
    public static void say(String s){
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }

    /**
     * Method to say the task list
     */
    public void say() {
        StringBuilder allItems = new StringBuilder("Got your cheese:");
        for (int i = 0; i < tasks.size(); i++) {
            allItems.append("\n").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        say(String.valueOf(allItems));
    }
    /**
     * Method to say a newly added/deleted task
     * @param t Task
     */
    public void say(Task t, boolean delete) {
        String del;
        if (delete) {
            del = "Removed cheese :(\n";
        } else {
            del = "Added new cheese ;)\n";
        }
        String s = del + t.toString() + "\n" + tasks.size() + " cheese in the shelf";
        say(s);
    }

    public static void main(String[] args) {
        //Variables for bot
        WheelyBigCheese bot;
        try {
            bot = new WheelyBigCheese();
        } catch (CheeseException e) {
            System.out.println(e.getMessage());
            return;
        }
        Scanner sc = new Scanner(System.in); // Scanner to get user input
        String input;
        boolean exitChat = false;

        say(greeting);

        //Main logic for bot
        do {
            //Get user input and basic manipulation of input
            input = sc.nextLine();
            String[] inputTokens = input.split(" ");
            String command = inputTokens[0];

            try {
                //Switch statement for different responses to different commands
                switch (command) {
                case "bye":
                    exitChat = true;
                    break;
                case "list":
                    bot.say();
                    break;
                case "mark":
                    Task markT = bot.mark(inputTokens, true);
                    say("Beep bop. Cheese melted:\n" + markT);
                    break;
                case "unmark":
                    Task unmarkT = bot.mark(inputTokens, false);
                    say("Bop beep. Unmelted cheese:\n" + unmarkT);
                    break;
                case "todo":
                    ToDo todo = ToDo.of(input);
                    bot.save(todo);
                    bot.say(todo, false);
                    break;
                case "deadline":
                    Deadline deadline = Deadline.of(input);
                    bot.save(deadline);
                    bot.say(deadline, false);
                    break;
                case "event":
                    Event event = Event.of(input);
                    bot.save(event);
                    bot.say(event, false);
                    break;
                case "delete":
                    Task deletedTask = bot.update(inputTokens, true);
                    bot.say(deletedTask, true);
                    break;
                default:
                    bot.save(new Task(input));
                    say("added: " + input);
                }
            } catch (CheeseException e) {
                say(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!exitChat);

        say(ending);
    }
}
