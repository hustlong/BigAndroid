package cc.biglong.bigandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.widget.DimmerIndicator;
import cc.biglong.bigandroid.widget.SlideVerticalBar;

/**
 * Created by biglong on 2017/10/25.
 */

public class SlideVerticalBarActivity extends AppCompatActivity implements SlideVerticalBar.OnListener, SeekBar.OnSeekBarChangeListener {


    @BindView(R.id.slideBar)
    SlideVerticalBar mSlideBar;
    @BindView(R.id.indicator)
    DimmerIndicator mIndicator;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.seekBar1)
    SeekBar mSeekBar1;
    @BindView(R.id.seekBar2)
    SeekBar mSeekBar2;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_slide_vertical_bar);
        ButterKnife.bind(this);

        mSlideBar.setOnListener(this);
        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onSlideVerticalBarListener(SlideVerticalBar bar, MotionEvent event, float progress) {
        mIndicator.setProgress(progress);
        mText.setText("" + progress);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekBar1) {
            mIndicator.setThickness(progress);
        } else {
            mSlideBar.setRadius(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
