package misc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class Ordered extends BlockJUnit4ClassRunner {

    private Failure failure;

    public Ordered(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(new RunListener() {
            @Override
            public void testFailure(Failure failure) throws Exception {
                Ordered.this.failure = failure;
            }
        });
        super.run(notifier);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        if (failure != null) {
            Description description = describeChild(method);
            notifier.fireTestStarted(description);
            notifier.fireTestAssumptionFailed(new Failure(description, failure.getException()));
            notifier.fireTestFinished(description);
        } else {
            super.runChild(method, notifier);
        }
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
