package com.example.room4;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
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
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
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
        statement.bindLong(1, entity.getId());
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
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getId());
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
  public LiveData<List<Mahasiswa>> getListMahasiswa() {
    final String _sql = "SELECT * FROM tb_mahasiswa";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tb_mahasiswa"}, false, new Callable<List<Mahasiswa>>() {
      @Override
      @Nullable
      public List<Mahasiswa> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "nim");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "nama");
          final List<Mahasiswa> _result = new ArrayList<Mahasiswa>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Mahasiswa _item;
            _item = new Mahasiswa();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            _item.setName(_tmpName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
