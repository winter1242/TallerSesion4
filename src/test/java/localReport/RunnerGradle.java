package localReport;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunnerGradle {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(SuiteReport.class);

        for (Failure failure: result.getFailures())
            System.out.println("ERROR al generar reporte......."+failure.getMessage());

    }

}