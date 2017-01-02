package hu.autsoft.androidapidemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimingLogger;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimingLoggerActivity extends AppCompatActivity {

    private class MyJob extends Thread {
        public static final String TAG_MYJOB = "TAG_MYJOB";

        public void run() {
            TimingLogger timings = new TimingLogger(TAG_MYJOB, "MyJob");
            tvTimeLogResult.post(new Runnable() {
                @Override
                public void run() {
                    tvTimeLogResult.setText("Phase 1 started");
                }
            });

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timings.addSplit("Phase 1 ready");
            tvTimeLogResult.post(new Runnable() {
                @Override
                public void run() {
                    tvTimeLogResult.setText("Phase 2 started");
                }
            });

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timings.addSplit("Phase 2 ready");
            tvTimeLogResult.post(new Runnable() {
                @Override
                public void run() {
                    tvTimeLogResult.setText("Phase 3 started");
                }
            });

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timings.addSplit("Phase 3 ready");
            tvTimeLogResult.post(new Runnable() {
                @Override
                public void run() {
                    tvTimeLogResult.setText("Job's done.");
                }
            });

            timings.dumpToLog();
        }
    }

    @BindView(R.id.tvTimeLogResult)
    TextView tvTimeLogResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing_logger);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTimeLogStart)
    public void timeLoggerClicked(View v) {
        new MyJob().start();
    }
}
