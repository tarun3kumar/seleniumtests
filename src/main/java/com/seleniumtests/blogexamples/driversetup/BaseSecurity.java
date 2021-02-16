package com.seleniumtests.blogexamples.driversetup;

import static java.lang.String.format;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.zaproxy.clientapi.core.Alert;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseSecurity extends BaseClassOnDemandDriverSetupWithProxy {

    private static final String SECURITY_RISK_HIGH = "High";
    private static final String SECURITY_RISK_MEDIUM = "Medium";
    private static final String SECURITY_RISK_LOW = "Low";
    private static final String SECURITY_RISK_INFORMATIONAL = "Informational";
    private static ClientApi clientApi = new ClientApi("127.0.0.1", 8080);
    private static String securityTestReportPath = "target/zap-security-report.html";


    @AfterMethod(alwaysRun = true)
    public static void waitForPassiveScanToComplete() throws ClientApiException {
        log.info("--- Waiting for passive scan to finish --- ");
        try {
            // Passive scanner run by default: https://stackoverflow.com/a/35944273/270835
            clientApi.pscan.enableAllScanners(); // enable passive scanner.

            ApiResponse response = clientApi.pscan.recordsToScan();

            //iterating till pending scan count is 0
            while (!response.toString().equals("0")) {
                response = clientApi.pscan.recordsToScan();
            }
        } catch (ClientApiException e) {
            throw new ClientApiException("Was zap proxy started?", e);
        }
        log.info("--- Passive scan finished! ---");
    }

    public static void checkRiskCount(String filterURL) throws ClientApiException {
        int riskCountHigh = 0;
        int riskCountMedium = 0;
        int riskCountLow = 0;
        int riskCountInformational = 0;
        int totalRiskCount;

        List<Alert> alertList = clientApi.getAlerts(filterURL, 0, 9999999);
        for (Alert alert : alertList) {
            String riskName = alert.getRisk().name();
            switch (riskName) {
                case SECURITY_RISK_HIGH:
                    riskCountHigh = riskCountHigh + 1;
                    break;
                case SECURITY_RISK_MEDIUM:
                    riskCountMedium = riskCountMedium + 1;
                    break;
                case SECURITY_RISK_LOW:
                    riskCountLow = riskCountLow + 1;
                    break;
                case SECURITY_RISK_INFORMATIONAL:
                    riskCountInformational = riskCountInformational + 1;
                    break;
                default:
                    throw new IllegalStateException(format("Unknown risk level %s", riskName));
            }
        }
        totalRiskCount = riskCountHigh + riskCountMedium + riskCountLow + riskCountInformational;
        log.info("Total risk count: {}", totalRiskCount);
        Preconditions.checkState(totalRiskCount == 0,
                format("Page %s" +
                                "\nHigh Risk count: %s" +
                                "\nMedium Risk count: %s" +
                                "\nLow Risk count: %s" +
                                "\nInformational Risk count: %s" +
                                "\nplease check: %s",
                        filterURL, riskCountHigh, riskCountMedium, riskCountLow, riskCountInformational, securityTestReportPath));
    }

    @AfterSuite(alwaysRun = true)
    public void generateScanReport() throws ClientApiException, IOException {
        byte[] bytes = clientApi.core.htmlreport();
        // storing the bytes in to html report.
        String str = new String(bytes, StandardCharsets.UTF_8);
        File newTextFile = new File(securityTestReportPath);
        try (FileWriter fw = new FileWriter(newTextFile)) {
            fw.write(str);
        }
    }
}