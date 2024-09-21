package bob;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * Utility class that handles getting classes from specific packages.
 */
class ClassGetter {
    /**
     * Returns all classes in the given package.
     *
     * @param packageName the fully-qualified name of the package
     *                    as defined in section 6.5.3 of The Java™ Language Specification
     * @return the set of classes in the package
     */
    public static Set<Class<?>> getAllClasses(String packageName) {
        if (isRunningFromJar()) {
            String classPath;
            try {
                classPath = ClassGetter.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
                        .getPath();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            try {
                return getAllClassesJar(new File(classPath), packageName);
            } catch (IOException e) {
                return Set.<Class<?>>of();
            }
        } else {
            return getAllClassesNotJar(packageName);
        }
    }

    /**
     * Checks if the program is being run from a JAR file.
     *
     * @return true if the program is run from a JAR file, false otherwise
     */
    private static boolean isRunningFromJar() {
        String className = ClassGetter.class.getName().replace('.', '/');
        URL classUrl = ClassGetter.class.getResource("/" + className + ".class");
        String protocol = classUrl.getProtocol();

        return protocol.equals("jar");
    }

    /**
     * Returns all the classes in a package by manually scanning for classes in the file system.
     * Code from <a href="https://www.baeldung.com/java-find-all-classes-in-package">
     *     https://www.baeldung.com/java-find-all-classes-in-package
     * </a>
     *
     * @param packageName the name of the package
     * @return the set of classes in the package
     */
    private static Set<Class<?>> getAllClassesNotJar(String packageName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream stream = classLoader.getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(filename -> filename.endsWith(".class"))
                .map(filename -> filename.substring(0, filename.lastIndexOf('.')))
                .map(className -> getClass(packageName + "." + className))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Returns all the classes in a package by manually scanning the contents of a JAR file. This is done by:
     * Opening the JAR file with the java JarFile class, then iterating through the JarEntries, and filtering
     * the classes that match the package name.
     *
     * @param file File object that represents the JAR file
     * @param packageName name of the package
     * @return the set of classes in the package
     * @throws IOException if the JarFile cannot be opened
     */
    private static Set<Class<?>> getAllClassesJar(File file, String packageName) throws IOException {
        return getClassNamesFromJar(file).stream()
                .filter(className -> className.startsWith(packageName))
                .map(ClassGetter::getClass)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private static Set<String> getClassNamesFromJar(File file) throws IOException {
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        Set<String> set = new HashSet<>();

        while (jarEntries.hasMoreElements()) {
            JarEntry entry = jarEntries.nextElement();
            String entryName = entry.getName();
            if (!entryName.endsWith(".class")) {
                continue;
            }

            String className = entryName.substring(0, entryName.lastIndexOf('.'))
                                        .replace('/', '.');
            set.add(className);
        }

        jarFile.close();
        return set;
    }
}
