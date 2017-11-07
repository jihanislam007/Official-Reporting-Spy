package mazharul.islam.jihan.reportings_spy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mazharul.islam.jihan.reportings_spy.JsonModel.AdminMessageItem;
import mazharul.islam.jihan.reportings_spy.R;

/**
 * Created by Jihan on 11-Oct-17.
 */

public class CentralMessageGetAdaptor extends BaseAdapter{

    Activity context;
    ArrayList<AdminMessageItem> messageItems;

    public CentralMessageGetAdaptor(Activity context, ArrayList<AdminMessageItem> messageItems) {
        super();
        this.context = context;
        this.messageItems=messageItems;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return messageItems.size();
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return messageItems.get(position);
    }
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public class ViewHolder {
        public TextView dateText;
        public TextView timetxt;
        public TextView messagetxt;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        CentralMessageGetAdaptor.ViewHolder holder;
        LayoutInflater inflater =  context.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.custom_listview_central_get_message, null);
            holder = new CentralMessageGetAdaptor.ViewHolder();
            holder.dateText = (TextView) convertView.findViewById(R.id.DateTextView);
            holder.timetxt = (TextView) convertView.findViewById(R.id.TimeTextView);
            holder.messagetxt = (TextView) convertView.findViewById(R.id.MessageTextView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (CentralMessageGetAdaptor.ViewHolder) convertView.getTag();
        }

        holder.dateText.setText(messageItems.get(position).datetime.split(" ")[0]);
        holder.timetxt.setText(messageItems.get(position).datetime.split(" ")[1]);
        holder.messagetxt.setText(messageItems.get(position).msg);
     //   holder.messagetxt.setText("Hello Admin");
       // System.out.println(messageItems.get(position));

        return convertView;
    }

}