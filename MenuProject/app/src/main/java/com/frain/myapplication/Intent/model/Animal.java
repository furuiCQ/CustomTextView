package com.frain.myapplication.Intent.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2016/10/25.
 */
public class Animal implements Parcelable
{
    private String animalName;
    private String author;
    private int publishDate;

    public Animal()
    {

    }

    public String getanimalName()
    {
        return animalName;
    }

    public void setanimalName(String animalName)
    {
        this.animalName = animalName;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public int getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate(int publishDate)
    {
        this.publishDate = publishDate;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(animalName);
        out.writeString(author);
        out.writeInt(publishDate);
    }
    public static final Parcelable.Creator<Animal> CREATOR = new Creator<Animal>()
    {
        @Override
        public Animal[] newArray(int size)
        {
            return new Animal[size];
        }

        @Override
        public Animal createFromParcel(Parcel in)
        {
            return new Animal(in);
        }
    };

    public Animal(Parcel in)
    {
        animalName = in.readString();
        author = in.readString();
        publishDate = in.readInt();
    }
}