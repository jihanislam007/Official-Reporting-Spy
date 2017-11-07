package mazharul.islam.jihan.reportings_spy.Activity.SpyActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import mazharul.islam.jihan.reportings_spy.Activity.DetailsNewsViewActivity;
import mazharul.islam.jihan.reportings_spy.Activity.SettingsActivity;
import mazharul.islam.jihan.reportings_spy.Adapter.AdminListViewAdaptor;
import mazharul.islam.jihan.reportings_spy.Adapter.SpyListAdopter;
import mazharul.islam.jihan.reportings_spy.JsonModel.ReportListItem;
import mazharul.islam.jihan.reportings_spy.R;

public class SpyNewsViewActivity extends AppCompatActivity {

    ListView listView;
    ImageView setting;
    ArrayList<ReportListItem> reportListItems=new ArrayList<ReportListItem>();

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
        listView = (ListView) findViewById(R.id.SpyNewsListView);
        SpyListAdopter spyListAdopter = new SpyListAdopter(SpyNewsViewActivity.this, reportListItems);
        listView.setAdapter(spyListAdopter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = new Intent(SpyNewsViewActivity.this , DetailsNewsViewActivity.class);
                startActivity(in);
            }
        });
    }
}

