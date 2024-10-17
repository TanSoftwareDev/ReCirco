package edu.whu.recirco;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

public class CodeGenerator {

    public static void main(String[] args) {
        //创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //配置数据源
        autoGenerator.setDataSource(dataSourceConfig());
        //全局配置
        autoGenerator.setGlobalConfig(globalConfig());
        //配置包名
        autoGenerator.setPackageInfo(packageConfig());
        //配置生成策略
        autoGenerator.setStrategy(strategyConfig());
        //执行生成操作
        autoGenerator.execute();
    }

    private static StrategyConfig strategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("type");
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityLombokModel(true);
        return strategyConfig;
    }

    private static PackageConfig packageConfig() {
        PackageConfig packageInfo = new PackageConfig();
        //设置生成的包名，与代码所在位置不冲突， 二者叠加组成完整路径
        packageInfo.setParent("edu.whu.recirco");
        //设置实体类包名
        packageInfo.setEntity("domain");
        //设置数据层包名
        packageInfo.setMapper("dao");
        return packageInfo;
    }

    private static GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        //设置代码生成位置
        globalConfig.setOutputDir(System.getProperty("user.dir")
                +"/springboot/src/main/java");
        //设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setOpen(false);
        //设置作者
        globalConfig.setAuthor("susong");
        //设置是否覆盖原始生成的文件
        globalConfig.setFileOverride(false);
        //设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setMapperName("%sDao");
        //设置Id生成策略
        globalConfig.setIdType(IdType.AUTO);
        return globalConfig;
    }

    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://rm-2zelo1hw67ko419r9zo.mysql.rds.aliyuncs.com:3306/xm_recirco_manager?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2b8&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root219410@");
        return dataSource;
    }
}
