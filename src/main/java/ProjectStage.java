import java.text.SimpleDateFormat;
import java.util.Date;
class ProjectStage{
    private boolean indicator;
    private int value;
    private String date;
    public ProjectStage(boolean indicator, int value, String date){
        this.indicator = indicator;
        this.value = value;
        this.date=date;
    }
    public String getDate(){
        return date;
    }
    public Boolean getIndicator() {
        return indicator;
    }
    public Integer getValue(){
        return value;
    }
}