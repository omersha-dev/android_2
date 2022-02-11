package com.example.myapplication;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

interface FirebaseCallbacks {
    default void onSignup(boolean isExists) {}
    default void foundUser(Map<String, Object> foundUser) {}
    default void onSignIn() {}
    default void onSignInFailed(String errorMessage) {}
    default void onAccountDataUpdated() {}
    default void onSuccessfullPost() {}
    default void onFailedPost() {}
    default void onPostsLoaded(ArrayList posts) {}
}
