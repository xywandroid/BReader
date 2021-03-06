package com.coressoft.breader.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.coressoft.breader.bean.Note;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTE".
*/
public class NoteDao extends AbstractDao<Note, Void> {

    public static final String TABLENAME = "NOTE";

    /**
     * Properties of entity Note.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Time = new Property(0, String.class, "time", false, "TIME");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Relevant_content = new Property(2, String.class, "relevant_content", false, "RELEVANT_CONTENT");
        public final static Property Content = new Property(3, String.class, "content", false, "CONTENT");
        public final static Property Name = new Property(4, String.class, "name", false, "NAME");
        public final static Property Chapter = new Property(5, String.class, "chapter", false, "CHAPTER");
        public final static Property Section = new Property(6, String.class, "section", false, "SECTION");
        public final static Property Label = new Property(7, String.class, "label", false, "LABEL");
        public final static Property LastOprTime = new Property(8, long.class, "lastOprTime", false, "LAST_OPR_TIME");
    }


    public NoteDao(DaoConfig config) {
        super(config);
    }
    
    public NoteDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTE\" (" + //
                "\"TIME\" TEXT," + // 0: time
                "\"TITLE\" TEXT," + // 1: title
                "\"RELEVANT_CONTENT\" TEXT," + // 2: relevant_content
                "\"CONTENT\" TEXT," + // 3: content
                "\"NAME\" TEXT," + // 4: name
                "\"CHAPTER\" TEXT," + // 5: chapter
                "\"SECTION\" TEXT," + // 6: section
                "\"LABEL\" TEXT," + // 7: label
                "\"LAST_OPR_TIME\" INTEGER NOT NULL );"); // 8: lastOprTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Note entity) {
        stmt.clearBindings();
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(1, time);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String relevant_content = entity.getRelevant_content();
        if (relevant_content != null) {
            stmt.bindString(3, relevant_content);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
 
        String chapter = entity.getChapter();
        if (chapter != null) {
            stmt.bindString(6, chapter);
        }
 
        String section = entity.getSection();
        if (section != null) {
            stmt.bindString(7, section);
        }
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(8, label);
        }
        stmt.bindLong(9, entity.getLastOprTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Note entity) {
        stmt.clearBindings();
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(1, time);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String relevant_content = entity.getRelevant_content();
        if (relevant_content != null) {
            stmt.bindString(3, relevant_content);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(4, content);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(5, name);
        }
 
        String chapter = entity.getChapter();
        if (chapter != null) {
            stmt.bindString(6, chapter);
        }
 
        String section = entity.getSection();
        if (section != null) {
            stmt.bindString(7, section);
        }
 
        String label = entity.getLabel();
        if (label != null) {
            stmt.bindString(8, label);
        }
        stmt.bindLong(9, entity.getLastOprTime());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Note readEntity(Cursor cursor, int offset) {
        Note entity = new Note( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // time
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // relevant_content
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // content
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // chapter
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // section
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // label
            cursor.getLong(offset + 8) // lastOprTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Note entity, int offset) {
        entity.setTime(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRelevant_content(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setChapter(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSection(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLabel(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLastOprTime(cursor.getLong(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Note entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Note entity) {
        return null;
    }

    @Override
    public boolean hasKey(Note entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
