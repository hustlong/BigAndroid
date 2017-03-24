package cc.biglong.bigandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.biglong.bigandroid.widget.CircleProgress;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.circleProgress1)
    CircleProgress mCircleProgress1;
    @BindView(R.id.circleProgress2)
    CircleProgress mCircleProgress2;
    @BindView(R.id.seekBar1)
    SeekBar mSeekBar1;
    @BindView(R.id.seekBar2)
    SeekBar mSeekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar bar, int i, boolean b) {
        if (bar.getId() == R.id.seekBar1) {
            mCircleProgress1.setProgress(i);
            mCircleProgress2.setProgress(i);
        } else {
            mCircleProgress1.setThickness(i);
            mCircleProgress2.setThickness(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar bar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar bar) {

    }
}
