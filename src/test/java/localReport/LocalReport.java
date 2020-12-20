package localReport;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalReport {
    public static void main(String []args){
        String path="D:\\ArchivoIntelliJ\\TallerSesion4\\build\\reports\\cucumber\\";
        File reportHTMLFolder=new File(path+"cucumberReporting");

        List<String> jsonFiles=new ArrayList<>();
        jsonFiles.add(path+"report.json");

        Configuration configuration = new Configuration(reportHTMLFolder,"JB Group Movile Tesing");

        configuration.setBuildNumber("v1.0000");
        configuration.addClassifications("SO","Android");
        configuration.addClassifications("Owner","Eynar");
        configuration.addClassifications("Version OS","9.0");
        configuration.addClassifications("Branch","master");
        configuration.addClassifications("Report","Local");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles,configuration);
        reportBuilder.generateReports();
    }
}
