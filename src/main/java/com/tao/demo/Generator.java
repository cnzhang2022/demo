package com.tao.demo;

import com.tao.frameworks.mybatis.tools.CodeGenerator;
import java.math.BigDecimal;

/**
 * 代码自动生成
 *
 * @author 李银 on 2019-09-02 22:02:08
 */
public class Generator {

    public static void main(String[] args) {

        /**
         * 项目绝对路径，如果不填写则为当前项目地址 classpath
         */
        CodeGenerator.PROJECT_ABSOLUTE_DIR = null;
        /**
         * 生成代码输出的初始包路径
         */
        CodeGenerator.BASE_PACKAGE = Generator.class.getPackage().getName();
        /**
         * 生成代码输出的初始包路径下的子目录，自定义
         * 如：abc, 那么文件就输出到  com.zt.myok下
         */
        CodeGenerator.SUB_PACKAGE = "";
        /**
         * 数据库连接地址
         */
        CodeGenerator.DB_URL = "jdbc:mysql://47.99.237.229:3306/test";
        /**
         * 数据库用户
         */
        CodeGenerator.DB_USERNAME = "root";
        /**
         * 数据库密码
         */
        CodeGenerator.DB_PASSWORD = "root";
        /**
         * 表名， 为空会则生成整个数据库的所有表
         */
        CodeGenerator.TOBE_GENERATE_TABLES = "users";
        /**
         * 作者信息
         */
        CodeGenerator.AUTHOR = "zt";

        CodeGenerator.addTypeMapping("INT UNSIGNED", Integer.class);
        CodeGenerator.addTypeMapping("DECIMAL UNSIGNED", BigDecimal.class);
        CodeGenerator.addTypeMapping("TINYINT", Integer.class);
        CodeGenerator.addTypeMapping("TINYINT UNSIGNED", Integer.class);
        CodeGenerator.addTypeMapping("BIT", Integer.class);

        CodeGenerator.main(null);


    }

}
