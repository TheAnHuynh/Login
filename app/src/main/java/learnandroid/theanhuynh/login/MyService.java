package learnandroid.theanhuynh.login;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by An on 10/15/2017.
 */

public class MyService extends android.app.Service{
    final static String TAG = MyService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"On Bind!");
        Toast.makeText(MyService.this,"On Bind!", Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"On Unbind!");
        Toast.makeText(MyService.this,"On Unbind!", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"On Create!");
        Toast.makeText(this,"On create",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"On Start Command!");
        Toast.makeText(this,"On Start Command!",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d("TAG","On Destroy!");
        Toast.makeText(this,"On Destroy!",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
