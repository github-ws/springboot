package ws.util;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class Util {

    public static void fanxingleixing(String f) throws Exception {
        Field filed = Util.class.getDeclaredField(f);
        ParameterizedType genericType = (ParameterizedType) filed.getGenericType();
        System.out.println(Arrays.toString(genericType.getActualTypeArguments()));
    }

    public static Field fanshequziduan(Class cl,String n) {
        ClassLoader classLoader= cl.getClassLoader();
        Class c=cl.getClassLoader().getClass();
        Field f=null;
        try {
            f = c.getClass().getDeclaredField("classes");
            return f;
        } catch (NoSuchFieldException e) {
        }
        c=  (Class)c.getGenericSuperclass();
        while (c!=null){
            try {
                f = c.getDeclaredField("classes");

                return f;
            } catch (NoSuchFieldException e) {
            }
            c=(Class)c.getGenericSuperclass();
        }
        return f;
    }

    public static void exceltosql() throws Exception {
        FileInputStream f=new FileInputStream(new File("C:\\Users\\ws\\Desktop\\a.xlsx"));
        XSSFWorkbook w=new XSSFWorkbook(f);
        XSSFSheet sheet=w.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        String table=sheet.getRow(0).getCell(0).getStringCellValue();
        String sq="SQ_"+table;
        System.out.println("create sequence "+sq+" start with 1 nocache increment by 1 ;");
        String sql="CREATE TABLE "+table+"  (\n\"OID\" NUMBER(18,0) DEFAULT "+sq+".NEXTVAL NOT NULL ENABLE,\n";

        for (int i=sheet.getFirstRowNum()+1;i<sheet.getLastRowNum()+1;i++){
            row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j <row.getLastCellNum(); j++) {
                cell=row.getCell(j);

                if(j==row.getLastCellNum()-1){
                    sql +=cell.getStringCellValue()+",\n";
                }else{
                    sql += cell.getStringCellValue().trim().toUpperCase()+" " ;
                }
            }
        }
        sql+="\"CREATEDON\" VARCHAR2(45 CHAR),\n";
        sql+="\"CREATEDTIME\" DATE DEFAULT CURRENT_TIMESTAMP,\n";
        sql+="\"FUSERCREATOR\" NUMBER(18,0),\n";
        sql+="\"UPDATEDON\" VARCHAR2(45 CHAR),\n";
        sql+="\"UPDATEDTIME\" DATE,\n";
        sql+="\"FUSERUPDATER\" NUMBER(18,0),\n";
        sql+="\"ENABLED\" VARCHAR2(45 CHAR) DEFAULT '是',\n";
        sql+="OptimisticLockField NUMBER(10,0) DEFAULT 0,\n";
        sql+="GCRecord NUMBER(10,0),\n";
        sql+="CONSTRAINT " +table+"_PK PRIMARY KEY (\"OID\"));";
        System.out.println(sql);
    }

    public static void exceltoinsert() throws Exception {
        FileInputStream f=new FileInputStream(new File("C:\\Users\\ws\\Desktop\\a.xlsx"));
        XSSFWorkbook w=new XSSFWorkbook(f);
        XSSFSheet sheet=w.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        String table=sheet.getRow(0).getCell(0).getStringCellValue();

        for (int i=sheet.getFirstRowNum()+1;i<sheet.getLastRowNum()+1;i++){
            String sql="insert into "+table+"  (TypeCode ,TypeName,Content) values ( ";
            row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j <row.getLastCellNum(); j++) {
                cell=row.getCell(j);
                if(CellType.NUMERIC== cell.getCellType()){
                    if(j==row.getLastCellNum()-1){
                        sql +=(int)cell.getNumericCellValue()+")";
                    }else{
                        sql += (int)cell.getNumericCellValue()+", " ;
                    }
                }else{
                    if(j==row.getLastCellNum()-1){
                        sql +="'"+cell.getStringCellValue()+"');";
                    }else{
                        sql += "'"+cell.getStringCellValue().trim().toUpperCase()+"', " ;
                    }
                }

            }
            System.out.println(sql);
        }
    }

    public static void jdbc() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/my?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "root");
        PreparedStatement p=conn.prepareStatement("select 1 from dual");
        ResultSet r=p.executeQuery();
        while (r.next()){
            System.out.println(r.getObject(1));
        }
        System.out.println(conn.isClosed());
       // conn.close();
        try {
            r=p.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(conn.isClosed());
    }

    public static void wendang(){
        DocsConfig config = new DocsConfig();
        config.setProjectPath("D:\\util\\t\\Test"); // root project path
        config.setProjectName("Projectwssssss"); // project name
        config.setApiVersion("V1.0");       // api version
        config.setDocsPath("D:\\d"); // api docs target path
        config.setAutoGenerate(Boolean.TRUE);  // auto generate
        Docs.buildHtmlDocs(config); // execute to generate
    }

    public static void main(String[] args) {
        ConcurrentHashMap m=new ConcurrentHashMap();
        m.put("b",7);
        m.put("a",35);
        for(Object k : m.keySet())

        {

            System.out.println(m.get(k));

        }
    }
}
