package localReport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReportJunit {

    @Test
    public void generateReportHTML(){
        LocalReportConsole.generateReport();
    }

}