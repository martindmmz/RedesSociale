package com.martin.redes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    Button btn_compartir;
    ImageView verimg;

    Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        btn_compartir = (Button)findViewById(R.id.btn_compartir);
        verimg = (ImageView)findViewById(R.id.imgv_imagen);



        btn_compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

                if(isLoggedIn){
                    compartirfoto();
                }else{
                    Toast.makeText(getApplicationContext(),"Ingresa a tu cuenta de Facebook primero.",Toast.LENGTH_LONG).show();

                }

            }
        });


        foto = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.imagen);
        verimg.setImageBitmap(foto);






        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    private void compartirfoto()
    {
        SharePhoto compartir = new SharePhoto.Builder().setBitmap(foto).build();
        ShareContent contenido = new SharePhotoContent.Builder().addPhoto(compartir).build();
        ShareDialog.show(this,contenido);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
