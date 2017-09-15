package br.com.wnascimento.entreguei.data.local;

public class PersistenceContract {


    public static abstract class UserEntry {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static abstract class AddressEntry {
        public static final String TABLE_NAME = "address";
        public static final String COLUMN_NAME_CEP = "cep";
        public static final String COLUMN_NAME_STREET = "street";
        public static final String COLUMN_NAME_NEIGHBORHOOD = "neighborhood";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_COMPLEMENT = "complement";
    }

    public static abstract class UserAddressEntry {
        public static final String TABLE_NAME = "user_address";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_ADDRESS_CEP = "address_id";
    }

}
