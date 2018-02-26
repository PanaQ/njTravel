package travel.nanjing.com.travel.business.travelRecord


import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handarui.baselib.net.RetrofitFactory
import com.handarui.baselib.util.RequestBeanMaker
import com.handarui.baselib.util.RxUtil
import com.handarui.iqfun.business.base.BaseVMFragment
import com.zhexinit.ov.common.query.PagerQuery
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.api.service.NoteService
import travel.nanjing.com.travel.databinding.FragmentTravelRecordBinding


/**
 * A simple [Fragment] subclass.
 */
class TravelRecordFragment : BaseVMFragment<TravelRecordFragment, TravelRecordViewModel>() {

    private lateinit var dataBinding: FragmentTravelRecordBinding

    override fun initViewModel() {
        viewModel = TravelRecordViewModel(this@TravelRecordFragment)
    }

    private lateinit var adapter: TravelRecordAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate<FragmentTravelRecordBinding>(inflater, R.layout.fragment_travel_record, container, false)

        dataBinding.viewModel = TravelRecordViewModel(this)
        dataBinding.recordRv.layoutManager = LinearLayoutManager(context)

        adapter = TravelRecordAdapter(context)
        adapter.onclick = TravelRecordAdapter.Onclick { startActivity(Intent(context, RecordDetailActivity::class.java)) }
        dataBinding.recordRv.adapter = adapter

        var userId = arguments?.getLong("userId")
        if (userId != null) {
            getContentById(userId)
            dataBinding.addRecord.visibility = View.INVISIBLE
        } else {
            getContentAll()
            dataBinding.addRecord.visibility = View.VISIBLE
        }

        return dataBinding.root
    }

    private fun getContentById(userId: Long) {
        var requestBean = RequestBeanMaker.getRequestBean<Long>()
        requestBean.param = userId

        var service = RetrofitFactory.createRestService(NoteService::class.java)
        RxUtil.wrapRestCall(service.getNoteById(requestBean), requestBean.reqId)
                .subscribe({

                }, {

                })
    }

    private fun getContentAll() {
        var requestBean = RequestBeanMaker.getRequestBean<PagerQuery<Void>>()
        requestBean.param.pageSize = 100

        var service = RetrofitFactory.createRestService(NoteService::class.java)
        RxUtil.wrapRestCall(service.getNoteList(requestBean), requestBean.reqId)
                .subscribe({

                }, {

                })
    }

}
