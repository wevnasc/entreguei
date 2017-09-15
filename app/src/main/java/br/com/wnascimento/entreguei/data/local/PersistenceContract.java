package br.com.wnascimento.entreguei.data.local;

public class PersistenceContract {


    public static abstract class UserEntry {
        public static final String TABLE_NAME = "motoboy";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }


}
