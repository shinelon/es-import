package com.shinelon.esimport;

import com.shinelon.esimport.service.IMigrationService;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author shinelon
 */
@SpringBootApplication
public class EsImportApplication {
    private static Options OPTIONS = new Options();
    private static CommandLine commandLine;
    private static String HELP_STRING = null;


    public static void main(String[] args) {

        initCliArgs(args);
        SpringApplication application = new SpringApplication(EsImportApplication.class);
        Properties defaultProperties = new Properties();
        String m = commandLine.getOptionValue("m");
        defaultProperties.setProperty("es.import.mode", m);
        String h = commandLine.getOptionValue("h");
        defaultProperties.setProperty("es.host", h);
        String s = commandLine.getOptionValue("s");
        defaultProperties.setProperty("csv.path", s);
        application.setDefaultProperties(defaultProperties);
        ConfigurableApplicationContext applicationContext = application.run(args);
        IMigrationService migrationService = applicationContext.getBean(IMigrationService.class);
        if (StringUtils.equals("reset", m)) {
            migrationService.modeReset();
        }else {
            migrationService.modeCreate();
        }
        migrationService.migration();
    }


    /**
     * init args
     *
     * @param args args
     */
    private static void initCliArgs(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        OPTIONS.addOption("help", "usage help");

        OPTIONS.addOption(Option.builder("m").required().argName("importMode").hasArg(true).longOpt("importMode").type(String.class).desc("import-mode: create:createIndex; reset:resetIndex;").build());
        // host
        OPTIONS.addOption(Option.builder("h").argName("esHost").hasArg(true).longOpt("esHost").type(String.class).desc("the host of es server").build());
        // srcPath
        OPTIONS.addOption(Option.builder("s").argName("srcPath").hasArg(true).longOpt("srcPath").type(String.class).desc("the srcPath of csv").build());
        try {
            commandLine = commandLineParser.parse(OPTIONS, args);
            if (commandLine.hasOption("help")) {
                System.out.println("\n" + getHelpString());
                System.exit(0);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage() + "\n" + getHelpString());
            System.exit(0);
        }

    }

    /**
     * get string of help usage
     *
     * @return help string
     */
    public static String getHelpString() {
        if (HELP_STRING == null) {
            HelpFormatter helpFormatter = new HelpFormatter();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
            helpFormatter.printHelp(printWriter, HelpFormatter.DEFAULT_WIDTH, " -help", null,
                    OPTIONS, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, null);
            printWriter.flush();
            HELP_STRING = new String(byteArrayOutputStream.toByteArray());
            printWriter.close();
        }
        return HELP_STRING;
    }
}
