package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class Main12Activity extends AppCompatActivity {

    // google
    private SignInButton signInButton;
    GoogleSignInClient googleSignInClient;
    private String TAG = "Main12Activity";
    private FirebaseAuth firebaseAuth;
    private int RC_SIGN_IN = 1;

    // facebook
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private static final String TAG1 = "FacebookAuthentication";
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        loginButton = (LoginButton) findViewById(R.id.facebook_login);  // facebook
        loginButton.setReadPermissions("email", "public_profile");
        signInButton = (SignInButton) findViewById(R.id.google_sign_in);  // Google
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG1, "onSuccess" + loginResult);
                handleFBtoken(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {
                Log.d(TAG1, "onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG1, "onError" + error);

            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    updateUI(user);
                } else {
                    updateUI(null);
                }
            }
        };
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    firebaseAuth.signOut();
                } else {

                }
            }
        };


    }

    private void handleFBtoken(AccessToken token) {
        Log.d(TAG1, "handleFBtoken" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG1, "Sign In Success");
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    updateUI(firebaseUser);

                } else {
                    Log.d(TAG1, "Failure", task.getException());
                    Toast.makeText(Main12Activity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });

    }

    private void updateUI(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            Toast.makeText(this, firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Main14Activity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void signIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completed_task) {
//        try {
//            GoogleSignInAccount gsi = completed_task.getResult(ApiException.class);
//            Toast.makeText(this, "Succesful", Toast.LENGTH_SHORT).show();
//            FirebaseGoogleAuth(gsi);

        try {
            GoogleSignInAccount acc = completed_task.getResult(ApiException.class);
            Toast.makeText(getApplicationContext(), "Signing Success", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
            //email
            firebaseAuth.sendPasswordResetEmail(acc.getEmail());

            Intent intent = new Intent(Main12Activity.this,Main14Activity.class);
            intent.putExtra("message", acc.getDisplayName());
            startActivity(intent);


        } catch (ApiException e) {
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();

        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Main12Activity.this, "SignIn Success", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Toast.makeText(Main12Activity.this, "SignIn Failure", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

            }

            private void updateUI(FirebaseUser fuser) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                if (account != null) {
                    String personName = account.getDisplayName();
                    String personGivenName = account.getGivenName();
                    String familyName = account.getFamilyName();
                    String email = account.getEmail();
                    Uri pic = account.getPhotoUrl();
                    Toast.makeText(Main12Activity.this, personName + familyName + email, Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}
