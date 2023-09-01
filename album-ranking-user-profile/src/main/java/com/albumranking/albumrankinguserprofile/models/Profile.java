package com.albumranking.albumrankinguserprofile.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Profile {

    @Id
    private String userId;
    private String userMail;
    private String userName;
    private String userImage;

    public Profile(String userId, String userMail, String userName, String userImage) {
        this.userId = userId;
        this.userMail = userMail;
        this.userName = userName;
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userId='" + userId + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userName='" + userName + '\'' +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}
