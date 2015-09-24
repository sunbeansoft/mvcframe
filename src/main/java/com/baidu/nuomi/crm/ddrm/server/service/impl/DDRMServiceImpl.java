package com.baidu.nuomi.crm.ddrm.server.service.impl;


import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMDomain;
import com.baidu.nuomi.crm.ddrm.dao.entity.DDRMProperty;
import com.baidu.nuomi.crm.ddrm.server.GlobalSession;
import com.baidu.nuomi.crm.ddrm.server.model.DDRMRequest;
import com.baidu.nuomi.crm.ddrm.server.model.DDRMResult;
import com.baidu.nuomi.crm.ddrm.server.model.DDRMServiceResult;
import com.baidu.nuomi.crm.ddrm.server.model.FieldResult;
import com.baidu.nuomi.crm.ddrm.server.service.DDRMService;
import com.baidu.nuomi.crm.ddrm.service.IDDRMDomainService;
import com.baidu.nuomi.crm.ddrm.service.IDDRMPropertyService;
import io.netty.channel.Channel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunbeansoft on 15-8-30.
 */
@Service
public class DDRMServiceImpl implements DDRMService {
    @Autowired
    private IDDRMDomainService iddrmDomainService;
    @Autowired
    private IDDRMPropertyService iddrmPropertyService;

    @Override
    public DDRMResult queryDomainProperties(String domain) {
        DDRMResult message = new DDRMResult();
        message.setSuccess(true);
        DDRMDomain ddrmDomain = iddrmDomainService.getDDRMDomainInfo(domain);
        message.setClassFullName(ddrmDomain.getClassFullName());
        message.setDomain(ddrmDomain.getDomain());
        List<DDRMProperty> ddrmPropertyList = iddrmPropertyService.getAllPropertiesByDomain(domain);
        List<FieldResult> fieldResults = new ArrayList<FieldResult>();
        for (DDRMProperty result : ddrmPropertyList) {
            FieldResult fieldResult = new FieldResult();
            fieldResult.setName(result.getPropertyKey());
            fieldResult.setValue(result.getPropertyValue());
            fieldResults.add(fieldResult);
        }
        message.setResult(fieldResults);
        return message;
    }

    @Override
    public DDRMServiceResult operateClientRequest(Object clientRequest, Channel channel) {
        DDRMServiceResult result = new DDRMServiceResult(false, true);
        try {
            if (clientRequest instanceof DDRMRequest) {
                operateDomainProperties((DDRMRequest) clientRequest, channel, result);
            }
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 处理获取参数请求
     *
     * @param request client请求
     * @param channel 连接
     * @param result  返回结果
     */
    private void operateDomainProperties(DDRMRequest request, Channel channel, DDRMServiceResult result) {
        GlobalSession.addDomainMap(request.getDomain(), channel);
        DDRMResult message = queryDomainProperties(request.getDomain());
        result.setResult(message);
        result.setSuccess(true);
    }

    @Override
    public DDRMServiceResult dropChannel(Channel channel) {
        DDRMServiceResult result = new DDRMServiceResult(false, false);
        boolean success = GlobalSession.dropChannel(channel);
        result.setSuccess(success);
        return result;
    }

    @Override
    public DDRMServiceResult pushPropertiesToDomain(String domain, DDRMResult properties) {
        DDRMServiceResult result = new DDRMServiceResult(false, false);
        List<Channel> channels = GlobalSession.getDomainContext(domain);
        if (CollectionUtils.isEmpty(channels)) {
            result.setMsg("没有" + domain + "所对应的数据");
            return result;
        }
        for (Channel channel : channels) {
            channel.writeAndFlush(properties);
            System.out.println("push to " + channel.remoteAddress() + " " + properties);
        }
        result.setSuccess(true);
        return result;
    }

    @Override
    public DDRMServiceResult pushPropertiesToDomain(String domain, DDRMResult properties, String ip, String port) {
        DDRMServiceResult result = new DDRMServiceResult(false, false);
        List<Channel> channels = GlobalSession.getDomainContext(domain);
        if (CollectionUtils.isEmpty(channels)) {
            result.setMsg("没有" + domain + "所对应的数据");
            return result;
        }
        for (Channel channel : channels) {
            InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
            if (address.getHostName().equals(ip) && address.getPort() == Integer.parseInt(port)) {
                channel.writeAndFlush(properties);
                System.out.println("push to " + channel.remoteAddress() + " " + properties);
                break;
            }
        }
        result.setSuccess(true);
        return result;
    }
}
