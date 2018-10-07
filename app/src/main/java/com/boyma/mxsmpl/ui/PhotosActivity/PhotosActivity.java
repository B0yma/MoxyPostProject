package com.boyma.mxsmpl.ui.PhotosActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.boyma.mxsmpl.R;
import com.boyma.mxsmpl.ui.adapters.GridAdapter;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class PhotosActivity extends MvpAppCompatActivity implements IPhotosActivityView {

    @InjectPresenter
    PhotosActivityPresenter mpresenter;
    private FloatingActionButton add;
    private Uri photoUri;
    private final int CAMERA_REQUEST = 0;
    private RecyclerView recgrid_layout;
    private GridAdapter mAdapter;
    private TextView textemty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        mpresenter.onCreate(
                getIntent().getIntExtra("idtype1",-1),
                getIntent().getIntExtra("idtype2",-1)
        );

        initUI();

    }

    private void initUI() {
        add = findViewById(R.id.add);
        add.setOnClickListener(v -> onAddClick());

        recgrid_layout =  findViewById(R.id.recgrid_layout);
        initrecgrid();

        textemty = findViewById(R.id.textemty);
    }

    private void initrecgrid() {
        recgrid_layout.setHasFixedSize(false);
        recgrid_layout.clearOnScrollListeners();
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recgrid_layout.setLayoutManager(mLayoutManager);
        mAdapter = new GridAdapter(this);
        recgrid_layout.setAdapter(mAdapter);
    }

    private void onAddClick() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CAMERA_REQUEST);

        /*File f = new File(this.getExternalCacheDir(), "tempImage");
        Intent takePhotoIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        photoUri = Uri.fromFile(f);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        //takePhotoIntent.putExtra("output", photoUri);
        startActivityForResult(takePhotoIntent, CAMERA_REQUEST);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        photoUri = imageReturnedIntent.getData();
        switch(requestCode) {
            /*case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    photoUri = imageReturnedIntent.getData();
                    Log.e("imageUri",photoUri.toString());
                    Glide.with(EditImageActivity.this)
                            .load(photoUri)
                            .fitCenter()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(photo_imageView);
                }
                break;*/
            case CAMERA_REQUEST:
                if(resultCode == RESULT_OK){
                    mpresenter.onPhotoAccept(photoUri,getContentResolver());
                }
                break;
        }
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void refreshGrid(List<String> uris) {
        for (String s: uris){System.out.println("uri:"+s);}
        mAdapter.clear();
        mAdapter.addAll(uris);
    }

    @Override
    public void hideEmptyText() {
        textemty.setVisibility(View.GONE);
    }
}
