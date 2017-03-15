package example.com.multouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private ImageView iv;
    float firstdis = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.fl);
        iv = (ImageView) findViewById(R.id.iv);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float disxy;
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                       // System.out.println("action down");
                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (motionEvent.getPointerCount() >= 2) {
                            float x = motionEvent.getX(0) - motionEvent.getX(1);
                            float y = motionEvent.getY(0) - motionEvent.getY(1);
                            disxy = (float) Math.sqrt(x * x + y * y);
                            //System.out.println("xydis"+disxy);
                            if (firstdis<0){
                                firstdis=disxy;
                            }
                            System.out.println("firstdis="+firstdis);
                            if (disxy - firstdis > 5) {
                                //System.out.println("放大");
                                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                lp.height = (int) (iv.getHeight() * 1.1f);
                                lp.width = (int) (iv.getWidth() * 1.1f);
                                iv.setLayoutParams(lp);
                                firstdis=disxy;

                            } else if (firstdis - disxy > 5) {
                                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) iv.getLayoutParams();
                                lp.height = (int) (iv.getHeight() * 0.9f);
                                lp.width = (int) (iv.getWidth() * 0.9f);
                                iv.setLayoutParams(lp);
                                firstdis=disxy;
                                //System.out.println("缩小");
                            }

                        }
                        //System.out.println("point count"+motionEvent.getPointerCount());
                        // System.out.println(String.format("x0:%f y0:%f x1:%f y1:%f",motionEvent.getX(0),motionEvent.getY(0),motionEvent.getX(1),motionEvent.getY(1)));
                 /*       FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) iv.getLayoutParams();
                        lp.topMargin= (int) motionEvent.getY();
                        lp.leftMargin= (int) motionEvent.getX();
                        iv.setLayoutParams(lp);*/
                        //System.out.println("action move");
                        break;
                    case MotionEvent.ACTION_UP:
                        //System.out.println("FIRST DIS="+firstdis);
                        break;
                }
                return true;
            }

        });
    }
}
