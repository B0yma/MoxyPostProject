package com.boyma.mxsmpl.localdb.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

@Entity
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long idtype1;
    public long idtype2;
    public String photoUri;

    public ImageEntity(long idtype1, long idtype2, String photoUri) {
        this.idtype1 = idtype1;
        this.idtype2 = idtype2;
        this.photoUri = photoUri;
    }
}
