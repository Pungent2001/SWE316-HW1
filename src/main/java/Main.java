
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.poi.ss.usermodel.DataFormatter;

public class Main extends Application {
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

        ArrayList<Project> projects = new ArrayList<Project>();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String projFile = "Projects.xls";
        String detailedStagesFile = "Stages_Detailed.xls";
        String stagesFile = "Stages.xls";
        try {
            Sheet projSheet = WorkbookFactory.create(new FileInputStream(projFile)).getSheetAt(0);
            Sheet detailedStagesSheet = WorkbookFactory.create(new FileInputStream(detailedStagesFile)).getSheetAt(0);
            Sheet stagesSheet = WorkbookFactory.create(new FileInputStream(stagesFile)).getSheetAt(0);// Assuming we are working with one sheet only
            DataFormatter formatter = new DataFormatter(); // idk what this is

            for (int i = 1; i < projSheet.getLastRowNum(); i++) {
                ArrayList<ProjectStage> stages = new ArrayList<ProjectStage>();
                Row projRow = projSheet.getRow(i);

                System.out.println("Row " + i + "\n"
                        + projRow.getCell(1).getRichStringCellValue().getString()+"\n"
                        + Integer.parseInt(formatter.formatCellValue(projRow.getCell(2)))+"\n"
                        + formatter.formatCellValue(projRow.getCell(3))+"\n"
                        + formatter.formatCellValue(projRow.getCell(4))+"\n");

                String projID = projRow.getCell(0).getRichStringCellValue().getString();

                Project currentProject = new Project(projRow.getCell(1).getRichStringCellValue().getString(),
                        Integer.parseInt(formatter.formatCellValue(projRow.getCell(2))),
                        formatter.formatCellValue(projRow.getCell(3)),
                        formatter.formatCellValue(projRow.getCell(4)));
// ----------------------------------------------------------------------------------------------
                for (int j = 1; i < detailedStagesSheet.getLastRowNum(); i++) {
                    Row stageRow = stagesSheet.getRow(j);
                    Row detailedStageRow = detailedStagesSheet.getRow(j);
                    String stageID = stageRow.getCell(0).getRichStringCellValue().getString();
                    System.out.println("Proj:" +projID + "\nStage:" + stageID);
                    boolean indicator = false;

                    if(Objects.equals(stageID, projID)){
                        if(stageRow.getCell(3).getRichStringCellValue().getString().equals("J")){
                            indicator = true;
                        }
                        int value = Integer.parseInt(formatter.formatCellValue(stageRow.getCell(5)));
                        String date = formatter.formatCellValue(detailedStageRow.getCell(3));
                        ProjectStage myStage = new ProjectStage(indicator, value, date);
                        stages.add(myStage);
                        currentProject.setStageList(stages);
                    }




                    System.out.println(indicator);
                    System.out.println("cell:" + stageRow.getCell(5));
                    int value = Integer.parseInt(formatter.formatCellValue(stageRow.getCell(5)));
                    System.out.println(value);
                    String date = formatter.formatCellValue(detailedStageRow.getCell(2));
                    System.out.println(date);

                    if (Objects.equals(stageID, projID)){

                        System.out.println("stage details: \n"+indicator);
                        System.out.println(value);
                        System.out.println(date);
                        stages.add(new ProjectStage(indicator,value,date));
                    }
                }
// ------------------------------------------------------------------------------------------------

//                currentProject.setStageList(stages);
                projects.add(currentProject);
            }
            System.out.println(projects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch();}
}