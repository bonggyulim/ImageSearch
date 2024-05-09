package com.example.imagesearch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [EntityData::class], exportSchema = false, version = 1)
@TypeConverters(
    value = [
        URLConverters::class,
        DateConverters::class
    ]
)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getDao(): DataDAO

    companion object {
        // DB 오픈시 version 정보에 따라 Migration 수행
        // 속성 추가 및 변경 시에는 Migration으로 버전up을 해야함, 임의로 DataClass에서 속성 추가 못함
        private var INSTANCE: MyDatabase? = null
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student_table ADD COLUMN last_update INTEGER")
            }
        }

        // 싱글톤패턴 사용, databaseBuilder를 통해 클래스 객체 생성
        fun getDatabase(context: Context) : MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, MyDatabase::class.java, "school_database")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                // for in-memory database
                /*INSTANCE = Room.inMemoryDatabaseBuilder(
                    context, MyDatabase::class.java
                ).build()*/
            }
            return INSTANCE as MyDatabase
        }
    }
}