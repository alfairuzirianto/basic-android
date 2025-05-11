package com.example.djmag;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Dj implements Parcelable {
    private Integer photo;
    private String name, description, rank, song, artists, djLink, songLink;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoto() {
        return photo;
    }
    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSong(String song) { this.song = song; }
    public String getSong() { return song; }

    public void setSongArtists(String artists) { this.artists = artists; }
    public String getSongArtists() { return artists; }

    public void setDjLink(String djLink) { this.djLink = djLink; }
    public String getDjLink() { return djLink; }

    public void setSongLink(String songLink) { this.songLink = songLink; }
    public String getSongLink() { return songLink; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.photo);
        dest.writeString(this.rank);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.song);
        dest.writeString(this.artists);
        dest.writeString(this.djLink);
        dest.writeString(this.songLink);
    }

    Dj() {

    }
    
    private Dj(Parcel in) {
        this.photo = in.readInt();
        this.rank = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.song = in.readString();
        this.artists = in.readString();
        this.djLink = in.readString();
        this.songLink = in.readString();
    }

    public static final Parcelable.Creator<Dj> CREATOR = new Parcelable.Creator<Dj>() {
        @Override
        public Dj createFromParcel(Parcel source) {
            return new Dj(source);
        }

        public Dj[] newArray(int size) {
            return new Dj[size];
        }
    };
}
