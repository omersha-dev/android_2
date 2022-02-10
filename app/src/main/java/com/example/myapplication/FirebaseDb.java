package com.example.myapplication;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FirebaseDb {

    private static Map<String, Object> currentUser = null;
    private static FirebaseDb firebaseDb = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static FirebaseDb getInstance() {
        if (firebaseDb == null) {
            firebaseDb = new FirebaseDb();
        }
        return firebaseDb;
    }

    public boolean isSignedIn() {
        if (currentUser == null)
            return false;
        return true;
    }

    public static Map<String, Object> getCurrentUser() {
        return currentUser;
    }

    public void isUserExists(String email, FirebaseCallbacks callback) {
        db.collection("users").document(email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.getData() != null && documentSnapshot.getData().get("email").equals(email)) {
                            callback.foundUser(documentSnapshot.getData());
                        } else {
                            callback.foundUser(documentSnapshot.getData());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Found nothing");
                    }
                });
    }

    public boolean signUp(String email, String password, String validatePassword, FirebaseCallbacks callback) {

        if (!password.equals(validatePassword)) {
            return false;
        }

        this.isUserExists(email, new FirebaseCallbacks() {
            @Override
            public void foundUser(Map<String, Object> foundUser) {
                if (foundUser != null && !foundUser.isEmpty()) {
                    callback.onSignup(true);
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("email", email);
                    user.put("password", password);

                    db.collection("users")
                            .document(email)
                            .set(user, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    System.out.println("Succeed");
                                    currentUser = user;
                                    callback.onSignIn();
//                                     TODO: Add callbaack:
//                                     1. remove the signup fragment from backstack
//                                     2. Sign user in
//                                     3. Open feed fragment
                                    }
                                })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("Failed");
//                                    TODO: Add callback:
//                                    What to do if the registration failed
                                }
                            });
                }
            }
        });

        return true;

    }

    public void signIn(String email, String password, FirebaseCallbacks callbacks) {
        this.isUserExists(email, new FirebaseCallbacks() {
            @Override
            public void foundUser(Map<String, Object> foundUser) {
                if (foundUser == null || foundUser.isEmpty()) {
                    callbacks.onSignInFailed("Could not find user");
                } else {
                    if (!foundUser.get("password").equals(password)) {
                        callbacks.onSignInFailed("Wrong password");
                    } else {
                        currentUser = foundUser;
                        callbacks.onSignIn();
                    }
                }
            }
        });
    }

    public void updateUserData(Map<String, Object> newUserData) {

//        newUserData.forEach((key, value) -> {
//            if (currentUser.containsKey(key) && currentUser.get(key).equals(value)) {
//                newUserData.remove(key);
//            }
//        });

        db.collection("users")
                .document((String) currentUser.get("email"))
                .update(newUserData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        System.out.println("Done!");
                    }
                });
    }

}
