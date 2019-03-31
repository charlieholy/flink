package com.charlie.utils;

import javafx.scene.control.Tab;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Map;
import java.util.Set;

public class HbaseUtils {
   private static Admin admin = null;
   private static Connection conn = null;
   static {
       Configuration conf = HBaseConfiguration.create();
       conf.set("hbase.rootdir","hdfs://192.168.1.128:9000/hbase");
       conf.set("hbase.zookeeper.quorum","192.168.1.128");
       conf.set("hbase.client,scanner.timeout.period","600000");
       try {
           conn = ConnectionFactory.createConnection(conf);
           admin = conn.getAdmin();
       }catch (Exception e){
           e.printStackTrace();
       }
   }

    /**
     * 插入数据
     * @param tablename
     * @param rowkey
     * @param familyname
     * @param datamap
     * @throws Exception
     */
   public static void put(String tablename,String rowkey,String familyname,Map<String,String> datamap) throws Exception{
       Table table = conn.getTable(TableName.valueOf(tablename));
       byte[] rowkeybyte = Bytes.toBytes(rowkey);
       Put put = new Put(rowkeybyte);
       if(datamap != null){
           Set<Map.Entry<String,String>> set = datamap.entrySet();
           for(Map.Entry<String,String> entry:set){
               String key = entry.getKey();
               Object value = entry.getValue();
               put.addColumn(Bytes.toBytes(familyname),Bytes.toBytes(key),Bytes.toBytes(value+""));
           }
           table.put(put);
           table.close();
           System.out.println("ok");
       }
   }

    /**
     * 查询
     * @param tablename
     * @param rowkey
     * @param familyname
     * @param colum
     * @return
     * @throws Exception
     */
   public static String getdata(String tablename,String rowkey,String familyname,String colum) throws Exception{
       Table table = conn.getTable(TableName.valueOf(tablename));
       byte[] rowkeybyte = Bytes.toBytes(rowkey);
       Get get = new Get(rowkeybyte);
       Result result = table.get(get);
       byte[] resultbytes = result.getValue(familyname.getBytes(),colum.getBytes());
       if(resultbytes == null){
           return null;
       }
       return new String(resultbytes);
   }

   public static void putdata(String tablename,String rowkey,String familyname,String colum,String data) throws Exception{
       Table table = conn.getTable(TableName.valueOf(tablename));
       Put put = new Put(rowkey.getBytes());
       put.addColumn(familyname.getBytes(),colum.getBytes(),data.getBytes());
       table.put(put);

   }

    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir","");
    }
}
