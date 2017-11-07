package mazharul.islam.jihan.reportings_spy.JsonModel;

public class AdminMessageItem
{
    public String datetime;
    public String msg;

    public AdminMessageItem() {
    }

    @Override
    public String toString() {
        return "AdminMessageItem{" +
                "datetime='" + datetime + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}