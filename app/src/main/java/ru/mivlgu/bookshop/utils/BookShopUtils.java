package ru.mivlgu.bookshop.utils;

import ru.mivlgu.bookshop.R;

public class BookShopUtils {
    public BookShopUtils() {
    }

    public static int getImageFromResourse(String img){
        int result = 0;
        switch (img){
            case "fallout.jpg":
                result =  R.drawable.fallout;
                break;
            case "doom_kings.jpg":
                result =  R.drawable.doom_kings;
                break;
            case "half_life.jpg":
                result =  R.drawable.half_life;
                break;
            case "press_reset.jpg":
                result =  R.drawable.press_reset;
                break;
            case "kodzima.jpg":
                result =  R.drawable.kodzima;
                break;
        }
        return result;
    }
}
