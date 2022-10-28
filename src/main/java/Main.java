
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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

        try {
            // Projects.xls
            FileInputStream xlsProject = new FileInputStream("Projects.xls");
            Workbook bookProject = WorkbookFactory.create(xlsProject);
            Sheet sheetProject = bookProject.getSheetAt(0);
            DataFormatter formatterProject = new DataFormatter();

            // Stages.xls
            FileInputStream xlsStage = new FileInputStream("Stages.xls");
            Workbook bookStage = WorkbookFactory.create(xlsStage);
            Sheet sheetStage = bookProject.getSheetAt(0);
            DataFormatter formatterStage = new DataFormatter();

            //Stages.xls
            FileInputStream xlsDetail = new FileInputStream("Stages_Detailed.xls");
            Workbook bookDetail = WorkbookFactory.create(xlsStage);
            Sheet sheetDetail = bookProject.getSheetAt(0);
            DataFormatter formatterDetail = new DataFormatter();

            // PSEUDO-CODE
            // extract ONE project from table
            //


            for (Row row : sheetProject) {

                for (Cell cell : row) {
                    CellReference cellRefProject = new CellReference(row.getRowNum(), cell.getColumnIndex()); // Project ID
                    String text = formatterProject.formatCellValue(cell);
                    System.out.println("[" + cellRefProject.formatAsString() + "]: " + text);
                }
            }

        } catch (Exception e) {
            System.out.println();
        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        String xlsFile = "Projects.xls";
//        try {
//            FileInputStream fileInput = new FileInputStream(xlsFile);
//            Workbook book = WorkbookFactory.create(fileInput);
//            Sheet sheet = book.getSheetAt(0); // Assuming we are working with one sheet only
////            DataFormatter formatter = new DataFormatter(); // idk what this is
//
//            for (Row row: sheet) {
//                for (Cell cell: row) {
////                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
////                    String text = formatter.formatCellValue(cell); // idk
////                    System.out.println( "[" + cellRef.formatAsString() + "]: " + text); // [Cell]: Value
//
//
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            System.out.println("String: " + cell.getRichStringCellValue().getString());
//                            break;
//                        case NUMERIC:
//                            if (DateUtil.isCellDateFormatted(cell)) {
//                                System.out.println("Formatted: " + cell.getDateCellValue());
//                            } else {
//                                System.out.println("Unformatted: " + cell.getNumericCellValue());
//                            }
//                            break;
//                        case BOOLEAN:
//                            System.out.println(cell.getBooleanCellValue());
//                            break;
//                        case FORMULA:
//                            System.out.println("FORMULA: " + cell.getCellFormula());
//                            break;
//                        case BLANK:
//                            System.out.println("BLANK");
//                            break;
//                        default:
//                            System.out.println("Unknown");
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    }
}