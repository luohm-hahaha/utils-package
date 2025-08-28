package com.lhm.utils.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.cache.impl.TimedCache;

/**
 * @Auther: luohm
 * @Date: 2025/8/28 - 14:41
 * @Description: com.lhm.utils.cache
 * @version: 1.0
 */
public class CacheUtils {

    /**
     * 定时缓存
     */
    public void timeCache(){
        // 创建定时缓存，默认5分钟过期
        TimedCache<String, String> codeCache = CacheUtil.newTimedCache(5 * 60 * 1000);
        // 启动定时清理（每隔1分钟检查一次过期缓存）
        codeCache.schedulePrune(1 * 60 * 1000);

        // 存储验证码（key:手机号，value:验证码）
        codeCache.put("13800138000", "654321");

        // 验证时获取（如果过期返回null）
        String code = codeCache.get("13800138000");
        if ("654321".equals(code)) {
            System.out.println("验证通过");
        }

        // 主动清除某个缓存（比如用户注销）
        codeCache.remove("13800138000");
    }

    /**
     * LRU缓存
     * LRU缓存是一种缓存算法，最近最少使用的缓存会被优先清除。
     */
    public void lruCache(){
        // 创建LRU缓存，最多存1000个用户数据（满了就淘汰最久未使用的）
        LRUCache<String, Object> userCache = CacheUtil.newLRUCache(1000);

        // 存储用户信息（key:用户ID）
        //userCache.put("user_1001", new User(1001, "张三"));
        //userCache.put("user_1002", new User(1002, "李四"));

        // 用户访问时获取（访问后会更新"最近使用时间"）
        //User user = userCache.get("user_1001");

        // 当存入第1001个用户时，最久没被访问的那个会被自动删除
        //userCache.put("user_2000", new User(2000, "新用户"));
    }


    /**
     * LFU缓存
     * LFU缓存是一种缓存算法，最近最少使用的缓存会被优先清除。
     */
    public void lfuCache(){
        // 创建LFU缓存，最多存500个商品（容量满了淘汰访问次数最少的）
        //LFUCache<Long, Goods> goodsCache = CacheUtil.newLFUCache(500);

        // 存储商品
        //goodsCache.put(1001L, new Goods(1001, "手机"));
        //goodsCache.put(1002L, new Goods(1002, "耳机"));

        // 模拟用户访问（访问次数越多，越不容易被淘汰）
        for (int i = 0; i < 100; i++) {
           // goodsCache.get(1001L); // 手机被访问100次（热门）
        }
        //goodsCache.get(1002L); // 耳机只被访问1次（冷门）

        // 当缓存满了，会先淘汰耳机（访问次数少）
    }


    /**
     * FIFO缓存
     * FIFO缓存是一种缓存算法，先进先出的缓存会被优先清除。
     */
    public void fifoCache(){
        // 创建FIFO缓存，最多存10条公告（先进先出）
        //FIFOCache<Integer, Notice> noticeCache = CacheUtil.newFIFOCache(10);

        // 依次添加公告（ID从1到10）
        for (int i = 1; i <= 10; i++) {
        //    noticeCache.put(i, new Notice(i, "公告" + i));
        }

        // 新增第11条公告时，最早的公告1会被自动删除
        //noticeCache.put(11, new Notice(11, "最新公告"));
        //System.out.println(noticeCache.containsKey(1)); // 输出false（已被淘汰）
    }
}
