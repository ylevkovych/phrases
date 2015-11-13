package com.levkip.phrases.webapp.controller;

import com.levkip.phrases.exception.InvalidInputException;
import com.levkip.phrases.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class HandleController {

    private static Logger logger = LoggerFactory.getLogger(HandleController.class);

    private static final String ERROR = "error";
    private static final String CODE = "code";

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Map<String, Object> handleInvalidInputException(InvalidInputException ex)
    {
        return this.processError(ex, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handlePersistenceException(PersistenceException ex)
    {
        return this.processError(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }





    private Map<String, Object> processError(Exception ex, HttpStatus httpStatus)
    {
        logger.warn(ex.getLocalizedMessage(), ex);

        Map<String, Object> error = new HashMap<String, Object>();
        error.put(ERROR, ex.getLocalizedMessage());
        error.put(CODE, httpStatus.value());

        return error;
    }
}
