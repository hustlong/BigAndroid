package cc.biglong.bigandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.widget.ProgressRingView;

/**
 * Created by biglong on 2017/10/25.
 */

public class ProgressRingActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.progressRingView)
    ProgressRingView mProgressRingView;
    @BindView(R.id.seekBar)
    SeekBar mSeekBar;

    @Override
    public void onCreate(Bundle savedinstance) {
        super.onCreate(savedinstance);
        setContentView(R.layout.activity_progress_ring);
        ButterKnife.bind(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @OnClick({R.id.start, R.id.stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                mProgressRingView.startAnimator();
                break;
            case R.id.stop:
                mProgressRingView.endAnim();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mProgressRingView.setRingThickness(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
