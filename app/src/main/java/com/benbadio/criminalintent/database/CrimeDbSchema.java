package com.benbadio.criminalintent.database;

/**
 * Created by benba on 9/7/2016.
 */
public class CrimeDbSchema {
    public static final class CrimeTable {
        // The NAME of the table
        public static final String NAME = "crimes";

        //The COLUMNS of the table
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }

    }
}
