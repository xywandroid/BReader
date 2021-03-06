package com.coressoft.breader.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.coressoft.breader.bean.Verse;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VERSE".
*/
public class VerseDao extends AbstractDao<Verse, Void> {

    public static final String TABLENAME = "VERSE";

    /**
     * Properties of entity Verse.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", false, "id");
        public final static Property Book = new Property(1, int.class, "book", false, "book");
        public final static Property Chapter = new Property(2, int.class, "chapter", false, "chapter");
        public final static Property Verse = new Property(3, int.class, "verse", false, "verse");
        public final static Property Con = new Property(4, String.class, "con", false, "con");
        public final static Property ConEn = new Property(5, String.class, "conEn", false, "conEn");
    }


    public VerseDao(DaoConfig config) {
        super(config);
    }
    
    public VerseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VERSE\" (" + //
                "\"id\" INTEGER NOT NULL ," + // 0: id
                "\"book\" INTEGER NOT NULL ," + // 1: book
                "\"chapter\" INTEGER NOT NULL ," + // 2: chapter
                "\"verse\" INTEGER NOT NULL ," + // 3: verse
                "\"con\" TEXT," + // 4: con
                "\"conEn\" TEXT);"); // 5: conEn
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VERSE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Verse entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getBook());
        stmt.bindLong(3, entity.getChapter());
        stmt.bindLong(4, entity.getVerse());
 
        String con = entity.getCon();
        if (con != null) {
            stmt.bindString(5, con);
        }
 
        String conEn = entity.getConEn();
        if (conEn != null) {
            stmt.bindString(6, conEn);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Verse entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getBook());
        stmt.bindLong(3, entity.getChapter());
        stmt.bindLong(4, entity.getVerse());
 
        String con = entity.getCon();
        if (con != null) {
            stmt.bindString(5, con);
        }
 
        String conEn = entity.getConEn();
        if (conEn != null) {
            stmt.bindString(6, conEn);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Verse readEntity(Cursor cursor, int offset) {
        Verse entity = new Verse( //
            cursor.getInt(offset + 0), // id
            cursor.getInt(offset + 1), // book
            cursor.getInt(offset + 2), // chapter
            cursor.getInt(offset + 3), // verse
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // con
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // conEn
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Verse entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setBook(cursor.getInt(offset + 1));
        entity.setChapter(cursor.getInt(offset + 2));
        entity.setVerse(cursor.getInt(offset + 3));
        entity.setCon(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setConEn(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Verse entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Verse entity) {
        return null;
    }

    @Override
    public boolean hasKey(Verse entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
