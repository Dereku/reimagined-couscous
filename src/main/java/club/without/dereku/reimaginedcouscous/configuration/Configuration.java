package club.without.dereku.reimaginedcouscous.configuration;

import java.util.*;

// POJO
public class Configuration {
    private boolean shouldBeCreated = true;
    private Map<String, List<Integer>> mappy = new LinkedHashMap<String, List<Integer>>() {{
        final Random random = new Random();
        for (int i = 0; i < 4; i++) {
            final String key = "Sample#" + i;
            final List<Integer> list = new LinkedList<>();
            for (int ii = 0; ii < 12; ii++) {
                list.add(random.nextInt(100));
            }
            this.put(key, list);
        }
    }};

    public boolean isShouldBeCreated() {
        return shouldBeCreated;
    }

    public Map<String, List<Integer>> getMappy() {
        return mappy;
    }
}
