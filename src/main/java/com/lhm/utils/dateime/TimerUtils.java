package com.lhm.utils.dateime;

import cn.hutool.core.date.TimeInterval;

/**
 * @Auther: luohm
 * @Date: 2025/8/28 - 10:49
 * @Description: com.lhm.utils.dateime
 * @version: 1.0
 */
public class TimerUtils {


    /**
     * 单任务计时
     * interval()：毫秒
     * intervalSecond()：秒
     * intervalMinute()：分钟
     */
    public void singleTask() throws InterruptedException {
        // 创建计时器
        TimeInterval timer = new TimeInterval();

        // 开始计时（也可以在创建时直接调用start()）
        timer.start();

        // 模拟业务操作（比如查询数据库）
        Thread.sleep(150);

        // 输出耗时（默认毫秒）
        long cost = timer.interval();
        System.out.println("查询数据库耗时：" + cost + "ms"); // 输出：查询数据库耗时：150ms
    }

}
