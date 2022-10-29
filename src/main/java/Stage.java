import java.text.SimpleDateFormat;
import java.util.Date;
class ProjectStage{
    private String indicator;
    private Integer value;
    private String date;
    public ProjectStage(String indicator, Integer value, String date){
        this.indicator = indicator;
        this.value = value;
        this.date=date;
    }
    public String getDate(){
        return date;
    }
    public String getIndicator() {
        return indicator;
    }
    public Integer getValue(){
        return value;
    }
}