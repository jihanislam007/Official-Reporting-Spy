package mazharul.islam.jihan.reportings_spy.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import io.realm.Realm;
import io.realm.RealmResults;
import mazharul.islam.jihan.reportings_spy.Offline.OfflineInfo;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.RealmModel.EmailList;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class SettingsActivity extends AppCompatActivity {

    EditText SenderEmailAddress,
            SenderEmailPassword,
            ReciverEmailAddress;
    ListView SettingListView;
    Button SenderDoneButton,
            AddReciverMail;
    ImageView LessSecureImageView;
    OfflineInfo offlineInfo;
    ArrayList<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    Realm realm;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context=this;
        realm = Realm.getDefaultInstance();
        offlineInfo = new OfflineInfo(this);
        SenderEmailAddress = (EditText) findViewById(R.id.SenderEmailAddressEditText);
        SenderEmailAddress.setText(offlineInfo.getEmail());
        SenderEmailPassword = (EditText) findViewById(R.id.EmailPasswordEditText);
        SenderEmailPassword.setText(offlineInfo.getEmailPassword());

        ReciverEmailAddress = (EditText) findViewById(R.id.ReciverEmailAddressEditText);

        SettingListView = (ListView) findViewById(R.id.SettingListView);
        AddReciverMail = (Button) findViewById(R.id.EmailAddButton);
        SenderDoneButton = (Button) findViewById(R.id.SenderDoneButton);
        LessSecureImageView = (ImageView) findViewById(R.id.LessSecureImageView);




        adapter = new ArrayAdapter<String>(this,
                R.layout.setting_listview_layout, data);
        SettingListView.setAdapter(adapter);

        SenderDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offlineInfo.saveEmail(SenderEmailAddress.getText().toString());
                offlineInfo.saveEmailPassword(SenderEmailPassword.getText().toString());


                AsyncHttpClient client=new AsyncHttpClient();
                RequestParams params=new RequestParams();
                params.add("email",SenderEmailAddress.getText().toString());
                params.add("password",SenderEmailPassword.getText().toString());
                client.post(ServerInfo.BASE_URL+"SetEmail/",params,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            if(response.getBoolean("res")){
                                Toast.makeText(context, "Successfully save", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        AddReciverMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(ReciverEmailAddress.getText().toString());
                adapter.notifyDataSetChanged();

                realm.beginTransaction();
                EmailList emailList=new EmailList();
                emailList.email=ReciverEmailAddress.getText().toString();
                realm.insertOrUpdate(emailList);
                realm.commitTransaction();
                loadEmailList();
                Toast.makeText(context, "Successfully saved", Toast.LENGTH_SHORT).show();
            }
        });

        SettingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Delete Email");
                builder.setMessage("Are you sure want to delete this email?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteEmail(data.get(position));
                        dialogInterface.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // I do not need any action here you might
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



                return false;
            }
        });
        LessSecureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myaccount.google.com/lesssecureapps"));
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, "com.android.browser");
                context.startActivity(intent);
            }
        });
        loadEmailList();

    }
    public void deleteEmail(String email){
        EmailList emailList=realm.where(EmailList.class).equalTo("email",email).findFirst();
        realm.beginTransaction();
        emailList.deleteFromRealm();
        realm.commitTransaction();
        loadEmailList();

    }
    public void loadEmailList(){
        data.clear();
        adapter.notifyDataSetChanged();
        RealmResults<EmailList> listEmail=realm.where(EmailList.class).findAll();
        for (EmailList email:listEmail
                ) {
            data.add(email.email);
        }
        adapter.notifyDataSetChanged();
    }

}
