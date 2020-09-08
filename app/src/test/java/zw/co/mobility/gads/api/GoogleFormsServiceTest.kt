package zw.co.mobility.gads.api

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class GoogleFormsServiceTest {

    @Test
    fun googleFormsService_canSubmitProject() {
        val wasSuccessful = createGoogleFormsService().postProjectSubmission(
            "tmbenhura@gmail.com",
            "Taurai",
            "Benhura",
            "http://TEST/IGNORE"
        )?.execute()?.isSuccessful

        assertThat(wasSuccessful, `is` (true))
    }
}