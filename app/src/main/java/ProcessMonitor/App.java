/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ProcessMonitor;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;


public class App {
    static Logger logger = LogManager.getLogger(App.class.getName());
    public static void main(String[] args) {
        logger.debug("started");
        try {
            List processList = load_yaml();

            String line;
            int matched = -1;
            Process p = null;

            if (SystemProperty.isWindows()) {
                p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            } else if (SystemProperty.isLinux()) {
                p = Runtime.getRuntime().exec("ps aho cmd");
            }

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                for (Object s : processList) {
                    matched = line.indexOf((String)s);
                    if (matched != -1) {
                        processList.remove(processList.indexOf((String)s));
                        break;
                    }
                }
            }
            input.close();
            for (Object s : processList) {
                logger.error("process not found: " + (String)s);
            }
        } catch (Exception err) {
            logger.error("failed", err);
        }
        logger.debug("finished");
    }

    static List load_yaml() {
        //Path input = Paths.get("user.yml");
        Yaml yaml = new Yaml();

        List list = (List)yaml.load(ClassLoader.getSystemResourceAsStream("list.yaml"));
        return list;
    }
}
