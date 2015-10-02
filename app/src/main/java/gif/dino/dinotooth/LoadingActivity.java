package gif.dino.dinotooth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

import java.io.DataOutputStream;

@Fullscreen
@EActivity(R.layout.activity_loading)
public class LoadingActivity extends Activity {

    ToothSocket socket = null;
    DataOutputStream dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                switch(inputMessage.what){
                    case ToothSocket.MessageTypeClass.SIMSOCK_DATA :
                        String msg = (String) inputMessage.obj;
                        Log.d("JOOL", msg);
                        Log.d("JOOL", "SIMSOCK_DATA ~~~~~~~~~! 심!솎!");
                        Toast.makeText(getApplicationContext(), "SIMSOCK_DATA", Toast.LENGTH_LONG).show();
                        // do something with UI
                        break;

                    case ToothSocket.MessageTypeClass.SIMSOCK_CONNECTED :
                        // do something with UI
                        Log.d("JOOL", "SIMSOCK_연결잼~~~~~~~~~! 심!솎!");
                        Toast.makeText(getApplicationContext(), "SIMSOCK_CONNECTED", Toast.LENGTH_LONG).show();
                        break;

                    case ToothSocket.MessageTypeClass.SIMSOCK_DISCONNECTED :
                        // do something with UI
                        Log.d("JOOL", "SIMSOCK_연결바이 ~~~~~~~~~! 심!솎!");
                        Toast.makeText(getApplicationContext(), "SIMSOCK_DISCONNECTED", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };

        try {
            socket = new ToothSocket(DataConstants.IP_ADDRESS, DataConstants.PORT_NUM, mHandler);
            socket.start();
        }catch (Exception e){
            Log.e("JOOL", "socket error");
        }


        // 1.5초후 시작
        doInBackgroundAfterTwoSeconds();
    }

    @Background(delay = 1500)
    void doInBackgroundAfterTwoSeconds() {
        if(socket != null) {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
        }
    }

}
