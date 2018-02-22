package travel.nanjing.com.travel.business.own

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.business.funs.FriendsActivity

/**
 */
class MyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_my, container, false)
        view.findViewById<TextView>(R.id.attation).setOnClickListener({
            startActivity(Intent(context,FriendsActivity::class.java))
        })
        view.findViewById<TextView>(R.id.funs).setOnClickListener({
            startActivity(Intent(context,FriendsActivity::class.java))
        })
        return view
    }

}// Required empty public constructor
