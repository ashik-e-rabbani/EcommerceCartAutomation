package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDataFromExternal {
    public static List externalData() throws IOException {
        List externalFormData = new ArrayList<>();
            try {
            FileInputStream fis = new FileInputStream("/home/ashik/Projects/OTHERS/cartAutomation/src/test/java/utils/test.txt");
            Scanner externalDataScanner = new Scanner(fis);
            while (externalDataScanner.hasNextLine()) {
                System.out.println(externalDataScanner.nextLine());
                externalFormData.add(externalDataScanner.nextLine());
            }
            externalDataScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return externalFormData;
    }

    public static List externalDataComma() throws IOException {
        List externalFormData = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("/home/ashik/Projects/OTHERS/cartAutomation/src/test/java/utils/test.txt"));
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            for (String str : values) {
//                System.out.println(str);
                externalFormData.add(str);
            }
        }
        br.close();
        return externalFormData;

    }


}