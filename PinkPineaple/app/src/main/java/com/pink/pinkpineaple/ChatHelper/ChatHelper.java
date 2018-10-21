package com.pink.pinkpineaple.ChatHelper;

import android.app.AlertDialog;
import android.content.Context;

import com.pink.pinkpineaple.R;

import java.util.Random;

/**
 * Created by unmwjsi001 on 24-06-2017.
 */

public class ChatHelper {

    private static Random randomAvatarGenerator = new Random();
    private static final int NUMBER_OF_AVATAR = 4;

    /*Generate an avatar randomly*/
    public static int  generateRandomAvatarForUser(){
        return randomAvatarGenerator.nextInt(NUMBER_OF_AVATAR);
    }

    /*Get avatar id*/

    public static int getDrawableAvatarId(int givenRandomAvatarId){

        switch (givenRandomAvatarId){

            case 0:
                return R.mipmap.user_avatar_1;
            case 1:
                return R.mipmap.user_avatar_2;
            case 2:
                return R.mipmap.user_avatar_3;
            default:
                return R.mipmap.user_avatar_4;
        }
    }

    public static AlertDialog buildAlertDialog(String title, String message, boolean isCancelable, Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(title);

        if(isCancelable){
            builder.setPositiveButton(android.R.string.ok, null);
        }else {
            builder.setCancelable(false);
        }
        return builder.create();
    }
}