package com.example.ywq9682.eyepetizer.base;

import com.example.ywq9682.eyepetizer.main.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;

/**
 * Created by dllo on 16/7/22.
 */
public class SingleLiteOrm {
    private static SingleLiteOrm singleLiteOrm;
    private LiteOrm liteOrm;
    //类  类型  对象
    private SingleLiteOrm() {
        liteOrm = LiteOrm.newCascadeInstance(MyApp.context, "dbName.db");
    }
    //单利唯一的实例,在调用方法
    public static SingleLiteOrm getSingleLiteOrm() {
        if (singleLiteOrm == null) {
            synchronized (SingleLiteOrm.class) {
                if (singleLiteOrm == null) {
                    singleLiteOrm = new SingleLiteOrm();
                }
            }
        }
        return singleLiteOrm;
    }
    public LiteOrm getLiteOrm() {
        return liteOrm;
    }
    //封装起来
    //插入单条数据
    //object 是所有类的父类
    public void insertSingle(Object T) {
        liteOrm.insert(T);
    }
//插入多条数据
    public <T> void insertAllSingle(List<Class<T>> list) {
            liteOrm.insert(list);
    }
    //更新数据
    public void upDateSingle(Object T) {
        liteOrm.update(T);
    }
    //删除数据库的全部数据(Class<T>(对象) tClass(对象)  )
    public <T> void delectSingle(Class<T> tClass) {
        liteOrm.delete(tClass);
    }
    //按条件删除数据
    public <T> void delectSingleCondition(Class<T> tClass, String colume, String condition) {
        liteOrm.delete(new WhereBuilder(tClass).where(colume + " = ?", new String[]{condition}));
    }
    //查询本地所有数据
    //插入是插入 ,但是将来他的值还会需要用到  所以需要传出来
    public <T> List<T> quaryAllSingle(Class<T> tClass) {
        List list = liteOrm.query(tClass);
        return list;
    }
    //条件查询数据
    public <T> List<T> querySingleCondition(Class<T> tClass, String columnName, String condition) {

        List list = liteOrm.query(new QueryBuilder(tClass).where(columnName + " =  ? ", new String[]{condition}));
        return list;
    }
}
