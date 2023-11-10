package toast.brot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static toast.brot.NamedFileGenerator.setFilePathVariables;
import static toast.brot.NamedPaletteFileGenerator.setFilePalettePathVariables;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("1. Create new ACT/SPR Files");
        System.out.println("2. Creating new Palette Files");
        System.out.println("Enter your choice:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1 -> {
                System.out.println("Creating new Spr and Act files");
                setFilePathVariables();
            }
            case 2 -> {
                System.out.println("Creating new Palette Files");
                setFilePalettePathVariables();
            }
            default -> System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
}