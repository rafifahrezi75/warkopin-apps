package com.komputerkit.warkopin.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("user")
    private User user;

    @SerializedName("token")
    private String token;

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public static class User {
        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("email")
        private String email;

        @SerializedName("email_verified_at")
        private String emailVerifiedAt;

        @SerializedName("role")
        private String role;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public String getRole() {
            return role;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }
}
