package travel.nanjing.com.travel.business.own

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.handarui.baselib.net.TokenManager
import com.handarui.iqfun.util.LoginUtils
import com.squareup.picasso.Picasso
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.business.funs.FriendsActivity
import travel.nanjing.com.travel.business.login.LoginActivity
import travel.nanjing.com.travel.business.myrecord.DealMyRecordActivity
import travel.nanjing.com.travel.business.myrecord.DealMyTogetherActivity

/**
 */
class MyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_my, container, false)

        var ava = view.findViewById<ImageView>(R.id.imageView4)
        var name = view.findViewById<TextView>(R.id.name)
        name.text = LoginUtils.name
        Picasso.with(context).load(LoginUtils.portraitUrl).into(ava)


        view.findViewById<TextView>(R.id.attation).setOnClickListener({
            var intent = Intent(context, FriendsActivity::class.java)
            intent.putExtra("type", "attention")
            startActivity(intent)
        })
        view.findViewById<TextView>(R.id.funs).setOnClickListener({
            var intent = Intent(context, FriendsActivity::class.java)
            intent.putExtra("type", "funs")
            startActivity(intent)
        })
        view.findViewById<TextView>(R.id.my_record).setOnClickListener({
            var intent = Intent(context, DealMyRecordActivity::class.java)
            startActivity(intent)
        })
        view.findViewById<TextView>(R.id.my_together).setOnClickListener({
            var intent = Intent(context, DealMyTogetherActivity::class.java)
            startActivity(intent)
        })
        view.findViewById<TextView>(R.id.login_out).setOnClickListener({
            TokenManager.removeToken(context)
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            activity.finish()
        })

        return view
    }
}
