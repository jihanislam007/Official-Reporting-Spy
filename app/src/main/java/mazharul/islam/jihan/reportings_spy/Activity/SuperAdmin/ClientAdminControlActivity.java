package mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class ClientAdminControlActivity extends AppCompatActivity {

    EditText AdminEmailAddressEditText;
    Button SenderDoneButton,
            StopAdminButton,
            DeleteDataButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_admin_control);

       //// AdminEmailAddressEditText = (EditText) findViewById(R.id.AdminEmailAddressEditText);
       // SenderDoneButton = (Button) findViewById(R.id.SenderDoneButton);
        StopAdminButton = (Button) findViewById(R.id.StopAdminButton);
       // DeleteDataButton = (Button) findViewById(R.id.DeleteDataButton);

        StopAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(ClientAdminControlActivity.this, R.anim.bounce);
                StopAdminButton.startAnimation(myAnim);

                AsyncHttpClient client=new AsyncHttpClient();
                client.get(ServerInfo.BASE_URL+"StopAdmin",new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            if(response.getBoolean("res")){
                                Toast.makeText(ClientAdminControlActivity.this, "Successfully stop admin", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

}
