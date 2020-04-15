package com.alfauz.orderme.utils;

import com.alfauz.orderme.config.ConfigProperties;
import com.alfauz.orderme.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Miscellaneous {

    private static final Logger LOG = LogManager.getLogger(Miscellaneous.class);
    private static final String SERVLET_START = "/reportservlet?repName=";
    private static ConfigProperties configProperties;
    private static JdbcTemplate jdbcTemplate;
    private static SimpleJdbcCall simpleJdbcCall;
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static Validator validator;

    @Autowired
    public Miscellaneous(
            final ConfigProperties configProperties,
            final JdbcTemplate jdbcTemplate,
            final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
            final Validator validator
    ) {
        Miscellaneous.configProperties = configProperties;
        Miscellaneous.jdbcTemplate = jdbcTemplate;
        Miscellaneous.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        Miscellaneous.validator = validator;
    }

    public static Exception getNestedException(Exception rootException) {
        if (rootException.getCause() == null) {
            LOG.info("Last Exception: {}", rootException);
            return rootException;
        } else {
            Exception cause = (Exception) rootException.getCause();
            LOG.log(Level.INFO, rootException.getClass().getName() + "Exception Cause: {0}", cause);
            return getNestedException(cause);
        }
    }

    public static String getResourceMessage(String rsrcBundle, String key)
            throws NullPointerException, MissingResourceException, ClassCastException {
        ResourceBundle bundle = ResourceBundle.getBundle(rsrcBundle);
        return bundle.getString(key);
    }

    public static ResourceBundle getResourceBundle(String bundle)
            throws NullPointerException, MissingResourceException {
        return ResourceBundle.getBundle(bundle);
    }

    public static String convertDBError(Exception e) {
        final String[] resourceMessage = {null};
        final boolean[] found = {false};
        String errorMessage = Miscellaneous.getNestedException(e).getMessage();
        Set<ResourceBundle> rbList = new HashSet<>();
        rbList.add(Miscellaneous.getResourceBundle("dbconstraints"));
        rbList.add(Miscellaneous.getResourceBundle("dberrors"));

        rbList.stream().filter(f -> false == found[0]).forEach(rb -> {
//          rbList.forEach(rb -> {
            rb.keySet()
                    .stream()
                    .filter(key -> errorMessage.toUpperCase().contains(key.toUpperCase()))
                    .map(s -> Miscellaneous.getResourceMessage(rb.getBaseBundleName(), s))
                    .forEach(message -> {
                        LOG.log(Level.INFO, "Message: {0}", message);
                        found[0] = true;
                        resourceMessage[0] = message;
                    });
        });

//        outer:
//        for (ResourceBundle rb : rbList) {
// for (String key : rb.keySet()) {
//                if (errorMessage.toUpperCase().contains(key.toUpperCase())) {
//                    String msg = Miscellaneous.getResourceMessage(rb.getBaseBundleName(), key);
//                    resourceMessage[0] = msg;
//                    break outer;
//                }
//            }
//        }

        return resourceMessage[0];
    }

//    public static boolean exists(String operationClass, Long id) throws ClassNotFoundException, NoSuchMethodException {
//        Class aClass = Class.forName(operationClass);
//        Object obj = aClass.cast(new Object());
//        Method method = aClass.getDeclaredMethod("exists");
//        return false;
//    }

//  public static int exists(String table, String column, Long columnValue) {
//    final String dbServer = configProperties.getDb().get("server");
//    final String dbPort = configProperties.getDb().get("port");
//    final String dbService = configProperties.getDb().get("service");
//    final String dbUsername = configProperties.getDb().get("username");
//    final String dbPassword = configProperties.getDb().get("password");
//
//    LOG.debug("Server: {}", dbServer);
//    LOG.debug("port: {}", configProperties.getApp().get("port"));
//    int result = 0;
//    try {
//      String sql = "BEGIN :RESULT := RECORD_EXISTS ( :P_TABLE, :P_COLUMN, :P_COLUMN_VALUE); END;";
////            System.out.println("SF Result: " + sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter("PTABLE", table).setParameter("PCOLUMN", column).setParameter("PID", id).getSingleResult());
//      Connection conn = DB.getInstance(dbServer, dbPort, dbService, dbUsername, dbPassword)
//          .getConnection();
//      CallableStatement stmt = conn.prepareCall(sql);
//      stmt.registerOutParameter("RESULT", Types.INTEGER);
//      stmt.setString("P_TABLE", table);
//      stmt.setString("P_COLUMN", column);
//      stmt.setLong("P_COLUMN_VALUE", columnValue);
//      stmt.execute();
//      result = stmt.getInt("RESULT");
//      LOG.info("Result: ", result);
//      conn.close();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    }
//    /*
////        String sql = "SELECT XXIM_RECORD_EXISTS (" + table + ", " + column + ", " + id + ") FROM DUAL";
////                System.out.println("Query: " + sql);
////        int result = jdbcTemplate.queryForInt(
////                sql, Integer.class);
////        System.out.println("Result: " + result);
//        MapSqlParameterSource in = new MapSqlParameterSource();
//        in.addValue("P_TABLE", table);
//        in.addValue("P_COLUMN", column);
//        in.addValue("P_ID", id);
//        Integer res = countRecordsJdbcCall.executeFunction(Integer.class, in);
//        System.out.println("Result: " + res);
//
////        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("XXIM_RECORD_EXISTS");
////        MapSqlParameterSource in = new MapSqlParameterSource();
////        in.addValue("P_TABLE", table);
////        in.addValue("P_COLUMN", column);
////        in.addValue("P_ID", id);
////        Map<String, Object> out = jdbcCall.execute(in);
////        for (Map.Entry<String, Object> entry : out.entrySet()) {
////            System.out.println(entry.getValue());
////        }
////        ;
////        String sql = "SELECT COUNT(1) FROM " + table + " WHERE " + column + " = " + id;
////        SessionFactory factory =
////                HibernateUtil.getSessionFactory();
////        System.out.println("Result: " + factory.openSession().createSQLQuery(sql).getSingleResult());
////        EntityManager em = factory.createEntityManager();
////        System.out.println("Result: " + em.createNativeQuery(sql)
////                .setParameter(1, table)
////                .setParameter(2, column)
////                .setParameter(3, id)
////                .getSingleResult());*/
//    return result;
//  }

/*  public static Integer exists(String table, String column, Long columnValue) {
    simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withFunctionName("RECORD_EXISTS");
    final SqlParameterSource in = new MapSqlParameterSource()
        .addValue("P_TABLE", table)
        .addValue("P_COLUMN", column)
        .addValue("P_COLUMN_VALUE", columnValue);
    final Integer result = simpleJdbcCall.executeFunction(Integer.class, in);
    LOG.info("Result: {}", result);
    return result;
  }*/

    public static Integer exists(String table, String column, Long columnValue) {
        Integer result = null;
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("P_TABLE", table)
                .addValue("P_COLUMN", column)
                .addValue("P_COLUMN_VALUE", columnValue);
        result = namedParameterJdbcTemplate.queryForObject(
                "SELECT RECORD_EXISTS (:P_TABLE, :P_COLUMN, :P_COLUMN_VALUE) FROM DUAL", namedParameters,
                Integer.class);
        return result;
    }


    public static final String callReport(String repName, JSONObject params) {
        String reportURL =
                SERVLET_START + repName + (params != null ? "&params=" + params.toString() : "");
        LOG.info("Report URL: " + reportURL);
        return reportURL;
    }

    public static <T> void constraintViolation(final T clazz) {
        final Set<ConstraintViolation<T>> constraintViolations = validator
                .validate(clazz);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            final ConstraintViolationException exception = new ConstraintViolationException(constraintViolations);
            throw new BadRequestException(exception.getMessage());
        }
    }
}
