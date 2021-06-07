package sample.export;


import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import sample.model.Report;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import static sample.export.SetingsExcel.*;

public class Export {
    File path = new File(File.listRoots()[0],"ReportsFolder");
    protected void report(ObservableList<Report> list) {
        if (!path.exists()) {
            System.out.println(path);
            System.out.println(" created");
            path.mkdir();
        }else {
            System.out.println("not created");
        }

        String name = "Отчет";

        String currentTime;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
        currentTime = sdf.format(date);

        File file = new File(File.listRoots()[0], "ReportsFolder" + System.getProperty("file.separator") + name + " от " + currentTime + ".xlsx");

        try {
            if (file.createNewFile()) {
                XSSFWorkbook book = new XSSFWorkbook();
                XSSFSheet sheet = book.createSheet(name);

                XSSFCellStyle style = createCellStyl(book);
                style.setFont(createFontMain(book));

                XSSFCellStyle style1 = createCellStyl(book);
                style1.setFont(createFont1(book));

                XSSFCellStyle style2 = createCellStyl(book);
                style2.setFont(createFont2(book));

                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

                Row row = sheet.createRow(0);
                Cell name1 = row.createCell(0);
                name1.setCellValue("Отчёт по моделированию");
                name1.setCellStyle(style);

                Row row1 = sheet.createRow(1);

                Cell idCell = row1.createCell(0);
                idCell.setCellValue("Номер");
                idCell.setCellStyle(style1);

                Cell evmCell = row1.createCell(1);
                evmCell.setCellValue("Использование ЭВМ");
                evmCell.setCellStyle(style1);

                Cell prCell = row1.createCell(2);
                prCell.setCellValue("Простой проектировщика");
                prCell.setCellStyle(style1);

                Cell T1Cell = row1.createCell(3);
                T1Cell.setCellValue("Количество заявок терминал 1");
                T1Cell.setCellStyle(style1);

                Cell T2Cell = row1.createCell(4);
                T2Cell.setCellValue("Количество заявок терминал 2");
                T2Cell.setCellStyle(style1);

                Cell colResCell = row1.createCell(5);
                colResCell.setCellValue("Количество заявок терминал 3");
                colResCell.setCellStyle(style1);

                Cell zadanieCell = row1.createCell(6);
                zadanieCell.setCellValue("Количество заданий");
                zadanieCell.setCellStyle(style1);

                int rowNum = 1;
                for (Report report : list) {
                    createSheetHeaderReport(sheet, ++rowNum, report, style2);
                }

                sheet.autoSizeColumn (0);
                sheet.autoSizeColumn (1);
                sheet.autoSizeColumn (2);
                sheet.autoSizeColumn (3);
                sheet.autoSizeColumn (4);

                try {
                    book.write(new FileOutputStream(file));
                    book.close();
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void createSheetHeaderReport(XSSFSheet sheet, int i, Report report, XSSFCellStyle style) {
        Row row = sheet.createRow(i);

        Cell Cell = row.createCell(0);
        Cell.setCellValue(report.getNumber());
        Cell.setCellStyle(style);

        Cell Cell1 = row.createCell(1);
        Cell1.setCellValue(report.getEvmUsage());
        Cell1.setCellStyle(style);

        Cell Cell2 = row.createCell(2);
        Cell2.setCellValue(report.getOperatorLag());
        Cell2.setCellStyle(style);

        Cell Cell3 = row.createCell(3);
        Cell3.setCellValue(report.getCountT1());
        Cell3.setCellStyle(style);

        Cell Cell4 = row.createCell(4);
        Cell4.setCellValue(report.getCountT2());
        Cell4.setCellStyle(style);

        Cell Cell5 = row.createCell(5);
        Cell5.setCellValue(report.getCountT3());
        Cell5.setCellStyle(style);

        Cell Cell6 = row.createCell(6);
        Cell6.setCellValue(report.getZadanie());
        Cell6.setCellStyle(style);
    }

    protected void reportWord(ObservableList<Report> list) throws IOException {
        if (!path.exists()) {
            path.mkdir();
        }

        String name = "Отчёт о моделировании";

        String currentTime;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        currentTime = sdf.format(date);

        String current;
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
        current = sdf1.format(date1);

        File file = new File(File.listRoots()[0], "ReportsFolder" + System.getProperty("file.separator") + name + " от " + currentTime + ".docx");
        if (file.createNewFile()) {
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun titleRun = title.createRun();
            titleRun.setText(name);
            titleRun.setColor("000000");
            titleRun.setBold(true);
            titleRun.setFontFamily("Times New Roman");
            titleRun.setFontSize(18);

            XWPFParagraph subTitle = document.createParagraph();
            subTitle.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun subTitleRun = subTitle.createRun();
            subTitleRun.setText("от " + currentTime);
            subTitleRun.setColor("000000");
            subTitleRun.setFontFamily("Times New Roman");
            subTitleRun.setFontSize(16);
            subTitleRun.setTextPosition(20);

            XWPFTable table = document.createTable();
            table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(9500));

            XWPFTableRow tableRow0 = table.getRow(0);
            tableRow0.getCell(0).setText("№ отчёта");

            XWPFTableCell tableCell1 = tableRow0.addNewTableCell();
            tableCell1.setText("Нагрузка ЭВМ");

            XWPFTableCell tableCell2 = tableRow0.addNewTableCell();
            tableCell2.setText("Простой проектировщика");

            XWPFTableCell tableCell3 = tableRow0.addNewTableCell();
            tableCell3.setText("Количество строк 1 терминал");

            XWPFTableCell tableCell4 = tableRow0.addNewTableCell();
            tableCell4.setText("Количество строк 2 терминал");

            XWPFTableCell tableCell5 = tableRow0.addNewTableCell();
            tableCell5.setText("Количество строк 3 терминал");

            XWPFTableCell tableCell6 = tableRow0.addNewTableCell();
            tableCell6.setText("Количество заданий");

            for (Report report : list) {
                createWordTable(table, report);
            }

            FileOutputStream out = null;
            out = new FileOutputStream(file);
            document.write(out);
            out.close();
            Desktop.getDesktop().open(file);
        }

    }

    private static void createWordTable(XWPFTable table, Report report) {
        XWPFTableRow tableRow1 = table.createRow();
        tableRow1.getCell(0).setText(String.valueOf(report.getNumber()));
        tableRow1.getCell(1).setText(String.valueOf(report.getEvmUsage()));
        tableRow1.getCell(2).setText(String.valueOf(report.getOperatorLag()));
        tableRow1.getCell(3).setText(String.valueOf(report.getCountT1()));
        tableRow1.getCell(4).setText(String.valueOf(report.getCountT2()));
        tableRow1.getCell(5).setText(String.valueOf(report.getCountT3()));
        tableRow1.getCell(6).setText(String.valueOf(report.getZadanie()));
    }
}
