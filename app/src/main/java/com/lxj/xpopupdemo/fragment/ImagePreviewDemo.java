package com.lxj.xpopupdemo.fragment;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImagePreviewPopupView;
import com.lxj.xpopup.interfaces.OnLoadImageListener;
import com.lxj.xpopup.photoview.PhotoView;
import com.lxj.xpopupdemo.R;

import java.util.ArrayList;

/**
 * Description:
 * Create by lxj, at 2019/1/22
 */
public class ImagePreviewDemo extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_preview;
    }
    ArrayList<String> list = new ArrayList<>();
    ImagePreviewPopupView popupView;
    @Override
    public void init(View view) {
        final ImageView imageView = view.findViewById(R.id.iv_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XPopup.get(getContext())
                        .asCustom(popupView)
                        .show();
            }
        });
//        imageView.setOnPhotoTapListener(new OnPhotoTapListener() {
//            @Override
//            public void onPhotoTap(ImageView view, float x, float y) {
//                Log.e("tag", "aaaaaaaaaaaa");
//            }
//        });



        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2279952540,2544282724&fm=26&gp=0.jpg");
        Glide.with(this).asBitmap().load(list.get(0)).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                imageView.setImageBitmap(resource);
                popupView = new ImagePreviewPopupView(getContext())
                        .setSrcView(imageView, resource)
                        .setImageUrls(list)
                        .setLoadImageListener(new OnLoadImageListener() {
                            @Override
                            public void loadImage(int position, String url, ImageView imageView) {
                                Glide.with(getContext()).load(url).into(imageView);
                            }
                        });
            }
        });
//        imageView.setImageResource(R.drawable.t);
    }
}