package com.boyma.mxsmpl.ui.PhotosActivity;

import android.content.ContentResolver;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.boyma.mxsmpl.base.App;
import com.boyma.mxsmpl.localdb.models.ImageEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PhotosActivityPresenter extends MvpPresenter<IPhotosActivityView>{

    private int idtype1;
    private int idtype2;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public PhotosActivityPresenter() {
        updateGrid();
    }



    public void onCreate(int idtype1, int idtype2) {
        this.idtype1 = idtype1;
        this.idtype2 = idtype2;
        getViewState().showToast(String.valueOf(idtype1)+":"+String.valueOf(idtype2));
    }


    public void onPhotoAccept(Uri photoUri, ContentResolver contentResolver) {

        ImageEntity imageEntity = new ImageEntity(idtype1,idtype2,copyFile(photoUri,contentResolver));

        mDisposable.add(insertImageEntity(imageEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    System.out.println("Complete");

                    updateGrid();

                    },
                        throwable -> System.out.println("Failed")));
    }

    private String copyFile(Uri photoUri, ContentResolver contentResolver) {
        File dst = new File(App.getInstance().getAppDir(),String.valueOf(System.currentTimeMillis()));

        try {
            copy(photoUri,dst,contentResolver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(dst.getPath());
        return dst.getPath();
    }

    private void copy(Uri src, File dst, ContentResolver contentResolver) throws IOException {
        InputStream in = contentResolver.openInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }

        /*if (!dst.getParentFile().exists())
            dst.getParentFile().mkdirs();

        if (!dst.exists()) {
            dst.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(src).getChannel();
            destination = new FileOutputStream(dst).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }*/
    }

    private void updateGrid() {
        App.getInstance().getDatabase().imageEntityDao().getPhotoUris()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<String>>() {

                    @Override
                    public void onSuccess(List<String> uris) {
                        System.out.println("imageEntities.size():"+uris.size());
                        if (uris.size()!=0){
                            getViewState().hideEmptyText();
                            getViewState().refreshGrid(uris);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Completable insertImageEntity(ImageEntity imageEntity) {
        return Completable.fromAction(() -> {
            App.getInstance().getDatabase().imageEntityDao().insert(imageEntity);
        });
    }
}
