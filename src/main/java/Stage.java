import java.text.SimpleDateFormat;
import java.util.Date;
class Stage{
    private Boolean indicator;
    private Integer value;
    private String date;
    public Stage(Boolean indicator, Integer value, String date){
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