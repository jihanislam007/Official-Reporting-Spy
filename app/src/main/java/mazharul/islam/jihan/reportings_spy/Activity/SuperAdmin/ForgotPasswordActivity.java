package mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import mazharul.islam.jihan.reportings_spy.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText email;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.EmailEditText);
        confirm = (Button) findViewById(R.id.ConfirmButton);

    }
}
