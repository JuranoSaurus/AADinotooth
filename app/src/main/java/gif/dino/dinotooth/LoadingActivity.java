package gif.dino.dinotooth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

@Fullscreen
@EActivity(R.layout.activity_loading)
public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1.5초후 시작
        doInBackgroundAfterTwoSeconds();
    }

    @Background(delay = 1500)
    void doInBackgroundAfterTwoSeconds() {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
    }

}
