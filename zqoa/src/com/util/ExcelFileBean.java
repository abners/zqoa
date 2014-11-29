package com.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Excel文件的模型<br/>
 * 适用于简单的二维表格:列名、数据<br/>
 * 
 * 设置表头(列名)和数据后,设置文件生成策略:
 * <ul>
 * 		<li>1.基于模版 - 按照模版的格式填充表头和数据</li>
 * 		<li>2.新文件 - 单元格样式和格式几行高列宽自行匹配</li>
 * </ul>
 * 模版Excel文件要求前两行必须有内容,可自行设计第二行的单元格样式、数据类型、行高、列宽。<br/>
 * 功能:<br/>
 * 1.生成Excel文件<br/>
 * 2.返回一个<code>InputStream</code>对象(ByteArrayInputStream实例),配合Struts实现从内存下载文件<br/>
 * 未完成:<br/>
 * 表头的纵向和横向功能
 * @author 岳晓
 * @since  2012/09/22 07:09:42pm
 * @version 1.1
 */
public class ExcelFileBean {
	public static final ExcelHeaderDirection HEADER_DIRECTION_HORIZONTAL = ExcelHeaderDirection.HEADER_DIRECTION_HORIZONTAL;
	public static final ExcelHeaderDirection HEADER_DIRECTION_VERTICAL = ExcelHeaderDirection.HEADER_DIRECTION_VERTICAL;
	public static  ExcelFileStrategy Excel_File_NEW = ExcelFileStrategy.Excel_File_NEW;
	public static  ExcelFileStrategy Excel_File_TEMPLATE = ExcelFileStrategy.Excel_File_TEMPLATE;
	
	private ExcelHeaderDirection headerDirecttion = HEADER_DIRECTION_HORIZONTAL;
	
	public ExcelHeaderDirection getHeaderDirecttion() {
		return headerDirecttion;
		
	}
	public void setHeaderDirecttion(ExcelHeaderDirection headerDirecttion) {
		this.headerDirecttion = headerDirecttion;
	}
	private ExcelFileStrategy fileStrategy = Excel_File_NEW;
	public ExcelFileStrategy getFileStrategy() {
		return fileStrategy;
	}
	public void setFileStrategy(ExcelFileStrategy fileStrategy) {
		this.fileStrategy = fileStrategy;
	}

	/*
	 * Fields
	 */
	private String[] header;
	private List<List<Object>> data;
	private CellStyle headerCellStyle;
	private CellStyle dateCellStyle;
	private File file;
	private File templateFile;
	private String sheetName;
	
