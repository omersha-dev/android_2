package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

interface FirebaseCallbacks {
    default void onSignup(boolean isExists) {}
    default void foundUser(Map<String, Object> foundUser) {}
    default void onSignIn() {}
    default void onSignInFailed(String errorMessage) {}
    default void onAccountDataUpdated() {}
}
