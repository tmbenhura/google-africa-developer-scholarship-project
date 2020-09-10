package zw.co.mobility.gads.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zw.co.mobility.gads.api.SkillIQ

class SkillIQLeaderViewModel : ViewModel() {

    private val _leaders = MutableLiveData<List<SkillIQ>>()
    val leaders: LiveData<List<SkillIQ>> = _leaders

    fun setLeaders(leaders: List<SkillIQ>) {
        _leaders.value = leaders
    }
}