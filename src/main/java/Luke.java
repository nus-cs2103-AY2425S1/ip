import java.io.*;
import java.util.ArrayList;

public class Luke {
    static final String SAVE_PATH = "./data/Luke.txt";

    public static void save(ArrayList<Task> tasks) throws IOException {
        File saveFile = new File(SAVE_PATH);
        if (!saveFile.exists()) {
            File dir = new File("./data");
            boolean dirCreated = dir.mkdir();
            if (dirCreated && !saveFile.createNewFile()) {
                throw new FileNotFoundException("Could not create file " + saveFile.getAbsolutePath());
            }
        }
        FileWriter fw = new FileWriter(SAVE_PATH);
        for (Task task : tasks) {
            fw.write(task.getSaveFormat() + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> tasks = new ArrayList<>();
        String logo =
                " __\n"
                        + "| |    _   _ _  _____\n"
                        + "| |   | | | | |/ / _ \\\n"
                        + "| |__ | |_| |   <  __/\n"
                        + "|____| \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Yo! I'm Luke.\nHow's it hanging?");
        String line;
        String[] splittedLine;

        while (true) {
            line = br.readLine();
            splittedLine = line.split(" ");
            if (splittedLine[0].equals("bye")) {
                break;
            } else if (splittedLine[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
                }
            } else if (splittedLine[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                Task t = tasks.get(Integer.parseInt(splittedLine[1]) - 1);
                t.mark();
                System.out.println(t);
            } else if (splittedLine[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                Task t = tasks.get(Integer.parseInt(splittedLine[1]) - 1);
                t.unMark();
                System.out.println(t);
            } else if (splittedLine[0].equals("delete")) {
                int index = Integer.parseInt(splittedLine[1]) - 1;
                Task t = tasks.get(index);
                tasks.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println(t);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            } else {
                Task t;
                if (splittedLine[0].equals("todo")) {
                    t = new TodoBuilder().build(line.substring(4).trim());
                    tasks.add(t);
                } else if (splittedLine[0].equals("deadline")) {
                    try {
                        t = new DeadlineBuilder().build(line.substring(8).trim());
                        tasks.add(t);
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                } else if (splittedLine[0].equals("event")) {
                    try {
                        t = new EventBuilder().build(line.substring(5).trim());
                        tasks.add(t);
                    } catch (LukeException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                } else {
                    System.out.printf("Yo! This command \"%s\" doesn't exist.\n", splittedLine[0]);
                    continue;
                }
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            }
        }
        save(tasks);
        System.out.println("Aight, Cya later.");
        br.close();
    }
}



