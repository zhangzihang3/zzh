package com.zzh.service.impl;

import com.zzh.bean.Person;
import com.zzh.service.robTickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class RobTickServiceImpl implements robTickService {
    @Autowired
    Connection connection;

    @Override
    public Boolean robTick(Person person, String commodity) {
        /**
         * TODO 一个人只能抢一件商品，这里是模拟
         */
        if (person != null) {
            PreparedStatement preparedStatement = null;
            try {
                connection.setTransactionIsolation(4);
                connection.commit();
                preparedStatement = connection.prepareStatement("update test set num = num -1 where name = ?");
                preparedStatement.setString(1, commodity);
                int i = preparedStatement.executeUpdate();
                System.out.println(i);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }

        return null;
    }
}
