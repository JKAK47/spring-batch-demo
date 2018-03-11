package org.vincent.translate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.vincent.translate.bean.TranslateBean;

public class FileUtils {
	
	private static String FILEDIR="C:\\app\\file";
	private static String FromFILENAME="filein.txt";
	private static String toFILENAME="fileout.txt";
	private static String CharSet="UTF-8";
	/**
	 *  读取txt文件映射为键值对
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static List<TranslateBean> getResources() throws URISyntaxException, IOException{
		List<TranslateBean> listBean=new ArrayList<>();
		Path path=Paths.get(FILEDIR, FromFILENAME);
		//Reader reader=new FileReader(file);
		try(BufferedReader reader= new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()),CharSet))){
			String text = reader.readLine();
			while (null != text) {
				TranslateBean bean=new TranslateBean();
				text=text.trim();
				if (text.startsWith("#")) {
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
			e.printStackTrace();
			throw e;
		}
		return listBean;
	}
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
		 * 通过这个将一个文件中待翻译资源 翻译为英文并持久化到文件中。
		 * @param args
		 * @throws URISyntaxException
		 * @throws IOException
		 */
	public static void main(String[] args) throws URISyntaxException, IOException {
			try {
				List<TranslateBean> beans=getResources();
				for (TranslateBean translateBean : beans) {
					System.out.println(translateBean);
					String EnText = TranslateUtils.translateFromEng(translateBean.getChText());
					translateBean.setEnText(EnText);
					System.out.println(translateBean);
				}
				saveFile(beans);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
