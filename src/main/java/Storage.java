import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filepath) {
        file = new File(filepath);
    }

    private void createFile() throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    private Task decode(String encodedString) {
        Task task = null;
        int n;
        String desc;

        switch(encodedString.charAt(0)) {
        case 'T':
            // T<isDone><desc>
            task = new Todo(encodedString.substring(2));
            break;
        case 'D':
            // D<isDone><len(desc)#4><desc><by>
            n = Integer.parseInt(encodedString.substring(2, 6));
            desc = encodedString.substring(6, 6 + n);
            task = new Deadline(desc, encodedString.substring(6 + n));
            break;
        case 'E':
            // E<isDone><len(desc)#4><desc><len(from)#4><from><to>
            n = Integer.parseInt(encodedString.substring(2, 6));
            int curr = 6 + n;
            desc = encodedString.substring(6, curr);
            n = Integer.parseInt(encodedString.substring(curr, curr + 4));
            curr += 4;
            String from = encodedString.substring(curr, curr + n);
            task = new Event(desc, from, encodedString.substring(curr + n));
            break;
        default:
            // TODO: throw error
            break;
        }

        if (encodedString.charAt(1) == '1') {
            task.mark();
        }

        return task;
    }

    private String encode(Task task) {
        StringBuilder str = new StringBuilder();

        if (task instanceof Todo) {
            // Todo: T<isDone><desc>
            str.append("T");
            str.append(task.isDone ? 1 : 0);
            str.append(task.description);
        } else if (task instanceof Deadline deadline) {
            // Deadline: D<isDone><len(desc)#4><desc><by>
            str.append("D");
            str.append(task.isDone ? 1 : 0);
            str.append(String.format("%04d", deadline.description.length()));
            str.append(deadline.description);
            str.append(deadline.by);
        } else if (task instanceof Event event) {
            // Event: E<isDone><len(desc)#4><desc><len(from)#4><from><to>
            str.append("E");
            str.append(task.isDone ? 1 : 0);
            str.append(String.format("%04d", event.description.length()));
            str.append(event.description);
            str.append(String.format("%04d", event.from.length()));
            str.append(event.from);
            str.append(event.to);
        }

        str.append('\n');
        return str.toString();
    }

    public List<Task> load() throws IOException {
        if (!file.exists()) {
            createFile();
        }

        List<Task> list = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNext()) {
            try {
                list.add(decode(scanner.nextLine()));
            } catch (RuntimeException e) {
                throw new FileCorruptedException();
            }
        }

        return list;
    }

    public void save(List<Task> tasksList) throws IOException {
        if (!file.exists()) {
            createFile();
        }

        FileWriter fw = new FileWriter(file, false);
        for (Task task : tasksList) {
            fw.write(encode(task));
        }
        fw.close();
    }
}
