package com.tao.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统监控专用
 *
 * @author 张涛 on 2020-03-12 16:00:00
 */
@RestController
@Slf4j
public class Monitor {

    @GetMapping("")
    String monitor(HttpServletRequest request) {
        log.info("请求ip：{}", request.getRemoteAddr());
        return "I'm myok and health!";
    }
}
