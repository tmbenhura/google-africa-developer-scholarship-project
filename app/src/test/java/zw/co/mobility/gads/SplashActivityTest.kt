package zw.co.mobility.gads

import android.content.Intent
import android.widget.ImageView
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

    @Test
    fun splash_showsLogo() {
        val activity = Robolectric.setupActivity(SplashActivity::class.java)
        val imageView = activity.findViewById<ImageView>(R.id.logo)

        assertEquals(R.drawable.ic_logo, shadowOf(imageView.drawable).createdFromResId)
    }
}