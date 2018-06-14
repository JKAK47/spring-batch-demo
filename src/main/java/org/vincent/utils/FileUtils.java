package org.vincent.utils;

import java.io.File;

public class FileUtils {
	
	
	public static void main(String[] args) throws Exception {
		FileUtils fileUtils=new FileUtils();
		fileUtils.deleteFilePath("C:\\Users\\liuhy\\Desktop\\121提前满期");
	}
	/**
	 * @param cloudpath 删除的目录以及它的子目录和文件
	 * @return 返回文件成功与否
	 * @throws Exception
	 */
	public boolean deleteFilePath(String cloudpath)throws Exception{
		boolean flag=false;
		
		//检查目录路径
		File dir=new File(cloudpath);
		if(!dir.exists()){
			flag=true;
			return flag;
		}
		flag=deleteFile(dir);
		
		
		return flag;
	}


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param filePath 将要删除的文件目录
     */
    private static boolean deleteFile(File filePath) {
        if (filePath.isDirectory()) {
        	//获取该文件夹下所有的文件的文件名和文件夹名称
            String[] children = filePath.list();
            //递归删除目录中的子目录下所有文件以及子目录和他的子文件
            for (int i=0; i<children.length; i++) {
            	//通过 new File(filePath, children[i]) filepath目录和改目录下的一个文件 children[i] 构成一个新的File实例
                boolean success = deleteFile(new File(filePath, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return filePath.delete();
    }
}
