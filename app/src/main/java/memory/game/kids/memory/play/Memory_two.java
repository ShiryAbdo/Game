package memory.game.kids.memory.play;

import android.content.SharedPreferences;

import org.androidsoft.utils.sound.SoundManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.model.Memory;
import memory.game.kids.memory.model.Tile;
import memory.game.kids.memory.model.TileList;

public class Memory_two
{

    private static final int MAX_TILES_PER_ROW = 6;
    private static final int MIN_TILES_PER_ROW = 4;
     private TileList thisList = new TileList();

     Memory memory ;

    public Memory_two( Memory Mmemory )
    {
           memory =Mmemory ;


    }






    public int getCount()
    {
        return thisList.size();
    }

    public int getMaxTilesPerRow()
    {
        return MAX_TILES_PER_ROW;
    }

    public int getMinTilesPerRow()
    {
        return MIN_TILES_PER_ROW;
    }

    public int getResId(int position)
    {
        return thisList.get(position).getResId();
    }

    public void reset()
    {

        thisList.clear();
        thisList = memory.getmList();
        for(int i = 0 ; i <thisList.size(); i ++){
            thisList.get(i).select();
        }
    }












}
