package club.without.dereku.reimaginedcouscous;

import club.without.dereku.reimaginedcouscous.configuration.Configuration;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReimaginedCouscous {
    private final static Gson GSON = new Gson();

    public static void main(String[] args) {
        ReimaginedCouscous rc = new ReimaginedCouscous();
        final File configLocation = new File(rc.whereIsMyWorkDir(), "configuration.json");
        final Configuration configuration = rc.loadConfig(configLocation);
        System.out.println("Should be created: " + configuration.isShouldBeCreated());
        configuration.getMappy().forEach((s, integers) -> System.out.println(s + ": " + integers));
        rc.saveConfig(configuration, configLocation);
    }

    private Configuration loadConfig(final File configLocation) {
        if (!configLocation.exists()) {
            return new Configuration();
        }
        try (FileInputStream fis = new FileInputStream(configLocation);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             JsonReader jr = new JsonReader(isr)) {
            return GSON.fromJson(jr, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Configuration();
    }

    private void saveConfig(final Configuration cfg, final File configLocation) {
        try (FileOutputStream fos = new FileOutputStream(configLocation);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             JsonWriter jw = new JsonWriter(osw)) {
            jw.setIndent("    ");
            jw.setSerializeNulls(true);
            GSON.toJson(cfg, cfg.getClass(), jw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File whereIsMyWorkDir() {
        return new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile();
    }
}
