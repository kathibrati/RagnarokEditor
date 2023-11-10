package toast.brot;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static toast.brot.NamedFileGenerator.getString;


public class NamedPaletteFileGenerator {

    public static void setFilePalettePathVariables() throws IOException {
        String inputPathPalette = getInputPath();
        System.out.println("Enter Palette Number");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int paletteNumber = Integer.parseInt(reader.readLine());
        String outputPath = getOutputPath();

        if (StringUtils.isBlank(inputPathPalette) || StringUtils.isBlank(outputPath) || paletteNumber<0) {
            System.out.println("Output selection canceled.");
            return;
        }

        createFiles(inputPathPalette, outputPath, paletteNumber);
    }

    private static void createFiles(String inputPathPalette, String outputPath, int paletteNumber) throws IOException {
        for (String jobName : JobNames.ALL_JOB_NAMES) {
            String outputFileNamePaletteFemale = outputPath + "\\¿©" + File.separator + jobName + "_¿©_"+paletteNumber+".pal";
            String outputFileNamePaletteMale = outputPath + "\\³²" + File.separator + jobName + "_³²_"+paletteNumber+".pal";

            outPutFileNames(inputPathPalette, outputFileNamePaletteFemale);
            outPutFileNames(inputPathPalette, outputFileNamePaletteMale);
        }
        System.out.println("Files have been created in the output directory.");
    }

    private static String getOutputPath() {
        return getString();
    }

    private static String getInputPath() {
        String inputPath = "";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Palette Path from Input File");

        int openDialog = fileChooser.showOpenDialog(null);

        if (openDialog == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            inputPath = inputFile.getAbsolutePath();
            System.out.println("Input Palette: " + inputPath);
        } else {
            System.out.println("Input Selection canceled.");
        }

        return inputPath;
    }

    private static void outPutFileNames(String inputPathPalette, String outputFileNamePalette) throws IOException {
        File sourceAct = new File(inputPathPalette);
        File destinationPal = new File(outputFileNamePalette);

        FileUtils.copyFile(sourceAct, destinationPal);

    }
}


