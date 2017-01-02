package hu.autsoft.androidapidemos;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScreenCaptureActivity extends AppCompatActivity {

    @BindView(R.id.ivScreenshot)
    ImageView ivScreenshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_capture);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTakeScreenshot)
    public void takeScreenshotClicked() {
        View viewRoot = getWindow().getDecorView().getRootView();
        viewRoot.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(viewRoot.getDrawingCache());
        viewRoot.setDrawingCacheEnabled(false);

        ivScreenshot.setImageBitmap(bitmap);
    }
}
