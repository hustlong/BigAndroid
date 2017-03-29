package cc.biglong.bigandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cc.biglong.bigandroid.activity.CircleProgressActivity;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.adapter.WidgetsAdapter;
import cc.biglong.bigandroid.entity.WidgetInfo;

/**
 * Created by biglong on 2017/3/29.
 */

public class WidgetsFragment extends Fragment implements WidgetsAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    Unbinder unbinder;
    private WidgetsAdapter mWidgetsAdapter;

    private List<WidgetInfo> INFO_LIST;

    private final static int COLUMN = 1;

    public static WidgetsFragment newInstance(String info) {
        Bundle args = new Bundle();
        WidgetsFragment fragment = new WidgetsFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_widgets, null);
        unbinder = ButterKnife.bind(this, view);

        initWidgets();

        return view;
    }

    private void initWidgets() {
        INFO_LIST = new ArrayList<>();
        INFO_LIST.add(new WidgetInfo(R.drawable.ic_circle_progress,"圆环进度指示器"));
        INFO_LIST.add(new WidgetInfo(R.drawable.ic_colors_flow,"彩色圆环转圈动画"));

        mWidgetsAdapter = new WidgetsAdapter(getActivity(),this);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), COLUMN);
        mRecyclerView.addItemDecoration(new WidgetsAdapter.ItemDecoration(16, COLUMN));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mWidgetsAdapter);
        mWidgetsAdapter.setData(INFO_LIST);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(int pos, WidgetInfo category) {
        switch (pos) {
            case 0:
                startActivity(new Intent(getContext(),CircleProgressActivity.class));
                break;
            case 1:
                break;
        }
    }
}
