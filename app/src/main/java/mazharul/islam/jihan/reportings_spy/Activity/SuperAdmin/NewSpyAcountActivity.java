package mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import mazharul.islam.jihan.reportings_spy.JsonModel.ReporterListItem;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class NewSpyAcountActivity extends AppCompatActivity {
    EditText PasswordCarecterOneEditText,PasswordCarecterTwoEditText,PasswordCarecterThreeEditText,PasswordNumberOneEditText,PasswordNumberTwoEditText,PasswordNumberThreeEditText;

    EditText reporter_type , reporter_name , reporter_address , user_name;
    ReporterListItem userInformation;
    TextView header;
    Button btnSaveUserInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_spy_acount);


        PasswordCarecterOneEditText= (EditText) findViewById(R.id.PasswordCarecterOneEditText);
        PasswordCarecterTwoEditText= (EditText) findViewById(R.id.PasswordCarecterTwoEditText);
        PasswordCarecterThreeEditText= (EditText) findViewById(R.id.PasswordCarecterThreeEditText);
        PasswordNumberOneEditText= (EditText) findViewById(R.id.PasswordNumberOneEditText);
        PasswordNumberTwoEditText= (EditText) findViewById(R.id.PasswordNumberTwoEditText);
        PasswordNumberThreeEditText= (EditText) findViewById(R.id.PasswordNumberThreeEditText);


        header= (TextView) findViewById(R.id.header);
        btnSaveUserInformation= (Button) findViewById(R.id.btnSaveUserInformation);
        btnSaveUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });


     //   reporter_type= (EditText) findViewById(R.id.ReportTypeEditText);
        reporter_name= (EditText) findViewById(R.id.ReportNameEditText);
        reporter_address= (EditText) findViewById(R.id.ReportAdressEditText);
        user_name= (EditText) findViewById(R.id.ReportUserNameEditText);

        editTextFocusController();

        try {
            Gson gson=new Gson();
            String userInfo=getIntent().getStringExtra("user");
            if(userInfo.length()>1){
                userInformation=gson.fromJson(userInfo,ReporterListItem.class);
                setUserInformation(userInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void saveUserInformation(){
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        if(userInformation!=null){
            params.add("id",userInformation.reporterId+"");
        }else{
            params.add("id",1+"");
        }
        params.add("type","spy");
        params.add("status","");
        params.add("name",reporter_name.getText().toString());
        params.add("address",reporter_address.getText().toString());
        params.add("user_name",user_name.getText().toString());
        params.add("password",getPassword());
        client.post(ServerInfo.BASE_URL+"SaveUser/",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(NewSpyAcountActivity.this, "Successfully save reporter", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUserInformation(ReporterListItem userInformation){

      //  reporter_type.setText(userInformation.reporterType);
        reporter_name.setText(userInformation.reporterName);
        reporter_address.setText(userInformation.reporterAddress);
        user_name.setText(userInformation.userName);
        setPassword(userInformation.password);

        header.setText("User Information Edit");

    }

    public void setPassword(String password){
        try {
            PasswordCarecterOneEditText.setText(password.charAt(0)+"");
            PasswordCarecterTwoEditText.setText(password.charAt(1)+"");
            PasswordCarecterThreeEditText.setText(password.charAt(2)+"");
            PasswordNumberOneEditText.setText(password.charAt(3)+"");
            PasswordNumberTwoEditText.setText(password.charAt(4)+"");
            PasswordNumberThreeEditText.setText(password.charAt(5)+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        PasswordCarecterOneEditText.addTextChangedListener(new TextWatcher() {
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
    }
    public String getPassword() {
        return PasswordCarecterOneEditText.getText().toString() + PasswordCarecterTwoEditText.getText().toString() +
                PasswordCarecterThreeEditText.getText().toString() + PasswordNumberOneEditText.getText().toString() +
                PasswordNumberTwoEditText.getText().toString() + PasswordNumberThreeEditText.getText().toString();
    }

}
