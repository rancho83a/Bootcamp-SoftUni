package com.example.demo.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.service.JWTService;
import com.example.demo.service.RoutesRoleService;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.example.demo.constant.Constants.*;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class PreAuthorizationFilter extends BaseAuthFilter {

    private final JWTService jwtService;
    private final RoutesRoleService routesRoleService;

    public PreAuthorizationFilter(JWTService jwtService, RoutesRoleService routesRoleService) {
        this.jwtService = jwtService;
        this.routesRoleService = routesRoleService;
    }


    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        return method.equalsIgnoreCase(POST_TYPE) ||
                (!requestURI.equals(PATH_AUTH_USERS) &&
                        !requestURI.equals(PATH_AUTH_USERS_ADD) &&
                        !requestURI.equals(PATH_AUTH_TOKEN) &&
                        !requestURI.equals(PATH_AUTH_TOKEN_ADD));
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String header = request.getHeader(HEADER_STRING);

        if (header==null){
            header=request.getParameter(HEADER_STRING);
        }

        if(header==null || this.jwtService.isInBlackList(this.getTokenFromHeader(header))){
            this.blockRequest(ctx, HttpServletResponse.SC_UNAUTHORIZED);

            return null;
        }


        Optional<DecodedJWT> decodedJWTCandidate = this.jwtService.getDecodedJWT(this.getTokenFromHeader(header));

        if(decodedJWTCandidate.isEmpty()){
            this.blockRequest(ctx, HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        DecodedJWT decodedJWT = decodedJWTCandidate.get();

        String userId = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim(ROLES).asArray(String.class);

        String applicationName = this.getApplicationName(request);

        if(!this.routesRoleService.areRequiredRolesProvided(
            applicationName,  request.getMethod(), request.getRequestURI(),roles)){

            this.blockRequest(ctx, HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        ctx.addZuulRequestHeader(X_USER_ID, userId);
        ctx.addZuulRequestHeader(X_USER_ROLES, String.join(", ", roles));

        return null;
    }

    private String getApplicationName(HttpServletRequest request) {


        int firstSlashIndex = request.getRequestURI().indexOf('/',1);
        if(firstSlashIndex==-1){
            return request.getRequestURI();
        }

        return request.getRequestURI().substring(1,firstSlashIndex);
    }

}
