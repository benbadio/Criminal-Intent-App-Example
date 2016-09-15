package com.benbadio.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.benbadio.criminalintent.Crime;
import com.benbadio.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by benba on 9/7/2016.
 *
 * A Cursor is used to read data from a database.
 * Normally a cursor would perform a query and return
 * raw database values, which aren't likely to translate
 * to their corresponding object attributes. Extending
 * CursorWrapper solves this by wrapping the cursor in a
 * new object with added methods to handle the conversion.
 */
public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
