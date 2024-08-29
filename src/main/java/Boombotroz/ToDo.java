package Boombotroz;

import java.io.FileNotFoundException;

/**
 * Deals with TODO typed task.
 */
public class ToDo extends Task {
    public ToDo(boolean mark, String task) {
        super(mark, task);

    }

    /**
     * @inheritDoc
     */
    @Override
    public void hasDate(Ui ui) throws BoomException {}

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String s;
        s = String.format("[T]%s", super.toString());
        return s;

    }

}
