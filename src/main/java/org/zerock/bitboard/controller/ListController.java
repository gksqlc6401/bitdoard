package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;
import org.zerock.bitboard.dto.PageMaker;
import org.zerock.bitboard.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "ListController", value = "/board/list")
public class ListController extends HttpServlet {

    private Integer getInt(String str) {
        try {
            int values = Integer.parseInt(str);//문자열은 int값으로 형변환
            if( values <= 0){//페이지의 파라미터 값이 0보다 작으면 생기는 에러를 막아준다.
                return null;//0보다 작으면 null(빈값)로 리턴
            }
            return values;
        }catch (Exception e){
            return null;//Integer은 return값을 null로한다.
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer page = getInt(request.getParameter("page"));//String값으로 가져와서 getInt메소드로 Integer로 형변환해줌.
        Integer size = getInt(request.getParameter("size"));//String값으로 가져와서 getInt메소드로 Integer로 형변환해줌.

        PageDTO pageDTO = PageDTO.builder().build();

        if(page != null) {pageDTO.setPage(page);};//만약 페이지가 null이 아니면 pageDTO에서 page를 넣어준다
        if(size != null) {pageDTO.setSize(size);};//만약 페이지가 null이 아니면 pageDTO에서 size를 넣어준다

        log.info("======================");
        log.info(pageDTO);

        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("======================================");
        log.info(dtoList);

        request.setAttribute("dtoList",dtoList);

        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(),1230);

        request.setAttribute("pageMaker",pageMaker);

        request.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(request,response);

    }


}

