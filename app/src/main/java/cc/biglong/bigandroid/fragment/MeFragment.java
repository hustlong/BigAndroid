package cc.biglong.bigandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.entity.GithubUser;
import cc.biglong.bigandroid.presenter.GithubUserPresenter;
import cc.biglong.bigandroid.view.GithubUserView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by biglong on 2017/3/29.
 */

public class MeFragment extends Fragment implements GithubUserView {

    private final static String USER = "hustlong";

    @BindView(R.id.avatar)
    CircleImageView mAvatar;
    @BindView(R.id.infoTv)
    TextView mInfoTv;
    Unbinder unbinder;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    private GithubUserPresenter mPresenter;

    public static MeFragment newInstance(String info) {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        unbinder = ButterKnife.bind(this, view);


        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getUserInfo(USER);
            }
        });
        mRefresh.setColorSchemeColors(0xFFF44336);
        mPresenter = new GithubUserPresenter(this);

        refresh();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.detachView();
    }

    @Override
    public void showError(String error) {
        mRefresh.setRefreshing(false);
        Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showGithubUserInfo(GithubUser user) {
        mRefresh.setRefreshing(false);
        mInfoTv.setText(user.toString());
        Glide.with(getActivity()).load(user.getAvatar_url()).centerCrop().into(mAvatar);
    }

    public void refresh() {
        if (mRefresh.isRefreshing())
            return;
        else  {
            mRefresh.setRefreshing(true);
            mPresenter.getUserInfo(USER);
        }
    }
}
