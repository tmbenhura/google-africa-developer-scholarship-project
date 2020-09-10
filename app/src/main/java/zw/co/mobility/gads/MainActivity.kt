package zw.co.mobility.gads

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import zw.co.mobility.gads.ui.main.SectionsPagerAdapter
import zw.co.mobility.gads.ui.submission.dialog.ImageDialogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val submitProject: View = findViewById(R.id.submit_project)
        submitProject.setOnClickListener { view ->
            startActivity(Intent(this, ProjectSubmissionActivity::class.java))
        }

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (!isConnected) {
            val dialogFragment = ImageDialogFragment(R.drawable.ic_failure, R.string.error_no_connectivity)
            dialogFragment.show(supportFragmentManager, "errorDialog")
        }
    }
}