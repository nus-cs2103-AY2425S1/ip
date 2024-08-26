package count.action;

import count.TaskList;
import count.exception.CountException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save extends Action {
    TaskList ls;
    public Save(TaskList ls) {
        this.ls = ls;
    }

    @Override
    public String run() throws CountException {
        String output = "";
        for (int i = 0; i < ls.getList().size(); i++) {
            output += (i + 1) + "." + ls.getList().get(i).toString() + "\n";
        }

        try {
            File file = new File("./Count.txt");
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileWriter writer = new FileWriter("./Count.txt");
            writer.write(output);
            writer.close();

        } catch (IOException e) {
            throw new CountException("File Creation failed");
        }
        return "Your list has been saved over at Count.txt";
    }
}

