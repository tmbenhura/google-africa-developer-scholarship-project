package zw.co.mobility.gads

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ProjectSubmissionActivityTest {
    @Test
    fun projectSubmission_activityExists() {
        val activity = Robolectric.buildActivity<ProjectSubmissionActivity>(ProjectSubmissionActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        assertNotNull(activity)
    }
}