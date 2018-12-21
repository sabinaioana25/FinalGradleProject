import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class TestAsyncTask {

    private CountDownLatch timer;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    public void testAsyncTask () {
        EndpointAsyncTask testAsyncTask = new EndpointAsyncTask(new EndpointAsyncTask.Callback() {
            @Override
            public void onFinished(String result) {
               timer.countDown();
            }
        });
        testAsyncTask.execute();
        String joke = null;
        try {
            timer.await(10,TimeUnit.SECONDS);
            joke = testAsyncTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            joke = "";
            e.printStackTrace();
        }

        assertNotNull(joke);
    }
}
