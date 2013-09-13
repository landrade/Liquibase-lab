package br.com.landrade.tests.utils;

import static junit.framework.Assert.fail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseStructUtils {
	
	public static void assertExistTable(Connection con, String tableName) throws SQLException {
		final ResultCollector rc = new ResultCollector();

		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet tables = dbmd.getTables(null, null, tableName.toUpperCase(), null);
		while (tables.next()) {
			if (tables.getString(3).toUpperCase().equals(tableName.toUpperCase())) {
				rc.found = true;
			}
		}

		if (!rc.found) {
			fail("Table not found in schema : " + tableName);
		}
	}
	static class ResultCollector {
		public boolean found = false;
	}

}
