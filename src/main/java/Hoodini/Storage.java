package Hoodini;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;



public class Storage {
    private ArrayList<Input> input;
    private Ui ui;
    private static int counter = 0;

    public Storage(Ui ui) {
        this.input = new ArrayList<>();
        this.ui = ui;
    }

    public void store(Input input) {
        if(input.empty()) {
            ui.invalidTask();
        } else {
            this.input.add(input);
            counter++;
            ui.store(input, counter);
        }


    }

    public void add(Input input) {
        this.input.add(input);
        counter++;
    }

    public void writeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Input i : input) {
                writer.write(i.toString());
                writer.newLine();
            }
        }
    }


    public void output() {
        System.out.println("Here are the list of tasks that needs to be completed: ");
        for(int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + input.get(i));
        }
    }

    public void mark(String str) {
        int i = Integer.parseInt(str.substring(5));
        if (i > counter) {
            ui.invalidInput();
        } else {
            input.get(i-1).done();

        }


    }

    public void delete(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            ui.invalidInput();
        } else {
            Input input1 = input.get(i-1);
            input.remove(i-1);
            counter--;
            ui.delete(input1, counter);

        }
    }

    public void empty() {

        System.out.println("Whoopsie! Please enter a task");
    }

    public void unmark(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            ui.invalidInput();
        } else {
            input.get(i-1).undone();

        }

    }

}
