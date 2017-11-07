package mazharul.islam.jihan.reportings_spy.JsonModel;

public class ReportListItem
{
    public String reporterName;
    public String dateTime;
    public int reportId;
    public String reportAddress;
    public String headLine;

    public ReportListItem() {
    }

    @Override
    public String toString() {
        return "ReportListItem{" +
                "reporterName='" + reporterName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", reportId=" + reportId +
                ", reportAddress='" + reportAddress + '\'' +
                '}';
    }
}