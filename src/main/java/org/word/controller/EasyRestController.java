package org.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.word.service.impl.SwaggerToExcelCase;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class EasyRestController {
    @Autowired
    SwaggerToExcelCase swaggerToExcelCase;

    @RequestMapping(value = "excel", method = RequestMethod.GET)
    @ResponseBody
    public void toEasyUseCase(String url, @RequestParam(value = "filepath", required = false) String filePath) {
        swaggerToExcelCase.exec(filePath,url);
    }

    @RequestMapping(value = "downExcel", method = RequestMethod.GET)
    public void DownEasyUseCase(String url, HttpServletResponse response) {
        try {
            swaggerToExcelCase.downExcel(url,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
