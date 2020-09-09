package zw.co.mobility.gads.ui.submission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zw.co.mobility.gads.api.createGoogleFormsService

/**
 * ViewModel provider factory to instantiate SubmissionViewModel.
 * Required given SubmissionViewModel has a non-empty constructor
 */
class SubmissionViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubmissionViewModel::class.java)) {
            return SubmissionViewModel(
                createGoogleFormsService()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}