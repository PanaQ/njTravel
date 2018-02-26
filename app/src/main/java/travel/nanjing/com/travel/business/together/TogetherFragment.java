package travel.nanjing.com.travel.business.together;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handarui.iqfun.business.base.BaseVMFragment;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.together.addTogether.AddTogetherActivity;
import travel.nanjing.com.travel.databinding.FragmentTogetherBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TogetherFragment extends BaseVMFragment<TogetherFragment, TogetherViewModel> {


    private TogetherAdapter adapter;

    public TogetherFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTogetherBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_together, container, false);
        dataBinding.setViewModel(new TogetherViewModel(this));

        dataBinding.addTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddTogetherActivity.class));
            }
        });

        dataBinding.togetherRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TogetherAdapter(getContext());
        adapter.onclick = new TogetherAdapter.Onclick() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getContext(), TogetherDetailActivity.class));
            }
        };
        dataBinding.togetherRv.setAdapter(adapter);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String type = arguments.getString("userInfo");
            if (type != null) {
                dataBinding.addTogether.setVisibility(View.INVISIBLE);
            } else {
                dataBinding.addTogether.setVisibility(View.VISIBLE);
            }
        }

        return dataBinding.getRoot();
    }

    @Override
    public void initViewModel() {
        viewModel = new TogetherViewModel(this);
    }
}
