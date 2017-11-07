package mazharul.islam.jihan.reportings_spy.Offline;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arif on 8/4/2017.
 */

public class OfflineInfo {
    SharedPreferences sharedpreferences;
    Context context;
    public OfflineInfo(Context context){
        if(context==null){
            System.out.println("Context is null....");
        }
        this.context=context;
        sharedpreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
    }

    public void saveUserName(String s){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("username",s);
        editor.commit();
    }

    public String getUserName(){
        return sharedpreferences.getString("username","");
    }


    public void saveEmail(String s){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("email",s);
        editor.commit();
    }

    public String getEmail(){
        return sharedpreferences.getString("email","");
    }


    public void saveEmailPassword(String s){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("email_password",s);
        editor.commit();
    }

    public String getEmailPassword(){
        return sharedpreferences.getString("email_password","");
    }


    public void clearAll(){
        sharedpreferences.edit().clear().apply();
    }
}
