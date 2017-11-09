package memory.game.kids.memory.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


public class MemoryGridViewD extends GridView
{
    private static final int MARGINH = 15;
    private static final int MARGINM = 2;

    private Memory_two mMemory;
    private Context mContext;

    public MemoryGridViewD(Context context)
    {
        super(context);

        mContext = context;



    }

    public MemoryGridViewD(Context context, AttributeSet attrs)
    {
        super( context , attrs );
        mContext = context;

    }

    public MemoryGridViewD(Context context, AttributeSet attrs, int defStyle)
    {
        super( context , attrs , defStyle );
        mContext = context;

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        update( );
    }

    public void update()
    {
    	if (getWidth()<480) {
    		setAdapter(new ImageAdapter( mContext, getWidth(), getHeight(), MARGINM , mMemory));
		}else{
			setAdapter(new ImageAdapter( mContext, getWidth(), getHeight(), MARGINH , mMemory));
		}
        
    }


    public void setMemory( Memory_two memory )
    {
        mMemory = memory;
    }

}
