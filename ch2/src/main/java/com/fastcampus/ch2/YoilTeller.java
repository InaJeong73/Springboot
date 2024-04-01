package com.fastcampus.ch2;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

// 년월일을 입력하면 요일을 알려주는 원격 프로그램
// 원격 프로그램을 등록
@Controller
public class YoilTeller {
//    url을 연결

    @RequestMapping("/getYoil")
        public String main(@ModelAttribute("myDate")MyDate myDate, Model model) throws IOException {

            //2. 작업-요일을 계산
        char yoil = getYoil(myDate);

        //@ModelAttribute(함수 매개변수 앞에 붙일 시)
        //매개변수에 @ModelAttribute 라고 입력하면 아래의 코드가 자동 생성되는것임.
        // model.addAttribute("myDate", myDate);
        //원하는 모델 명이 있다면 @ModelAttribute("원하는 모델명")해주면 됌.
        //모델 명을 따로 작성하지 않으면 매개변수의 클래스명의 앞글자를 소문자로 변환하여 이름을 붙임.

        //@ModelAttribute(함수명 앞에 붙일 시)
        //@ModelAttribute("yoil")
        // 메서드의 반환 타입이 yoil이라는 이름으로 저장됌.


        //작업한 결과를 모델에 반환(Dispatcher Sublet이 Model을 View로 전달해준다.)
//            model.addAttribute("year", myDate.getYear());
//            model.addAttribute("month", myDate.getMonth());
//            model.addAttribute("day", myDate.getDay());
            model.addAttribute("yoil", yoil);

           //return "yoil"; //yoil.html - 뷰의 이름을 반환
            return "yoil";
        }

        @ModelAttribute("/yoil")
        private static char getYoil(MyDate myDate) {
        Calendar cal=Calendar.getInstance();
        cal.clear();
        cal.set(myDate.getYear(), myDate.getMonth()-1, myDate.getDay());

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);// DAY_OF_WEEK가 1~7까지를 반환 1:일, 2: 월
        char yoil = "일월화수목금토".charAt(dayOfWeek-1);
        return yoil;
    }
}
