package com.example.ronote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ronote.model.RoNote
import com.example.ronote.model.Users

@Database(entities = [RoNote::class,Users::class], version = 1)
abstract class RoNoteRoomDatabase: RoomDatabase() {
    abstract fun roNoteDao(): RoNoteDAO
    companion object{
        @Volatile
        private var INSTANCE: RoNoteRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): RoNoteRoomDatabase{

            if (INSTANCE == null){
                synchronized(RoNoteRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RoNoteRoomDatabase::class.java, "RoNote.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE as RoNoteRoomDatabase
        }
    }
}