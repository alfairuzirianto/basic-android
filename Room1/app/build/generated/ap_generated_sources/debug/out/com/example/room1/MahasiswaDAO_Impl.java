package com.example.room1;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MahasiswaDAO_Impl implements MahasiswaDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Mahasiswa> __insertionAdapterOfMahasiswa;

  private final EntityDeletionOrUpdateAdapter<Mahasiswa> __deletionAdapterOfMahasiswa;

  private final EntityDeletionOrUpdateAdapter<Mahasiswa> __updateAdapterOfMahasiswa;

  public MahasiswaDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMahasiswa = new EntityInsertionAdapter<Mahasiswa>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `tb_mahasiswa` (`nim`,`nama`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Mahasiswa entity) {
        statement.bindLong(1, entity.getMahasiswaId());
        if (entity.getMahasiswaNama() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMahasiswaNama());
        }
      }
    };
    this.__deletionAdapterOfMahasiswa = new EntityDeletionOrUpdateAdapter<Mahasiswa>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tb_mahasiswa` WHERE `nim` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Mahasiswa entity) {
        statement.bindLong(1, entity.getMahasiswaId());
      }
    };
    this.__updateAdapterOfMahasiswa = new EntityDeletionOrUpdateAdapter<Mahasiswa>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tb_mahasiswa` SET `nim` = ?,`nama` = ? WHERE `nim` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Mahasiswa entity) {
        statement.bindLong(1, entity.getMahasiswaId());
        if (entity.getMahasiswaNama() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMahasiswaNama());
        }
        statement.bindLong(3, entity.getMahasiswaId());
      }
    };
  }

  @Override
  public void insertMahasiswa(final Mahasiswa mahasiswa) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMahasiswa.insert(mahasiswa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteMahasiswa(final Mahasiswa mahasiswa) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMahasiswa.handle(mahasiswa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateMahasiswa(final Mahasiswa mahasiswa) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMahasiswa.handle(mahasiswa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Mahasiswa> getAllMahasiswa() {
    final String _sql = "SELECT  * FROM tb_mahasiswa";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMahasiswaId = CursorUtil.getColumnIndexOrThrow(_cursor, "nim");
      final int _cursorIndexOfMahasiswaNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
      final List<Mahasiswa> _result = new ArrayList<Mahasiswa>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Mahasiswa _item;
        final String _tmpMahasiswaNama;
        if (_cursor.isNull(_cursorIndexOfMahasiswaNama)) {
          _tmpMahasiswaNama = null;
        } else {
          _tmpMahasiswaNama = _cursor.getString(_cursorIndexOfMahasiswaNama);
        }
        _item = new Mahasiswa(_tmpMahasiswaNama);
        final int _tmpMahasiswaId;
        _tmpMahasiswaId = _cursor.getInt(_cursorIndexOfMahasiswaId);
        _item.setMahasiswaId(_tmpMahasiswaId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Mahasiswa findMahasiswa(final int nim) {
    final String _sql = "SELECT * FROM tb_mahasiswa WHERE nim LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, nim);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMahasiswaId = CursorUtil.getColumnIndexOrThrow(_cursor, "nim");
      final int _cursorIndexOfMahasiswaNama = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
      final Mahasiswa _result;
      if (_cursor.moveToFirst()) {
        final String _tmpMahasiswaNama;
        if (_cursor.isNull(_cursorIndexOfMahasiswaNama)) {
          _tmpMahasiswaNama = null;
        } else {
          _tmpMahasiswaNama = _cursor.getString(_cursorIndexOfMahasiswaNama);
        }
        _result = new Mahasiswa(_tmpMahasiswaNama);
        final int _tmpMahasiswaId;
        _tmpMahasiswaId = _cursor.getInt(_cursorIndexOfMahasiswaId);
        _result.setMahasiswaId(_tmpMahasiswaId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
