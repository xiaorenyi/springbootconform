package com.xry.gateway.fiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 全局过滤器
 * @author yuqinggen
 * @create 2018-11-05 17:10
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //在这里做权限校验，比如说拿到token，对token校验，判断是否允许继续调用
        HttpHeaders headers = exchange.getRequest().getHeaders();
        List<String> tokenList = headers.get("token");
        if (tokenList == null || tokenList.isEmpty()) {
            log.info("token is null.not allow call.");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //获取token校验
        String token =  tokenList.get(0);
        log.info("allow call.token = {}",token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
