package Utils;

import java.sql.*;

/**
 * ConnectDataBaseAndExecute 类的描述:数据库的连接以及执行SQL语句
 * @VERSION v1.0
 * @DATE 2021.12.21
 * @author Zhang Haohan,Zhang Yingying
 */
public class ConnectDataBaseAndExecute {
    String driver="oracle.jdbc.driver.OracleDriver";
    String url="jdbc:oracle:thin:@localhost:1521:xe";
    String user="musicboss";
    String password="123456";
    static Connection conn;


    /**
     * connectDatabase方法的简述
     * @VERSION v1.0
     * @DATE 2021.12.21
     * @author Zhang Haohan,Zhang Yingying
     * @return true 表示连接上数据库 false表示未连接上数据库
     */
    public boolean connectDatabase() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            Class.forName(driver);
            if (conn != null) {
                System.out.println("Connected to the Database");
                return true;
            } else {
                System.out.println("Failed to connect");
                return false;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * executeSqlQuery方法的简介
     * @VERSION v1.0
     * @DATE 2021.12.21
     * @Author Zhang Haohan,Zhang Yingying
     * @param type 请求的数据类型,原因是数据库直接导出xml可能是不带有请求类型的
     * @param sqlQuery 要执行的SQL语句
     * @return true 表示执行成功 false 表示执行失败
     * todo 可能需要加一个处理PreparedStatement的需求,也可能在其他函数当中去进行转换
     * todo 可能有不同的语句类型需要处理,比如说UPDATE,DELETE等
     */
    public boolean executeSqlQuery(String type,String sqlQuery){
        String sqlLanguage=sqlQuery;
        Statement statement1=null;
        if(conn!=null){
            try {
                //statement1 = conn.createStatement( );
                //statement1.executeUpdate(sqlLanguage);
                switch (type){
                    case "CLICK_A_USER":
                    case "CLICK_A_SONGLIST":
                    case "CLICK_A_SONG":
                    case "CLICK_AN_ALBUM":
                    case "LOGIN_A_USER":
                    case "SHOW_MY_SONGLISTS":
                        //System.out.println("databaseclickasong");
                        statement1=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs=statement1.executeQuery(sqlQuery);
                        DecideClassToMakeXml.decideClassToAccept(type,rs);
                        break;
                    case "INSERT_USERBASE":
                    case "INSERT_USERINFO":
                    case "INSERT_USERSONGLIST":
                    case "INSERT_SONGLIST":
                        statement1=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                        statement1.executeUpdate(sqlQuery);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }
                // todo:将这个String类型返回给客户端
                //ServerSocketUtils.sendToClientXml(result);
                System.out.println("数据执行成功!");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally{
                if(statement1!=null){
                    try {
                        statement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // conn是null的情况,return false
        return false;
    }

    /**
     * closeDatabase方法的简介:关闭数据库
     * @VERSION v1.0
     * @DATE 2021.12.21
     * @Author Zhang Haohan,Zhang Yingying
     * @return true 关闭数据库成功 false 关闭数据库失败
     */
    public boolean closeDatabase(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


}

/**
 * @VERSION v1.0
 * @DATE 2021.12.21
 * @Author  Zhang Haohan,Zhang Yingying
 * ConnectDataBaseAndExecute类的测试类
 */
class TestMain1{
    public static void main(String[] args) {
        ConnectDataBaseAndExecute databaseConn=new ConnectDataBaseAndExecute();
        databaseConn.connectDatabase();
        databaseConn.executeSqlQuery("CLICK_A_USER","SELECT DBMS_XMLGEN.GETXML('SELECT * FROM user_info WHERE user_id=20') FROM DUAL");
        databaseConn.closeDatabase();
    }
}
