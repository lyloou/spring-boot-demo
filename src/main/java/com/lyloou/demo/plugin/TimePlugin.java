package com.lyloou.demo.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class,
                        RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class,
                        RowBounds.class, ResultHandler.class})
})
public class TimePlugin implements Interceptor {


    private static final long DEFAULT_UP_TIME = 500L;


    private static final String UP_TIME_KEY = "up.time";


    private long upTime = 500L;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long timeConsuming = endTime - startTime;
        if (timeConsuming > upTime) {
            MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
            Object paramObject = invocation.getArgs()[1];
            String sql = statement.getBoundSql(paramObject).getSql();
            System.out.println("\n注意:\nSQL语句【" + sql + "】, \n"
                    + "参数 【" + String.valueOf(paramObject) + "】\n"
                    + "耗时【" + timeConsuming + "】毫秒\n");
        }
        return result;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }


    @Override
    public void setProperties(Properties properties) {

        String upTimeStr = properties.getProperty(UP_TIME_KEY, DEFAULT_UP_TIME + "");
        this.upTime = Long.valueOf(upTimeStr);
    }
}