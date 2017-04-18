/*
 * This application is property of Otema and is licenced under otema ©2017
 * No part of it whatsoever shall be shared or used unless under written permission by Otema
 * A breech of this may lead to prosecution.
 */
package com.otema.autocallendargenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Maseno
 */
public class Callendar {
    public static void main(String []args){
        String strYear = JOptionPane.showInputDialog(null,
             "Enter a Year(e.g., 2001):",
             "Input",
             JOptionPane.QUESTION_MESSAGE);
        Document document = new Document();
        try{
            document.open();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(strYear + ".pdf"));
        document.close();
        writer.close();
        //Convert the string into an int value
     int Year = Integer.parseInt(strYear);
             for(int i = 1; i < 13; i++) {
                  document.add(printMonth(Year, i));
               }
        }
        catch(DocumentException | FileNotFoundException e){
            
        }
    }
     //------------------------------------------------------------
        static void printMonth(int year, int month) {
            printMonthTitle(year, month);
            printMonthBody(year, month);
        }
         
        //------------------------------------------------------------
        static void printMonthTitle(int year, int month){
            Document document = new Document();
              try{
                 
            Font red = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
            Font black_lg = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
            Font black_sm = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
            Chunk type = new Chunk("Designed Atomatically by java", black_sm);
            Chunk head = new Chunk("MASENO UNIVERSITY", black_lg);
            Chunk BgSpace = new Chunk("          " );
            Chunk SmSpace = new Chunk( " ");
            Chunk Underline = new Chunk("----------------------------");
            Chunk DaysOfTheWeek = new Chunk(" Sun Mon Tue Wed Thu Fri Sat");
            type.setBackground(new BaseColor(0, 153, 153));
            Paragraph p = new Paragraph(type);
            Paragraph headtext = new Paragraph(head);
            document.open();
             PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(headtext);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
           document.add(BgSpace + getMonthName(month) + SmSpace + year);
            document.add(Underline);
            document.add(DaysOfTheWeek);
            }
            
         catch (DocumentException e){
             
         }
                       
        }
         
        //------------------------------------------------------------
        static String getMonthName(int month){
            String monthName = null;
            switch (month) {
                case 1: monthName = "January"; break;
                case 2: monthName = "February"; break;
                case 3: monthName = "March"; break;
                case 4: monthName = "April"; break;
                case 5: monthName = "May"; break;
                case 6: monthName = "June"; break;
                case 7: monthName = "July"; break;
                case 8: monthName = "August"; break;
                case 9: monthName = "Semptember"; break;
                case 10: monthName = "October"; break;
                case 11: monthName = "November"; break;
                case 12: monthName = "December"; break;
            }
            return monthName;
        }
         
        //------------------------------------------------------------
        static void printMonthBody(int year, int month) {
            int startDay = getStartDay(year, month);
             
            int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
             
            int i = 0;
            for (i = 0; i < startDay; i++)
                System.out.print("    ");
             
            for (i = 1; i <= numberOfDaysInMonth; i++) {
                if (i < 10)
                    System.out.print("   " + i);
                else
                    System.out.print("  " + i);
                 
                if ((i + startDay) % 7 == 0)
                    System.out.println();
            }
             
            System.out.println();
        }
         
        //------------------------------------------------------------
        static int getStartDay(int year, int month) {
            int startDay1800 = 3;
            int totalNumberOfDays = getTotalNumberOfDays(year, month);
             
            return (totalNumberOfDays + startDay1800) % 7;
        }
         
        //------------------------------------------------------------
        static int getTotalNumberOfDays(int year, int month) {
            int total = 0;
             
            for (int i = 1800; i < year; i++)
                if (isLeapYear(i))
                    total = total + 366;
                else
                    total = total + 365;
             
            for (int i = 1; i < month; i++)
                total = total + getNumberOfDaysInMonth(year, i);
             
            return total;
        }
         
        //------------------------------------------------------------
        static int getNumberOfDaysInMonth(int year, int month) {
            if (month == 1 || month == 3 || month == 5 || month == 7 ||
                    month == 8 || month == 10 || month == 12)
                return 31;
             
            if (month == 4 || month == 6 || month == 9 || month == 11)
                return 30;
             
            if (month == 2)
                return isLeapYear(year) ? 29 : 28;
             
            return 0;
                 
        }
         
        //------------------------------------------------------------
        static boolean isLeapYear(int year) {
            return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        }
   
}
