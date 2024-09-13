package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class Storage {
    private final String filePath;

    Storage(String _filePath) {
        this.filePath = _filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException, ParseException {
        ArrayList<Task> data = new ArrayList<>();

        File f = new File(this.filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] split = Arrays.stream(s.nextLine().split("\\|")).map(String::trim).toArray(String[]::new);
            String type = split[0];

            switch (type) {
                case "T" -> data.add(new ToDo(split[2]));
                case "D" -> data.add(new DeadLine(split[2], Parser.parseDate(split[3])));
                case "E" -> data.add(new Event(split[2], Parser.parseDate(split[3]), Parser.parseDate(split[4])));
            }

            if (Objects.equals(split[1], "1")) {
                data.get(data.size() - 1).mark();
            }
        }
        return data;
    }

    public void writeData(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

        for (Task task : tasks.getList()) {
            fw.write(task.saveFormat());
        }

        fw.close();
    }
}
