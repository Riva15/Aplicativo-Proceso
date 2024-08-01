package riva.ticona.proyectoaplicativo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CalendarDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "calendar.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_CALENDAR = "calendar"
        const val COLUMN_CALENDAR_ID = "id"
        const val COLUMN_CALENDAR_TITLE = "title"
        const val COLUMN_CALENDAR_DESCRIPTION = "description"
        const val COLUMN_CALENDAR_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_CALENDAR ("
                + "$COLUMN_CALENDAR_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_CALENDAR_TITLE TEXT, "
                + "$COLUMN_CALENDAR_DESCRIPTION TEXT, "
                + "$COLUMN_CALENDAR_DATE TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CALENDAR")
        onCreate(db)
    }
}
