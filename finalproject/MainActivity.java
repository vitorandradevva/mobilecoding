import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private EditText emailEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        emailEditText = findViewById(R.id.emailEditText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                Bitmap drawingBitmap = drawingView.getBitmap();
                // Implement email sending logic here
                // You can use the 'email' and 'drawingBitmap' variables
                // to send the email with the drawing content
            }
        });
    }

    public static class DrawingView extends View {

        private Path drawingPath;
        private Paint drawingPaint;
        private Canvas drawingCanvas;
        private Bitmap drawingBitmap;

        public DrawingView(Context context, AttributeSet attrs) {
            super(context, attrs);
            setupDrawing();
        }

        private void setupDrawing() {
            drawingPath = new Path();
            drawingPaint = new Paint(Paint.DITHER_FLAG);
            drawingPaint.setColor(Color.BLACK);
            drawingPaint.setStyle(Paint.Style.STROKE);
            drawingPaint.setStrokeJoin(Paint.Join.ROUND);
            drawingPaint.setStrokeCap(Paint.Cap.ROUND);
            drawingPaint.setStrokeWidth(5);
        }

        public Bitmap getBitmap() {
            return drawingBitmap;
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            drawingBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            drawingCanvas = new Canvas(drawingBitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(drawingBitmap, 0, 0, drawingPaint);
           