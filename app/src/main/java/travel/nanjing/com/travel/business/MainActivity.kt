package travel.nanjing.com.travel.business

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.handarui.iqfun.business.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.business.friends.FriendsFragment
import travel.nanjing.com.travel.business.own.MyFragment
import travel.nanjing.com.travel.business.together.TogetherFragment
import travel.nanjing.com.travel.business.travelRecord.TravelRecordFragment

class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentControl(travelRecord)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                fragmentControl(togetherFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fragmentControl(friendsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.userInfo -> {
                fragmentControl(myFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val travelRecord: TravelRecordFragment by lazy { TravelRecordFragment() }
    private val togetherFragment: TogetherFragment by lazy { TogetherFragment() }
    private val friendsFragment: FriendsFragment by lazy { FriendsFragment() }
    private val myFragment: MyFragment by lazy { MyFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fragmentControl(travelRecord)
    }

    private fun fragmentControl(showFragment: Fragment) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, showFragment)
        fragmentTransaction.commit()
    }
}
