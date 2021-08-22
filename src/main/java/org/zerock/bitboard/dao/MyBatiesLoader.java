package org.zerock.bitboard.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;


public enum MyBatiesLoader {

    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    MyBatiesLoader() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public SqlSessionFactory getFactory() {
        return this.sqlSessionFactory;
    }
}
