package zw.co.mobility.gads.ui.submission

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zw.co.mobility.gads.R
import zw.co.mobility.gads.api.GoogleFormsService

class SubmissionViewModel(private val googleFormsService: GoogleFormsService) : ViewModel() {

    private val _submissionForm = MutableLiveData<SubmissionFormState>()
    val submissionFormState: LiveData<SubmissionFormState> = _submissionForm

    private val _submissionResult = MutableLiveData<SubmissionResult>()
    val submissionResult: LiveData<SubmissionResult> = _submissionResult

    fun submit(firstName: String, lastName: String, emailAddress: String, projectUrl: String) {
        val result = googleFormsService.postProjectSubmission(
            emailAddress,
            firstName,
            lastName,
            projectUrl
        )

        result.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>?, response: Response<Void?>) {
                if (response.isSuccessful()) {
                    _submissionResult.value =
                        SubmissionResult(success = R.string.submission_succeeded)
                } else {
                    _submissionResult.value = SubmissionResult(error = R.string.submission_failed)
                    System.out.println("Request Error :: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<Void?>?, t: Throwable) {
                _submissionResult.value = SubmissionResult(error = R.string.submission_failed)
            }
        })
    }

    fun submissionDataChanged(
        firstName: String,
        lastName: String,
        emailAddress: String,
        projectUrl: String
    ) {
        if (!isNameValid(firstName)) {
            _submissionForm.value = SubmissionFormState(firstNameError = R.string.invalid_first_name)
        } else if (!isNameValid(lastName)) {
            _submissionForm.value = SubmissionFormState(lastNameError = R.string.invalid_last_name)
        } else if (!isEmailAddressValid(emailAddress)) {
            _submissionForm.value = SubmissionFormState(emailAddressError = R.string.invalid_email_address)
        } else if (!isProjectUrlValid(projectUrl)) {
            _submissionForm.value = SubmissionFormState(projectUrlError = R.string.invalid_project_url)
        } else if (!isGithubUrlValid(projectUrl)) {
            _submissionForm.value = SubmissionFormState(projectUrlError = R.string.invalid_github_url)
        } else {
            _submissionForm.value = SubmissionFormState(isDataValid = true)
        }
    }

    // Name validation check
    private fun isNameValid(name: String): Boolean {
        return name.length > 2
    }

    // Email validation check
    private fun isEmailAddressValid(emailAddress: String): Boolean {
        return if (emailAddress.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
        } else {
            emailAddress.isNotBlank() && emailAddress.contains('@')
        }
    }

    // Url validation check
    private fun isProjectUrlValid(url: String): Boolean {
        return url.length > 7 && url.startsWith("http", ignoreCase = true)
    }

    // Github Url validation check
    private fun isGithubUrlValid(url: String): Boolean {
        return url.contains("github.com", ignoreCase = true)
    }
}