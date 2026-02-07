package com.example.complaint_system.Utility;

public class CPT_SYTMUtil {

    public enum ComplaintStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String CANCELLED = "cancelled";

    public static final String USER_NOT_FOUND = "user not found";
    public static final String USER_ALREADY_EXISTS = "user already exists";
    public static final String USER_LOCKED = "user locked";
    public static final String USER_UNAUTHORISED = "user unauthorized";
    public static final String REGISTRATION_SUCCESS = "registration success";
    public static final String REGISTRATION_FAILURE = "registration failed";
    public static final String REGISTRATION_CANCELLED = "registration cancelled";
    public static final String REGISTRATION_LOCKED = "registration locked";
    public static final String LOGIN_SUCCESS = "login success";
    public static final String LOGIN_FAILURE = "login failed";
    public static final String LOGIN_CANCELLED = "login cancelled";
    public static final String LOGOUT_SUCCESS = "logout";
    public static final String LOGOUT_FAILURE = "logout failed";
    public static final String INVALID_PASSWORD_INVALID_USERNAME = "invalid password and username";
    public static final String COMPLAINTS_NOT_FOUND = "complaints not found";
    public static final String COMPLAINTS_ALREADY_EXISTS = "complaints already exists";
    public static final String COMPLAINTS_CANCELLED = "complaints cancelled";
    public static final String COMPLAINTS_FETCHED = "complaints fetched";
    public static final String COMPLAINTS_REGISTERED = "complaints registered";


    public static final int SUCCESS_CODE = 200;
    public static final int CREATED_CODE = 201;
    public static final int ACCEPTED_CODE = 202;
    public static final int NO_CONTENT_CODE = 204;

    public static final int MOVED_PERMANENTLY_CODE = 301;
    public static final int FOUND_CODE = 302;
    public static final int NOT_MODIFIED_CODE = 304;

    public static final int BAD_REQUEST_CODE = 400;
    public static final int UNAUTHORIZED_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;
    public static final int NOT_FOUND_CODE = 404;
    public static final int METHOD_NOT_ALLOWED_CODE = 405;
    public static final int CONFLICT_CODE = 409;
    public static final int UNSUPPORTED_MEDIA_CODE = 415;
    public static final int UNPROCESSABLE_ENTITY_CODE = 422;


    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final int NOT_IMPLEMENTED_CODE = 501;
    public static final int BAD_GATEWAY_CODE = 502;
    public static final int SERVICE_UNAVAILABLE_CODE = 503;

}
