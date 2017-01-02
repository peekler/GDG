package hu.autsoft.androidapidemos;

import android.content.Intent;
import android.os.health.HealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSpellChecker)
    public void spellCheckClicked(View view) {
        startActivity(new Intent(MainActivity.this, SpellCheckerActivity.class));
    }

    @OnClick(R.id.btnTextRecog)
    public void textRecogClicked(View view) {
        startActivity(new Intent(MainActivity.this, OCRActivity.class));
    }

    @OnClick(R.id.btnTimingLogger)
    public void timeLoggerClicked(View view) {
        startActivity(new Intent(MainActivity.this, TimingLoggerActivity.class));
    }

    @OnClick(R.id.btnScreenshot)
    public void screenshotClicked(View view) {
        startActivity(new Intent(MainActivity.this, ScreenCaptureActivity.class));
    }

    @OnClick(R.id.btnPdf)
    public void pdfCreateClicked(View view) {
        startActivity(new Intent(MainActivity.this, PDFCreateActivity.class));
    }
}
