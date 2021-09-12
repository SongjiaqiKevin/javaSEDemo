package entity.parallelTest;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchPatent {
    private String ansId;
    private String patentName;

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String toRow(){
        return String.format("%s,%s",this.ansId,this.patentName);
    }

    public void PointsToCsvFile(List<SearchPatent> pointsList){
        if (pointsList!=null && pointsList.size() > 0){
            // 表格头
            String[] headArr = new String[]{"PointId", "X", "Y"};
            //CSV文件路径及名称
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String filePath = "E:\\TestCsvDirectory"; //CSV文件路径
            String fileName = "CSV_"+ df.format(localDateTime) +".csv";//CSV文件名称
            File csvFile = null;
            BufferedWriter csvWriter = null;
            try {
                csvFile = new File(filePath + File.separator + fileName);
                File parent = csvFile.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                csvFile.createNewFile();

                // GB2312使正确读取分隔符","
                csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "utf-8"), 1024);

                //这部分在第一行居中展示文件名称，根据实际情况，可选择取消注释
            /*int num = headArr.length / 2;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                buffer.append(",");
            }
            csvWriter.write(buffer.toString() + fileName + buffer.toString());
            csvWriter.newLine();*/

                // 写入文件头部标题行
                csvWriter.write(String.join(",", headArr));
                csvWriter.newLine();

                // 写入文件内容
                for (SearchPatent points : pointsList) {
                    csvWriter.write(points.toRow());
                    csvWriter.newLine();
                }
                csvWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    csvWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
