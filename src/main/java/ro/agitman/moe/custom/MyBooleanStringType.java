package ro.agitman.moe.custom;

import org.mentabean.DBType;
import org.mentabean.type.BooleanStringType;
import org.mentabean.type.SizedType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by edi on 7/14/2016.
 */
public class MyBooleanStringType extends BooleanStringType implements DBType<Boolean>, SizedType {

    protected Boolean getBooleanValue(final String s) {
        if ("T".equalsIgnoreCase(s)) {
            return Boolean.TRUE;
        }
        if ("F".equalsIgnoreCase(s)) {
            return Boolean.FALSE;
        }
        return null;
    }

    @Override
    public void bindToStmt(final PreparedStatement stmt, final int index, final Boolean value) throws SQLException {
        stmt.setBoolean(index, value != null ? value : Boolean.FALSE);
    }
}
