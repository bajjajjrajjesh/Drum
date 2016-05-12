package com.virtual.drum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SuppressLint("UseSparseArrays")
public class DrumBeats extends FragmentActivity implements
        android.view.View.OnClickListener, AnyDialogListener,
        AnyDialogListener2, AnyDialogListenerMusic {

    public static final int NUM_CHANNELS = 1;
    public static final int SAMPLE_RATE = 16000;
    public static final int BITRATE = 128;
    public static final int MODE = 1;
    public static final int QUALITY = 2;
    private MediaPlayer mMediaPlayer;
    private Boolean play_bacground_music = false;

    boolean isexitadshown = false;
    boolean isAnimate = true;
    Animation animation;
    Bitmap bass_left;
    int bass_leftH;
    ImageView bass_leftView;
    int bass_leftW;
    int bass_leftX = 214;
    int bass_leftY = 240;
    int bass_left_posX;
    int bass_left_posY;
    Bitmap bass_right;
    int bass_rightH;
    ImageView bass_rightView;
    int bass_rightW;
    int bass_rightX = 405;
    int bass_rightY = 240;
    int bass_right_posX;
    int bass_right_posY;
    Bitmap bell;
    int bellH;
    ImageView bellView;
    int bellW;
    int bellX = 645;
    int bellY = 135;
    int bell_posX;
    int bell_posY;
    int cacheID;
    Bitmap crash1;
    int crash1H;
    ImageView crash1View;
    int crash1W;
    int crash1X = 0;
    int crash1Y = -40;
    int crash1_posX;
    int crash1_posY;
    Bitmap crash2;
    int crash2H;
    ImageView crash2View;
    int crash2W;
    int crash2X = 170;
    int crash2Y = -40;
    int crash2_posX;
    int crash2_posY;
    Bitmap crash3;
    int crash3H;
    ImageView crash3View;
    int crash3W;
    int crash3X = 371;
    int crash3Y = -40;
    int crash3_posX;
    int crash3_posY;
    OnClickListener dialogClickListener;
    double heightRatio;
    Bitmap hihat_open;
    int hihat_openH;
    ImageView hihat_openView;
    int hihat_openW;
    int hihat_openX = 10;
    int hihat_openY = 100;
    int hihat_open_posX;
    int hihat_open_posY;

    ImageView tambourine_View;
    int tambourine_X = 530;
    int tambourine_Y = 250;
    int tambourine_W;
    int tambourine_H;
    int tambourine_posX;
    int tambourine_posY;
    Bitmap tambourine;

    ImageView block_View;
    int block_X = -20;
    int block_Y = 250;
    int block_W;
    int block_H;
    int block_posX;
    int block_posY;
    Bitmap block;

    Bitmap hihat_pedal;
    int hihat_pedalH;

    ImageView hihat_pedalView;
    int hihat_pedalW;
    int hihat_pedalX = 150;
    int hihat_pedalY = 270;
    int hihat_pedal_posX;
    int hihat_pedal_posY;
    RelativeLayout parent;
    boolean playing = false;
    Random r = new Random();
    Bitmap ride;
    int rideH;
    ImageView rideView;
    int rideW;
    int rideX = 540;
    int rideY = -100;
    int ride_posX;
    int ride_posY;
    Bitmap snare;
    int snareH;
    ImageView snareView;
    int snareW;
    int snareX = 326;
    int snareY = 246;
    int snare_posX;
    int snare_posY;
    private HashMap<Integer, Integer> soundMap;
    SoundPool soundPool;
    OnClickListener startDialogClickListener;
    Bitmap tom1;
    int tom1H;
    ImageView tom1View;
    int tom1W;
    int tom1X = 190;
    int tom1Y = 130;
    int tom1_posX;
    int tom1_posY;
    Bitmap tom2;
    int tom2H;
    ImageView tom2View;
    int tom2W;
    int tom2X = 338;
    int tom2Y = 102;
    int tom2_posX;
    int tom2_posY;
    Bitmap tom3;
    int tom3H;
    ImageView tom3View;
    int tom3W;
    int tom3X = 483;
    int tom3Y = 130;
    int tom3_posX;
    int tom3_posY;
    double widthRatio;
    private int x;
    private int y;
    int touchCount = 0;

    private LinearLayout linearLayout1;

    private ToggleButton btn_record;
    private Button btn_play;

    private Thread recordThread = null;

    private RecordPlay playThread = null;
    private boolean isRecodlaying = false;

    private void animate(int var1) {

        Log.d("XXXXXX animation :", var1 + " ");

        switch (var1) {
            case 1:
                this.rideView.startAnimation(this.animation);
                break;
            case 2:
                this.crash2View.startAnimation(this.animation);
                break;
            case 3:
                this.crash1View.startAnimation(this.animation);
                break;
            case 4:
                this.crash3View.startAnimation(this.animation);
                break;
            case 5:
                this.snareView.startAnimation(this.animation);
                break;
            case 6:
                this.tom1View.startAnimation(this.animation);
                break;
            case 7:
                this.tom2View.startAnimation(this.animation);
                break;

            case 8:
                this.tom3View.startAnimation(this.animation);
                break;
            case 9:
                this.bass_rightView.startAnimation(this.animation);
                break;
            case 10:
                this.hihat_openView.startAnimation(this.animation);
                break;
            case 11:
                this.hihat_pedalView.startAnimation(this.animation);
                break;
            case 12:
                this.bellView.startAnimation(this.animation);
                break;
            case 13:
                this.bass_leftView.startAnimation(this.animation);
                break;

            case 14:
                this.block_View.startAnimation(this.animation);
                break;

            case 15:
                this.tambourine_View.startAnimation(this.animation);
                break;

        }

    }

    public static Bitmap getBitmapFromAsset(Context var0, String var1) {
        AssetManager var2 = var0.getAssets();

        try {
            Bitmap var4 = BitmapFactory.decodeStream(var2.open(var1));
            return var4;
        } catch (IOException var5) {
            return null;
        }
    }

    private void loadInstruments() {
        this.bass_left = getBitmapFromAsset(this, "gfx/bass.png");
        this.bass_leftH = (int) ((double) this.bass_left.getHeight() * this.heightRatio);
        this.bass_leftW = (int) ((double) this.bass_left.getWidth() * this.widthRatio);
        this.bass_left = Bitmap.createScaledBitmap(this.bass_left,
                this.bass_leftW, this.bass_leftH, true);
        this.bass_left_posX = (int) ((double) this.bass_leftX * this.widthRatio);
        this.bass_left_posY = (int) ((double) this.bass_leftY * this.heightRatio);
        this.bass_leftView = new ImageView(this);
        LayoutParams var1 = new LayoutParams(this.bass_leftW, this.bass_leftH);
        var1.leftMargin = this.bass_left_posX;
        var1.topMargin = this.bass_left_posY;
        this.parent.addView(this.bass_leftView, var1);
        this.bass_leftView.setImageBitmap(this.bass_left);
        this.bass_left = null;
        this.bass_right = getBitmapFromAsset(this, "gfx/bass.png");
        this.bass_rightH = (int) ((double) this.bass_right.getHeight() * this.heightRatio);
        this.bass_rightW = (int) ((double) this.bass_right.getWidth() * this.widthRatio);
        this.bass_right = Bitmap.createScaledBitmap(this.bass_right,
                this.bass_rightW, this.bass_rightH, true);
        this.bass_right_posX = (int) ((double) this.bass_rightX * this.widthRatio);
        this.bass_right_posY = (int) ((double) this.bass_rightY * this.heightRatio);
        this.bass_rightView = new ImageView(this);
        LayoutParams var2 = new LayoutParams(this.bass_rightW, this.bass_rightH);
        var2.leftMargin = this.bass_right_posX;
        var2.topMargin = this.bass_right_posY;
        this.parent.addView(this.bass_rightView, var2);
        this.bass_rightView.setImageBitmap(this.bass_right);
        this.bass_right = null;
        this.hihat_pedal = getBitmapFromAsset(this, "gfx/hihat_pedal.png");
        this.hihat_pedalH = (int) ((double) this.hihat_pedal.getHeight() * this.heightRatio);
        this.hihat_pedalW = (int) ((double) this.hihat_pedal.getWidth() * this.widthRatio);
        this.hihat_pedal = Bitmap.createScaledBitmap(this.hihat_pedal,
                this.hihat_pedalW, this.hihat_pedalH, true);
        this.hihat_pedal_posX = (int) ((double) this.hihat_pedalX * this.widthRatio);
        this.hihat_pedal_posY = (int) ((double) this.hihat_pedalY * this.heightRatio);
        this.hihat_pedalView = new ImageView(this);
        LayoutParams var3 = new LayoutParams(this.hihat_pedalW,
                this.hihat_pedalH);
        var3.leftMargin = this.hihat_pedal_posX;
        var3.topMargin = this.hihat_pedal_posY;
        this.parent.addView(this.hihat_pedalView, var3);
        this.hihat_pedalView.setImageBitmap(this.hihat_pedal);
        this.hihat_pedal = null;

        this.block_View = new ImageView(this);

        this.tom1 = getBitmapFromAsset(this, "gfx/tom.png");
        this.tom1H = (int) ((double) this.tom1.getHeight() * this.heightRatio);
        this.tom1W = (int) ((double) this.tom1.getWidth() * this.widthRatio);
        this.tom1 = Bitmap.createScaledBitmap(this.tom1, this.tom1W,
                this.tom1H, true);
        this.tom1_posX = (int) ((double) this.tom1X * this.widthRatio);
        this.tom1_posY = (int) ((double) this.tom1Y * this.heightRatio);
        this.tom1View = new ImageView(this);
        LayoutParams var4 = new LayoutParams(this.tom1W, this.tom1H);
        var4.leftMargin = this.tom1_posX;
        var4.topMargin = this.tom1_posY;
        this.parent.addView(this.tom1View, var4);
        this.tom1View.setImageBitmap(this.tom1);
        this.tom1 = null;
        this.snare = getBitmapFromAsset(this, "gfx/snare.png");
        this.snareH = (int) ((double) this.snare.getHeight() * this.heightRatio);
        this.snareW = (int) ((double) this.snare.getWidth() * this.widthRatio);
        this.snare = Bitmap.createScaledBitmap(this.snare, this.snareW,
                this.snareH, true);
        this.snare_posX = (int) ((double) this.snareX * this.widthRatio);
        this.snare_posY = (int) ((double) this.snareY * this.heightRatio);
        this.snareView = new ImageView(this);
        LayoutParams var5 = new LayoutParams(this.snareW, this.snareH);
        var5.leftMargin = this.snare_posX;
        var5.topMargin = this.snare_posY;
        this.parent.addView(this.snareView, var5);
        this.snareView.setImageBitmap(this.snare);
        this.snare = null;
        this.tom2 = getBitmapFromAsset(this, "gfx/tom.png");
        this.tom2H = (int) ((double) this.tom2.getHeight() * this.heightRatio);
        this.tom2W = (int) ((double) this.tom2.getWidth() * this.widthRatio);
        this.tom2 = Bitmap.createScaledBitmap(this.tom2, this.tom2W,
                this.tom2H, true);
        this.tom2_posX = (int) ((double) this.tom2X * this.widthRatio);
        this.tom2_posY = (int) ((double) this.tom2Y * this.heightRatio);
        this.tom2View = new ImageView(this);
        LayoutParams var6 = new LayoutParams(this.tom2W, this.tom2H);
        var6.leftMargin = this.tom2_posX;
        var6.topMargin = this.tom2_posY;
        this.parent.addView(this.tom2View, var6);
        this.tom2View.setImageBitmap(this.tom2);
        this.tom2 = null;
        this.tom3 = getBitmapFromAsset(this, "gfx/tom.png");
        this.tom3H = (int) ((double) this.tom3.getHeight() * this.heightRatio);
        this.tom3W = (int) ((double) this.tom3.getWidth() * this.widthRatio);
        this.tom3 = Bitmap.createScaledBitmap(this.tom3, this.tom3W,
                this.tom3H, true);
        this.tom3_posX = (int) ((double) this.tom3X * this.widthRatio);
        this.tom3_posY = (int) ((double) this.tom3Y * this.heightRatio);
        this.tom3View = new ImageView(this);
        LayoutParams var7 = new LayoutParams(this.tom3W, this.tom3H);
        var7.leftMargin = this.tom3_posX;
        var7.topMargin = this.tom3_posY;
        this.parent.addView(this.tom3View, var7);
        this.tom3View.setImageBitmap(this.tom3);
        this.tom3 = null;
        this.bell = getBitmapFromAsset(this, "gfx/bell.png");
        this.bellH = (int) ((double) this.bell.getHeight() * this.heightRatio);
        this.bellW = (int) ((double) this.bell.getWidth() * this.widthRatio);
        this.bell = Bitmap.createScaledBitmap(this.bell, this.bellW,
                this.bellH, true);
        this.bell_posX = (int) ((double) this.bellX * this.widthRatio);
        this.bell_posY = (int) ((double) this.bellY * this.heightRatio);
        this.bellView = new ImageView(this);
        LayoutParams var8 = new LayoutParams(this.bellW, this.bellH);
        var8.leftMargin = this.bell_posX;
        var8.topMargin = this.bell_posY;
        this.parent.addView(this.bellView, var8);
        this.bellView.setImageBitmap(this.bell);
        this.bell = null;

        this.block = getBitmapFromAsset(this, "gfx/block.png");
        this.block_H = (int) ((double) this.block.getHeight() * this.heightRatio);
        this.block_W = (int) ((double) this.block.getWidth() * this.widthRatio);
        this.block = Bitmap.createScaledBitmap(this.block, this.block_W,
                this.block_H, true);
        this.block_posX = (int) ((double) this.block_X * this.widthRatio);
        this.block_posY = (int) ((double) this.block_Y * this.heightRatio);
        this.block_View = new ImageView(this);
        LayoutParams var14 = new LayoutParams(this.block_W, this.block_H);
        var14.leftMargin = this.block_posX;
        var14.topMargin = this.block_posY;
        this.parent.addView(this.block_View, var14);
        this.block_View.setImageBitmap(this.block);
        this.block = null;

        this.tambourine = getBitmapFromAsset(this, "gfx/tambourine.png");
        this.tambourine_H = (int) ((double) this.tambourine.getHeight() * this.heightRatio);
        this.tambourine_W = (int) ((double) this.tambourine.getWidth() * this.widthRatio);
        this.tambourine = Bitmap.createScaledBitmap(this.tambourine,
                this.tambourine_W, this.tambourine_H, true);
        this.tambourine_posX = (int) ((double) this.tambourine_X * this.widthRatio);
        this.tambourine_posY = (int) ((double) this.tambourine_Y * this.heightRatio);
        this.tambourine_View = new ImageView(this);
        LayoutParams var15 = new LayoutParams(this.tambourine_W,
                this.tambourine_H);
        var15.leftMargin = this.tambourine_posX;
        var15.topMargin = this.tambourine_posY;
        this.parent.addView(this.tambourine_View, var15);
        this.tambourine_View.setImageBitmap(this.tambourine);
        this.tambourine = null;

        this.hihat_open = getBitmapFromAsset(this, "gfx/hihat_open.png");
        this.hihat_openH = (int) ((double) this.hihat_open.getHeight() * this.heightRatio);
        this.hihat_openW = (int) ((double) this.hihat_open.getWidth() * this.widthRatio);
        this.hihat_open = Bitmap.createScaledBitmap(this.hihat_open,
                this.hihat_openW, this.hihat_openH, true);
        this.hihat_open_posX = (int) ((double) this.hihat_openX * this.widthRatio);
        this.hihat_open_posY = (int) ((double) this.hihat_openY * this.heightRatio);
        this.hihat_openView = new ImageView(this);
        LayoutParams var9 = new LayoutParams(this.hihat_openW, this.hihat_openH);
        var9.leftMargin = this.hihat_open_posX;
        var9.topMargin = this.hihat_open_posY;
        this.parent.addView(this.hihat_openView, var9);
        this.hihat_openView.setImageBitmap(this.hihat_open);
        this.hihat_open = null;
        this.crash1 = getBitmapFromAsset(this, "gfx/crash1.png");
        this.crash1H = (int) ((double) this.crash1.getHeight() * this.heightRatio);
        this.crash1W = (int) ((double) this.crash1.getWidth() * this.widthRatio);
        this.crash1 = Bitmap.createScaledBitmap(this.crash1, this.crash1W,
                this.crash1H, true);
        this.crash1_posX = (int) ((double) this.crash1X * this.widthRatio);
        this.crash1_posY = (int) ((double) this.crash1Y * this.heightRatio);
        this.crash1View = new ImageView(this);
        LayoutParams var10 = new LayoutParams(this.crash1W, this.crash1H);
        var10.leftMargin = this.crash1_posX;
        var10.topMargin = this.crash1_posY;
        this.parent.addView(this.crash1View, var10);
        this.crash1View.setImageBitmap(this.crash1);
        this.crash1 = null;
        this.crash3 = getBitmapFromAsset(this, "gfx/crash1.png");
        this.crash3H = (int) ((double) this.crash3.getHeight() * this.heightRatio);
        this.crash3W = (int) ((double) this.crash3.getWidth() * this.widthRatio);
        this.crash3 = Bitmap.createScaledBitmap(this.crash3, this.crash3W,
                this.crash3H, true);
        this.crash3_posX = (int) ((double) this.crash3X * this.widthRatio);
        this.crash3_posY = (int) ((double) this.crash3Y * this.heightRatio);
        this.crash3View = new ImageView(this);
        LayoutParams var11 = new LayoutParams(this.crash3W, this.crash3H);
        var11.leftMargin = this.crash3_posX;
        var11.topMargin = this.crash3_posY;
        this.parent.addView(this.crash3View, var11);
        this.crash3View.setImageBitmap(this.crash3);
        this.crash3 = null;
        this.crash2 = getBitmapFromAsset(this, "gfx/crash2.png");
        this.crash2H = (int) ((double) this.crash2.getHeight() * this.heightRatio);
        this.crash2W = (int) ((double) this.crash2.getWidth() * this.widthRatio);
        this.crash2 = Bitmap.createScaledBitmap(this.crash2, this.crash2W,
                this.crash2H, true);
        this.crash2_posX = (int) ((double) this.crash2X * this.widthRatio);
        this.crash2_posY = (int) ((double) this.crash2Y * this.heightRatio);
        this.crash2View = new ImageView(this);
        LayoutParams var12 = new LayoutParams(this.crash2W, this.crash2H);
        var12.leftMargin = this.crash2_posX;
        var12.topMargin = this.crash2_posY;
        this.parent.addView(this.crash2View, var12);
        this.crash2View.setImageBitmap(this.crash2);
        this.crash2 = null;

        this.ride = getBitmapFromAsset(this, "gfx/ride.png");
        this.rideH = (int) ((double) this.ride.getHeight() * this.heightRatio);
        this.rideW = (int) ((double) this.ride.getWidth() * this.widthRatio);
        this.ride = Bitmap.createScaledBitmap(this.ride, this.rideW,
                this.rideH, true);
        this.ride_posX = (int) ((double) this.rideX * this.widthRatio);
        this.ride_posY = (int) ((double) this.rideY * this.heightRatio);
        this.rideView = new ImageView(this);
        LayoutParams var13 = new LayoutParams(this.rideW, this.rideH);
        var13.leftMargin = this.ride_posX;
        var13.topMargin = this.ride_posY;
        this.parent.addView(this.rideView, var13);
        this.rideView.setImageBitmap(this.ride);
        this.ride = null;

    }

    private void loadSounds() {
        this.soundPool = new SoundPool(12, 3, 0);
        this.soundMap.put(Integer.valueOf(1),
                Integer.valueOf(this.soundPool.load(this, R.raw.bass, 1)));
        this.soundMap.put(Integer.valueOf(2), Integer.valueOf(this.soundPool
                .load(this, R.raw.hihat_pedal, 1)));
        this.soundMap
                .put(Integer.valueOf(3), Integer.valueOf(this.soundPool.load(
                        this, R.raw.hihat_open, 1)));
        this.soundMap.put(Integer.valueOf(4),
                Integer.valueOf(this.soundPool.load(this, R.raw.snare, 1)));
        this.soundMap.put(Integer.valueOf(5),
                Integer.valueOf(this.soundPool.load(this, R.raw.bell, 1)));
        this.soundMap.put(Integer.valueOf(6),
                Integer.valueOf(this.soundPool.load(this, R.raw.tom1, 1)));
        this.soundMap.put(Integer.valueOf(7),
                Integer.valueOf(this.soundPool.load(this, R.raw.tom2, 1)));
        this.soundMap.put(Integer.valueOf(8),
                Integer.valueOf(this.soundPool.load(this, R.raw.tom3, 1)));
        this.soundMap.put(Integer.valueOf(9),
                Integer.valueOf(this.soundPool.load(this, R.raw.crash1, 1)));
        this.soundMap.put(Integer.valueOf(10),
                Integer.valueOf(this.soundPool.load(this, R.raw.crash2, 1)));
        this.soundMap.put(Integer.valueOf(11),
                Integer.valueOf(this.soundPool.load(this, R.raw.crash3, 1)));
        this.soundMap.put(Integer.valueOf(12),
                Integer.valueOf(this.soundPool.load(this, R.raw.ride, 1)));
        this.soundMap.put(Integer.valueOf(13),
                Integer.valueOf(this.soundPool.load(this, R.raw.bass, 1)));

        this.soundMap.put(Integer.valueOf(14),
                Integer.valueOf(this.soundPool.load(this, R.raw.block, 1)));

        this.soundMap
                .put(Integer.valueOf(15), Integer.valueOf(this.soundPool.load(
                        this, R.raw.tambourine, 1)));
    }

    private void playSound(int var1, int var2) {
        var2 = var2 - status_var_height - relativeLayout1.getHeight();

        if (var1 >= this.tom3_posX && var1 <= this.tom3_posX + this.tom3W
                && var2 >= this.tom3_posY
                && var2 <= this.tom3_posY + this.tom3H) {
            this.soundPool.play(8, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(8);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(8);
            }
            return;
        } else if (var1 >= this.ride_posX
                && var1 <= this.ride_posX + this.rideW
                && var2 >= this.ride_posY
                && var2 <= this.ride_posY + this.rideH) {
            this.soundPool.play(12, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(1);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(1);
            }
        } else if (var1 >= this.crash2_posX
                && var1 <= this.crash2_posX + this.crash2W
                && var2 >= this.crash2_posY
                && var2 <= this.crash2_posY + this.crash2H) {
            this.soundPool.play(10, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(2);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(2);
            }
            return;
        } else if (var1 >= this.crash1_posX
                && var1 <= this.crash1_posX + this.crash1W
                && var2 >= this.crash1_posY
                && var2 <= this.crash1_posY + this.crash1H) {
            this.soundPool.play(9, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(3);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(3);
            }
            return;
        } else if (var1 >= this.crash3_posX
                && var1 <= this.crash3_posX + this.crash3W
                && var2 >= this.crash3_posY
                && var2 <= this.crash3_posY + this.crash3H) {
            this.soundPool.play(11, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(4);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(4);
            }
            return;
        } else if (var1 >= this.snare_posX
                && var1 <= this.snare_posX + this.snareW
                && var2 >= this.snare_posY
                && var2 <= this.snare_posY + this.snareH) {
            this.soundPool.play(4, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(5);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(5);
            }
            return;
        } else if (var1 >= this.tambourine_posX
                && var1 <= this.tambourine_posX + this.tambourine_W
                && var2 >= this.tambourine_posY
                && var2 <= this.tambourine_posY + this.tambourine_H) {
            this.soundPool.play(15, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(15);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(15);
            }
            return;

        } else if (var1 >= this.tom1_posX && var1 <= this.tom1_posX + this.tom1W
                && var2 >= this.tom1_posY
                && var2 <= this.tom1_posY + this.tom1H) {
            this.soundPool.play(6, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(6);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(6);
            }
            return;
        } else if (var1 >= this.tom2_posX && var1 <= this.tom2_posX + this.tom2W
                && var2 >= this.tom2_posY
                && var2 <= this.tom2_posY + this.tom2H) {
            this.soundPool.play(7, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(7);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(7);
            }
            return;
        } else if (var1 >= this.bass_right_posX
                && var1 <= this.bass_right_posX + this.bass_rightW
                && var2 >= this.bass_right_posY
                && var2 <= this.bass_right_posY + this.bass_rightH) {
            this.soundPool.play(1, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(9);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(9);
            }
            return;
        } else if (var1 >= this.hihat_open_posX
                && var1 <= this.hihat_open_posX + this.hihat_openW
                && var2 >= this.hihat_open_posY
                && var2 <= this.hihat_open_posY + this.hihat_openH) {
            this.soundPool.play(3, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(10);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(10);
            }
            return;
        } else if (var1 >= this.hihat_pedal_posX
                && var1 <= this.hihat_pedal_posX + this.hihat_pedalW
                && var2 >= this.hihat_pedal_posY
                && var2 <= this.hihat_pedal_posY + this.hihat_pedalH) {
            this.soundPool.play(2, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(11);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(11);
            }
            return;
        } else if (var1 >= this.bell_posX && var1 <= this.bell_posX + this.bellW
                && var2 >= this.bell_posY
                && var2 <= this.bell_posY + this.bellH) {
            this.soundPool.play(5, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(12);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(12);
            }
            return;
        } else if (var1 >= this.bass_left_posX
                && var1 <= this.bass_left_posX + this.bass_leftW
                && var2 >= this.bass_left_posY
                && var2 <= this.bass_left_posY + this.bass_leftH) {
            this.soundPool.play(13, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(13);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(13);
            }
            return;

        } else if (var1 >= this.block_posX
                && var1 <= this.block_posX + this.block_W
                && var2 >= this.block_posY
                && var2 <= this.block_posY + this.block_H) {
            this.soundPool.play(14, 1.0F, 1.0F, 1, 0, 1.0F);
            this.animate(14);
            if (isRcording) {
                list_time.add(System.currentTimeMillis());
                list_drum_number.add(14);
            }
            return;

        }

    }

    private int status_var_height;

    public static final String file_save_directory = Environment
            .getExternalStorageDirectory() + "/Virtual drum";
    private File file;
    Handler myHandler;
    Handler threadHandler;
    private boolean isRcording;
    private List<Long> list_time;
    private List<Integer> list_drum_number;
    private RelativeLayout relativeLayout1;

    private Button btn_music;
    private AdView mAdView;
    private AdRequest adRequest, adRequest2;
    private InterstitialAd mInterstitialAd;


    protected void onCreate(Bundle var1) {
        list_time = new ArrayList<Long>();
        list_drum_number = new ArrayList<Integer>();

        super.onCreate(var1);
        this.setContentView(R.layout.main);


        mAdView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().addTestDevice("359328062559895").build();
        adRequest2 = new AdRequest.Builder().addTestDevice("359328062559895").build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        // Insert the Ad Unit ID
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.banner_add_i));
        mInterstitialAd.loadAd(adRequest2);

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("BBBBBB", "onloaded");
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
                Log.d("BBBBBB", "onClosed");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.d("BBBBBB", "Failed");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d("BBBBBB", "left");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.d("BBBBBB", "open");
            }
        });



        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

        btn_play = (Button) findViewById(R.id.btn_play);
        btn_play.setOnClickListener(this);

        btn_music = (Button) findViewById(R.id.btn_music_play);
        btn_music.setOnClickListener(this);
        file = new File(file_save_directory);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        btn_record = (ToggleButton) findViewById(R.id.btn_record);
        btn_record.setChecked(false);
        btn_record.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isCheck) {
                if (isCheck) {
                    list_time = new ArrayList<Long>();
                    list_drum_number = new ArrayList<Integer>();
                    btn_play.setEnabled(false);
                    isRcording = true;
                } else {
                    if (list_drum_number.size() > 0) {

                        AnyDiaglogFragment diaglogFragment = new AnyDiaglogFragment();
                        diaglogFragment.show(getSupportFragmentManager(),
                                "diaglog");

                        btn_play.setEnabled(true);
                    } else {
                        btn_play.setEnabled(true);
                        Toast.makeText(DrumBeats.this,
                                "Select at least  one beats to save ",
                                Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
        soundMap = new HashMap<Integer, Integer>();
        status_var_height = getStatusBarHeight();
        this.parent = (RelativeLayout) this.findViewById(R.id.parent);
        this.setVolumeControlStream(3);
        DisplayMetrics var4 = this.getResources().getDisplayMetrics();
        this.x = var4.widthPixels;
        this.y = var4.heightPixels;
        this.heightRatio = (double) this.y / 480.0D;
        this.widthRatio = (double) this.x / 800.0D;
        this.animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        this.loadInstruments();
        this.loadSounds();
        myHandler = new Handler() {
            public void handleMessage(Message msg) {
                final int what = msg.what;

                recordplaying(what);

            }
        };

        threadHandler = new Handler() {
            public void handleMessage(Message msg) {
                btn_play.setBackgroundResource(R.drawable.btn_play_bacgorund2);
                isRecodlaying = false;

            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean onTouchEvent(MotionEvent var1) {
        int var2 = var1.getAction();
        int var3 = var2 >> 8;

        Log.d("position X :", (int) var1.getX() + "");
        Log.d("position Y: ", (int) var1.getY() + "");

        switch (var2 & 255) {
            case 0:
                this.playSound((int) var1.getX(var3), (int) var1.getY(var3));
                break;
            case 5:
                this.playSound((int) var1.getX(var3), (int) var1.getY(var3));
        }

        return true;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private class Save implements Runnable {

        String file_name_w;

        public Save(String fileName) {
            file_name_w = fileName + ".drum";
        }

        @Override
        public void run() {

            File file = new File(file_save_directory, file_name_w);
            try {
                file.createNewFile();

                FileOutputStream fOut = new FileOutputStream(file);

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(fOut));
                writer.write(0 + ";" + list_drum_number.get(0));
                writer.newLine();
                for (int i = 1; i < list_time.size(); i++) {
                    writer.write((list_time.get(i) - list_time.get(i - 1))
                            + ";" + list_drum_number.get(i));
                    writer.newLine();
                }
                writer.close();
                fOut.close();
                list_time = new ArrayList<Long>();
                list_drum_number = new ArrayList<Integer>();


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            list_time = new ArrayList<Long>();
            list_drum_number = new ArrayList<Integer>();

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_play:
                if (isRecodlaying) {
                    if (playThread != null && playThread.isRunning) {
                        playThread.killThread(true);
                        recordThread = null;
                        playThread = null;

                    }
                    btn_play.setBackgroundResource(R.drawable.btn_play_bacgorund2);
                    isRecodlaying = false;

                } else {
                    AnyDiaglogFragmentForActivity activity = new AnyDiaglogFragmentForActivity();
                    activity.show(getSupportFragmentManager(), "dd");
                }

                break;


            case R.id.btn_music_play:
                if (play_bacground_music) {
                    btn_music.setBackgroundResource(R.drawable.music_play);
                    mMediaPlayer.stop();
                    play_bacground_music = false;
                    // mMediaPlayer.release();

                } else {

                    AnyDiaglogFragmentForMusic forMusic = new AnyDiaglogFragmentForMusic();
                    forMusic.show(getSupportFragmentManager(), "music");
                }

                break;

        }

    }

    class RecordPlay implements Runnable {
        boolean isRunning = false;
        boolean kill = false;
        File file;

        public void killThread(boolean b) {
            kill = b;
        }

        public boolean isRunning() {

            return isRunning;
        }

        public RecordPlay(File f_name) {
            this.file = f_name;
        }

        @Override
        public void run() {
            isRunning = true;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                List<Long> list_time = new ArrayList<Long>();
                List<Integer> list_drum = new ArrayList<Integer>();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] value = line.split(";");
                    list_time.add(Long.valueOf(value[0]));
                    list_drum.add(Integer.valueOf(value[1]));
                }

                reader.close();
                myHandler.sendEmptyMessage(list_drum.get(0));

                for (int i = 1; i < list_time.size(); i++) {

                    if (kill) {
                        break;

                    }
                    try {

                        Thread.sleep(list_time.get(1));
                        myHandler.sendEmptyMessage(list_drum.get(i));

                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            isRunning = false;
            threadHandler.sendEmptyMessage(100);

        }

    }

    private void recordplaying(int num) {

        switch (num) {
            case 1:
                this.soundPool.play(12, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(1);
                break;
            case 2:
                this.soundPool.play(10, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(2);
                break;
            case 3:
                this.soundPool.play(9, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(3);
                break;
            case 4:
                this.soundPool.play(11, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(4);
                break;
            case 5:
                this.soundPool.play(4, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(5);
                break;
            case 6:
                this.soundPool.play(6, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(6);
                break;
            case 7:
                this.soundPool.play(7, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(7);
                break;
            case 8:
                this.soundPool.play(8, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(8);
                break;
            case 9:
                this.soundPool.play(1, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(9);
                break;
            case 10:
                this.soundPool.play(3, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(10);
                break;
            case 11:
                this.soundPool.play(2, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(11);
                break;
            case 12:
                this.soundPool.play(5, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(12);
                break;
            case 13:
                this.soundPool.play(13, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(13);
                break;

            case 14:
                this.soundPool.play(14, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(14);
                break;

            case 15:
                this.soundPool.play(15, 1.0F, 1.0F, 1, 0, 1.0F);
                this.animate(15);
                break;

        }
    }

    @Override
    public void onDialogSingleItemSelected(File filename) {
        playThread = new RecordPlay(filename);
        recordThread = new Thread(playThread);
        recordThread.start();
        isRecodlaying = true;
        btn_play.setBackgroundResource(R.drawable.stop);
    }

    @Override
    public void OnDialogCancel() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSaveFileName(String name) {
        displayInterstitial();
        new Thread(new Save(name)).start();


    }

    @Override
    protected void onStop() {

        super.onStop();

        if (isRecodlaying) {
            if (playThread != null && playThread.isRunning) {
                playThread.killThread(true);
                recordThread = null;
                playThread = null;

            }
        }
        if (play_bacground_music) {
            mMediaPlayer.stop();
        }
    }

    @Override
    public void onMusicFileName(String name) {

        try {
            playSong(name);
            play_bacground_music = true;
            btn_music.setBackgroundResource(R.drawable.music_play_stop);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void playSong(String path) throws IllegalArgumentException,
            IllegalStateException, IOException {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.reset();
        mMediaPlayer.setDataSource(path);
        mMediaPlayer.prepare();
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                play_bacground_music = false;
                mp.stop();
                mp.release();
                btn_music.setBackgroundResource(R.drawable.music_play);
            }
        });
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("359328062559895")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


}