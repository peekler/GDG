package hu.autsoft.androidapidemos.ocr;

import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;


public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> ocrGraphicOverlay;
    private TextView tvOcrResult;

    public OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, TextView tvOcrResult) {
        this.ocrGraphicOverlay = ocrGraphicOverlay;
        this.tvOcrResult = tvOcrResult;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        ocrGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
                sb.append(item.getValue()+"\n");
            }
            OcrGraphic graphic = new OcrGraphic(ocrGraphicOverlay, item);
            ocrGraphicOverlay.add(graphic);
        }

        tvOcrResult.post(new Runnable() {
            @Override
            public void run() {
                tvOcrResult.setText(sb.toString());
            }
        });
    }

    @Override
    public void release() {
        ocrGraphicOverlay.clear();
    }
}
