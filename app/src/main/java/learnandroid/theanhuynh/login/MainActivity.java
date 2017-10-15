package learnandroid.theanhuynh.login;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();

    EditText txtUsername,txtEmail,txtPassword,txtConfirmPassword;
    Button btnSignup;

    BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this,"On Receive",Toast.LENGTH_SHORT).show();
            Log.d(TAG,"On receive!");
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            boolean isConnected = (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting());

            if(isConnected){
                Toast.makeText(MainActivity.this,"Network is connected!",Toast.LENGTH_LONG).show();
                Log.d(TAG,"Started Service!");
                startService(new Intent(MainActivity.this,MyService.class));
            }
            else{
                Toast.makeText(MainActivity.this,"Network is NOT connected!",Toast.LENGTH_LONG).show();
                Log.d(TAG,"Stopped Service!");
                stopService(new Intent(MainActivity.this,MyService.class));
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtPasswordConfirm);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnSignup = (Button) findViewById(R.id.btnSignup);
    }

    private void addEvents() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String passwordComfirm = txtConfirmPassword.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty() || passwordComfirm.isEmpty() || email.isEmpty()){
                    Toast.makeText(MainActivity.this,"Bạn điền thiếu thông tin",Toast.LENGTH_LONG).show();
                }else{
                    if(!password.equals(passwordComfirm)){
                        Toast.makeText(MainActivity.this,"Xác nhận password không khớp",Toast.LENGTH_LONG).show();
                    }else{
                        if(!email.contains("@")){
                            Toast.makeText(MainActivity.this,"Email sai cấu trúc",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this,SignUpSuccsessActivity.class);
                            intent.putExtra("USERNAME",username);
                            intent.putExtra("PASSWORD",password);
                            intent.putExtra("EMAIL",email);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(networkChangeReceiver !=null){
            unregisterReceiver(networkChangeReceiver);
        }
    }
}
