package travel.nanjing.com.travel.business.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.MainActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText count;
    private EditText password;
    private Button login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        count = ((EditText) findViewById(R.id.count));
        password = ((EditText) findViewById(R.id.password));
        login = ((Button) findViewById(R.id.login));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("root".equals(count.getText().toString()) &&
                        "root".equals(password.getText().toString())) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

