package ProcessMonitor;

public class SystemProperty {
    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().startsWith("mac");
    }
    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().startsWith("windows");
    }
    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().startsWith("linux");
    }
}