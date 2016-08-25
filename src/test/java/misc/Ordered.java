package misc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class Ordered extends BlockJUnit4ClassRunner {

    public Ordered(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testMethods = new ArrayList<>(super.computeTestMethods());
        Collections.sort(testMethods,
                Comparator.comparing(x -> x.getAnnotation(Order.class).value()));
        return Collections.unmodifiableList(testMethods);
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Order {
        int value();
    }
}
