package mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class SuperAdminPasswordChangActivity extends AppCompatActivity {
    EditText PasswordCarecterOneEditText,PasswordCarecterTwoEditText,PasswordCarecterThreeEditText,PasswordNumberOneEditText,PasswordNumberTwoEditText,PasswordNumberThreeEditText;

    EditText oldPasswordCarecterOneEditText,oldPasswordCarecterTwoEditText,oldPasswordCarecterThreeEditText,oldPasswordNumberOneEditText,oldPasswordNumberTwoEditText,oldPasswordNumberThreeEditText;
    Button changeButton;
    EditText UserNameEditText;
    private boolean getResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_admin_password_chang);

        PasswordCarecterOneEditText= (EditText) findViewById(R.id.PasswordCarecterOneEditText);
        PasswordCarecterTwoEditText= (EditText) findViewById(R.id.PasswordCarecterTwoEditText);
        PasswordCarecterThreeEditText= (EditText) findViewById(R.id.PasswordCarecterThreeEditText);
        PasswordNumberOneEditText= (EditText) findViewById(R.id.PasswordNumberOneEditText);
        PasswordNumberTwoEditText= (EditText) findViewById(R.id.PasswordNumberTwoEditText);
        PasswordNumberThreeEditText= (EditText) findViewById(R.id.PasswordNumberThreeEditText);

        oldPasswordCarecterOneEditText= (EditText) findViewById(R.id.OldPasswordCarecterOneEditText);
        oldPasswordCarecterTwoEditText= (EditText) findViewById(R.id.OldPasswordCarecterTwoEditText);
        oldPasswordCarecterThreeEditText= (EditText) findViewById(R.id.OldPasswordCarecterThreeEditText);
        oldPasswordNumberOneEditText= (EditText) findViewById(R.id.OldPasswordNumberOneEditText);
        oldPasswordNumberTwoEditText= (EditText) findViewById(R.id.OldPasswordNumberTwoEditText);
        oldPasswordNumberThreeEditText= (EditText) findViewById(R.id.OldPasswordNumberThreeEditText);

        UserNameEditText= (EditText) findViewById(R.id.UserNameEditText);


        changeButton= (Button) findViewById(R.id.changeButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        editTextFocusController();

    }

    private void changePassword() {
        getResult=false;
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.add("userName",UserNameEditText.getText().toString());
        params.add("oldPassword",getOldPassword());
        params.add("newPassword",getNewPassword());
        client.post(ServerInfo.BASE_URL+"AdminPasswordChange/",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getBoolean("res")){
                        Toast.makeText(SuperAdminPasswordChangActivity.this, "Successfully change password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SuperAdminPasswordChangActivity.this, "User Name Or Password Does not match in admin account", Toast.LENGTH_SHORT).show();
                    }
                    getResult=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
                if(!getResult){
                    Toast.makeText(SuperAdminPasswordChangActivity.this, "User Name Is InValid", Toast.LENGTH_SHORT).show();
                }
                getResult=false;
            }
        });
    }

    public String getOldPassword(){
        return
                oldPasswordCarecterOneEditText.getText().toString()+
                        oldPasswordCarecterTwoEditText.getText().toString()+
                        oldPasswordCarecterThreeEditText.getText().toString()+
                        oldPasswordNumberOneEditText.getText().toString()+
                        oldPasswordNumberTwoEditText.getText().toString()+
                        oldPasswordNumberThreeEditText.getText().toString();
    }
    public String getNewPassword(){
        return
                PasswordCarecterOneEditText.getText().toString()+
                        PasswordCarecterTwoEditText.getText().toString()+
                        PasswordCarecterThreeEditText.getText().toString()+
                        PasswordNumberOneEditText.getText().toString()+
                        PasswordNumberTwoEditText.getText().toString()+
                        PasswordNumberThreeEditText.getText().toString();
    }

    private void editTextFocusController() {
        PasswordCarecterOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    PasswordCarecterOneEditText.clearFocus();
                    PasswordCarecterTwoEditText.requestFocus();
                    PasswordCarecterTwoEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PasswordCarecterTwoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    PasswordCarecterTwoEditText.clearFocus();
                    PasswordCarecterThreeEditText.requestFocus();
                    PasswordCarecterThreeEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PasswordCarecterThreeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    PasswordCarecterThreeEditText.clearFocus();
                    PasswordNumberOneEditText.requestFocus();
                    PasswordNumberOneEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PasswordNumberOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    PasswordNumberOneEditText.clearFocus();
                    PasswordNumberTwoEditText.requestFocus();
                    PasswordNumberTwoEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PasswordNumberTwoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    PasswordNumberTwoEditText.clearFocus();
                    PasswordNumberThreeEditText.requestFocus();
                    PasswordNumberThreeEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        PasswordNumberThreeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    PasswordNumberThreeEditText.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // ,,,,

        oldPasswordCarecterOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    oldPasswordCarecterOneEditText.clearFocus();
                    oldPasswordCarecterTwoEditText.requestFocus();
                    oldPasswordCarecterTwoEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        oldPasswordCarecterTwoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    oldPasswordCarecterTwoEditText.clearFocus();
                    oldPasswordCarecterThreeEditText.requestFocus();
                    oldPasswordCarecterThreeEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        oldPasswordCarecterThreeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    oldPasswordCarecterThreeEditText.clearFocus();
                    oldPasswordNumberOneEditText.requestFocus();
                    oldPasswordNumberOneEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        oldPasswordNumberOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    oldPasswordNumberOneEditText.clearFocus();
                    oldPasswordNumberTwoEditText.requestFocus();
                    oldPasswordNumberTwoEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        oldPasswordNumberTwoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    oldPasswordNumberTwoEditText.clearFocus();
                    oldPasswordNumberThreeEditText.requestFocus();
                    oldPasswordNumberThreeEditText.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        oldPasswordNumberThreeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=1){
                    oldPasswordNumberThreeEditText.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
