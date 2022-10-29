
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;

public class Main extends Application {
    static ArrayList<Project> projects = new ArrayList<Project>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("timeline.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Project Timeline Viewer");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String projFile = "Projects.xls";
        String stagesFile = "Stages.xls";
        String detailedStagesFile = "Stages_Detailed.xls";
        try {
            Sheet projSheet = WorkbookFactory.create(new FileInputStream(projFile)).getSheetAt(0);
            Sheet stagesSheet = WorkbookFactory.create(new FileInputStream(stagesFile)).getSheetAt(0);// Assuming we are working with one sheet only
            Sheet detailedStagesSheet = WorkbookFactory.create(new FileInputStream(detailedStagesFile)).getSheetAt(0);
            DataFormatter formatter = new DataFormatter(); // idk what this is

            for (int i = 1; i < projSheet.getLastRowNum(); i++) { // PROJECT ROW FOR LOOP
                ArrayList<ProjectStage> stages = new ArrayList<ProjectStage>();
                Row projRow = projSheet.getRow(i); // ?????????????????????????????????????????


                // Getting project details
                String projID = projRow.getCell(0).getRichStringCellValue().getString();
                String customerProjID = projRow.getCell(1).getRichStringCellValue().getString();
                int projectStage = Integer.parseInt(formatter.formatCellValue(projRow.getCell(2)));
                String startDate = formatter.formatCellValue(projRow.getCell(3));
                String endDate = formatter.formatCellValue(projRow.getCell(4));

                System.out.print("PROJECT DETAILS: ");
                System.out.println("[" + i + "]\t" + projID + "\t" + customerProjID + "\t" + projectStage + "\t" + startDate + "\t" + endDate);

                Project currentProject = new Project(projID, projectStage, startDate, endDate);
//
// ----------------------------------------------------------------------------------------------
//
                for (int j = 1; j < stagesSheet.getLastRowNum(); j++) {
                    Row stageRow = stagesSheet.getRow(j);
//                    Row detailedStageRow = detailedStagesSheet.getRow(j);
                    String stageID = stageRow.getCell(0).getRichStringCellValue().getString(); // aka Object Value
                    System.out.println("Proj:" +projID + "\nStage:" + stageID);

                    if(projID.equals(stageID)){
                        int docNo = Integer.parseInt(formatter.formatCellValue(stageRow.getCell(1)));
                        String fieldName = stageRow.getCell(2).getRichStringCellValue().getString();
                        String indicator = stageRow.getCell(3).getRichStringCellValue().getString();
                        int textflag = Integer.parseInt(formatter.formatCellValue(stageRow.getCell(4)));
                        int valueNew = Integer.parseInt(formatter.formatCellValue(stageRow.getCell(5)));
//                        int valueOld = Integer.parseInt(formatter.formatCellValue(stageRow.getCell(6)));

                        for (int k = 1; k < detailedStagesSheet.getLastRowNum(); k++) {
                            Row detailRow = detailedStagesSheet.getRow(k);
                            int docNoDetail = Integer.parseInt(formatter.formatCellValue(detailRow.getCell(1)));

                            if (docNo == docNoDetail) {
                                String dateDetail = formatter.formatCellValue(detailRow.getCell(3));
//                                String timeDetail = // CELL VALUE
//                                String lang = detailRow.getCell(5).getRichStringCellValue().getString();

                                ProjectStage projStage = new ProjectStage(indicator, valueNew, dateDetail);
                                currentProject.addStage(projStage);
                                break;
                            }
                        }
                    }
                }
// ------------------------------------------------------------------------------------------------

                projects.add(currentProject);
            }
            System.out.println(projects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch();}
}