package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReports" + File.separator + "extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        Properties configProp = new Properties();
        File configPropFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "tutorialsninja" + File.separator + "qa" + File.separator + "config" + File.separator + "config.properties");
        FileInputStream fisConfigProp = null;
        try {
            fisConfigProp = new FileInputStream(configPropFile);
            configProp.load(fisConfigProp);
            extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
            extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
            extentReport.setSystemInfo("Email", configProp.getProperty("ValidEmail"));
            extentReport.setSystemInfo("Password", configProp.getProperty("ValidPassword"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fisConfigProp != null) {
                try {
                    fisConfigProp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }

	
}
