package zw.co.mobility.gads

import android.content.Intent
import android.view.View
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.LooperMode

@LooperMode(LooperMode.Mode.PAUSED)
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

    @Test
    fun main_canLaunchProjectSubmissionActivity() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val button = activity.findViewById<View>(R.id.submit_project)
        button.performClick()

        val expectedIntent = Intent(activity, ProjectSubmissionActivity::class.java)
        val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actual.component)
    }
}