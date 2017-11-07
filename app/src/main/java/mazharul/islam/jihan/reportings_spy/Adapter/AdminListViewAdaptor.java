package mazharul.islam.jihan.reportings_spy.Adapter;

/**
 * Created by Jihan on 11-Oct-17.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mazharul.islam.jihan.reportings_spy.JsonModel.ReportListItem;
import mazharul.islam.jihan.reportings_spy.R;

public class AdminListViewAdaptor extends BaseAdapter
{
    Activity context;
    ArrayList<ReportListItem> reportListItems;

    public AdminListViewAdaptor(Activity context, ArrayList<ReportListItem> reportListItems) {
        super();
        this.context = context;
        this.reportListItems=reportListItems;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return reportListItems.size();
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return reportListItems.get(position);
    }
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    private class ViewHolder {
        TextView nameText;
        TextView addresstxt;
        TextView timetxt;
        TextView newstxt;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.custom_listview_admin_view, null);
            holder = new ViewHolder();
            holder.nameText = (TextView) convertView.findViewById(R.id.NameTextView);
            holder.addresstxt = (TextView) convertView.findViewById(R.id.AddressTextView);
            holder.timetxt = (TextView) convertView.findViewById(R.id.TimeTextView);
            holder.newstxt = (TextView) convertView.findViewById(R.id.NewsTextView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameText.setText(reportListItems.get(position).reporterName);
        holder.addresstxt.setText(reportListItems.get(position).reportAddress);
        holder.timetxt.setText(reportListItems.get(position).dateTime.split(" ")[1]);
        holder.newstxt.setText(reportListItems.get(position).headLine);

        return convertView;
    }

}