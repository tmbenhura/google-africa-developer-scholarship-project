package zw.co.mobility.gads.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import zw.co.mobility.gads.R
import zw.co.mobility.gads.api.SkillIQ

class SkillIQAdapter(leaders: List<SkillIQ>?, layoutInflater: LayoutInflater) : RecyclerView.Adapter<SkillIQAdapter.LeaderViewHolder>() {
    val leaders = leaders
    val layoutInflater = layoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillIQAdapter.LeaderViewHolder {
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return SkillIQAdapter.LeaderViewHolder(
            view,
            view.findViewById<ImageView>(R.id.badge).apply {
                setImageDrawable(ContextCompat.getDrawable(parent.context, R.drawable.ic_skill_iq))
            },
            view.findViewById<TextView>(R.id.title),
            view.findViewById<TextView>(R.id.subtitle)
        )
    }

    override fun getItemCount(): Int {
        return leaders!!.size
    }

    override fun onBindViewHolder(holder: LeaderViewHolder, position: Int) {
        val leader = leaders!![position]

        holder.titleView.text = leader.name
        holder.subtitleView.text =  leader.score.toString() + " SkillIQ score, " + leader.country
    }

    class LeaderViewHolder(itemView: View, badgeView: ImageView, titleView: TextView, subtitleView: TextView) : RecyclerView.ViewHolder(itemView) {
        val badgeView = badgeView
        val titleView = titleView
        val subtitleView = subtitleView
    }
}
