package edu.whu.recirco.security;

import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Strings.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response); //调用后续的过滤器
            return;
        }
        try {
            final String token = header.substring(7);
            //解析token，如果token不合法会抛出异常
            Claims claims = jwtTokenUtil.getClaimFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
            if (userDetails != null && jwtTokenUtil.validateToken(token, userDetails)) {
                //创建一个身份令牌放入security context中，后面的过滤器可以使用
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            //解析token时产生的异常记入日志，不用直接抛出。因为后续的过滤器没有身份信息，会返回认证错误
            logger.warn(e);
        }
        chain.doFilter(request, response); //调用后续的过滤器
    }
}