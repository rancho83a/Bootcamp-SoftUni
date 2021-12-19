package com.example.shoppingList.config;

import com.example.shoppingList.service.ProductService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class MobileSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final ProductService productService;

    public MobileSecurityExpressionHandler(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
       OwnerSecurityExpressionRoot root = new OwnerSecurityExpressionRoot(authentication);

        root.setProductService(productService);
        root.setPermissionEvaluator((getPermissionEvaluator()));
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
