import java.io.*;

public class textDisKit {
    public static void main(String[] args) {
        String fileName = "E:\\dispose\\raw.sql";
//        File rawFile = new File(fileName);
        byte[] caches = new byte[1024*8];
        StringBuilder sb = new StringBuilder();
        try {
            FileOutputStream fos = new FileOutputStream("E:\\dispose\\delete.txt",true);
            FileInputStream fis = new FileInputStream(fileName);
            int index;
            while((index = fis.read(caches))!=-1){
                if(((char)index)!='\r'){
                    String yy = new String(caches,0,index,"utf8");
                    sb.append(yy);
                }
            }
            //开始对字符串进行处理
            String raw = sb.toString();
            //获取表名
            Integer orginNum=0;
            Integer gpsPoint;
            Integer startIndex;
            Integer endIndex;
            String tableName;
//            StringBuffer tableNames= new StringBuffer();
            String keyWord;
            Integer keyStartIndex;
            Integer keyEndIndex;
            Integer keyValStartIndex;
            Integer keyValEndIndex;
            String keyVal;

            String deleteSql;
            do {
                //获取表名
                gpsPoint = raw.indexOf("insert into",orginNum);
                startIndex = raw.indexOf('`',gpsPoint);
                endIndex = raw.indexOf('`',startIndex+1);
                orginNum=gpsPoint + 10;
                tableName = raw.substring(startIndex+1,endIndex);
                System.out.println(tableName);
                //获取主键
                keyStartIndex = raw.indexOf('`',endIndex+1);
                keyEndIndex =raw.indexOf('`',keyStartIndex+1);
                keyWord = raw.substring(keyStartIndex+1,keyEndIndex);

                //获取主键值
                keyValStartIndex = raw.indexOf("values",orginNum)+7;
                keyValEndIndex = raw.indexOf(',',keyValStartIndex);
                keyVal = raw.substring(keyValStartIndex,keyValEndIndex);
                System.out.println("主键值："+keyVal);

                //组装语句
                deleteSql = "delete from "+tableName+" where "+keyWord+" = '" +keyVal+"';\n";
                //输出删除语句
                fos.write(deleteSql.getBytes("UTF-8"));
                fos.flush();
            }while(gpsPoint!=-1);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
