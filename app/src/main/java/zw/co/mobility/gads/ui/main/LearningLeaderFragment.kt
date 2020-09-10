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
import zw.co.mobility.gads.api.Learner
import zw.co.mobility.gads.api.createGadsService

class LearningLeaderFragment : Fragment() {

    companion object {
        fun newInstance() = LearningLeaderFragment()
    }

    private lateinit var viewModel: LearningLeaderViewModel
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
        viewModel = ViewModelProviders.of(this).get(LearningLeaderViewModel::class.java)
        viewModel.learners.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = LearnerAdapter(it, activity!!.layoutInflater)
        })
        val learnerResult = createGadsService().learningLeaders()
        learnerResult?.enqueue(object : Callback<List<Learner?>?> {
            override fun onResponse(
                call: Call<List<Learner?>?>,
                response: Response<List<Learner?>?>
            ) {
                viewModel.setLearners(response.body() as List<Learner>)
            }

            override fun onFailure(call: Call<List<Learner?>?>, t: Throwable) {
            }
        })
    }
}