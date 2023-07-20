/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import tools.MyTool;
import mng.LogIn;

public class DealerList extends ArrayList<Dealer> {

    LogIn loginObj = null;
    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    private String dataFile = "";
    boolean changed = false;

    public DealerList(LogIn loginObj) {
        super();
        this.loginObj = loginObj;
    }

    private void loadDealerFromFile() {
        List<String> lines = MyTool.readLinesFromFile("D:\\FALL 2022\\LAB211\\DealerData\\Dealers");
        for (String line : lines) {
            Dealer dl = new Dealer(line);
            this.add(dl);
        }
    }

    public void initWithFile() {
        Config cR = new Config();
        dataFile = cR.getDealerFile();
        loadDealerFromFile();
    }

    public DealerList getContinuingList() {
        DealerList result = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == true) {
                result.add(d);
            }
        }
        return result;
    }

    public DealerList getUnContinuingList() {
        DealerList result = new DealerList(loginObj);
        for (Dealer d : this) {
            if (d.isContinuing() == false) {
                result.add(d);
            }
        }
        return result;
    }

    private int searchDealer(String ID) {
        ID = ID.trim().toUpperCase();
        int index = -1;
        int n = this.size();
        for (int i = 0; i < n; i++) {
            if (this.get(i).getId().compareToIgnoreCase(ID) == 0) {
                index = i;
            }
        }
        return index;
    }

    public void searchDealer() {
        String newID = MyTool.readNonBlank("Dealer's ID need searching");
        newID = newID.toUpperCase();
        int pos = searchDealer(newID);
        if (pos < 0) {
            System.out.println("NOT FOUND!");
        } else {
            System.out.println("The position of dealer: " + pos);
        }
    }

    public void addDealer() {
        String ID;
        String name;
        String addr;
        String phone;
        boolean continuing;
        int pos;
        do {
            ID = MyTool.readNonBlank("ID of new dealer: ");
            ID = ID.toUpperCase();
            pos = searchDealer(ID);
            if (pos >= 0) {
                System.out.println("ID is duplicated!");
            }
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer: ").toUpperCase();
        addr = MyTool.readNonBlank("Address of new dealer: ");
        phone = MyTool.readPattern("Phone number: ", Dealer.PHONE_FORMAT);
        continuing = true;
        Dealer d = new Dealer(ID, name, addr, phone, continuing);
        this.add(d);
        System.out.println("New dealer has been added.");
        changed = true;
    }

    public void removeDealer() {
        String newID = MyTool.readNonBlank("Dealer's ID need removing: ");
        newID = newID.toUpperCase();
        int pos = searchDealer(newID);
        if (pos < 0) {
            System.out.println("NOT FOUND");
        } else {
            this.get(pos).setContinuing(false);
            System.out.println("Removed");
            changed = true;
        }
    }

    public void updateDealer() {
        System.out.println("Dealer's ID needs updating: ");
        String ID = MyTool.sc.nextLine();
        int pos = searchDealer(ID);
        if (pos < 0) {
            System.out.println("Dealer " + ID + "not found");
        } else {
            Dealer d = this.get(pos);
            String newName = "";
            System.out.println("new name, ENTER for omitting");
            newName = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newName.isEmpty()) {
                d.setName(newName);
                changed = true;
            }
            String newAddr = "";
            System.out.println("new address, ENTER for omitting: ");
            newAddr = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newAddr.isEmpty()) {
                d.setAddr(newAddr);
                changed = true;
            }
            String newPhone = "";
            System.out.println("new phone, ENTER for omitting: ");
            newPhone = MyTool.sc.nextLine().trim().toUpperCase();
            if (!newPhone.isEmpty()) {
                d.setPhone(newPhone);
                changed = true;
            }
        }
    }

    public void printAllDealers() {
        if (this.isEmpty()) {
            System.out.println("Empty List");
        } else {
            System.out.println(this);
        }
    }

    public void printContinuingDealers() {
        this.getContinuingList().printAllDealers();
    }

    public void printUncontinuingDealers() {
        this.getUnContinuingList().printAllDealers();
    }

    public void writeDealerToFie() {
        if (changed) {
            MyTool.writeFile(dataFile, this);
            changed = false;
        }
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
}// class DealerList