	/*
	 * getters and setters
	 */
	public CellStyle getHeaderCellStyle() {
		return headerCellStyle;
	}
	public void setHeaderCellStyle(CellStyle headerCellStyle) {
		this.headerCellStyle = headerCellStyle;
	}
	public CellStyle getDateCellStyle() {
		return dateCellStyle;
	}
	public void setDateCellStyle(CellStyle dateCellStyle) {
		this.dateCellStyle = dateCellStyle;
	}
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}
	public List<List<Object>> getData() {
		return data;
	}
	public void setData(List<List<Object>> data) {
		this.data = data;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public File getFile() {
		return file;
	}
	public File getTemplateFile() {
		return templateFile;
	}
	public void setTemplateFile(File templateFile) {
		this.templateFile = templateFile;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * 将实例存入介质
	 * @throws Exception 
	 */
	public boolean persistence() throws Exception {
		try {
			writeHeader();
			writeData();
			FileOutputStream fos = new FileOutputStream(file);
			hwb.write(fos);
			fos.close();
			return true;
		} catch (IOException e) {
			Log4j.errorLog(this, e);
			throw e;
		}
	}
	
	HSSFWorkbook hwb;
	ByteArrayInputStream bais;
	/**
	 * @return bais Type - ByteArrayInputStream继承<code>InputStream</code>，配合struts从内存下载文件
	 * @throws Exception 
	 */
	public ByteArrayInputStream toByteArrayInputStream() throws Exception {
		if(bais == null) {
			if(hwb == null) {
				writeHeader();
				writeData();
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			hwb.write(baos);
			bais = new ByteArrayInputStream(baos.toByteArray());
			baos.close();
		}
		return bais;
	}
	/**
	 * 写入表头
	 * @throws IOException,FileNotFoundException 
	 */
	private void writeHeader() throws IOException,FileNotFoundException {
		/*
		 * 如果未指定sheetName则设置为"sheetA"
		 */
		if(sheetName == null) sheetName ="sheetA";

		HSSFSheet sheet = null;
		HSSFRow row = null;
		/*
		 * 如果存储策略为新EXCEl文件,执行操作
		 */
		if(fileStrategy == Excel_File_NEW) {
			hwb = new HSSFWorkbook();
			sheet = hwb.createSheet(sheetName);
			row = sheet.createRow(0);
			System.out.println(row);
		}
		/*
		 * 如果存储策略为模版策略,执行操作
		 */
		else if(fileStrategy == Excel_File_TEMPLATE) {
			try {
				FileInputStream fis = new FileInputStream(templateFile);
				hwb = new HSSFWorkbook(fis);
				sheet = hwb.getSheetAt(0);
			row = sheet.getRow(0);
			} catch (FileNotFoundException e) {
				Log4j.errorLog(this, e);
				throw e;
			} catch (IOException e) {
				Log4j.errorLog(this, e);
				throw e;
			}	
		}
		/*
		 * 修改hwb第一个sheet的名字
		 */
		hwb.setSheetName(0, sheetName);
		/*
		 * 表头不为空时写入表头
		 * 并冻结首行
		 */
		//System.out.println(header);
		if(header!=null) {
			int size =  header.length;
			System.out.println(row);
			for(int i = 0 ; i< size ; i ++) {
				HSSFCell cell = null;
				if(fileStrategy == Excel_File_NEW) {
					cell=row.createCell(i);
				}
				else if(fileStrategy == Excel_File_TEMPLATE){
					cell=row.getCell(i);
				}
				String value = header[i];
				cell.setCellValue(value ==null ? "" : value.toString());
			}
			sheet.createFreezePane(0, 1);
		}
		
	}
	/**
	 * 写入数据区域
	 * @throws Exception 
	 */
	private void writeData() throws Exception {
		if(data == null) return;
		HSSFSheet sheet = hwb.getSheet(sheetName);
		if(sheet == null) sheet = hwb.createSheet(sheetName);
		if(fileStrategy == Excel_File_NEW) {
			final int rows = data.size();
			for (int i =0 ; i < rows ; i ++) {
				HSSFRow row=sheet.createRow(i + 1);
				final int cols = data.get(i).size();
				for (int j = 0 ; j < cols ; j ++) {
					HSSFCell cell=row.createCell(j);
					Object value = data.get(i).get(j);
					cell.setCellValue(value == null ? "" : value.toString());
				}
			}
			
		}
		else if(fileStrategy == Excel_File_TEMPLATE) {
			try {
				/*
				 * 加载模版Excel
				 * 从模板读取单元的属性:
				 * 样式集合 ,写入单元格时逐个设置样式
				 * 单元格数据类型集合,写入单元格时逐个设置数据类型
				 */
				FileInputStream fis = new FileInputStream(templateFile);
				HSSFWorkbook hwbTemplate = new HSSFWorkbook(fis);
				HSSFSheet sheettemplate = hwbTemplate.getSheetAt(0);
				HSSFRow rowTemplate =sheettemplate.getRow(1);
				
				List<HSSFCellStyle> cellStyleL = new ArrayList<HSSFCellStyle>();
				List<Integer> cellTypeL = new ArrayList<Integer>();
				int lastCellNum = rowTemplate.getLastCellNum();
				for(int i = 0 ; i < lastCellNum ; i ++) {
					HSSFCell cell=rowTemplate.getCell(i);
					HSSFCellStyle cellStyle = hwb.createCellStyle();
					cellStyle.cloneStyleFrom(cell.getCellStyle());
					cellStyleL.add(cellStyle);
					
					cellTypeL.add(cell.getCellType());
				}
				final int rows = data.size();
				for (int i =0 ; i < rows ; i ++) {
					HSSFRow row=sheet.createRow(i + 1);
					row.setHeight(rowTemplate.getHeight());
					final int cols = data.get(i).size();
					for (int j = 0 ; j < cols ; j ++) {
						HSSFCell cell=row.createCell(j);
						Object value = data.get(i).get(j);
						value = value == null ? "" : value;
						int cellType = cellTypeL.get(j);
						try {
							switch(cellType) {
								case HSSFCell.CELL_TYPE_BLANK   : cell.setCellValue(value.toString()); 					break;
								case HSSFCell.CELL_TYPE_BOOLEAN : cell.setCellValue((Boolean)value); 					break;
								case HSSFCell.CELL_TYPE_FORMULA : cell.setCellFormula(value.toString());				break;
								case HSSFCell.CELL_TYPE_NUMERIC : cell.setCellValue(Double.valueOf(value.toString()));	break;
								case HSSFCell.CELL_TYPE_STRING  : cell.setCellValue(value.toString());					break;
								case HSSFCell.CELL_TYPE_ERROR   : cell.setCellValue("!#VALUE");							break;
								default : throw new Exception("未知格式!");
							}
						} catch (IllegalArgumentException e) {
							/*转换不了的设置为空字符串*/
							cell.setCellValue("");
						}
						cell.setCellStyle(cellStyleL.get(j));
					}
				}
			}catch(Exception e) {
				Log4j.errorLog(this, e);
				throw e;
			}
		}
	}	
}