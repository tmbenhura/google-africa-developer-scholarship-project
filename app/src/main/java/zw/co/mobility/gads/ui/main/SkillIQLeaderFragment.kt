package zw.co.mobility.gads.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zw.co.mobility.gads.R
import zw.co.mobility.gads.api.SkillIQ
import zw.co.mobility.gads.api.createGadsService

class SkillIQLeaderFragment : Fragment() {

    companion object {
        fun newInstance() = SkillIQLeaderFragment()
    }

    private lateinit var viewModel: SkillIQLeaderViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = root.findViewById(R.id.list)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SkillIQLeaderViewModel::class.java)
        viewModel.leaders.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = SkillIQAdapter(it, activity!!.layoutInflater)
        })
        val leaderResult = createGadsService().skillIQLeaders()
        leaderResult?.enqueue(object : Callback<List<SkillIQ?>?> {
            override fun onResponse(
                call: Call<List<SkillIQ?>?>,
                response: Response<List<SkillIQ?>?>
            ) {
                viewModel.setLeaders(response.body() as List<SkillIQ>)
            }

            override fun onFailure(call: Call<List<SkillIQ?>?>, t: Throwable) {
            }
        })
    }
}