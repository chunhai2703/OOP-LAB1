/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyTool {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean validStr(String str, String regEx) {
        return str.matches(regEx);
    }

    public static boolean validPassword(String str, int minLen) {
        if (str.length() < minLen) {
            return false;
        }
        return str.matches(".*[\\W]+.*")
                && str.matches(".*[a-zA-Z]+.*")
                && str.matches(".*[\\d]+.*");
    }

    public static Date parseDate(String dateStr, String dateFormat) {
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    public static String dataToStr(Date date, String dateFormat) {
        SimpleDateFormat dS = (SimpleDateFormat) SimpleDateFormat.getInstance();
        dS.applyPattern(dateFormat);
        return dS.format(date);
    }

    public static boolean parseBool(String boolStr) {
        char c = boolStr.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static String readNonBlank(String message) {
        String input = "";
        do {
            System.out.println(message + ": ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.println(message + ": ");
            input = sc.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static boolean readBool(String message) {
        String input;
        System.out.println(message + "[1/0-Y/N-T/F]: ");
        input = sc.nextLine().trim();
        if (input.isEmpty()) {
            return false;
        }
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static List<String> readLinesFromFile(String filename){
        ArrayList<String> list = new ArrayList();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.err.println("Error: file doesnt exist");
            } else {
                FileReader fd = new FileReader(f);
                BufferedReader bf = new BufferedReader(fd);
                String line;
                while ((line = bf.readLine()) != null) {
                    if (!line.isEmpty()) {
                        list.add(line);
                    }
                }
                bf.close();
                fd.close();
            }
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
        return list;
    }

    public static void writeFile(String filename, List list) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.err.println(f.getCanonicalFile() + " does not exists to write");
            }
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i).toString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.err.println("Error: cannot write data to file " + filename);
            System.err.println("Reason: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Phone: 9 or 11 digits - OK
        /*System.out.println("Test with phone numbers:");
        System.out.println(validStr("012345678", "\\d{9}|\\d{11}"));
        System.out.println(validStr("01234567891", "\\d{9}|\\d{11}"));
        System.out.println(validStr("12345678", "\\d{9}|\\d{11}"));*/

        //Test password - OK
        /*System.out.println(validPassword("qwerty", 8));
        System.out.println(validPassword("qwertyABC", 8));
        System.out.println(validPassword("12345678", 8));
        System.out.println(validPassword("qbc123456", 8));
        System.out.println(validPassword("abc@123456", 8));*/
        //ID format D000 -> OK
        /*System.out.println("Test with IDs:");
        System.out.println(validStr("A0001", "D\\d{3}"));
        System.out.println(validStr("10001", "D\\d{3}"));
        System.out.println(validStr("D0001", "D\\d{3}"));
        System.out.println(validStr("D101", "D\\d{3}"));*/
        //Test date format -> OK
        /*Date d= parseDate ("2022:12:07", "yyyy:MM:dd");
        System.out.println(d);
        System.out.println(dataToStr(d, "dd/MM/yyyy"));
        d= parseDate ("12/07/2022", "MM/dd/yyyy");
        System.out.println(d);
        d= parseDate ("2022/07/12", "yyyy/dd/MM");
        System.out.println(d);
        d= parseDate ("2000/29/02", "yyyy/dd/MM");
        System.out.println(d);
        d= parseDate ("2000/30/02", "yyyy/dd/MM");
        System.out.println(d);
        d= parseDate ("2000/40/16", "yyyy/dd/MM");
        System.out.println(d);*/
        //Test input data -> OK
        String input = readNonBlank("Input a non-blank string");
        System.out.println(input);
        input = readPattern("Phone 9/11 digits", "\\d{9}|\\d{11}");
        System.out.println(input);
        input = readPattern("ID- format X00000", "X\\d{5}");
        System.out.println(input);
        boolean b = readBool("Input boolean");
        System.out.println(b);
    }
}//class MyTool
