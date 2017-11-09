package memory.game.kids.memory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.androidsoft.utils.ui.BasicActivity;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.play.LevelsActivity;

public class PreferencesActivity extends BasicActivity implements OnClickListener
{
     private RadioButton mRbFruits;
    private RadioButton mRbHalloween;
    private RadioButton mRbSports;
	private RadioButton mRbFoods;
    Button startB ;

    //Create the bundle
    Bundle bundle = new Bundle();
//Add your data from getFactualResults method to bundle


    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
         setContentView( R.layout.settings);
        startB =  this.findViewById(R.id.idbutton);
//         updateHiScore();



        mRbFruits = (RadioButton) findViewById(R.id.radio_mode_fruits);
        mRbFruits.setOnClickListener(this);
        mRbHalloween = (RadioButton) findViewById(R.id.radio_mode_halloween);
        mRbHalloween.setOnClickListener(this);
        mRbSports = (RadioButton) findViewById(R.id.radio_mode_sports);
        mRbSports.setOnClickListener(this);
		mRbFoods = (RadioButton) findViewById(R.id.radio_mode_foods);
        mRbFoods.setOnClickListener(this);
//        int iconSet = PreferencesService.instance().getIconsSet();
//        if( iconSet == PreferencesService.ICONS_SET_FRUITS )
//        {
            mRbFruits.setChecked(true);
            mRbHalloween.setChecked(false);
            mRbSports.setChecked(false);
			mRbFoods.setChecked(false);
//        }
//        else if ( iconSet == PreferencesService.ICONS_SET_HALLOWEEN )
//        {
//            mRbFruits.setChecked(false);
//            mRbHalloween.setChecked(true);
//            mRbSports.setChecked(false);
//			mRbFoods.setChecked(false);
//        }
//        else if ( iconSet == PreferencesService.ICONS_SET_SPORTS )
//        {
//            mRbFruits.setChecked(false);
//            mRbHalloween.setChecked(false);
//            mRbSports.setChecked(true);
//			mRbFoods.setChecked(false);
//        }
//		else if ( iconSet == PreferencesService.ICONS_SET_FOODS )
//        {
//            mRbFruits.setChecked(false);
//            mRbHalloween.setChecked(false);
//            mRbSports.setChecked(false);
//			mRbFoods.setChecked(true);
//        }
        
  //        mCbSoundEnabled.setChecked( PreferencesService.instance().isSoundEnabled() );
        startB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferencesActivity.this,LevelsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    
    /**
     * {@inheritDoc } 
     */
    @Override
    public int getMenuResource()
    {
        return R.menu.menu_close;
    }

    /**
     * {@inheritDoc } 
     */
    @Override
    public int getMenuCloseId()
    {
        return R.id.menu_close;
    }

    /**
     * {@inheritDoc } 
     */
    public void onClick(View view)
    {

        if ( view == mRbFruits )
        {
            bundle.putInt("ICONS_SET", PreferencesService.ICONS_SET_FRUITS );
//            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_FRUITS );
        }
        else if ( view == mRbHalloween )
        {
            bundle.putInt("ICONS_SET", PreferencesService.ICONS_SET_HALLOWEEN );
//            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_HALLOWEEN );
        }
        else if ( view == mRbSports )
        {
            bundle.putInt("ICONS_SET", PreferencesService.ICONS_SET_SPORTS);
//            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_SPORTS );
        }
		else if ( view == mRbFoods )
        {
            bundle.putInt("ICONS_SET", PreferencesService.ICONS_SET_FOODS );
//            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_FOODS );
        }
    }

    private void updateHiScore()
    {
//        int hiscore = PreferencesService.instance().getHiScore();
//        if( hiscore == PreferencesService.HISCORE_DEFAULT )
//        {
//         }
//        else
//        {
//         }
    }

    
  
}
