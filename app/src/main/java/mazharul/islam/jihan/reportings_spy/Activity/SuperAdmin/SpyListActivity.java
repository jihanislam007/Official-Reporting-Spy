package mazharul.islam.jihan.reportings_spy.Activity.SuperAdmin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mazharul.islam.jihan.reportings_spy.Adapter.ReporterListAdopter;
import mazharul.islam.jihan.reportings_spy.JsonModel.ReporterListItem;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class SpyListActivity extends AppCompatActivity {

    ListView listView;
    Dialog mDialog;
    Button delete , edit , cancel;
    ArrayList<ReporterListItem> reporterListItems=new ArrayList<>();
    ReporterListAdopter reporterListAdopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spy_list);


        ///////////////////listView/////////////////////////////
        listView = (ListView) findViewById(R.id.ReporterListListView);
        reporterListAdopter = new ReporterListAdopter(this, reporterListItems);
        listView.setAdapter(reporterListAdopter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pop(position);

            }
        });

        ///////////////////listView/////////////////////////////

        getAllReporter();
    }


    ////////////////for popup////////////////////////
    public void pop(final int position) {

        mDialog = new Dialog(SpyListActivity.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.popup_layout_reporter_details);

        delete = (Button) mDialog.findViewById(R.id.DeleteButton);
        edit = (Button) mDialog.findViewById(R.id.EditButton);
        cancel = (Button) mDialog.findViewById(R.id.CncelButton);

        TextView nameTextView = (TextView) mDialog.findViewById(R.id.nameTextView);
        nameTextView.setText(reporterListItems.get(position).reporterName);
        TextView type = (TextView) mDialog.findViewById(R.id.type);
        type.setText(reporterListItems.get(position).reporterType);
        TextView address = (TextView) mDialog.findViewById(R.id.address);
        address.setText(reporterListItems.get(position).reporterAddress);
        TextView userName = (TextView) mDialog.findViewById(R.id.userName);
        userName.setText(reporterListItems.get(position).userName);
        TextView password = (TextView) mDialog.findViewById(R.id.password);
        password.setText(reporterListItems.get(position).password);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReporter(reporterListItems.get(position).reporterId);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                String userInfo=gson.toJson(reporterListItems.get(position));
                Intent in = new Intent(SpyListActivity.this, NewSpyAcountActivity.class);
                in.putExtra("user",userInfo);
                startActivity(in);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });


        mDialog.show();

    }

    public void getAllReporter() {
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(ServerInfo.BASE_URL+"GetAllReporter/",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                reporterListItems.clear();
                reporterListAdopter.notifyDataSetChanged();
                Gson gson=new Gson();
                Type type = new TypeToken<ArrayList<ReporterListItem>>() {}.getType();
                ArrayList<ReporterListItem> listItems=gson.fromJson(response.toString(),
                        type);
                reporterListItems.addAll(listItems);
                reporterListAdopter.notifyDataSetChanged();

            }
        });
    }
    public void deleteReporter(int reporterId){
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.add("id",reporterId+"");
        client.post(ServerInfo.BASE_URL+"DeleteReporter/",params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(SpyListActivity.this, "Successfully delete a reporter", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
                getAllReporter();

            }
        });
    }
    ////////////////for popup////////////////////////
}
