package org.paasta.container.platform.common.api.users;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

/**
 * User List Model 클래스
 *
 * @author kjhoon
 * @version 1.0
 * @since 2022.05.31
 */
@Data
public class UsersList {
    private String resultCode;
    private String resultMessage;


    @Column(name = "items")
    @ElementCollection(targetClass = String.class)
    private List<Users> items;

    public UsersList() {
    }


    public UsersList(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public UsersList(List<Users> items) {
        this.items = items;
    }
}
