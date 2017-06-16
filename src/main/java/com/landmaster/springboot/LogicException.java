package com.landmaster.springboot;

import java.util.List;

/**
 * Created by tdl on 2017/4/28.
 */
public class LogicException extends RuntimeException {

    private int level;
    private List<String> codes;
    private String message;
    private final void init() {
        level = 0;
        codes = null;
    }

    /**
     *
     * @return 错误等级
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return 错误编码，每个元素是一个错误编码
     */
    public List<String> getCodes() {
        return codes;
    }

    public LogicException() {
        init();
    }

    /**
     *
     * @param level 错误等级
     * @param message 错误原始描述
     */
    public LogicException(int level, String message) {
        super(message);
        this.message=message;
        init();
        this.level = level;
    }

    /**
     *
     * @param level 错误等级
     * @param codes 错误编码集合
     * @param message 原始的错误信息
     */
    public LogicException(int level, List<String> codes, String message) {
        super(message);
        this.message=message;
        init();
        this.level = level;
        this.codes = codes;
    }

    public LogicException(String message) {
        super(message);
        this.message=message;
        init();
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
        this.message=message;
    }

    public LogicException(Throwable cause) {
        super(cause);
    }

    public LogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message=message;
        init();
    }
    public String getMessage(){
        return this.message;
    }
}

