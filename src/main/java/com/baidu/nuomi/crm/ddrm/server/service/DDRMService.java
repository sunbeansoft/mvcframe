package com.baidu.nuomi.crm.ddrm.server.service;


import com.baidu.nuomi.crm.ddrm.server.model.DDRMResult;
import com.baidu.nuomi.crm.ddrm.server.model.DDRMServiceResult;
import io.netty.channel.Channel;


/**
 * Created by sunbeansoft on 15-8-30.
 */
public interface DDRMService {

    /**
     * 获取相关属性
     *
     * @param domain 域名称
     * @return
     */
    public DDRMResult queryDomainProperties(String domain);


    /**
     * 准对客户端的请求做出相应的操作
     *
     * @return
     */
    public DDRMServiceResult operateClientRequest(Object clientRequest, Channel channel);

    /**
     * 当client断开时将session中的数据去除
     *
     * @param channel 链接
     * @return
     */
    public DDRMServiceResult dropChannel(Channel channel);

    /**
     * 将配置数据推送到domain的各个服务其中
     *
     * @param domain     域名
     * @param properties 配置信息
     * @return
     */
    public DDRMServiceResult pushPropertiesToDomain(String domain, DDRMResult properties);

    public DDRMServiceResult pushPropertiesToDomain(String domain, DDRMResult properties, String ip, String port);
}
