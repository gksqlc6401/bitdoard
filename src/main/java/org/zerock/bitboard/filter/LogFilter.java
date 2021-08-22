package org.zerock.bitboard.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(urlPatterns = {"/*"})
@Log4j2
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("--------------------------------");
        log.info("--------------------------------");
        log.info("init filter");
        log.info("--------------------------------");
        log.info("--------------------------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter--------------------------------");
        log.info("doFilter--------------------------------");
        log.info("doFilter--------------------------------");
        log.info("doFilter--------------------------------");

        request.setCharacterEncoding("UTF-8");//한글쓰이게

        chain.doFilter(request,response);//필터 체인 사용해서 한글쓰이게, 바로 웹으로 보내주기 때문에 Http로 형변환 할필요가 없다.
    }

    @Override
    public void destroy() {

    }
}
