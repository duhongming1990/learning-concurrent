package com.dhm.concurrent.threadlocal.controller;

import com.dhm.concurrent.threadlocal.bean.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author duhongming
 * @Email 19919902414@189.cn
 * @Date 2018/9/21 16:08
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.get();
    }
}
