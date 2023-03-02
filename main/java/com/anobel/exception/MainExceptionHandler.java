package com.anobel.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class MainExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(MainExceptionHandler.class);



    private ModelAndView getModelAndView(HttpServletRequest request, HttpStatus httpStatus,Exception exception){
        logger.error("Exception = {} :: URL = {}: ",exception.getMessage(),request.getRequestURL());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code",httpStatus.value() + " // " + httpStatus.getReasonPhrase());
        modelAndView.addObject("message",exception.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView internalServerErrorHandler(HttpServletRequest request,Exception exception){
        return getModelAndView(request,HttpStatus.INTERNAL_SERVER_ERROR,exception);
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView accessDeniedExceptionHandler(HttpServletRequest request,AccessDeniedException exception){
        return getModelAndView(request,HttpStatus.FORBIDDEN,exception);
    }
    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView nullEntityReferenceExceptionHandler(HttpServletRequest request,NullEntityReferenceException exception){
        return getModelAndView(request,HttpStatus.BAD_REQUEST,exception);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView entityNotFoundExceptionHandler(HttpServletRequest request,EntityNotFoundException exception){
        return getModelAndView(request,HttpStatus.NOT_FOUND,exception);
    }
}
