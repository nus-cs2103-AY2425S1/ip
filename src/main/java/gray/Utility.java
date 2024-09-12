package gray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Consists of various utility functions.
 */
public class Utility {

    /**
     * Serializes the object to the file.
     *
     * @param file
     * @param obj
     * @throws IOException
     */
    public static void serialize(File file, Serializable obj) throws IOException {
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutput s = new ObjectOutputStream(f);
        s.writeObject(obj);
        s.flush();
        s.close();
        s.close();
    }

    /**
     * Deserializes an object from the file.
     *
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(File file) throws IOException, ClassNotFoundException {
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(in);
        Object obj = s.readObject();
        s.close();
        in.close();
        return obj;
    }
}
