package travel.nanjing.com.travel.business.travelRecord


import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handarui.baselib.net.RetrofitFactory
import com.handarui.baselib.util.RequestBeanMaker
import com.handarui.baselib.util.RxUtil
import com.handarui.iqfun.business.base.BaseVMFragment
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.business.api.model.bo.BaseNoteBo
import travel.nanjing.com.travel.business.api.service.NoteService
import travel.nanjing.com.travel.databinding.FragmentTravelRecordBinding
import travel.nanjing.com.travel.util.RxUtils
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class TravelRecordFragment : BaseVMFragment<TravelRecordFragment, TravelRecordViewModel>() {

    val TAG: String = "TravelRecordFragment"


    private lateinit var dataBinding: FragmentTravelRecordBinding

    override fun initViewModel() {
        viewModel = TravelRecordViewModel(this@TravelRecordFragment)
    }

    private lateinit var adapter: TravelRecordAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate<FragmentTravelRecordBinding>(inflater, R.layout.fragment_travel_record, container, false)

        var travelRecordViewModel = TravelRecordViewModel(this)
        dataBinding.viewModel = travelRecordViewModel
        dataBinding.recordRv.layoutManager = LinearLayoutManager(context)

        adapter = TravelRecordAdapter(context)
        adapter.onclick = TravelRecordAdapter.Onclick { it ->
            var intent = Intent(context, RecordDetailActivity::class.java)
            intent.putExtra("userId", adapter.data[it].userId)
            intent.putExtra("recordId", adapter.data[it].id)
            intent.putExtra("recordContent", adapter.data[it].content)
            startActivity(intent)
        }
        dataBinding.recordRv.adapter = adapter

        var userId = arguments?.getLong("userId")
        if (userId != null && userId != 0L) {
            getContentById(userId)
            dataBinding.addRecord.visibility = View.INVISIBLE
        } else {
            getContentAll();
            dataBinding.addRecord.visibility = View.VISIBLE
        }

        return dataBinding.root
    }

    private fun getContentById(userId: Long) {
        var requestBean = RequestBeanMaker.getRequestBean<Long>()
        requestBean.param=userId


        var service = RetrofitFactory.createRestService(NoteService::class.java)
        RxUtil.wrapRestCall(service.getNoteListByUserId(requestBean), requestBean.reqId)
                .subscribe({
                    adapter.data = it as ArrayList<BaseNoteBo>?
                }, {
                    Log.i(TAG, it.message)
                })
    }

    private fun getContentAll() {
        val requestBean = RequestBeanMaker.getRequestBean<Void>()
        val service = RetrofitFactory.createRestService(NoteService::class.java)
        RxUtils.wrapRestCall(service.getNoteList())
                .subscribe({
                    adapter.data = it as ArrayList<BaseNoteBo>?
                }, {
                    Log.e(TAG, it.message)
                })
    }

}
