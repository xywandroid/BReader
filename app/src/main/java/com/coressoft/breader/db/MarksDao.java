package com.coressoft.breader.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.coressoft.breader.bean.Marks;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MARKS".
*/
public class MarksDao extends AbstractDao<Marks, Void> {

    public static final String TABLENAME = "MARKS";

    /**
     * Properties of entity Marks.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", false, "ID");
        public final static Property Begin = new Property(1, long.class, "begin", false, "BEGIN");
        public final static Property Text = new Property(2, String.class, "text", false, "TEXT");
        public final static Property Time = new Property(3, String.class, "time", false, "TIME");
        public final static Property Book = new Property(4, int.class, "book", false, "BOOK");
        public final static Property Chapter = new Property(5, int.class, "chapter", false, "CHAPTER");
        public final static Property Verse = new Property(6, int.class, "verse", false, "VERSE");
    }


    public MarksDao(DaoConfig config) {
        super(config);
    }
    
    public MarksDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MARKS\" (" + //
                "\"ID\" INTEGER NOT NULL ," + // 0: id
                "\"BEGIN\" INTEGER NOT NULL ," + // 1: begin
                "\"TEXT\" TEXT," + // 2: text
                "\"TIME\" TEXT," + // 3: time
                "\"BOOK\" INTEGER NOT NULL ," + // 4: book
                "\"CHAPTER\" INTEGER NOT NULL ," + // 5: chapter
                "\"VERSE\" INTEGER NOT NULL );"); // 6: verse
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MARKS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Marks entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getBegin());
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(3, text);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
        stmt.bindLong(5, entity.getBook());
        stmt.bindLong(6, entity.getChapter());
        stmt.bindLong(7, entity.getVerse());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Marks entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getBegin());
 
        String text = entity.getText();
        if (text != null) {
            stmt.bindString(3, text);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
        stmt.bindLong(5, entity.getBook());
        stmt.bindLong(6, entity.getChapter());
        stmt.bindLong(7, entity.getVerse());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Marks readEntity(Cursor cursor, int offset) {
        Marks entity = new Marks( //
            cursor.getInt(offset + 0), // id
            cursor.getLong(offset + 1), // begin
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // text
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // time
            cursor.getInt(offset + 4), // book
            cursor.getInt(offset + 5), // chapter
            cursor.getInt(offset + 6) // verse
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Marks entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setBegin(cursor.getLong(offset + 1));
        entity.setText(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBook(cursor.getInt(offset + 4));
        entity.setChapter(cursor.getInt(offset + 5));
        entity.setVerse(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Marks entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Marks entity) {
        return null;
    }

    @Override
    public boolean hasKey(Marks entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}