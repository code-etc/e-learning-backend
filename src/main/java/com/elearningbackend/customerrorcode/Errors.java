package com.elearningbackend.customerrorcode;

public enum Errors {
    USER_ERROR("001","User errors"),
    USER_EXISTS("002","USER EXISTS"),
    USER_NOT_FOUND("003", "USER NOT FOUND"),
    USER_PASSWORD_NOT_MATCH("004", "PASSWORD NOT MATCH"),
    ERROR_FIELD_MISS("005","FIELD INPUT MISSING"), 
    EMAIL_EXISTS("006", "EMAIL EXISTS"),
    EMAIL_SAME_WITH_OTHER_USERS("007", "EMAIL SAME WITH OTHER USERS"),
    CATEGORY_NOT_FOUND("008", "CATEGORY NOT FOUND"),
    SUBCATEGORY_NOT_FOUND("009", "SUBCATEGORY NOT FOUND"),
    ANSWER_EXIST("010", "ANSWER EXIST"),
    ANSWER_NOT_EXITS("011", "ANSWER NOT EXIST"),
    USERNAME_AND_PASSWORD_IS_NOT_EMPTY("100","USERNAME AND PASSWORD IS NOT EMPTY"),
    NOT_TOKEN("101","NOT TOKEN"),
    TOKEN_NOT_MATCH("102","TOKEN NOT MATCH"),
    ACCESS_DENIED("103","ACCESS DENIED");

    private final String id;
    private final String message;

    Errors(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {return id;}

    public String getMessage() {return message;}

}
