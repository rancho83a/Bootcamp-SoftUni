package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import static com.example.demo.constant.Constants.TOKEN_PREFIX;

public abstract class BaseAuthFilter extends ZuulFilter {

    protected String getTokenFromHeader(String header){

        return header.replace(TOKEN_PREFIX, "");
    }

    protected void blockRequest(RequestContext requestContext, int statusCode) {

        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(statusCode);
    }
}
