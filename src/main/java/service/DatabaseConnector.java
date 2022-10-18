package service;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector{
   private Connection con;
    public DatabaseConnector(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/StudentDatabase","root","root");

           this.con=con;

        }catch(Exception e){ System.out.println(e);}
    }
    public ArrayList<String> getAllEmail(){
        ArrayList<String> emailList = new ArrayList<>();
        try{
            Statement statement= con.createStatement();
            ResultSet rs = statement.executeQuery("Select Email from Student");
            while(rs.next()){
                emailList.add(rs.getString(1));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return emailList;
    }

}
