package memory.game.kids.memory.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.model.Memory;
import memory.game.kids.memory.play.LevelsActivity;
import memory.game.kids.memory.play.MemoryGridViewD;
import memory.game.kids.memory.play.Memory_two;

/**
 * MainActivity
 */
public class MainActivity extends AbstractMainActivity implements Memory.OnMemoryListener
{

    private static final int[] tiles_fruits =
    {
        R.drawable.a1, R.drawable.a2,
        R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
        R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
        R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14,
        R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18,
        R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22
    };
    
    private static final int[] tiles_halloween =
    {
        R.drawable.b1, R.drawable.b2,
        R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
        R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10,
        R.drawable.b11, R.drawable.b12
    };
    
    private static final int[] tiles_sports =
    {
        R.drawable.c1, R.drawable.c2,
        R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6,
        R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
        R.drawable.c11, R.drawable.c12
    };
	
	private static final int[] tiles_foods =
    {
        R.drawable.d1, R.drawable.d2,
        R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6,
        R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
        R.drawable.d11, R.drawable.d12
    };
    
    private static final int[][] icons_set = { tiles_fruits , tiles_halloween, tiles_sports, tiles_foods };
    
    private static final int[] sounds = {
      R.raw.gupp, R.raw.winch, R.raw.chtoing, R.raw.kito, R.raw.kato, 
      R.raw.ding, R.raw.ding2, R.raw.ding3, R.raw.ding4, R.raw.ding5,
      R.raw.ding6, R.raw.dong, R.raw.swirlup, R.raw.swipp
    };


    private static final int[] not_found_tile_set =
    {
        R.drawable.not_found_fruits, R.drawable.not_found_halloween, R.drawable.not_found_sports, R.drawable.not_found_foods
    };
    private Memory mMemory;
    private  Memory_two memory_two ;
    private MemoryGridView mGridView;
    private static   String PREFS_NAME ;
    Bundle bundle;
    LinearLayout layoutTime ;
    private long startTime ;
    private final long interval = 1 * 1000;
    public   CountDownTimer countDownTimer;
    TextView timeT ;
    long secondsRemaining  ,mTimeRemaining;
    Dialog dialog;
    MemoryGridViewD memoryGridViewDialoge ;
    Dialog okdialog ;
    int set ;
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            PREFS_NAME=bundle.getString("PREFS_NAME");
        }
        Toast.makeText(getApplicationContext(),PREFS_NAME ,Toast.LENGTH_LONG).show();
        countDownTimer = new MyCountDownTimer(startTime, interval);
        PreferencesService.init( this,PREFS_NAME );
        timeT=(TextView)findViewById(R.id.time);
        layoutTime=(LinearLayout)findViewById(R.id.layoutTime);
        layoutTime.setVisibility(View.GONE);
        set = PreferencesService.instance().getIconsSet();
        Toast.makeText(getApplicationContext(),"Set  "+set ,Toast.LENGTH_LONG).show();
        mMemory= new Memory( icons_set[ set], sounds , not_found_tile_set[ set], this ,MainActivity.this );
        mMemory.reset();
        newGame();

    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected View getGameView()
    {
        return mGridView;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void newGame()
    {

         mGridView = (MemoryGridView) findViewById(R.id.gridview);
        mGridView.setMemory(mMemory);
        drawGrid();
    }

   
    /**
     * {@inheritDoc }
     */
    @Override
    protected void preferences()
    {
        Intent intent = new Intent( this , PreferencesActivity.class );
        startActivity(intent);
    }

    @Override
    protected void startTime() {
        if(PREFS_NAME.equals("Level_two")){
            layoutTime.setVisibility(View.VISIBLE);
            startTime=  60 * 1000;
            timeT.setText(String.valueOf(startTime / 1000));
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.start();
        }else{
            layoutTime.setVisibility(View.GONE);
        }
    }

    @Override
    protected void cancleTime() {
        countDownTimer.cancel();

    }

    @Override
    protected void dialoge() {
        okdialog = new Dialog(this, R.style.custom_dialog_theme);
        okdialog.setContentView(R.layout.activity_show_images);
        memoryGridViewDialoge =(MemoryGridViewD)okdialog.findViewById(R.id.gridview_Dialode);
        memory_two= new Memory_two(mMemory);
        memory_two.reset();
        memoryGridViewDialoge.setMemory(memory_two);
        okdialog.show();

                 new Handler().postDelayed(new Runnable() {
                      @Override
                     public void run() {
                          okdialog.dismiss();
                          okdialog.cancel();
                }
                }, 9000);


    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onResume()
    {
        super.onResume();

        mMemory.onResume( PreferencesService.instance().getPrefs() );

        drawGrid();
        
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onPause()
    {
        super.onPause();

        mMemory.onPause( PreferencesService.instance().getPrefs() , mQuit);

    }


    /**
     * {@inheritDoc }
     */
    public void onComplete(int countMove)
    {
        int nHighScore = PreferencesService.instance().getHiScore();
        String title = getString(R.string.success_title);
        Object[] args = { countMove, nHighScore };
        String message = MessageFormat.format(getString(R.string.success), args );
        int icon = R.drawable.win;
        if (countMove < nHighScore)
        {
            title = getString(R.string.hiscore_title);
            message = MessageFormat.format(getString(R.string.hiscore), args );
            icon = R.drawable.hiscore;

            PreferencesService.instance().saveHiScore(countMove);
        }
        this.showEndDialog(title, message, icon);
    }

    /**
     * {@inheritDoc }
     */
    public void onUpdateView()
    {
        drawGrid();
    }

    /**
     * Draw or redraw the grid
     */
    private void drawGrid()
    {
        mGridView.update();
    }

    @Override
    public void onBackPressed() {
        cancleTime();
//        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.clear();
//        editor.commit();
        Intent intent = new Intent( MainActivity.this,  LevelsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long startTime, long interval) {


            super(startTime, interval);

        }

        @Override

        public void onFinish() {

            dialog= new Dialog(MainActivity.this, R.style.custom_dialog_theme);
            dialog.setContentView(R.layout.time_up);
            TextView text =(TextView)dialog.findViewById(R.id.text);
             text.setText("Time's up!");
            Button dialogButton = (Button) dialog.findViewById(R.id.backtoMenu);
             dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(),PreferencesActivity.class);
                     startActivity(intent);
                    finish();

                }
            });

            dialog.show();


        }



        @Override

        public void onTick(long millisUntilFinished) {
            secondsRemaining = millisUntilFinished / 1000 ;

            timeT.setText(" "+String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            if (secondsRemaining <= 10) {
                timeT.setTextColor(getResources().getColor(R.color.red));
            } else {
                timeT.setTextColor(Color.BLACK);


            }

        }

    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        cancleTime();
         super.onDestroy();
    }

}
