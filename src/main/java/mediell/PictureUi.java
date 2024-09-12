package mediell;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PictureUi {
    enum PictureType {
        DAY,
        NIGHT,
        HAPPY
    }

    public PictureUi() {

    }

    /**
     * Gets the picture to be displayed
     * @param instruction is the instructions that has been executed
     */
    public PictureType getPicture(Instruction instruction) {
        if (instruction.getInstructionType() == Instruction.Type.MARK) {
            return PictureType.HAPPY;
        }
        return getDefaultPicture();
    }

    public PictureType getDefaultPicture() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayAt6 = LocalDate.now().atTime(17, 0);
        if (now.isAfter(todayAt6)) {
            return PictureType.NIGHT;
        }
        return PictureType.DAY;
    }
}
