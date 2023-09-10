package toast.brot;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.io.*;
import java.util.List;


public class NamedFileGenerator {

    public static void setFilePathVariables() throws FileNotFoundException {
        JFileChooser inputChooser = new JFileChooser();
        inputChooser.setDialogTitle("Select Path from Input File");
        int inputResult = inputChooser.showOpenDialog(null);
        String inputPath = "";
        String outputPath = "";
        File outputDirectory;
        File inputFile;
        List<String> jobNames = JobNames.ALL_JOB_NAMES;

        if (inputResult == JFileChooser.APPROVE_OPTION) {
            inputFile = inputChooser.getSelectedFile();
            inputPath = inputFile.getAbsolutePath();
            System.out.println("Input Path: " + inputPath);
        } else {
            System.out.println("Input Selection canceled.");

            JFileChooser outputChooser = new JFileChooser();
            outputChooser.setDialogTitle("Select Path for Output Files");
            outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int outputResult = outputChooser.showOpenDialog(null);

            if (outputResult == JFileChooser.APPROVE_OPTION) {
                outputDirectory = outputChooser.getSelectedFile();
                outputPath = new File(outputDirectory, "outputpath.txt").getAbsolutePath();
                System.out.println("Output path: " + outputPath);

                if (StringUtils.isNotBlank(inputPath) && StringUtils.isNotBlank(outputPath)) {
                    try (BufferedReader inputReader = new BufferedReader(new FileReader(inputPath))) {
                        String inputLine;
                        int jobIndex = 0;

                        while ((inputLine = inputReader.readLine()) != null) {
                            if (jobIndex < jobNames.size()) {
                                String jobName = jobNames.get(jobIndex);
                                String outputFileNameSpr = outputPath + File.separator + jobName + ".spr";
                                String outputFileNameAct = outputPath + File.separator + jobName + ".act";

                                try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFileNameSpr))) {
                                    outputWriter.write(inputLine);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                jobIndex++;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Files have been created in the output directory.");

                } else {
                    System.out.println("Output selection canceled.");
                }
            }
        }
    }
}


