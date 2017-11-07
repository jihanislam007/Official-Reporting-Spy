package mazharul.islam.jihan.reportings_spy.RealmModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Arif on 11/6/2017.
 */

public class EmailList extends RealmObject {
    @PrimaryKey
    public String email;

    @Override
    public String toString() {
        return "EmailList{" +
                "email='" + email + '\'' +
                '}';
    }
}
