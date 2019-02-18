package com.uma.upsoundrecorder.ui.mainactivity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.uma.upsoundrecorder.R
import com.uma.upsoundrecorder.databinding.ActivityMainBinding
import com.uma.upsoundrecorder.datamanager.AppDataManager
import com.uma.upsoundrecorder.ui.listrecordings.ListFragment
import com.uma.upsoundrecorder.ui.listrecordings.ListFragmentModel
import com.uma.upsoundrecorder.ui.recordfragment.RecordFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_record.*

class MainActivity : AppCompatActivity(), View.OnClickListener, MainView {

//    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var tabAdapter: TabAdapter
    val MY_PERMISSIONS_RECORD_AUDIO = 1
    lateinit var mAppDataManager:AppDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.mainData = MainVM(this)

        mAppDataManager= AppDataManager(this)
        tabAdapter = TabAdapter(supportFragmentManager)
        container.adapter = tabAdapter
        tabLayout.setupWithViewPager(container)

//        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.position?.let { container.currentItem = it }
//            }
//        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun addData()
    {
        tabAdapter.test()

    }

    override fun onClick(v: View?) {
        when (v) {
            fabStart -> {
                chronometer.start()
                fabStop.visibility = View.VISIBLE
                fabStart.visibility = View.INVISIBLE
            }
            fabStop -> {
                chronometer.stop()
                fabStart.visibility = View.VISIBLE
                fabStop.visibility = View.INVISIBLE
            }
            else -> {
            }
        }
    }

//    private fun requestAudioPermissions() {
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.RECORD_AUDIO
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            //When permission is not granted by user, show them message why this permission is needed.
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    this,
//                    Manifest.permission.RECORD_AUDIO
//                )
//            ) {
//                Toast.makeText(this, "Please grant permissions to record audio", Toast.LENGTH_LONG).show()
//
//                //Give user option to still opt-in the permissions
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.RECORD_AUDIO),
//                    MY_PERMISSIONS_RECORD_AUDIO
//                )
//
//            } else {
//                // Show user dialog to grant permission to record audio
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.RECORD_AUDIO),
//                    MY_PERMISSIONS_RECORD_AUDIO
//                )
//            }
//        } else if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.RECORD_AUDIO
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//
//            //Go ahead with recording audio now
////            recordAudio()
//        }//If permission is granted, then go ahead recording audio
//    }
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (grantResults.isNotEmpty()
//            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            // permission was granted, yay!
//        }
//    }

//    /**
//     * A [FragmentPagerAdapter] that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
//
//        override fun getItem(position: Int): Fragment {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(
//                position + 1
//            )
//        }
//
//        override fun getCount(): Int {
//            // Show 3 total pages.
//            return 3
//        }
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    class PlaceholderFragment : Fragment() {
//
//        override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View? {
//            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
//            rootView.section_label.text = getString(
//                R.string.section_format, arguments?.getInt(
//                    ARG_SECTION_NUMBER
//                ))
//            return rootView
//        }
//
//        companion object {
//            /**
//             * The fragment argument representing the section number for this
//             * fragment.
//             */
//            private val ARG_SECTION_NUMBER = "section_number"
//
//            /**
//             * Returns a new instance of this fragment for the given section
//             * number.
//             */
//            fun newInstance(sectionNumber: Int): PlaceholderFragment {
//                val fragment = PlaceholderFragment()
//                val args = Bundle()
//                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
//                fragment.arguments = args
//                return fragment
//            }
//        }
//    }
}
