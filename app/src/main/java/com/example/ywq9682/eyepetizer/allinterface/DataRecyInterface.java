package com.example.ywq9682.eyepetizer.allinterface;

/**
 * Created by YWQ9682 on 2016/7/19.
 */
public interface DataRecyInterface {
    //
    // Interface是对abstract关键词的进一步强化，它能让你定义类的形式：
    // 方法名，参数列表，返回值，它的方法不用特别声明都是public的
    //    不同的类可以实现同一个接口
    //规范 回调
//            这就是接口的强大之处
    void DataRecyOnClick(int position);
}
