package com.dhm.concurrent.threadlocal.bean;

/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/21 15:17
 */
public class RequestHolder {

    private static final ThreadLocal<Long> REQUEST_HOLDER = new ThreadLocal();

    public static void set(Long id){
        REQUEST_HOLDER.set(id);
    }

    public static void remove(){
        REQUEST_HOLDER.remove();
    }

    public static Long get(){
        return REQUEST_HOLDER.get();
    }
}