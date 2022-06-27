package excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.Font.SS_SUB;

@Service
public class ExcelDownload {


    public String excelDownload(HttpServletResponse res) {
        List<ExcelVO> familyList = new ArrayList<ExcelVO>();


        ExcelVO headerVo = new ExcelVO();
        headerVo.setName("꽥꽥이");
        headerVo.setAge("3");
        headerVo.setAddress("303-1203");

//        headerVo = ExcelVO.builder()
//                .name("딩딩")
//                .age("2")
//                .address("아프리카 - 서수원로577번길 263")
//                .build();
//        familyList.add(headerVo);


        // final String fileName = "/Users/junhyoungcho/userList.xlsx";
        final String fileName = "C:\\excel_test\\userList.xlsx";

        /* 엑셀 그리기 */
        final String[] colNames = {
                "No", "성명", "나이", "거주지"
        };

        // 헤더 사이즈
        final int[] colWidths = {
                3000, 5000, 5000, 3000
        };

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = null;
        XSSFCell cell = null;
        XSSFRow row = null;

        //Font
        Font fontHeader = workbook.createFont();
        fontHeader.setFontName("맑은 고딕");	//글씨체
        fontHeader.setFontHeight((short)(9 * 20));	//사이즈
        fontHeader.setBold(true);	//볼드(굵게)
        Font font9 = workbook.createFont();
        font9.setFontName("맑은 고딕");	//글씨체
        font9.setFontHeight((short)(9 * 20));	//사이즈
        // 엑셀 헤더 셋팅
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index); // HSSFColor.GREY_25_PERCENT.index
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // HSSFCellStyle.SOLID_FOREGROUND
        headerStyle.setFont(fontHeader);
        // 엑셀 바디 셋팅
        CellStyle bodyStyle = workbook.createCellStyle();
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        bodyStyle.setBorderRight(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.THIN);
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setFont(font9);
        // 엑셀 왼쪽 설정
        CellStyle leftStyle = workbook.createCellStyle();
        leftStyle.setAlignment(HorizontalAlignment.LEFT);
        leftStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        leftStyle.setBorderRight(BorderStyle.THIN);
        leftStyle.setBorderLeft(BorderStyle.THIN);
        leftStyle.setBorderTop(BorderStyle.THIN);
        leftStyle.setBorderBottom(BorderStyle.THIN);
        leftStyle.setFont(font9);


        //rows
        int rowCnt = 0;
        int cellCnt = 0;
        int listCount = familyList.size();

        // 엑셀 시트명 설정
        sheet = workbook.createSheet("사용자현황");
        row = sheet.createRow(rowCnt++);
        //헤더 정보 구성
        for (int i = 0; i < colNames.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(colNames[i]);
            sheet.setColumnWidth(i, colWidths[i]);	//column width 지정
        }
        //데이터 부분 생성
        for(ExcelVO vo : familyList) {
            cellCnt = 0;
            row = sheet.createRow(rowCnt++);
            // 넘버링
            cell = row.createCell(cellCnt++);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(listCount--);
            // 성명
            cell = row.createCell(cellCnt++);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(vo.getName());
            // 나이
            cell = row.createCell(cellCnt++);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(vo.getAge());

            // 주소
            cell = row.createCell(cellCnt++);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(vo.getAddress());
        }
        res.setContentType("application/vnd.ms-excel");
        // 엑셀 파일명 설정
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            workbook.write(res.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "다운로드 완료";
    }

}
