package com.ably;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private AblyClient ablyClient;
    @Autowired
    private AblyChannel ablyChannel;

    @RequestMapping("/push")
    public void push() {
        ablyClient.create();
//        ablyChannel.subscribe();
    }

    @RequestMapping("/pop")
    public void pop() {
//        ablyClient.create();
        ablyChannel.subscribe();
    }
}
