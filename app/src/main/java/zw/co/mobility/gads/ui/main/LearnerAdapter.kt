package zw.co.mobility.gads.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zw.co.mobility.gads.R
import zw.co.mobility.gads.api.Learner

class LearnerAdapter(learners: List<Learner>?, layoutInflater: LayoutInflater) : RecyclerView.Adapter<LearnerAdapter.LearnerViewHolder>() {
    val learners = learners
    val layoutInflater = layoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnerViewHolder {
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return LearnerViewHolder(view,
            view.findViewById<ImageView>(R.id.badge),
            view.findViewById<TextView>(R.id.title),
            view.findViewById<TextView>(R.id.subtitle))
    }

    override fun getItemCount(): Int {
        return learners!!.size
    }

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        val learner = learners!![position]

        holder.titleView.text = learner.name
        holder.subtitleView.text = holder.subtitleView.resources.getQuantityString(R.plurals.learning_hours, learner.hours, learner.hours) + ", " + learner.country
    }

    class LearnerViewHolder(itemView: View, badgeView: ImageView, titleView: TextView, subtitleView: TextView) : RecyclerView.ViewHolder(itemView) {
        val badgeView = badgeView
        val titleView = titleView
        val subtitleView = subtitleView
    }
}
