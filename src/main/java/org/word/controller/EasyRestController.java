package org.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.word.service.WordService;
import org.word.service.impl.SwaggerToExcelCase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class EasyRestController {
    @Autowired
    SwaggerToExcelCase swaggerToExcelCase;

    @RequestMapping(value = "excel", method = RequestMethod.GET)
    @ResponseBody
    public void toEasyUseCase(String url, @RequestParam(value = "filepath", required = false) String filePath) {
        swaggerToExcelCase.exec(filePath,url);
    }


}
