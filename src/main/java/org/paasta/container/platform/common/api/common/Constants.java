package org.paasta.container.platform.common.api.common;

import org.paasta.container.platform.common.api.exception.CommonErrCode;
import org.paasta.container.platform.common.api.exception.ErrorMessage;
import org.paasta.container.platform.common.api.users.Users;
import org.paasta.container.platform.common.api.users.UsersList;

/**
 * Constants 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2020.08.25
 */
public class Constants {

    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    public static final String RESULT_STATUS_FAIL = "FAIL";
    public static final String RESULT_STATUS_NOTFOUND= "NOTFOUND";

    public static final String STRING_DATE_TYPE = "yyyy-MM-dd HH:mm:ss";
    public static final String STRING_TIME_ZONE_ID = "Asia/Seoul";
    public static final String TARGET_CP_API = "cpApi";

    public static final String IS_ADMIN_TRUE = "true";
    public static final String IS_ADMIN_FALSE = "false";
    public static final String NULL_REPLACE_TEXT = "-";
    public static final String IS_INACTIVE = "inactive";
    // API URI
    public static final String URI_API_ADMIN_TOKEN = "/adminToken";
    public static final String URI_API_ADMIN_TOKEN_DETAIL = "/adminToken/{tokenName:.+}";


    // authority
    public static final String AUTH_SUPER_ADMIN = "SUPER_ADMIN";
    public static final String AUTH_CLUSTER_ADMIN = "CLUSTER_ADMIN";
    public static final String AUTH_NAMESPACE_ADMIN = "NAMESPACE_ADMIN";
    public static final String AUTH_USER = "USER";

    public static final String DEFAULT_SUPER_ADMIN_ROLE = "cluster-admin";
    public static final String DEFAULT_CLUSTER_ADMIN_ROLE = "cluster-admin";
    public static final String CP_ADMIN_ROLE = "cp-admin-role";
    public static final String CP_INIT_ROLE = "cp-init-role";

    // sort
    public static final String DESC = "desc";
    public static final String ASC ="asc";
    public static final String USERS = "users";


    public static final String CP_USER_ID_COLUM = "userId";
    public static final String CP_USER_CREATED_COLUM ="created";

    public static final String CHECK_Y = "Y";
    public static final String CHECK_N = "N";

    public static final String ALL_VAL = "all";
    public static final String NONE_VAL = "";

    public static final String USER_CREATE_FAILED_MESSAGE = "User Create Failed.";
    public static final String USER_CREATE_SUCCESS_MESSAGE = "User Create successs.";
    public static final String USER_NOT_REGISTERED_IN_KEYCLOAK_MESSAGE = "USER_NOT_REGISTERED_IN_KEYCLOAK";
    public static final String USER_NOT_MAPPED_TO_THE_NAMESPACE_MESSAGE = "USER_NOT_MAPPED_TO_THE_NAMESPACE";
    public static final String SUPER_ADMIN_ALREADY_REGISTERED_MESSAGE = "SUPER_ADMIN_ALREADY_REGISTERED";
    public static final String USER_ALREADY_REGISTERED_MESSAGE = "USER_ALREADY_REGISTERED";
    public static final String USER_REGISTRATION_AVAILABLE_MESSAGE = "USER_REGISTRATION_AVAILABLE";
    public static final String DATA_NOT_FOUND_MESSAGE = "DATA_NOT_FOUND";
    public static final String REQUEST_COULD_NOT_BE_PROCESSED = "REQUEST_COULD_NOT_BE_PROCESSED";

    public static final String CLUSTER_STATUS_ACTIVE = "A";
    public static final String CLUSTER_STATUS_CREATING = "C";
    public static final String CLUSTER_STATUS_DISABLED_ = "D";

    public static final ErrorMessage NOT_FOUND_RESULT_STATUS =
            new ErrorMessage(Constants.RESULT_STATUS_FAIL, CommonErrCode.NOT_FOUND.name(), CommonErrCode.NOT_FOUND.getErrCode(),CommonErrCode.NOT_FOUND.name());



    public static final ResultStatus USER_CREATE_SUCCESS =  new ResultStatus(Constants.RESULT_STATUS_SUCCESS, USER_CREATE_SUCCESS_MESSAGE,
            200, USER_CREATE_SUCCESS_MESSAGE);

    public static final UsersList USER_NOT_REGISTERED_IN_KEYCLOAK = new UsersList(Constants.RESULT_STATUS_SUCCESS, Constants.USER_NOT_REGISTERED_IN_KEYCLOAK_MESSAGE);


    public static final Users USER_NOT_FOUND = new Users(Constants.RESULT_STATUS_FAIL, CommonStatusCode.NOT_FOUND.getMsg());
    public static final Users USER_NOT_FOUND_IN_KEYCLOAK = new Users(Constants.RESULT_STATUS_FAIL, Constants.USER_NOT_REGISTERED_IN_KEYCLOAK_MESSAGE);

    private Constants() {
        throw new IllegalStateException();
    }

    public static final String HOST_CLUSTER_TYPE = "HOST";
}