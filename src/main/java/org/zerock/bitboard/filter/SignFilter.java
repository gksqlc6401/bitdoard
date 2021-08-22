//package org.zerock.bitboard.filter;
//
//import lombok.extern.log4j.Log4j2;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Log4j2
//@WebFilter(filterName = "signin", urlPatterns={"/board/register","/board/read"})//이거 두개는 필터처리해라
//public class SignFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//       log.info("Signin Filter.........................run................");
//
//        HttpServletRequest req = (HttpServletRequest) request;//servlet은 자동으로 형변환이 되는데 filter는 형변환이 안되서 직접 해줘야함
//        HttpServletResponse res = (HttpServletResponse) response;//``형변환  형변환 하는이유는 웹에서 정보를 불러와야해서 다운캐스팅을 한다.
//
//        HttpSession session = req.getSession();//세션을 불러온다
//
//        //로그인 확인
//        if(session.getAttribute("member")==null) {//세션이 빈값이면
//            res.sendRedirect("/login");//튕겨낸다.
//            return;
//        }
//
//
//        chain.doFilter(request,response);//그냥 체인 돌아갈때 필터처리 하게하는것
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
