package ro.agitman.moe.dao;


import org.apache.commons.beanutils.BeanUtils;
import ro.agitman.moe.dao.annotations.Entity;
import ro.agitman.moe.dao.annotations.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edi on 7/24/16.http://www.codeproject.com/Tips/372152/Mapping-JDBC-ResultSet-to-Object-using-Annotations
 */
public class PojoMapper<T> {

    private Class<T> clazz;

    public PojoMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public List<T> mapRersultSetToObject(ResultSet rs, Class<T> clazz) {
        this.clazz = clazz;
        return mapRersultSetToObject(rs);
    }

    @SuppressWarnings("unchecked")
    public List<T> mapRersultSetToObject(ResultSet rs) {
        List<T> outputList = null;
        try {
            // make sure resultset is not null
            if (rs != null) {
                // check if outputClass has 'Entity' annotation
                if (clazz.isAnnotationPresent(Entity.class)) {
                    // get the resultset metadata
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // get all the attributes of outputClass
                    Field[] fields = clazz.getDeclaredFields();
                    while (rs.next()) {
                        T bean = (T) clazz.newInstance();
                        for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                            // getting the SQL column name
                            String columnName = rsmd.getColumnName(_iterator + 1);
                            // reading the value of the SQL column
                            Object columnValue = rs.getObject(_iterator + 1);
                            // iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : fields) {
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field.getAnnotation(Column.class);
                                    if ((column.name().equalsIgnoreCase(columnName) || field.getName().equalsIgnoreCase(columnName)) && columnValue != null) {
                                        BeanUtils.setProperty(bean, field.getName(), columnValue);
                                        break;
                                    }
                                }
                            }
                        }
                        if (outputList == null) {
                            outputList = new ArrayList<T>();
                        }
                        outputList.add(bean);
                    }

                } else {
                    // throw some error
                }
            } else {
                return null;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return outputList;
    }
}
