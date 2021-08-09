package repository.impl;

public class SQLRequests {

    // for Writer

    public static final String WRITER_CREATE_POST = "INSERT INTO posts (writer_id, created, updated)\n" +
            "VALUES (%d, \"%tF\", \"%tF\" );";

    public static final String WRITER_CREATE_REGION = "INSERT INTO regions (writer_id) \n" +
            "VALUES (%d);";


}
