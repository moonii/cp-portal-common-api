package org.paasta.container.platform.common.api.users;

import lombok.Data;

import java.util.List;

@Data
public class UsersDetailsList {

    private String resultCode;
    private String resultMessage;

    private List<UsersDetails> items;

    public UsersDetailsList() {}
    public UsersDetailsList(List<UsersDetails> items) {
        this.items = items;
    }
}
