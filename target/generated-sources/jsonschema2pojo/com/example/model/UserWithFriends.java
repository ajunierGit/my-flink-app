
package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.example.typeInfo.FriendListTypeInfoFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.flink.api.common.typeinfo.TypeInfo;


/**
 * UserWithFriends
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userData",
    "friends"
})
public class UserWithFriends implements Serializable
{

    /**
     * User
     * <p>
     * 
     * 
     */
    @JsonProperty("userData")
    private User userData;
    @JsonProperty("friends")
    @TypeInfo(FriendListTypeInfoFactory.class)
    private List<User> friends = new ArrayList<User>();
    private final static long serialVersionUID = -4534986295704341478L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserWithFriends() {
    }

    /**
     * 
     * @param userData
     * @param friends
     */
    public UserWithFriends(User userData, List<User> friends) {
        super();
        this.userData = userData;
        this.friends = friends;
    }

    /**
     * User
     * <p>
     * 
     * 
     */
    @JsonProperty("userData")
    public User getUserData() {
        return userData;
    }

    /**
     * User
     * <p>
     * 
     * 
     */
    @JsonProperty("userData")
    public void setUserData(User userData) {
        this.userData = userData;
    }

    @JsonProperty("friends")
    public List<User> getFriends() {
        return friends;
    }

    @JsonProperty("friends")
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserWithFriends.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userData");
        sb.append('=');
        sb.append(((this.userData == null)?"<null>":this.userData));
        sb.append(',');
        sb.append("friends");
        sb.append('=');
        sb.append(((this.friends == null)?"<null>":this.friends));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.friends == null)? 0 :this.friends.hashCode()));
        result = ((result* 31)+((this.userData == null)? 0 :this.userData.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserWithFriends) == false) {
            return false;
        }
        UserWithFriends rhs = ((UserWithFriends) other);
        return (((this.friends == rhs.friends)||((this.friends!= null)&&this.friends.equals(rhs.friends)))&&((this.userData == rhs.userData)||((this.userData!= null)&&this.userData.equals(rhs.userData))));
    }

}
