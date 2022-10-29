class ProjectStage{
    private String indicator;
    private int value;
    private String date;

    public ProjectStage(String indicator, int valueNew, String date){
        this.indicator = indicator;
        this.value = value;
        this.date = date;
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