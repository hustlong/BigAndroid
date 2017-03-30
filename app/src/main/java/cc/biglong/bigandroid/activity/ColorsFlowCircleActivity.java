package cc.biglong.bigandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.base.BaseActivity;
import cc.biglong.bigandroid.widget.ColorsFlowCircle;

/**
 * Created by biglong on 2017/3/30.
 */

public class ColorsFlowCircleActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.flow)
    ColorsFlowCircle mFlow;
    @BindView(R.id.seekBar)
    SeekBar mSeekBar;

    int mSelectedColor;
    private final static int[] mColors = new int[] {Color.RED,Color.BLUE,Color.GREEN,Color.CYAN,Color.GRAY,Color.YELLOW};

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_colors_flow_circle);
        ButterKnife.bind(this);

        mSeekBar.setOnSeekBarChangeListener(this);
        mFlow.setColors(mColors);
    }

    @Override
    public void onProgressChanged(SeekBar bar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar bar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar bar) {
        mFlow.setAnimDuration(6000-bar.getProgress());
    }
}
