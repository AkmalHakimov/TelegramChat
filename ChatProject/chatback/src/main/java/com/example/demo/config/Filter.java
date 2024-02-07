package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@CrossOrigin
//@Configuration
public class Filter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
    //    private List<MySecurity> securities = List.of(
//            new ReqSecurity("/user","get",List.of(RoleUser.ROLE_USER)),
//            new ReqSecurity("/product","post",List.of(RoleUser.ROLE_SUPERADMIN, RoleUser.ROLE_ADMIN))
//    );
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String url = request.getRequestURI();
//        String method = request.getMethod();
//        String userId = (request.getHeader("key"));
//        User currentUser = findUserById(userId);
//        if(hasPermission(url,method,currentUser))
//            filterChain.doFilter(request,response);
//    }
//
//    private boolean hasPermission(String url, String method, User currentUser) {
//        int count = 0;
//        for (ReqSecurity security : securities) {
//            if(security.getUrl().equals(url) && security.getMethod().equalsIgnoreCase(method)){
//                count++;
//            }
//        }
//
//        if(count>0){
//            if(currentUser==null){
//                return false;
//            }
//            for (ReqSecurity security : securities) {
//                if(security.getUrl().equals(url) && security.getMethod().equalsIgnoreCase(method)){
//                    if(security.getRoleUsers().contains(currentUser.getRole())) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//    private User findUserById(String userId) {
//        for (User user : Db.users) {
//            if(user.getId().toString().equals(userId)){
//                return user;
//            }
//        }
//        return null;
//    }
}
