package com.mohitvirmani.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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
import com.google.firebase.auth.GoogleAuthProvider;

import com.facebook.FacebookSdk;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {

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


    private int[] layouts = {R.layout.try_3, R.layout.try_l2, R.layout.try_liquid1};
    private MPagerAdapter mPagerAdapter;
    private ViewPager viewPager;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    private LottieAnimationView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);
        loginButton = (LoginButton) findViewById(R.id.facebook_login);  // facebook
        loginButton.setReadPermissions("email", "public_profile");
        signInButton = (SignInButton) findViewById(R.id.google_sign_in);  // Google
        firebaseAuth = FirebaseAuth.getInstance();


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        load = (LottieAnimationView) findViewById(R.id.my_progress1);

        mPagerAdapter = new MPagerAdapter(layouts, this);
        viewPager.setAdapter(mPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 3) {
                    currentPage = 0;

                }
                viewPager.setCurrentItem(currentPage++, true);

            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load.setVisibility(View.VISIBLE);
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
                    Toast.makeText(Login.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });

    }

    private void updateUI(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            Toast.makeText(this, firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", firebaseUser.getDisplayName());
            editor.apply();
//
//                            String myAnswer = dataSnapshot.child("mAnswer").getValue().toString();
//
//                            editor.putString("myAns", myAnswer);
//                            editor.apply();
//

            Intent intent = new Intent(this, MainPage.class);
//            intent.putExtra("name", firebaseUser.getDisplayName());
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

            FirebaseGoogleAuth(acc);


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", acc.getDisplayName());
            editor.apply();
//
//                            String myAnswer = dataSnapshot.child("mAnswer").getValue().toString();
//
//                            editor.putString("myAns", myAnswer);
//                            editor.apply();
//

//            Intent intent = new Intent(Main12Activity.this, Main14Activity.class);
//            intent.putExtra("message", acc.getDisplayName());
////            intent.putExtra("name", acc.getDisplayName());
//            startActivity(intent);


        } catch (ApiException e) {
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();

        }

    }

    private void FirebaseGoogleAuth(final GoogleSignInAccount acct) {
        System.out.println("::::1::::2:::::3");
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    System.out.println("::::4::::5:::::6");


                    boolean isNewUser = task.getResult().getAdditionalUserInfo().isNewUser();
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::" + isNewUser);
                    if (isNewUser) {

                        //email

                        firebaseAuth.sendPasswordResetEmail(acct.getEmail());
                        System.out.println("first");

                    }else{
                        System.out.println("second");
                    }

                    System.out.println("OOpsey");
                    Toast.makeText(getApplicationContext(), "Signing In Success", Toast.LENGTH_SHORT).show();

//                    Toast.makeText(Main12Activity.this, "SignIn Success", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    updateUI(user);
                } else {
                    Toast.makeText(Login.this, "SignIn Failure", Toast.LENGTH_SHORT).show();
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
//                    Toast.makeText(Main12Activity.this, personName + familyName + email, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainPage.class);
                    intent.putExtra("message", acct.getDisplayName());
//            intent.putExtra("name", acc.getDisplayName());
                    startActivity(intent);

                }

            }
        });

    }

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();

            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

}
