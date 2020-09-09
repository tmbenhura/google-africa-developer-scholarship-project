package zw.co.mobility.gads.ui.submission

/**
 * Data validation state of the submission form.
 */
data class SubmissionFormState(
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val emailAddressError: Int? = null,
    val projectUrlError: Int? = null,
    val isDataValid: Boolean = false
)