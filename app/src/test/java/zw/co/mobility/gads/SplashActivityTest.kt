package zw.co.mobility.gads

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

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
}