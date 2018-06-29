package org.vincent.batch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * http://blog.csdn.net/xuxu198899223/article/details/38079885
 */
public class CSVUtilsTest

{
    /**
     * 生成为CVS文件
     * @param exportData
     *              源数据List，数据行
     * @param map
     *              csv文件的列表 表头map
     * @param outPutPath
     *              文件路径 存放路径
     * @param fileName
     *              文件名称 存放CSV文件名(不包括后缀)
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,
                                     String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdir();
            }
            //定义文件名格式并创建
            csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            System.out.println("csvFile：" + csvFile);
            // UTF-8使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GBK"), 1024);
            System.out.println("csvFileOutputStream：" + csvFileOutputStream);
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null ? new String(
                        ((String) propertyEntry.getValue()).getBytes("GBK"), "GBK") : "");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
                System.out.println(new String(((String) propertyEntry.getValue()).getBytes("GBK"),
                        "GBK"));
            }
            csvFileOutputStream.write("\r\n");
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
                Object row = (Object) iterator.next();
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
                        .hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                            .next();
                    csvFileOutputStream.write("122");
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.write("\r\n");
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }


    public static void main(String[] args){
        List exportData = new ArrayList<Map>();
        Map row1 = new LinkedHashMap<String, String>();
        row1.put("1", "11");
        row1.put("2", "12");
        row1.put("3", "13");
        row1.put("4", "14");
        exportData.add(row1);
        row1 = new LinkedHashMap<String, String>();
        row1.put("1", "21");
        row1.put("2", "22");
        row1.put("3", "23");
        row1.put("4", "24");
        exportData.add(row1);
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "第一列");
        map.put("2", "第二列");
        map.put("3", "第三列");
        map.put("4", "第四列");
        String path="D:\\";
        String fileName="resultCsv";
        File file=createCSVFile(exportData,map,path,fileName);
        System.out.println("文件名称: "+file.getName());
        /*List<String> resultLst = new ArrayList<String>();
        try {
            FileWriter fw = new FileWriter("D:\\resultCsv.csv");
            fw.write("开始时间,结束时间,国外如流量,过外出流量,国内入流量,国内出流量,使用的IP,本次金额,退出类型,联网类型\r\n");
            fw.flush();
            for (int i = 0; i < resultLst.size(); i++) {
                fw.write(resultLst.get(i) + ",");
                BigDecimal count = new BigDecimal(i+1);
                if(count.remainder(new BigDecimal(10)).compareTo(new BigDecimal(0)) == 0) {
                    fw.write("\r\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
