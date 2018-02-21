package travel.nanjing.com.travel.business.own

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import travel.nanjing.com.travel.R

/**
 */
class MyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_my, container, false)
    }

}// Required empty public constructor
