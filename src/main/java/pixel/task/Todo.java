package pixel.task;

import pixel.PixelException;

public class Todo extends Task {
    private String type = "T";

    private static String modifyDescription(String des) throws PixelException {
        if (des.length() == 0) {
            throw new PixelException("OH NO!!! The description of Todo cannot be empty!");
        }
        return des;
    }

    public Todo(String description) throws PixelException {
        super(modifyDescription(description));
    }

    public Todo(String description, String done) {
        super(description, done);
    }

    @Override
    public String getType() {
        return this.type;
    }
}
