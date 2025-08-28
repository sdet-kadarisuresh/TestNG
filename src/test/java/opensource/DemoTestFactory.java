package opensource;

import org.testng.annotations.Factory;

public class DemoTestFactory {

    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new DemoTests("chrome"),
                new DemoTests("firefox"),
                new DemoTests("edge"),
                new DemoTests("opera")
        };
    }
}
