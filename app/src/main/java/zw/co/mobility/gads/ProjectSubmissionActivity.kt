package zw.co.mobility.gads

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import zw.co.mobility.gads.ui.submission.SubmissionViewModel
import zw.co.mobility.gads.ui.submission.SubmissionViewModelFactory
import zw.co.mobility.gads.ui.submission.dialog.ImageDialogFragment
import zw.co.mobility.gads.ui.submission.dialog.PromptDialogCallback
import zw.co.mobility.gads.ui.submission.dialog.PromptDialogFragment

class ProjectSubmissionActivity : AppCompatActivity() {

    private lateinit var submissionViewModel: SubmissionViewModel
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var emailAddress: EditText
    private lateinit var projectUrl: EditText
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_project_submission)

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        firstName = findViewById<EditText>(R.id.first_name)
        lastName = findViewById<EditText>(R.id.last_name)
        emailAddress = findViewById<EditText>(R.id.email_address)
        projectUrl = findViewById<EditText>(R.id.project_url)
        loading = findViewById<ProgressBar>(R.id.loading)
        val submit = findViewById<Button>(R.id.submit)

        submissionViewModel = ViewModelProviders.of(this, SubmissionViewModelFactory())
            .get(SubmissionViewModel::class.java)

        submissionViewModel.submissionFormState.observe(this@ProjectSubmissionActivity, Observer {
            val submissionFormState = it ?: return@Observer

            // Disable submit button unless the form is valid
            submit.isEnabled = submissionFormState.isDataValid

            if (submissionFormState.firstNameError != null) {
                firstName.error = getString(submissionFormState.firstNameError)
            }
            if (submissionFormState.lastNameError != null) {
                lastName.error = getString(submissionFormState.lastNameError)
            }
            if (submissionFormState.emailAddressError != null) {
                emailAddress.error = getString(submissionFormState.emailAddressError)
            }
            if (submissionFormState.projectUrlError != null) {
                projectUrl.error = getString(submissionFormState.projectUrlError)
            }
        })

        submissionViewModel.submissionResult.observe(this@ProjectSubmissionActivity, Observer {
            val submissionResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (submissionResult.error != null) {
                showSubmissionFailed(submissionResult.error)
            }
            if (submissionResult.success != null) {
                showSubmissionSucceeded(submissionResult.success)
            }
            setResult(Activity.RESULT_OK)
        })

        firstName.afterTextChanged {
            submissionChanged()
        }

        lastName.afterTextChanged {
            submissionChanged()
        }

        emailAddress.afterTextChanged {
            submissionChanged()
        }

        projectUrl.apply {
            afterTextChanged {
                submissionChanged()
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        promptBeforeSubmission()
                }
                false
            }

            submit.setOnClickListener {
                promptBeforeSubmission()
            }
        }
    }

    private fun submissionChanged() {
        submissionViewModel.submissionDataChanged(
            firstName.text.toString(),
            lastName.text.toString(),
            emailAddress.text.toString(),
            projectUrl.text.toString()
        )
    }

    private fun promptBeforeSubmission() {
        val dialogFragment = PromptDialogFragment(R.string.submission_prompt, object : PromptDialogCallback{
            override fun onAccept() {
                (supportFragmentManager.findFragmentByTag("promptDialog") as DialogFragment).dismiss()

                loading.visibility = View.VISIBLE
                submissionViewModel.submit(
                    firstName.text.toString(),
                    lastName.text.toString(),
                    emailAddress.text.toString(),
                    projectUrl.text.toString())
            }

            override fun onClose() {
                (supportFragmentManager.findFragmentByTag("promptDialog") as DialogFragment).dismiss()
            }

        })
        dialogFragment.show(supportFragmentManager, "promptDialog")
    }

    private fun showSubmissionSucceeded(@StringRes successString: Int) {
        val dialogFragment = ImageDialogFragment(R.drawable.ic_success, successString)
        dialogFragment.show(supportFragmentManager, "successDialog")
    }

    private fun showSubmissionFailed(@StringRes errorString: Int) {
        val dialogFragment = ImageDialogFragment(R.drawable.ic_failure, errorString)
        dialogFragment.show(supportFragmentManager, "failureDialog")
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}