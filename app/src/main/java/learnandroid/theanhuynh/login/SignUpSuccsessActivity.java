package learnandroid.theanhuynh.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpSuccsessActivity extends AppCompatActivity {
    TextView txtEmail,txtUsername,txtPassword;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_succsess);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        btnBack = (Button) findViewById(R.id.btnBack);

        Intent intent = getIntent();
        txtUsername.setText("Username: " + intent.getStringExtra("USERNAME"));
        txtPassword.setText("Password: " + intent.getStringExtra("PASSWORD"));
        txtEmail.setText("Email: " + intent.getStringExtra("EMAIL"));
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
