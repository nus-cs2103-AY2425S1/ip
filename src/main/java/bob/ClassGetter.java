package bob;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

class ClassGetter {
    public static Set<Class<?>> getClassesFromPackage(String packageName) {
        if (isRunningFromJar()) {
            // If program is running from a JAR file
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
            // If program is not running from a JAR file
            return getAllClassesNotJar(packageName);
        }
    }

    private static boolean isRunningFromJar() {
        String className = ClassGetter.class.getName().replace('.', '/');
        URL classUrl = ClassGetter.class.getResource("/" + className + ".class");
        String protocol = classUrl.getProtocol();

        return protocol.equals("jar");
    }

    // Code from https://www.baeldung.com/java-find-all-classes-in-package
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

            String className = entryName
                    .substring(0, entryName.lastIndexOf('.')) // Remove ".class" from the end
                    .replace('/', '.');
            set.add(className);
        }

        jarFile.close();
        return set;
    }
}
