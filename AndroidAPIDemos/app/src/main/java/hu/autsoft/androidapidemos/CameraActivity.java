package hu.autsoft.androidapidemos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hu.autsoft.androidapidemos.face.Camera2BasicFragment;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
