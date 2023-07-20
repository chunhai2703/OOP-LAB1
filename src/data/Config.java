/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import tools.MyTool;

public class Config {

    private static final String CONFIG_FILE = "D:\\FALL 2022\\LAB211\\DealerData\\config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() {
        readData();
    }

    private void readData() {
        List<String> lines = MyTool.readLinesFromFile(CONFIG_FILE);
        for (String line : lines) {
            line = line.toUpperCase();
            String[] parts = line.split(":");
            if (line.contains("accounts")) {
                accountFile = "DealerData/" + parts[1].trim();
            } else if (line.contains("dealers")) {
                dealerFile = "DealerData/" + parts[1].trim();
            } else if (line.contains("deliveries")) {
                deliveryFile = "DealerData/" + parts[1].trim();
            }
        }
    }

    //Getters- Implement it
    public String getAccountFile() {
        return accountFile = "D:\\FALL 2022\\LAB211\\DealerData\\accounts.txt";
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }

}//class Config
