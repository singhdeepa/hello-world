package com.bitjini.myapplication;

/**
 * Created by bitjini on 26/3/16.
 */
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("created_at")
    private String DateCreated;

    @SerializedName("id")
    private long Id;

    @SerializedName("id_str")
    private String IdStr;

    @SerializedName("text")
    private String Text,Text1;

    @SerializedName("profile_image_url")
    private String profile_image_url;

    @SerializedName("urls")
    private String Url;

    @SerializedName("name")
    private String name;

    @SerializedName("source")
    private String Source;

    @SerializedName("truncated")
    private Boolean IsTruncated;

    @SerializedName("in_reply_to_status_id")
    private long InReplyToStatusId;

    @SerializedName("in_reply_to_status_id_str")
    private String InReplyToStatusIdStr;

    @SerializedName("in_reply_to_user_id")
    private long InReplyToUserId;

    @SerializedName("in_reply_to_user_id_str")
    private String InReplyToUserIdStr;

    @SerializedName("in_reply_to_screen_name")
    private String InReplyToScreenName;

    @SerializedName("user")
    private TwitterUser User;

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getIdStr() {
        return IdStr;
    }

    public void setIdStr(String idStr) {
        IdStr = idStr;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;

    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getText1() {
        return Text1;
    }

    public void setText1(String text1) {
        Text1 = text1;

    }
    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public Boolean getIsTruncated() {
        return IsTruncated;
    }

    public void setIsTruncated(Boolean isTruncated) {
        IsTruncated = isTruncated;
    }

    public long getInReplyToStatusId() {
        return InReplyToStatusId;
    }

    public void setInReplyToStatusId(long inReplyToStatusId) {
        InReplyToStatusId = inReplyToStatusId;
    }

    public String getInReplyToStatusIdStr() {
        return InReplyToStatusIdStr;
    }

    public void setInReplyToStatusIdStr(String inReplyToStatusIdStr) {
        InReplyToStatusIdStr = inReplyToStatusIdStr;
    }

    public long getInReplyToUserId() {
        return InReplyToUserId;
    }

    public void setInReplyToUserId(long inReplyToUserId) {
        InReplyToUserId = inReplyToUserId;
    }

    public String getInReplyToUserIdStr() {
        return InReplyToUserIdStr;
    }

    public void setInReplyToUserIdStr(String inReplyToUserIdStr) {
        InReplyToUserIdStr = inReplyToUserIdStr;
    }

    public String getInReplyToScreenName() {
        return InReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName) {
        InReplyToScreenName = inReplyToScreenName;
    }

    public TwitterUser getUser() {
        return User;
    }

    public void setUser(TwitterUser user) {
        User = user;
    }

    @Override
    public String  toString(){
        return getText();
    }
}
