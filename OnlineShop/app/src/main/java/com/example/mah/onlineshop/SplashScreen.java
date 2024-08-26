package com.example.mah.onlineshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.screen_splash );


        Thread splashtime = new Thread ( )
        {
            @Override
            public void run()
            {
                try{
                    sleep(4500);
                }
                catch (Exception e){

                }
                finally {
                    startActivity ( new Intent ( SplashScreen.this,MainActivity.class ) );
                }
            }
        };

        splashtime.start ();


    }

    @Override
    protected void onPause() {
        super.onPause ();
        finish ();
    }
}
