package org.paasta.container.platform.common.api.users;


import lombok.Data;

import java.util.List;

/**
 * Users Admin Model 클래스
 *
 * @author kjh
 * @version 1.0
 * @since 2021.06.25
 */


@Data
public class UsersDetails {

    private String resultCode;
    private String resultMessage;

    private String userId;
    private String userAuthId;
    private String serviceAccountName;
    private String userType;
    private String created;

    private List<Users> items;


    public UsersDetails(String userId, String userAuthId, String serviceAccountName, String userType, String created) {
        this.userId = userId;
        this.userAuthId = userAuthId;
        this.serviceAccountName = serviceAccountName;
        this.userType = userType;
        this.created = created;
    }

    public UsersDetails(String userId, String userAuthId, String serviceAccountName, String userType, String created, List<Users> items) {
        this.userId = userId;
        this.userAuthId = userAuthId;
        this.serviceAccountName = serviceAccountName;
        this.userType = userType;
        this.created = created;
        this.items = items;
    }
}


