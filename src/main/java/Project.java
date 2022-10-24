import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class Project{
    private Stage[] stageList;
    private String projectID;
    private int stage;
    private String dateStart;
    private String dateEnd;
    //    private String nodeID;
//    private int customerNo;
//    private String dateCreation;
//    private String dateChanged;
//    private String currency;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public Project(String projectID, int stage, String dateStart, String dateEnd){
        this.projectID = projectID;
        this.stage = stage;
        this.dateStart=dateStart;
        this.dateEnd=dateEnd;
    }
    public void setStages(Stage[] setstages){
        this.stageList = setstages;
    }
    public long getDuration() throws ParseException {
        return (format.parse(dateEnd).getTime() - format.parse(dateStart).getTime())/((1000 * 60 * 60 * 24)%365);
    }
    public String getStartDate(){
        return dateStart;
    }
    public String getEndDate(){
        return dateEnd;
    }
    public String getProjectID(){
        return projectID;
    }
}