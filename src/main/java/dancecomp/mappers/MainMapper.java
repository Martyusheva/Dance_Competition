package dancecomp.mappers;

import org.sql2o.Sql2o;

public class MainMapper {
    protected static Sql2o sql2o = new Sql2o("jdbc:mysql://localhost/dancecomp_db"+
            "?verifyServerCertificate=false&useSSL=false&requireSSL=false"+
            "&useLegacyDatetimeCode=false&amp&serverTimezone=UTC"+
            "&useUnicode=yes&characterEncoding=UTF-8", "root", "rootpassword");
}