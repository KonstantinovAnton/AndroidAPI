package com.example.mobile_task1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItem {
    Connection connect;
    String ConnectionResult ="";
    Boolean isSuccess=false;

    public List<Map<String,String>> getList(){
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect != null)
            {
                String qu = "select * from Persons";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(qu);
                while(resultSet.next())
                {
                    Map<String,String> dtname = new HashMap<String, String>();
                    dtname.put("id_person",resultSet.getString("id_person"));
                    dtname.put("fname",resultSet.getString("fname"));
                    dtname.put("lname",resultSet.getString("lname"));
                    data.add(dtname);
                }
                ConnectionResult = "Success";
                isSuccess = true;
                connect.close();
            }
            else {
                ConnectionResult = "Failed";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  data;
    }

}
