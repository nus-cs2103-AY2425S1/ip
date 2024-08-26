import java.io.FileNotFoundException;

public class BlitzFileNotFoundException extends BlitzException {
    @Override
    public String toString() {
        return "There is something wrong with the database, please try again or contact the administrator!";
    }
}
