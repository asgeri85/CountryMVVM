package com.example.ulkelermvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(tableName = "country")
data class Country(

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name:String?=null,

    @ColumnInfo(name="capital")
    @SerializedName("capital")
    @Expose
    var capital:String?=null,

    @ColumnInfo(name="region")
    @SerializedName("region")
    @Expose
    var region:String?=null,

    @ColumnInfo(name="currency")
    @SerializedName("currency")
    @Expose
    var currency:String?=null,

    @ColumnInfo(name="flag")
    @SerializedName("flag")
    @Expose
    var flag:String?=null,

    @ColumnInfo(name="language")
    @SerializedName("language")
    @Expose
    var language:String?=null
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}