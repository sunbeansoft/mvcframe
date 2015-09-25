package com.baidu.nuomi.crm.ddrm.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by sunbeansoft on 15-9-1.
 */
@Service
public class Bootstrap {
    @Autowired
    private DDRMServer ddrmServer;

    private Thread server = new Thread(ddrmServer);

    @PostConstruct
    public void init() {
        server.start();
    }
}
