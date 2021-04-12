package com.flashcloud.root;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.Note;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class UploadExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {

        if (e instanceof MaxUploadSizeExceededException) {

            System.out.println("MaxUploadSizeExceededException Handling");
            ModelAndView modelAndView = new ModelAndView("result.html");
            modelAndView.addObject("errorMessage", "File Size Exceeded 2 MB");

            return modelAndView;
        }
        return new ModelAndView("home.html");
    }
}
