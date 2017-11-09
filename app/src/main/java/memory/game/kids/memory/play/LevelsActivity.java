package memory.game.kids.memory.play;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.ui.MainActivity;


public class LevelsActivity extends AppCompatActivity implements View.OnClickListener {
    Button levelOne  ,LevelTwo,levelThree;
    Bundle bundle ;
     int ICONS_SET ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        bundle = getIntent().getExtras();
        if(bundle!=null){
             ICONS_SET =bundle.getInt("ICONS_SET",0);

        }
        levelOne =(Button)findViewById(R.id.levelOne);
        LevelTwo =(Button)findViewById(R.id.LevelTwo);
        levelThree =(Button)findViewById(R.id.levelThree);
        levelOne.setOnClickListener(this);
        LevelTwo.setOnClickListener(this);
        levelThree.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == levelOne){
            PreferencesService.init( this,"Level_one" );
             PreferencesService.instance().saveIconsSet(ICONS_SET );
            Intent intent = new Intent(LevelsActivity.this, MainActivity.class);
            intent.putExtra("PREFS_NAME" ,"Level_one");
            startActivity(intent);

        }else if(view==LevelTwo){
            PreferencesService.init( this,"Level_two");
             PreferencesService.instance().saveIconsSet(ICONS_SET );
            Intent intent = new Intent(LevelsActivity.this, MainActivity.class);
            intent.putExtra("PREFS_NAME" ,"Level_two");
            startActivity(intent);


        }else if(view==levelThree){
            PreferencesService.init( this,"Level_three" );
             PreferencesService.instance().saveIconsSet(ICONS_SET );
            Intent intent = new Intent(LevelsActivity.this, MainActivity.class);
            intent.putExtra("PREFS_NAME" ,"Level_three");
            startActivity(intent);

        }else {
            Toast.makeText(getApplicationContext(),"else" ,Toast.LENGTH_LONG).show();

        }

    }
}
