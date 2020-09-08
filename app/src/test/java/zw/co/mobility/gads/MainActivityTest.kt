package zw.co.mobility.gads

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @Test
    fun main_activityExists() {
        val activity = Robolectric.buildActivity<MainActivity>(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        assertNotNull(activity)
    }
}