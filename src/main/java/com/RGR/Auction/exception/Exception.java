package com.RGR.Auction.exception;

import org.hibernate.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

@ControllerAdvice
public class Exception extends RuntimeException{
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleForNotFoundedPage()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        String message = "Данной страницы не существует";
        modelAndView.addObject("error",message);
        return modelAndView;
    }
    @ExceptionHandler(BindException.class)
    public ModelAndView handleForNotFoundPage()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Введите корректные данные");
        return modelAndView;
    }
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ModelAndView handleForNotAcceptable()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","406 Not Acceptable");
        return modelAndView;
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ModelAndView handleForTypeNotSupported()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","формат содержимого не поддерживается сервером");
        return modelAndView;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView HttpMessageNotReadableException()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Введите корректные данные");
        return modelAndView;
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleForNotSupportedException()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Данный пользователь не может полуить доступ к контенту");
        return modelAndView;
    }
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ModelAndView handleForNotWritableException()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Сервер не может обработать запрос");
        return modelAndView;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleForNotValidException()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Введите корректные данные");
        return modelAndView;
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleForMissingServletRequestParameter()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Введите корректные данные");
        return modelAndView;
    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ModelAndView handleForMissingServletRequestPart()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Введите корректные данные");
        return modelAndView;
    }
    @ExceptionHandler(TypeMismatchException.class)
    public ModelAndView handleForTypeMismatch()
    {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error","Введите корректные данные");
        return modelAndView;
    }
}
