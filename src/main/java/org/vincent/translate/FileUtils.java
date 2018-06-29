package org.vincent.translate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;
import org.vincent.translate.bean.TranslateBean;

public class FileUtils {
	
	private static String FILEDIR="C:\\app\\file";
	private static String FromFILENAME="filein.txt";
	private static String toFILENAME="fileout.txt";
	private static String TOSQL="fileout.sql";
	private static String CharSet="UTF-8";
	/**
	 *  读取txt文件映射为翻译键值对
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static List<TranslateBean> getResources() throws URISyntaxException, IOException{
		List<TranslateBean> listBean=new ArrayList<>();
		List<String> Notes=new ArrayList<>();//记录文档中注释项
		Path path=Paths.get(FILEDIR, FromFILENAME);
		TranslateBean bean=null;
		//Reader reader=new FileReader(file);
		try(BufferedReader reader= new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()),CharSet))){
			String text = reader.readLine();
			while (null != text) {
				bean=new TranslateBean();
				text=text.trim();
				if (text.startsWith("#")) {
					Notes.add(text.trim());
					text=reader.readLine();
					continue;
				}
				if (StringUtils.isEmpty(text)) {
					text=reader.readLine();
					continue;
				}
				String[] keyValue = text.split("=");
				bean.setKey(keyValue[0]);
				bean.setChText(keyValue[1]);
				listBean.add(bean);
				text = reader.readLine();
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(bean);
			e.printStackTrace();
			throw e;
		}
		return listBean;
	}
	/**
	 * 根据翻译后的键值对获取到安装 key=value 形式持久化到文件中
	 * @param beans
	 */
	public static void saveFile(List<TranslateBean>  beans){
		Path path=Paths.get(FILEDIR,toFILENAME);
		StringBuilder builder= null;
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile()),CharSet))){
			for (TranslateBean bean : beans) {
				builder=new StringBuilder();
				builder.append(bean.getKey()+"="+ bean.getEnText());
				writer.write(builder.toString());
				writer.newLine();
			}
			writer.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据翻译后的键值对获取到安装 key=value 形式持久化到文件中
	 * @param beans
	 * INSERT INTO t_expcodedef (appid, code, name, engname, smpname, lasttime) VALUES ('sys_lp', 'sys_lp-UW10092', '根據保單號、投保單號、投保人客戶號、受保人客戶號獲取核保問題信息集合失敗', 'According To The Policy Number, The Policy Number, The Insured Client Number, The Insured Client Number To Obtain The Insured Problem Information Set Failed', '根据保单号、投保单号、投保人客户号、受保人客户号获取核保问题信息集合失败', '2018-03-13');
	 */
	public static void saveSQL(List<TranslateBean>  beans) throws IOException {
		Path path=Paths.get(FILEDIR,TOSQL);
		File file=path.toFile();
		if (!file.exists()){
				file.createNewFile();
		}else {
				//清空文本内容
				clearFile(file);
		}
		StringBuilder builder= null;
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),CharSet))){

			for (TranslateBean bean : beans) {
				builder=new StringBuilder();
				builder.append("INSERT INTO t_expcodedef (appid, code, name, engname, smpname, lasttime) VALUES ('sys_lp', ");
				builder.append("'"+bean.getKey()+"', ");//code
				builder.append("'"+bean.getChText()+"', ");//name
				builder.append("'"+bean.getEnText()+"', ");//engname
				builder.append("'"+bean.getChText()+"', ");//engname
				builder.append("'2018-03-13');");
				writer.write(builder.toString());
				writer.newLine();
			}
			writer.flush();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		/**
		 * 不删除现有文件，只删除文件内容；
		 * 文件如果不存在将自动生成。
		 * 清空文件内容
		 * @param file
		 */
		private static void clearFile(File file) {
				try {
						if(!file.exists()) {
								file.createNewFile();
						}
						FileWriter fileWriter =new FileWriter(file);
						fileWriter.write("");
						fileWriter.flush();
						fileWriter.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		/**
		 * 通过这个将一个文件中待翻译资源 翻译为英文并持久化到文件中。
		 * @param args
		 * @throws URISyntaxException
		 * @throws IOException
		 */
	public static void main(String[] args) throws URISyntaxException, IOException {
			try {
				List<TranslateBean> beans=getResources();
				for (TranslateBean translateBean : beans) {
					String EnText = TranslateUtils.translateFromEng(translateBean.getChText());
					translateBean.setEnText(EnText);
				}
				saveSQL(beans);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
