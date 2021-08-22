package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Collection;

@WebServlet(name = "Upload", value = "/upload")
@Log4j2
@MultipartConfig(fileSizeThreshold = 1024 * 1024)//서블릿에서 첨부파일 업로드 기능 지원
public class UploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //GET 방식으로 화면 볼꺼임
        request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //파일 폴더 경로 지정
        String uploadFolder = "C:\\upload";//업로드 받을 폴더 경로 지정
        byte[] buffer = new byte[1024 * 8];//값의 크기만 지정
        //파일 업로드 시작
        Collection<Part> parts = request.getParts();//collection은 집합, part를 통해서 접근

        parts.forEach(part -> {
            log.info(part);//prat를 통해 가져온 데이터 객체를 보여줌

            String type = part.getContentType();//어떤 타입인지 알아내려고 함
            //log.info(type); //null이 찍히는 이유 -> 일반? 파일이라서
            if (type == null) { //null 일 때 이름먼저 파악
                log.info("partName: " + part.getName());
                return;
            }

            String fileName = part.getSubmittedFileName();//등록된 파일이름을 filename에 담어줌

            String uploadFileName = System.currentTimeMillis()+ "_" + fileName;//받은 시스템에서 번호매겨서 저장한다

            log.info(fileName);//파일 이름을 출력

            //try부터 파일을 업로드 하는 코드
            try (InputStream in = part.getInputStream();//빨대 꽂아서 데이터 보내줌
                 OutputStream fos = new FileOutputStream(uploadFolder + File.separator+ System.currentTimeMillis() + "_" + fileName);
                 //separator : 중간 경로 알려주는? , 업로드받을 폴더에 중간경로를 찍어줘서 fos에 담아준다(\\)
            ) {
                while (true) {
                    int count = in.read(buffer);//파일 몇개 보냈나 확인
                    if (count == -1) { break; } // -1이면 더 이상 읽을 데이터가 없음
                    fos.write(buffer, 0, count);//그냥 빈칸이면 뱉어낼 정보가 없음
                }

            } catch (Exception e) {

            } //원본 파일 저장 끝

            //이미지에 대해서만 섬네일
            if(type.startsWith("image")) {
                try {//Thumbnails은 섬네일 을 쓰려고
                    Thumbnails.of(new File(uploadFolder + File.separator + uploadFileName))//파일을 가져와서
                            .size(100, 100)//사이즈를 정해주고
                            .toFile(uploadFolder + File.separator + "s_" + uploadFileName);//섬네일 파일로 만들어서 저장한다
                }catch (Exception e) {

                }
            }

            log.info("-------------------------------------");

        });//for each
    }
}              //이제 attach 테이블을 만든다.