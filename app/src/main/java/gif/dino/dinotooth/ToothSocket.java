package gif.dino.dinotooth;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by jool on 15. 10. 2..
 */
public class ToothSocket extends Thread{

    private Socket mSocket;

    private BufferedReader buffRecv;
    private BufferedWriter buffSend;

    private String  mAddr = "localhost";
    private int     mPort = 8080;
    private boolean mConnected = false;
    private Handler mHandler = null;
    DataOutputStream dos;
    DataInputStream dis ;
    byte[] in;
    byte[] out;

    static class MessageTypeClass {
        public static final int SIMSOCK_CONNECTED = 1;
        public static final int SIMSOCK_DATA = 2;
        public static final int SIMSOCK_DISCONNECTED = 3;
    };
    public enum MessageType { SIMSOCK_CONNECTED, SIMSOCK_DATA, SIMSOCK_DISCONNECTED };

    public ToothSocket(String addr, int port, Handler handler)
    {
        mAddr = addr;
        mPort = port;
        mHandler = handler;
    }

    private void makeMessage(MessageType what, Object obj)
    {
        Message msg = Message.obtain();
        msg.what = what.ordinal();
        msg.obj  = obj;
        mHandler.sendMessage(msg);
    }

    private boolean connect (String addr, int port)
    {
        try {
            InetSocketAddress socketAddress  = new InetSocketAddress (InetAddress.getByName(addr), port);
            mSocket = new Socket();
            mSocket.connect(socketAddress, 5000);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        if(! connect(mAddr, mPort)) return; // connect failed
        if(mSocket == null)         return;

        try {
            in = new byte[1024];
            out = new byte[1024];
            dos = new DataOutputStream(mSocket.getOutputStream());
            dis= new DataInputStream(mSocket.getInputStream());
            buffRecv = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            buffSend = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mConnected = true;

        makeMessage(MessageType.SIMSOCK_CONNECTED, "");
        Log.d("SimpleSocket", "socket_thread loop started");

        String aLine = null;

        while( ! Thread.interrupted() ){ try {

            dis.read(in, 0, in.length);
            String receive = new String(in, 0, in.length);
            String mor = receive.substring(0, 2);
            DataConstants.MORNING_TIME = Integer.getInteger(mor);

            char grade = receive.charAt(3);
            DataConstants.MORNING_GRADE= Character.digit(grade,10);

  //          aLine = buffRecv.readLine();
            if(receive != null) makeMessage(MessageType.SIMSOCK_DATA, receive);
//            if(aLine != null) makeMessage(MessageType.SIMSOCK_DATA, aLine);
            else break;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}

        makeMessage(MessageType.SIMSOCK_DISCONNECTED, "");
        Log.d("SimpleSocket", "socket_thread loop terminated");

        try {
            buffRecv.close();
            buffSend.close();
            dos.close();
            dis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mConnected = false;
    }

    synchronized public boolean isConnected(){
        return mConnected;
    }

    public void sendString(String str){
//        PrintWriter out = new PrintWriter(buffSend, true);
//        out.println(str);
//        PrintWriter out_byte = new PrintWriter(buffSend, true);
//        out = str.getBytes();
//        out_byte.print(out);
        out = str.getBytes();

        try{
            dos.write(out);
            dos.flush();
        }catch (IOException e){}

    }

    public void sendInteger(int value){
        if(value > 9) return;
        if(value < 0) return;

        byte[] out = new byte[1];
        out[0] = (byte) value;

        try{
            dos.write(out);
            dos.flush();


        }catch (IOException e){}

    }

}
