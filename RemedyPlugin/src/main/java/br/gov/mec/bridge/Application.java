package br.gov.mec.bridge;

import br.gov.mec.remedy.RemedyInterface;
import br.gov.mec.sei.Bridge;
import br.gov.mec.sei.wsdl.Andamento;

import java.util.*;

public class Application {

    public static void main(String[] args) {

        System.out.println("Iniciando...");

        String seiendpoint = "";
        String serverName = "";
        int serverPort = 0;
        String username = "";
        String password = "";
        int interval = 15;

        boolean testAll = false;
        boolean testARS = false;
        boolean testSEI = false;

        HashMap<String, String> parsed = StaticUtils.parseArgs(args);

        if (parsed.size() > 0) {
            if (parsed.get(Application.PARAM_SEI) != null) {seiendpoint = parsed.get(Application.PARAM_SEI);}
            if (parsed.get(Application.PARAM_SRV) != null) {serverName = parsed.get(Application.PARAM_SRV);}
            if (parsed.get(Application.PARAM_PRT) != null) {serverPort = Integer.parseInt(parsed.get(Application.PARAM_PRT));}
            if (parsed.get(Application.PARAM_USR) != null) {username = parsed.get(Application.PARAM_USR);}
            if (parsed.get(Application.PARAM_PWD) != null) {password = parsed.get(Application.PARAM_PWD);}
            if (parsed.get(Application.PARAM_INT) != null) {interval = Integer.parseInt(parsed.get(Application.PARAM_INT));}
            if (parsed.get(Application.PARAM_TEST_ALL) != null) {testAll = parsed.get(Application.PARAM_TEST_ALL).toLowerCase().trim() == "yes";}
            if (parsed.get(Application.PARAM_TEST_ARS) != null) {testARS = parsed.get(Application.PARAM_TEST_ARS).toLowerCase().trim() == "yes";}
            if (parsed.get(Application.PARAM_TEST_SEI) != null) {testSEI = parsed.get(Application.PARAM_TEST_SEI).toLowerCase().trim() == "yes";}

        }

        if (serverName.isEmpty() || serverPort == 0 || username.isEmpty() || password.isEmpty()) {

            HashMap<String, String> configured = StaticUtils.loadCredentials("credentials.properties");
            if (seiendpoint.isEmpty()) {seiendpoint = configured.get(Application.PARAM_SEI);}
            if (serverName.isEmpty()) {serverName = configured.get(Application.PARAM_SRV);}
            if (username.isEmpty()) {username = configured.get(Application.PARAM_USR);}
            if (password.isEmpty()) {password = configured.get(Application.PARAM_PWD);}

            if (serverPort == 0) {

                String serverParam = configured.get(Application.PARAM_PRT);
                if (serverParam != null) serverPort = Integer.parseInt(serverParam);
            }

            if (interval == 0) {

                String intervalParam = configured.get(Application.PARAM_INT);
                if (intervalParam != null) serverPort = Integer.parseInt(intervalParam);
            }

        }

        if (seiendpoint.isEmpty() || serverName.isEmpty() || serverPort == 0 || username.isEmpty() || password.isEmpty()) {

            System.out.println("Parâmetros para conexão não disponíveis!");
            System.exit(-1);

        }


        if (testAll) {

            boolean resultARS = Application.testARS(serverName, serverPort, username, password);
            boolean resultSEI = Application.testSEI(seiendpoint);
            System.out.println(resultARS ? "ARS habilitado!" : "ARS não disponível!");
            System.out.println(resultSEI ? "SEI habilitado!" : "SEI não disponível!");
            System.exit(0);

        }

        if (testARS) {

            boolean result = Application.testARS(serverName, serverPort, username, password);
            System.out.println(result ? "ARS habilitado!" : "ARS não disponível!");
            System.exit(0);

        }

        if (testSEI) {

            boolean result = Application.testSEI(seiendpoint);
            System.out.println(result ? "SEI habilitado!" : "SEI não disponível!");
            System.exit(0);

        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ServiceTaskExecutor(seiendpoint, serverName, serverPort, username, password), 0, interval * milliInSeconds);

    }

    private static boolean testARS(String serverName, int serverPort, String serverUser, String serverPwd) {

        RemedyInterface server = new RemedyInterface(serverName, serverPort, serverUser, serverPwd);
        boolean connected = server.connect();
        if (connected) {server.disconnect();}
        return connected;

    }

    private static boolean testSEI(String endpoint) {

        Bridge sei = new Bridge(endpoint);
        Andamento[] result = sei.getLancamentos("TESTE");
        return result != null;

    }

    private static final long milliInSeconds = 60 * 60;
    private static final String PARAM_SEI = "sei";
    private static final String PARAM_SRV = "server";
    private static final String PARAM_PRT = "port";
    private static final String PARAM_USR = "username";
    private static final String PARAM_PWD = "password";
    private static final String PARAM_INT = "interval";
    private static final String PARAM_TEST_SEI = "testsei";
    private static final String PARAM_TEST_ARS = "testars";
    private static final String PARAM_TEST_ALL = "testall";


}
