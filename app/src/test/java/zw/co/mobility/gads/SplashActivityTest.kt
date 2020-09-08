package zw.co.mobility.gads

import android.content.Intent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class SplashActivityTest {
    @Test
    fun splash_activityExists() {
        val activity = Robolectric.buildActivity<SplashActivity>(SplashActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        assertNotNull(activity)
    }

    @Test
    fun splash_launchesMainActivity() {
        val activity = Robolectric.buildActivity<SplashActivity>(SplashActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        val expectedIntent = Intent(activity, MainActivity::class.java)
        val actual: Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity()
        assertEquals(expectedIntent.component, actual.component)
    }
}