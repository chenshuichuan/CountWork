package com.ruoyi.project.count.utils;

import com.ruoyi.project.count.work.domain.Work;
import com.ruoyi.project.system.files.domain.Files;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadExcel {

    /**
     * @param file
     * @throws EncryptedDocumentException
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<Work> readExcel(Files file,String academy)
            throws EncryptedDocumentException, IOException, InvalidFormatException {
        List<Work> resultList = new ArrayList<>();
        //SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        // 同时支持ecxel-2003/2007/2010
        File excelFile = new File(file.getUrl());
        FileInputStream is = new FileInputStream(excelFile);
        // 这种方式 Excel 2003/2007/2010都是可以处理的
        Workbook workbook = WorkbookFactory.create(is);
        //int sheetCount = workbook.getNumberOfSheets();
        // 可以遍历每个sheet,当前仅读取第一个sheet

        Sheet sheet = workbook.getSheetAt(0);
        // 获取总行数
        int rowCount = sheet.getPhysicalNumberOfRows();
        //int planId = planTableService.selectMaxId() + 1;
        for (int r = 1; r < rowCount; r++) {
            Row row = sheet.getRow(r);
            //int cellCount = row.getPhysicalNumberOfCells();
            int cellCount = 13;
            Work temp = new Work(new Date(),file.getFileName(),academy);
            temp.setSomeStatus(0,0,1,0,new Date());
            for (int c = 0; c < cellCount; c++) {
                //System.out.println("c = "+c);
                Cell cell = row.getCell(c);
                if (cell == null){
                    continue;
                }
                CellType cellTypeEnum = cell.getCellTypeEnum();
                switch (c) {
                    //年级
                    case 0:
                        if (cellTypeEnum.equals(CellType.STRING)){
                            temp.setGrade(cell.getStringCellValue());
                        }
                        else if (cellTypeEnum.equals(CellType.NUMERIC)){
                            int grade = (int)cell.getNumericCellValue();
                            temp.setGrade(String.valueOf(grade));
                        }
                        break;
                    //课程代码
                    case 1:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setCourseCode(cell.getStringCellValue());
                        else if (cellTypeEnum.equals(CellType.NUMERIC)){
                            long grade = (long)cell.getNumericCellValue();
                            temp.setCourseCode(String.valueOf(grade));
                        }
                        break;
                    //课程名称
                    case 2:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setCourseName(cell.getStringCellValue());
                        break;
                    //专业名称
                    case 3:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setMajor(cell.getStringCellValue());
                        break;
                    //班级
                    case 4:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setClassName(cell.getStringCellValue());
                        else if (cellTypeEnum.equals(CellType.NUMERIC)){
                            int className = (int)cell.getNumericCellValue();
                            temp.setClassName(String.valueOf(className));
                        }
                        break;
                    //人数
                    case 5:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            temp.setNumber((int)cell.getNumericCellValue());
                        break;
                    //学分
                    case 6:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            temp.setCredit((float)cell.getNumericCellValue());
                        break;
                    //总学时
                    case 7:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            temp.setHours((float)cell.getNumericCellValue());
                        break;
                    //实验总学时
                    case 8:
                        if (cellTypeEnum.equals(CellType.NUMERIC))
                            temp.setExperimentHours((float)cell.getNumericCellValue());
                        break;
                    //课程性质
                    case 9:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setKind(cell.getStringCellValue());
                        break;
                    //周学时
                    case 10:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setWeekHours(cell.getStringCellValue());
                        break;
                    //考核方
                    case 11:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setReviewer(cell.getStringCellValue());
                        break;
                    //系数类别
                    case 12:
                        if (cellTypeEnum.equals(CellType.STRING))
                            temp.setModulusKind(cell.getStringCellValue());
                        break;
                    default:
                        break;
                }
            }
            resultList.add(temp);
        }

        workbook.close();
        return resultList;
    }

}
