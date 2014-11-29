package com.util;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.util.LinkedList;  
import java.util.List;  


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {  
     /** 
     * �����ṩ��ȡexcel �ķ��� 
     * */  
	public static List<List<String>> readExcel(File file , int cols) throws IOException{  
		String fileName = file.getName();  
		String extension = fileName.lastIndexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1);
		if("xls".equals(extension)){  
			return read2003Excel(file , cols);  
		}else if("xlsx".equals(extension)){  
			return read2007Excel(file , cols);  
		}else{  
			throw new IOException("��֧�ֵ��ļ�����");
		}  
	}  
	/** 
	* ��ȡ office 2003 excel 
	* @throws IOException  
	* @throws FileNotFoundException */  
	private static List<List<String>> read2003Excel(File file ,int cols) throws IOException{  
		List<List<String>> list = new LinkedList<List<String>>();
		Workbook wb = null;
        try {
        	wb = new XSSFWorkbook(new FileInputStream(file));
        } catch (Exception ex) {
        	wb = new HSSFWorkbook(new FileInputStream(file));
        }
        Sheet sheet = wb.getSheetAt(0);
		final int lastRow = sheet.getPhysicalNumberOfRows();
		//System.out.println("lastRow-------------------------------------="+lastRow);
		for(int i = 0;i < lastRow;i++){	//����������
			Row row = sheet.getRow(i);
			if (row == null) continue;
			List<String> linked = new LinkedList<String>();	//����һ������
			if(i==0) cols = row.getLastCellNum()<cols?row.getLastCellNum():cols;
			for(int j = 0; j < cols; j++) {	//����ǰ�е�������  
				Cell cell = row.getCell(j);
				String value;
				if (cell == null) value="";
				else value = cell.toString();
				//System.out.println(i+"��"+j+" �� :value="+value.trim()+"length"+value.trim().length());  
				linked.add(numberStringOneView(value.trim()));
			}
			list.add(linked);  
		}
		return list;  
	}  
	/** 
	* ��ȡOffice 2007 excel
	* */  
	private static List<List<String>> read2007Excel(File file , int cols) throws IOException {  
		List<List<String>> list = new LinkedList<List<String>>();  // ���� XSSFWorkbook ����strPath �����ļ�·��  
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));  	// ��ȡ��һ�±������
		XSSFSheet sheet = xwb.getSheetAt(0);
		final int lastRow = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < lastRow; i++) {  
			XSSFRow row = sheet.getRow(i);  
			if (row == null) continue; 
			List<String> linked = new LinkedList<String>();
			if(i==0) cols = row.getLastCellNum()<cols?row.getLastCellNum():cols;
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				String value;
				if (cell == null) value="";
				else value = cell.toString();   
				//System.out.println(i+"��"+j+" �� :value=hh"+value);
				linked.add(numberStringOneView(value.trim()));  
			}  
			list.add(linked);
		}  
		return list;
	}
	public static String numberStringOneView(String str){
		if(str.endsWith(".0")) {
			try {
				int i = Integer.parseInt(str.substring(0,str.lastIndexOf(".")));
				str = String.valueOf(i);
			}catch(NumberFormatException e){
				System.out.println("SKIP");
			}
		}
		return str;
	}
}  