package mazharul.islam.jihan.reportings_spy.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import mazharul.islam.jihan.reportings_spy.Activity.SpyActivity.SpyNewsViewActivity;
import mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin.ForgotPasswordActivity;
import mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin.SuperAdminViewActivity;
import mazharul.islam.jihan.reportings_spy.Offline.OfflineInfo;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class LogInActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner logInSpiner;
    EditText user_name;
    Button logIn;
    TextView forgot_pass;
    EditText PasswordCarecterOneEditText,
            PasswordCarecterTwoEditText,
            PasswordCarecterThreeEditText,
            PasswordNumberOneEditText,
            PasswordNumberTwoEditText,
            PasswordNumberThreeEditText;
    OfflineInfo offlineInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        offlineInfo = new OfflineInfo(this);
        takePermission();

        PasswordCarecterOneEditText = (EditText) findViewById(R.id.PasswordCarecterOneEditText);
        PasswordCarecterTwoEditText = (EditText) findViewById(R.id.PasswordCarecterTwoEditText);
        PasswordCarecterThreeEditText = (EditText) findViewById(R.id.PasswordCarecterThreeEditText);
        PasswordNumberOneEditText = (EditText) findViewById(R.id.PasswordNumberOneEditText);
        PasswordNumberTwoEditText = (EditText) findViewById(R.id.PasswordNumberTwoEditText);
        PasswordNumberThreeEditText = (EditText) findViewById(R.id.PasswordNumberThreeEditText);

        editTextFocusController();
        logInSpiner = (Spinner) findViewById(R.id.LogInSpinner);
        user_name = (EditText) findViewById(R.id.UserNameEditText);
        //   password = (EditText) findViewById(R.id.PasswordEditText);
        logIn = (Button) findViewById(R.id.LogInButton);
        forgot_pass = (TextView) findViewById(R.id.ForgotPassTextView);

        ///////////////Spinner////////////////////////////
        // Spinner click listener
        logInSpiner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Viewer");
        categories.add("Super Admin");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        logInSpiner.setAdapter(dataAdapter);

        ///////////////Spinner////////////////////////////
    }

    private void takePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            }, 1);
        }
    }

    public String getPassword() {
        return PasswordCarecterOneEditText.getText().toString() + PasswordCarecterTwoEditText.getText().toString() +
                PasswordCarecterThreeEditText.getText().toString() + PasswordNumberOneEditText.getText().toString() +
                PasswordNumberTwoEditText.getText().toString() + PasswordNumberThreeEditText.getText().toString();
    }

    //Focus controller for password
    private void editTextFocusController() {
        PasswordCarecterOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {
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
                if (s.length() >= 1) {
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
                if (s.length() >= 1) {
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
                if (s.length() >= 1) {
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
                if (s.length() >= 1) {
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
                if (s.length() >= 1) {
                    PasswordNumberThreeEditText.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        // ,,,,
    }

    //////////////////Spinner///////////////
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*// On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();*/

        if (position == 0) {
            forgot_pass.setVisibility(View.INVISIBLE);
            logIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isConnected(LogInActivity.this)){
                        Toast.makeText(LogInActivity.this,"No internet connection available.Please connect with internet",Toast.LENGTH_SHORT).show();
                        return;
                    }


                    final ProgressDialog dialog = ProgressDialog.show(LogInActivity.this, "",
                            "Login ... Please wait...", true);
                    dialog.show();

                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("username", user_name.getText().toString());
                    params.add("password", getPassword());
                    params.add("type", "spy");

                    offlineInfo.saveUserName( user_name.getText().toString());

                    client.post(ServerInfo.BASE_URL + "UserLogin/", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                if (response.getBoolean("res")) {
                                    Toast.makeText(LogInActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(LogInActivity.this, SpyNewsViewActivity.class);
                                    startActivity(in);
                                    finish();
                                } else {
                                    Toast.makeText(LogInActivity.this, "User name or password invalid", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                            }
                        }

                        @Override
                        public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
                            dialog.dismiss();
                        }
                    });


                }
            });

        } else if (position == 1) {
            forgot_pass.setVisibility(View.VISIBLE);
            logIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(!isConnected(LogInActivity.this)){
                        Toast.makeText(LogInActivity.this,"No internet connection available.Please connect with internet",Toast.LENGTH_SHORT).show();
                        return;
                    }


                    final ProgressDialog dialog = ProgressDialog.show(LogInActivity.this, "",
                            "Login ... Please wait...", true);
                    dialog.show();

                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("username", user_name.getText().toString());
                    params.add("password", getPassword());
                    params.add("type", "admin");

                    offlineInfo.saveUserName( user_name.getText().toString());
                    client.post(ServerInfo.BASE_URL + "login/", params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                if (response.getBoolean("res")) {
                                    Toast.makeText(LogInActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(LogInActivity.this, SuperAdminViewActivity.class);
                                    startActivity(in);
                                    finish();

                                } else {
                                    Toast.makeText(LogInActivity.this, "User name or password invalid", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                            }
                        }
                        @Override
                        public void onPostProcessResponse(ResponseHandlerInterface instance, HttpResponse response) {
                            dialog.dismiss();
                        }
                    });



                }

            });
        }

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LogInActivity.this, ForgotPasswordActivity.class);
                startActivity(in);

            }
        });
        /*// Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item + id, Toast.LENGTH_LONG).show();*/
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    //////////////////Spinner///////////////


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    static public boolean isConnected(Context context) {
        boolean hasConnection;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        hasConnection = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return hasConnection;
    }
}