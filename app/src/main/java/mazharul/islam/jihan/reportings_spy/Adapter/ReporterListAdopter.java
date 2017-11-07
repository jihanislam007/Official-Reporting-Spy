package mazharul.islam.jihan.reportings_spy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mazharul.islam.jihan.reportings_spy.JsonModel.ReporterListItem;
import mazharul.islam.jihan.reportings_spy.R;

/**
 * Created by Jihan on 11-Oct-17.
 */

public class ReporterListAdopter extends BaseAdapter {

    Activity context;
    ArrayList<ReporterListItem> reporterListItems;

    public ReporterListAdopter(Activity context, ArrayList<ReporterListItem> reporterListItems ) {
        super();
        this.context = context;
        this.reporterListItems=reporterListItems;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return reporterListItems.size();
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return reporterListItems.get(position);
    }
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    private class ViewHolderList {
        TextView nameText;
        TextView addresstxt;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        ReporterListAdopter.ViewHolderList holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.custom_listview_reporter_list, null);
            holder = new ReporterListAdopter.ViewHolderList();
            holder.nameText = (TextView) convertView.findViewById(R.id.NameTextView);
            holder.addresstxt = (TextView) convertView.findViewById(R.id.AddressTextView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ReporterListAdopter.ViewHolderList) convertView.getTag();
        }

        holder.nameText.setText(reporterListItems.get(position).reporterName);
        holder.addresstxt.setText(reporterListItems.get(position).reporterAddress);

        return convertView;
    }

}