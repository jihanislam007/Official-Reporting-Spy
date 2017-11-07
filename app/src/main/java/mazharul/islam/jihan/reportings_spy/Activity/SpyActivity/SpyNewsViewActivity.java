package mazharul.islam.jihan.reportings_spy.Activity.SpyActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import mazharul.islam.jihan.reportings_spy.Activity.DetailsNewsViewActivity;
import mazharul.islam.jihan.reportings_spy.Activity.SettingsActivity;
import mazharul.islam.jihan.reportings_spy.Adapter.AdminListViewAdaptor;
import mazharul.islam.jihan.reportings_spy.Adapter.SpyListAdopter;
import mazharul.islam.jihan.reportings_spy.JsonModel.ReportListItem;
import mazharul.islam.jihan.reportings_spy.R;
import mazharul.islam.jihan.reportings_spy.ServerInfo.ServerInfo;

public class SpyNewsViewActivity extends AppCompatActivity {

    ListView listView;
    ImageView setting;
    ArrayList<ReportListItem> reportListItems=new ArrayList<ReportListItem>();
    PullRefreshLayout swipeRefreshLayout;
    SpyListAdopter spyListAdopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spy_news_view);

        setting  = (ImageView) findViewById(R.id.settingImageView);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SpyNewsViewActivity.this, SettingsActivity.class);
                startActivity(in);
            }
        });


        ///////////////////listView/////////////////////////////

        swipeRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadReportList();

            }
        });

        listView = (ListView) findViewById(R.id.SpyNewsListView);
        spyListAdopter = new SpyListAdopter(SpyNewsViewActivity.this, reportListItems);
        listView.setAdapter(spyListAdopter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = new Intent(SpyNewsViewActivity.this , DetailsNewsViewActivity.class);
                startActivity(in);
            }
        });
    }

    public void loadReportList(){
        System.out.println("try to load server data");
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(ServerInfo.BASE_URL+"GetAllReport/",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                reportListItems.clear();
                spyListAdopter.notifyDataSetChanged();
                Gson gson=new Gson();
                Type type = new TypeToken<ArrayList<ReportListItem>>() {}.getType();
                ArrayList<ReportListItem> listItems=gson.fromJson(response.toString(),
                        type);

                reportListItems.addAll(listItems);
                spyListAdopter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}

