
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


class timelineController {
    @FXML
    public TableView<Project> tvProject;
    @FXML
    public TableColumn<Project, String> tcID;
    @FXML
    public TableColumn<Project, String> tcDateStart;
    @FXML
    public TableColumn<Project, String> tcDateEnd;

//    ObservableList<Project> projectList = FXCollections.observableArrayList(Main.projects);
    ObservableList<Project> projectList = FXCollections.observableArrayList();
//        for (int i = 0; i < Main.projects.size(); i++) {

//    }

    public void initialize() {

//        tcID.setcellro
//        tcID. Main.projects.get(i).getProjectID();
        projectList.addAll(Main.projects);
//        tcID.
//        tcID.setCellFactory(new PropertyValueFactory<>("projectID"));
//        tcID.setCellFactory(cellData);
//        tcID.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        tcID.setCellValueFactory(new PropertyValueFactory<Project, String>("projectID"));
//        tcDateStart.setCellValueFactory(new PropertyValueFactory<Project, String>("dateStart"));
//        tcDateEnd.setCellValueFactory(new PropertyValueFactory<Project, String>("dateEnd"));
        tvProject.setItems(projectList);
    }
}
