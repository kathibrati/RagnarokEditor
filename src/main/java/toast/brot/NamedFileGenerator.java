package toast.brot;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class NamedFileGenerator {

    public static void setFilePathVariables() throws IOException {
        String inputPathAct = getInputPath("Select Act Path from Input File", "Input Path Act: ");
        String inputPathSpr = getInputPath("Select Spr Path from Input File", "Input Path Spr: ");
        String outputPath = getOutputPath();

        if (StringUtils.isBlank(inputPathAct) || StringUtils.isBlank(inputPathSpr) || StringUtils.isBlank(outputPath)) {
            System.out.println("Output selection canceled.");
            return;
        }

        createFiles(inputPathAct, inputPathSpr, outputPath);
    }

    private static void createFiles(String inputPathAct, String inputPathSpr, String outputPath) throws IOException {
        for (String jobName : JobNames.ALL_JOB_NAMES) {
            String outputFileNameSprFemale = outputPath + "\\¿©" + File.separator + jobName + "_¿©.spr";
            String outputFileNameActFemale = outputPath + "\\¿©" + File.separator + jobName + "_¿©.act";
            String outputFileNameSprMale = outputPath + "\\³²" + File.separator + jobName + "_³².spr";
            String outputFileNameActMale = outputPath + "\\³²" + File.separator + jobName + "_³².act";

            outPutFileNames(inputPathAct, inputPathSpr, outputFileNameSprFemale, outputFileNameActFemale);
            outPutFileNames(inputPathAct, inputPathSpr, outputFileNameSprMale, outputFileNameActMale);
        }
        System.out.println("Files have been created in the output directory.");
    }

    private static String getOutputPath() {
        return getString();
    }

    static String getString() {
        String outputPath = "";

        JFileChooser outputChooser = new JFileChooser();
        outputChooser.setDialogTitle("Select Path for Output Files");
        outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int outputResult = outputChooser.showOpenDialog(null);

        if (outputResult == JFileChooser.APPROVE_OPTION) {
            File outputDirectory = outputChooser.getSelectedFile();
            outputPath = new File(outputDirectory.toURI()).getAbsolutePath();
            System.out.println("Output path: " + outputPath);
        }

        return outputPath;
    }

    private static String getInputPath(String dialogTitle, String x) {
        String inputPath = "";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);

        int openDialog = fileChooser.showOpenDialog(null);

        if (openDialog == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            inputPath = inputFile.getAbsolutePath();
            System.out.println(x + inputPath);
        } else {
            System.out.println("Input Selection canceled.");
        }

        return inputPath;
    }

    private static void outPutFileNames(String inputPathAct, String inputPathSpr, String outputFileNameSpr, String outputFileNameAct) throws IOException {
        File sourceAct = new File(inputPathAct);
        File sourceSpr = new File(inputPathSpr);
        File destinationAct = new File(outputFileNameAct);
        File destinationSpr = new File(outputFileNameSpr);

        FileUtils.copyFile(sourceAct, destinationAct);
        FileUtils.copyFile(sourceSpr, destinationSpr);


    }
}


