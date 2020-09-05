package com.zzh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    Connection connection;
    @Autowired
    com.zzh.service.pointCut pointCut;
    @Test
    public void test(){
        String sql = "select * from test";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        pointCut.say();
    }

}
