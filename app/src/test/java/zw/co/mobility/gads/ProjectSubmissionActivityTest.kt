package zw.co.mobility.gads

import android.widget.ImageView
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows


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

    @Test
    fun projectSubmission_showsHeader() {
        val activity = Robolectric.setupActivity(ProjectSubmissionActivity::class.java)
        val imageView = activity.findViewById<ImageView>(R.id.header)

        Assert.assertEquals(
            R.drawable.ic_header,
            Shadows.shadowOf(imageView.drawable).createdFromResId
        )
    }
}