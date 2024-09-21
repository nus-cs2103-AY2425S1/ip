package bob;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

class ClassGetter {
    // Code from https://www.baeldung.com/java-find-all-classes-in-package
    public static Set<Class<?>> getAllClasses(String packageName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream stream = classLoader.getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(filename -> filename.endsWith(".class"))
                .map(filename -> filename.substring(0, filename.lastIndexOf('.')))
                .map(className -> getClass(className, packageName))
                .collect(Collectors.toSet());
    }

    public static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
