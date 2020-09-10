package zw.co.mobility.gads.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zw.co.mobility.gads.api.Learner

class LearningLeaderViewModel : ViewModel() {

    private val _learners = MutableLiveData<List<Learner>>()
    val learners: LiveData<List<Learner>> = _learners

    fun setLearners(learners: List<Learner>) {
        _learners.value = learners
    }
}