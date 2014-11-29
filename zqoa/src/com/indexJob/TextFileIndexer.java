package com.indexJob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class TextFileIndexer {
	public static void main(String[] args) throws Exception {
		/**//* 指明要索引文件夹的位置,这里是C盘的S文件夹下 */
		File fileDir = new File(" c://s ");

		/**//* 这里放索引文件的位置 */
		File indexDir = new File(" c://index ");
		Analyzer luceneAnalyzer = new StandardAnalyzer(null); // 建立一个标准分析器
		IndexWriter indexWriter = new IndexWriter(indexDir, luceneAnalyzer,
				true); // 创建一个索引器
		File[] textFiles = fileDir.listFiles();
		long startTime = new Date().getTime();

		// 增加document到索引去
		for (int i = 0; i < textFiles.length; i++) {
			if (textFiles[i].isFile()
					&& textFiles[i].getName().endsWith(" .txt ")) {
				System.out.println(" File  " + textFiles[i].getCanonicalPath()
						+ "正在被索引 . ");
				String temp = FileReaderAll(textFiles[i].getCanonicalPath(),
						" GBK ");
				System.out.println(temp);
				Document document = new Document(); // Document是一个记录。用来表示一个条目。就是搜索建立的倒排索引的条目。比如说，你要搜索自己电脑上的文件。这个时候就可以创建field。然后用field组合成
													// document
													// 。最后会变成若干文件。这个document和
													// 文件系统document不是一个概念。
				Field FieldPath = new Field(" path ", textFiles[i].getPath(),
						Field.Store.YES, Field.Index.NO); // 创建一个字段
				Field FieldBody = new Field(" body ", temp, Field.Store.YES,
						Field.Index.TOKENIZED,
						Field.TermVector.WITH_POSITIONS_OFFSETS);
				document.add(FieldPath);
				document.add(FieldBody);
				indexWriter.addDocument(document);
			}
		}
		// optimize()方法是对索引进行优化
		indexWriter.optimize();
		indexWriter.close();

		// 测试一下索引的时间
		long endTime = new Date().getTime();
		System.out.println("这花费了 " + (endTime - startTime)
				+ "  毫秒来把文档增加到索引里面去! " + fileDir.getPath());
	}

	public static String FileReaderAll(String FileName, String charset)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(FileName), charset));
		String line = new String();
		String temp = new String();

		while ((line = reader.readLine()) != null) {
			temp += line;
		}
		reader.close();
		return temp;
	}
}
