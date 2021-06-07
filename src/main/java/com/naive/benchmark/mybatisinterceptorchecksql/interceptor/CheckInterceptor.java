package com.naive.benchmark.mybatisinterceptorchecksql.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

@Component
@Intercepts(
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "prepare",
                        args = {
                                Connection.class,
                                Integer.class
                        }
                )
        }
)
public class CheckInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(
                statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory()
        );
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        BoundSql boundSql = statementHandler.getBoundSql();
        String originSql = boundSql.getSql().toUpperCase();

        System.out.println("Check sql: " + id);
        switch (sqlCommandType) {
            case "SELECT":
            case "UPDATE":
            case "DELETE":
                if (!originSql.contains("WHERE")) {
                    throw new RuntimeException("SELECT SQL should contains WHERE!");
                }
                break;
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
